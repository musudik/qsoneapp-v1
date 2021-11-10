package au.com.qsone.controller;

import java.util.Date;
import java.util.List;

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

import au.com.qsone.entity.Property;

@Controller
@RequestMapping("/property")
public class PropertyController {
	
	private static final String add_edit_template="admin/property/add-edit-property";
    private static final String list_template="admin/property/list-property";
    private static final String list_redirect="redirect:/property/list";

    @Autowired
    private au.com.qsone.service.PropertyService propertyService;
    
    @GetMapping("/add")
    public String addProperty(Property property, Model model){
        model.addAttribute("property", property);
        List<Property> properties = propertyService.listAll();
        model.addAttribute("properties", properties);

        return add_edit_template;
    }
    
    

    @GetMapping("/edit/{id}")
    public String editProperty(@PathVariable("id") long id, Model model){
        Property property = propertyService.get(id);
        model.addAttribute("property", property);

        return add_edit_template;
    }
    
    
    @PostMapping("/save")
    public String saveProperty(@Valid @ModelAttribute("property") Property property, BindingResult result, Model model){
        model.addAttribute("property", property);

        if(result.hasErrors()){
            return add_edit_template;
        }
        
        if (property.getId() == 0) {
	        property.setCreatedBy(getCurrentUser());
	        property.setCreatedDate(new Date());
        } else {
        	property.setUpdatedBy(getCurrentUser());
	        property.setUpdatedDate(new Date());
        }
        propertyService.save(property);
        return list_redirect+"?success";
    }

	@GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") long id, Model model) {
        propertyService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listProperty(Model model) {
        List<Property> listProperties = propertyService.listAll();
        model.addAttribute("listProperties", listProperties);

        return list_template;
    }
    
    private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}