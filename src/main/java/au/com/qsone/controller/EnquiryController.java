package au.com.qsone.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.qsone.entity.Property;
import au.com.qsone.mapper.PropertyMapper;
import au.com.qsone.service.EmailService;
import au.com.qsone.web.dto.AddressFinderObject;
import au.com.qsone.web.dto.ClientPropertyDto;
import au.com.qsone.web.dto.MailObject;

@Controller
public class EnquiryController {
	
	private static final Map<String, Map<String, String>> labels;
	static {
        labels = new HashMap<>();

        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);
        
    }
	private static final String client_product_info_template="client/client-enquiry-form.html";
	
	@Autowired
	private au.com.qsone.service.EnquiryService enquiryService;
	
	@Autowired
    public EmailService emailService;
	 
	@GetMapping("/enquiry")
    public String enquiryProperty(ClientPropertyDto clientPropertyDto, Model model){
        model.addAttribute("clientPropertyDto", clientPropertyDto);
        return client_product_info_template;
    }
	
	@GetMapping("/enquiry/findAddress")
	@CrossOrigin(origins = "*")
    public ResponseEntity<List> findAddress(@RequestParam(required = true) String address) 
    		throws JsonMappingException, RestClientException, JsonProcessingException {
		
		final String uri = "https://australianaddresses.net.au/d?q="+address;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<AddressFinderObject> addressList = objectMapper.readValue(restTemplate.getForObject(uri, String.class), new TypeReference<List<AddressFinderObject>>() {});
	    List<String> addresses = addressList.stream().map(r -> r.getAddress()).collect(Collectors.toList());
	    
	    return new ResponseEntity<List>(addresses, HttpStatus.OK);
    }
	
	@PostMapping("/saveEnquiry")
    public String saveEnquiry(@Valid @ModelAttribute("property") ClientPropertyDto clientPropertyDto, BindingResult result, Model model) throws MessagingException, IOException {
        model.addAttribute("clientPropertyDto", clientPropertyDto);

        if(result.hasErrors()){
            return client_product_info_template;
        }
        
        Property property = PropertyMapper.INSTANCE.dtoToEntity(clientPropertyDto);
        property.setCreatedBy(getCurrentUser());
        property.setCreatedDate(new Date());
        Long jobId = enquiryService.save(property);
        
        model.addAttribute("jobId", jobId);
        //emailService.sendSimpleMessage(clientPropertyDto.getEmail(), "Test QSONE App", "Some Text");
        
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("owner", clientPropertyDto.getOwner());
        templateModel.put("address", clientPropertyDto.getFindAddress());
        templateModel.put("jobId", jobId);
        templateModel.put("qsonelogo", "/dist/img/qsone.png");
        
        emailService.sendMessageUsingThymeleafTemplate(clientPropertyDto.getEmail(), "QSONE Property Enquiry Acknowledgement #"+jobId, templateModel);
        
        return "redirect:/enquiry?job="+jobId;
    }
	
    private Model createMail(Model model) {
		
        Map<String, String> props = labels.get("send");
        Set<String> keys = props.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            model.addAttribute(key, props.get(key));
        }

        model.addAttribute("mailObject", new MailObject());
        return model;
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String createMail(Model model,
                             @ModelAttribute("mailObject") @Valid MailObject mailObject,
                             Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());

        return "emails";
    }

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}
