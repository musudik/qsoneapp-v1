package au.com.qsone.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

import au.com.qsone.entity.Property;
import au.com.qsone.mapper.PropertyMapper;
import au.com.qsone.web.dto.ClientPropertyDto;

@Controller
public class EnquiryController {
	
	private static final String client_product_info_template="/client/client-enquiry-form.html";
	
	@Autowired
	private au.com.qsone.service.EnquiryService enquiryService;
	 
	@GetMapping("/enquiry")
    public String enquiryProperty(ClientPropertyDto clientPropertyDto, Model model){
        model.addAttribute("clientPropertyDto", clientPropertyDto);
        return client_product_info_template;
    }
	
	@GetMapping("/findAddress")
	@CrossOrigin(origins = "*")
    public String findAddress(@RequestBody ClientPropertyDto clientPropertyDto, Model model){
		
		final String uri = "https://australianaddresses.net.au/d?q="+clientPropertyDto.getFindAddress();

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);

        model.addAttribute("clientPropertyDto", clientPropertyDto);
        return client_product_info_template;
    }
	
	@GetMapping("/findAddress1")
	@CrossOrigin(origins = "*")
	public String txToClose(HttpServletRequest request, HttpServletResponse response,@RequestBody ObjectNode json) { 
		ClientPropertyDto result = new ClientPropertyDto(); 
	    String txToClose= json.get("txToClose").asText(); 
	    
	    return txToClose;
	} 
	
	@PostMapping("/saveEnquiry")
    public String saveEnquiry(@Valid @ModelAttribute("property") ClientPropertyDto clientPropertyDto, BindingResult result, Model model){
        model.addAttribute("clientPropertyDto", clientPropertyDto);

        if(result.hasErrors()){
            return client_product_info_template;
        }
        
        Property property = PropertyMapper.INSTANCE.dtoToEntity(clientPropertyDto);
        property.setCreatedBy(getCurrentUser());
        property.setCreatedDate(new Date());
        Long jobId = enquiryService.save(property);
        
        model.addAttribute("jobId", jobId);
        return "redirect:/enquiry?job="+jobId;
    }

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}
