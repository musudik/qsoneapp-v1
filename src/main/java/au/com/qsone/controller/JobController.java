package au.com.qsone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import au.com.qsone.entity.Job;
import au.com.qsone.entity.Property;
import au.com.qsone.service.DepreciationService;
import au.com.qsone.service.EmailService;
import au.com.qsone.util.MediaTypeUtils;
import au.com.qsone.web.dto.DepreciationRequest;
import au.com.qsone.web.dto.JobState;

@Controller
@RequestMapping("/job")
public class JobController {
	
	private final static Logger logger = LoggerFactory.getLogger(JobController.class);
	
	private static final Map<String, JobState> jobStates;
	private static final String DEFAULT_FILE_NAME = "DepreciationSummary.pdf";
	
	@Autowired
    public EmailService emailService;
	
	@Autowired
    private DepreciationService depreciationService;
	
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
    private au.com.qsone.service.JobService JobService;
    
    @Autowired
    private au.com.qsone.service.PropertyService propertyService;

    private String add_edit_template="admin/job/add-edit-job";
    private String list_template="admin/job/list-job";
    private String list_redirect="redirect:/job/list";
	
	static {
        jobStates = new HashMap<String, JobState>();
        Yaml yaml = new Yaml(new Constructor(JobState.class));
		try {
			File file = ResourceUtils.getFile("classpath:state.yml");
			InputStream inputStream = new FileInputStream(file);
	        for (Object object : yaml.loadAll(inputStream)) {
	        	JobState js = (JobState) object;
	        	jobStates.put(js.getState(), js);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    
    
    @GetMapping("/add")
    public String addJob(Job job, Model model){
        model.addAttribute("job", job);
        
        getPropertyList(model);

        return add_edit_template;
    }

	private void getPropertyList(Model model) {
		List<Property> properties = propertyService.listAll();
        model.addAttribute("properties", properties.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getPropertyMap())));
	}

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable("id") long id, Model model){
        Job job = JobService.get(id);
        job.setNextActions(getNextActions(job.getStatus()));
        model.addAttribute("job", job);
        getPropertyList(model);
        //String createdDate = job.getCreatedDate().toString();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");  
        String createdDate = formatter.format(job.getCreatedDate());  
        model.addAttribute("createdDate", createdDate);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult result, Model model){
    	
        model.addAttribute("job", job);

        if(result.hasErrors()){
            return add_edit_template;
        }
        
        if (job.getId() == null) {
        	job.setCreatedBy(getCurrentUser());
        	job.setCreatedDate(new Date());
        } else {
        	job.setUpdatedBy(getCurrentUser());
        	job.setUpdatedDate(new Date());
        }
        JobService.save(job);
        return list_redirect+"?success";
    }

	@GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") long id, Model model) {
        JobService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listJob(Model model) {
        List<Job> listJobs = JobService.listAll();
        listJobs.forEach(j -> j.setNextActions(getNextActions(j.getStatus())));
        model.addAttribute("listJobs", listJobs);

        return list_template;
    }
    
    @GetMapping("/nextAction/{id}/{action}")
    public String nextAction(@PathVariable("id") long id, @PathVariable("action") String action, Model model) throws MessagingException, IOException {
    	Job job = JobService.get(id);
    	
    	switch(action){    
	    	case "QUOTE_SEND":    
	    	    sendQuote(job);    
	    		break;  
	    	case "ENQUIRY_REJECT":    
	    		rejectEnquiry(job);   
	    		break;
	    	case "QUOTE_ACCEPT":    
	    		quoteAccepted(job);   
	    		break;
	    	case "QUOTE_REJECT":    
	    		rejectQuote(job);   
	    		break;
	    	case "ENQUIRY_CLOSED":    
	    		closeEnquiry(job);   
	    		break;
	    	case "INVOICE_SEND":    
	    		sendInvoice(job);   
	    		break;
	    	case "PROPERTY_ACCESS":    
	    		accessProperty(job);   
	    		break;
	    	case "JOB_CREATE":    
	    		createJob(job);   
	    		break;
	    	case "JOB_INSPECT":    
	    		inspectJob(job);   
	    		break;
	    	case "JOB_DATA_CAPTURE":    
	    		dataCapture(job);   
	    		break;
	    	case "JOB_DATA_REVIEW":    
	    		dataCaptureDataReview(job);   
	    		break;
	    	case "JOB_DATA_AUTHORIZE":    
	    		dataCaptureDataAuthorize(job);   
	    		break;
	    	case "DEPRICIATION_SCHEDULE_CREATE":    
	    		depriciationSchedule(job);   
	    		break;
	    	case "DEPRICIATION_SCHEDULE_ANALYSE":    
	    		depriciationScheduleAnalyse(job);   
	    		break;
	    	case "PAYMENT_RECEIVED":    
	    		paymentReceived(job);   
	    		break;
	    	case "REPORT_CREATE":    
	    		createReport(job);   
	    		break;
	    	case "REPORT_SENT":    
	    		reportSent(job);  
	    		break;
    	}
    	
    	job.setStatus(action);
      	job.setUpdatedBy(getCurrentUser());
       	job.setUpdatedDate(new Date());
       	
        JobService.save(job);
        return list_redirect+"?success";
    }
    
    
	private void reportSent(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void createReport(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void paymentReceived(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void depriciationScheduleAnalyse(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void depriciationSchedule(Job job) {
		Property property = propertyService.get(Long.parseLong(job.getPropertyId()));
		DepreciationRequest depreciationRequest = new DepreciationRequest();
		File file = null;
        try {
            depreciationService.calclulatePrimeCost(depreciationRequest);

            MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, DEFAULT_FILE_NAME);

            file = new File(DEFAULT_FILE_NAME);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
	}

	private void dataCaptureDataAuthorize(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void dataCaptureDataReview(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void sendQuote(Job job) throws MessagingException, IOException {
		Property property = propertyService.get(Long.parseLong(job.getPropertyId()));
		Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("owner", property.getOwner());
        //templateModel.put("address", property.getFindAddress());
        templateModel.put("jobId", job.getId());
        templateModel.put("qsonelogo", "/dist/img/qsone.png");
		emailService.sendMessageUsingThymeleafTemplate(property.getEmail(), "QSONE Enquiry Quote for #"+job.getId(), templateModel, "template-quote");
	}
    
    private void rejectEnquiry(Job job) {
    	
	}
    
    private void quoteAccepted(Job job) {
    	
	}
    
    private void rejectQuote(Job job) {
    	
	}
    
    private void closeEnquiry(Job job) {
    	
	}
    
    private void dataCapture(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void inspectJob(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void createJob(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void accessProperty(Job job) {
		// TODO Auto-generated method stub
		
	}

	private void sendInvoice(Job job) throws MessagingException, IOException {
		Property property = propertyService.get(Long.parseLong(job.getPropertyId()));
		Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("owner", property.getOwner());
        //templateModel.put("address", property.getFindAddress());
        templateModel.put("jobId", job.getId());
        templateModel.put("qsonelogo", "/dist/img/qsone.png");
		emailService.sendAttachmentMessageUsingThymeleaf(property.getEmail(), "QSONE: Invoice for #"+job.getId(), templateModel, "template-invoice", "/samples/MODULE_DIAGRAM.pdf");
	}


	private List<JobState> getNextActions(String status) {
    	return jobStates.get(status) != null ? jobStates.get(status).getNextStates() : new ArrayList<JobState>();
	}

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}