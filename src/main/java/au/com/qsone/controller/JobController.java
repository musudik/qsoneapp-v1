package au.com.qsone.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.qsone.entity.Job;
import au.com.qsone.entity.Property;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private au.com.qsone.service.JobService JobService;
    
    @Autowired
    private au.com.qsone.service.PropertyService propertyService;

    private String add_edit_template="admin/job/add-edit-job";
    private String list_template="admin/job/list-job";
    private String list_redirect="redirect:/job/list";


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
        model.addAttribute("listJobs", listJobs);

        return list_template;
    }
    
    private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}