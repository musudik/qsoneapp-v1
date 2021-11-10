package au.com.qsone.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import au.com.qsone.entity.BuildingGroup;
import au.com.qsone.entity.BuildingSubGroup;
import au.com.qsone.entity.BuildingType;
import au.com.qsone.web.dto.BuildingTypeConfig;

@Controller
@RequestMapping("/building")
public class BuildingController {

	private static final String add_edit_template_building_group="admin/building/add-edit-building-group";
    private static final String list_template_building_group="admin/building/list-building-group";
    private static final String list_redirect_building_group="redirect:/building/listBuilding-group";
    
    private static final String add_edit_template_building_sub_group="admin/building/add-edit-building-sub-group";
    private static final String list_template_building_sub_group="admin/building/list-building-sub-group";
    private static final String list_redirect_building_sub_group="redirect:/building/listBuilding-sub-group";
    
    
	private static final String add_edit_template_building_type="admin/building/add-edit-building-type";
    private static final String list_template_building_type="admin/building/list-building-type";
    private static final String list_redirect_building_type="redirect:/building/listBuilding-type";
    
    private static final String view_building_types="admin/building/view-building-types";
    private static final String redirect_view_building_types="redirect:/building/viewBuildingTypes";
    
    @Autowired
    private au.com.qsone.service.BuildingService buildingService;
    
    
    @GetMapping("/viewBuildingTypes")
    public String viewBuildingTypes(BuildingTypeConfig buildingTypeConfig, Model model){
        List<BuildingGroup> listBuildingGroups = buildingService.listAllBuildingGroups();
        buildingTypeConfig.setBuildingGroups(listBuildingGroups);
        model.addAttribute("buildingTypeConfig", buildingTypeConfig);
        return view_building_types;
    }
    
    @GetMapping("/viewBuildingTypes/getBuildingSubGroups/{id}")
    public ResponseEntity<List> getBuildingSubGroups(@PathVariable("id") long id){
    	BuildingGroup buildingGroup = buildingService.getBuildingGroup(id);
    	List<String> items = buildingGroup.getBuildingSubGroups().stream().map(r -> r.getDisplay()).collect(Collectors.toList());
        return new ResponseEntity<List>(items, HttpStatus.OK);
    }
    
    @GetMapping("/viewBuildingTypes/getBuildingTypes/{id}")
    public ResponseEntity<List> getBuildingTypes(@PathVariable("id") long id){
    	BuildingSubGroup buildingSubGroup = buildingService.getBuildingSubGroup(id);
    	List<String> items = buildingSubGroup.getBuildingTypes().stream().map(r -> r.getDisplay()).collect(Collectors.toList());
        return new ResponseEntity<List>(items, HttpStatus.OK);
    }
    
    @GetMapping("/addBuildingGroup")
    public String addBuildingGroup(BuildingGroup buildingGroup, Model model){
        model.addAttribute("buildingGroup", buildingGroup);
        
        return add_edit_template_building_group;
    }
    
    @GetMapping("/editBuildingGroup/{id}")
    public String editBuildingGroup(@PathVariable("id") long id, Model model){
    	BuildingGroup buildingGroup = buildingService.getBuildingGroup(id);
        model.addAttribute("buildingGroup", buildingGroup);

        return add_edit_template_building_group;
    }
    
    @PostMapping("/saveBuildingGroup")
    public String saveBuildingGroup(@Valid @ModelAttribute("buildingGroup") BuildingGroup buildingGroup, BindingResult result, Model model){
        model.addAttribute("buildingGroup", buildingGroup);

        if(result.hasErrors()){
            return add_edit_template_building_group;
        }
        
        if (buildingGroup.getBuildingGroupId() == null) {
        	buildingGroup.setCreatedBy(getCurrentUser());
        	buildingGroup.setCreatedDate(new Date());
        } else {
        	buildingGroup.setUpdatedBy(getCurrentUser());
        	buildingGroup.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingGroup(buildingGroup);
        //return list_redirect_building_group+"?success";
        return redirect_view_building_types;
    }
    
    @PostMapping("/saveStayBuildingGroup")
    public String saveStayBuildingGroup(@Valid @ModelAttribute("buildingGroup") BuildingGroup buildingGroup, BindingResult result, Model model){
        model.addAttribute("buildingGroup", buildingGroup);

        if(result.hasErrors()){
            return add_edit_template_building_group;
        }
        
        if (buildingGroup.getBuildingGroupId() == null) {
        	buildingGroup.setCreatedBy(getCurrentUser());
        	buildingGroup.setCreatedDate(new Date());
        } else {
        	buildingGroup.setUpdatedBy(getCurrentUser());
        	buildingGroup.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingGroup(buildingGroup);
        
        model.addAttribute("buildingGroup", new BuildingGroup());
        return add_edit_template_building_group;
    }
    
    @GetMapping("/listBuildingGroup")
    public String listBuildingGroup(Model model) {
        List<BuildingGroup> listBuildingGroups = buildingService.listAllBuildingGroups();
        model.addAttribute("listBuildingGroups", listBuildingGroups);

        return list_template_building_group;
    }
    
    @GetMapping("/addBuildingSubGroup/{id}")
    public String addBuildingSubGroup(BuildingSubGroup buildingSubGroup, @PathVariable("id") long id, Model model){
    	BuildingGroup buildingGroup = buildingService.getBuildingGroup(id);
    	buildingSubGroup.setBuildingGroup(buildingGroup);
        model.addAttribute("buildingSubGroup", buildingSubGroup);
        
        return add_edit_template_building_sub_group;
    }
    
    @GetMapping("/editBuildingSubGroup/{id}")
    public String editBuildingSubGroup(@PathVariable("id") long id, Model model){
    	BuildingSubGroup buildingSubGroup = buildingService.getBuildingSubGroup(id);
        model.addAttribute("buildingSubGroup", buildingSubGroup);

        return add_edit_template_building_sub_group;
    }
    
    @PostMapping("/saveBuildingSubGroup")
    public String saveBuildingSubGroup(@ModelAttribute("buildingSubGroup") BuildingSubGroup buildingSubGroup, BindingResult result, Model model){
        model.addAttribute("buildingSubGroup", buildingSubGroup);

        if(result.hasErrors()){
            return add_edit_template_building_sub_group;
        }
        
        if (buildingSubGroup.getBuildingSubGroupId() == null) {
        	buildingSubGroup.setCreatedBy(getCurrentUser());
        	buildingSubGroup.setCreatedDate(new Date());
        } else {
        	buildingSubGroup.setUpdatedBy(getCurrentUser());
        	buildingSubGroup.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingSubGroup(buildingSubGroup);
        //return list_redirect_building_sub_group+"?success";
        
        return redirect_view_building_types;
    }
    
    @PostMapping("/saveStayBuildingSubGroup")
    public String saveStayBuildingSubGroup(@Valid @ModelAttribute("buildingSubGroup") BuildingSubGroup buildingSubGroup, BindingResult result, Model model){
        model.addAttribute("buildingSubGroup", buildingSubGroup);

        if(result.hasErrors()){
            return add_edit_template_building_sub_group;
        }
        
        if (buildingSubGroup.getBuildingSubGroupId() == null) {
        	buildingSubGroup.setCreatedBy(getCurrentUser());
        	buildingSubGroup.setCreatedDate(new Date());
        } else {
        	buildingSubGroup.setUpdatedBy(getCurrentUser());
        	buildingSubGroup.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingSubGroup(buildingSubGroup);
        
        model.addAttribute("buildingSubGroup", new BuildingSubGroup());
        return add_edit_template_building_sub_group;
    }
    
    @GetMapping("/listBuildingSubGroup")
    public String listBuildingSubGroup(Model model) {
        List<BuildingSubGroup> listBuildingSubGroups = buildingService.listAllBuildingSubGroups();
        model.addAttribute("listBuildingSubGroups", listBuildingSubGroups);

        return list_template_building_sub_group;
    }

    
    @GetMapping("/addBuildingType/{id}")
    public String addBuildingType(BuildingType buildingType, @PathVariable("id") long id, Model model){
    	BuildingSubGroup buildingSubGroup = buildingService.getBuildingSubGroup(id);
    	buildingType.setBuildingSubGroup(buildingSubGroup);
        model.addAttribute("buildingType", buildingType);
        
        return add_edit_template_building_type;
    }
    
    @GetMapping("/editBuildingType/{id}")
    public String editBuildingType(@PathVariable("id") long id, Model model){
    	BuildingType buildingType = buildingService.getBuildingType(id);
        model.addAttribute("buildingType", buildingType);

        return add_edit_template_building_type;
    }
    
    @PostMapping("/saveBuildingType")
    public String saveBuildingType(@Valid @ModelAttribute("buildingType") BuildingType buildingType, BindingResult result, Model model){
        model.addAttribute("buildingType", buildingType);

        if(result.hasErrors()){
            return add_edit_template_building_type;
        }
        
        if (buildingType.getBuildingTypeId() == null) {
        	buildingType.setCreatedBy(getCurrentUser());
        	buildingType.setCreatedDate(new Date());
        } else {
        	buildingType.setUpdatedBy(getCurrentUser());
        	buildingType.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingType(buildingType);
        //return list_redirect_building_type+"?success";
        return redirect_view_building_types;
    }
    
    @PostMapping("/saveStayBuildingType")
    public String saveStayBuildingType(@Valid @ModelAttribute("buildingType") BuildingType buildingType, BindingResult result, Model model){
        model.addAttribute("buildingType", buildingType);

        if(result.hasErrors()){
            return add_edit_template_building_type;
        }
        
        if (buildingType.getBuildingTypeId() == null) {
        	buildingType.setCreatedBy(getCurrentUser());
        	buildingType.setCreatedDate(new Date());
        } else {
        	buildingType.setUpdatedBy(getCurrentUser());
        	buildingType.setUpdatedDate(new Date());
        }
        buildingService.saveBuildingType(buildingType);
        
        model.addAttribute("buildingType", new BuildingType());
        return add_edit_template_building_type;
    }
    
    @GetMapping("/listBuildingType")
    public String listBuildingType(Model model) {
        List<BuildingType> listBuildingTypes = buildingService.listAllBuildingType();
        model.addAttribute("listBuildingTypes", listBuildingTypes);

        return list_template_building_type;
    }

    
    private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}
}
