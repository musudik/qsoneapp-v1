package au.com.qsone.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.qsone.entity.BuildingGroup;
import au.com.qsone.entity.BuildingSubGroup;
import au.com.qsone.entity.BuildingType;
import au.com.qsone.repository.IBuildingGroupRepository;
import au.com.qsone.repository.IBuildingSubGroupRepository;
import au.com.qsone.repository.IBuildingTypeRepository;

@Service
@Transactional
public class BuildingService {

	@Autowired
    private IBuildingGroupRepository repoBuildingGroupRepo;
	
	@Autowired
    private IBuildingSubGroupRepository repoBuildingSubGroupRepo;
	
	@Autowired
    private IBuildingTypeRepository repoBuildingTypeRepo;

	//BUILDING GROUP:
    public List<BuildingGroup> listAllBuildingGroups() {
        return repoBuildingGroupRepo.findAll();
    }

    public Page<BuildingGroup> findPaginatedBuildingGroups(String search, Pageable pageable) {
        List<BuildingGroup> buildingGroups = repoBuildingGroupRepo.findAll(); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BuildingGroup> list;

        if (buildingGroups.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, buildingGroups.size());
            list = buildingGroups.subList(startItem, toIndex);
        }

        Page<BuildingGroup> buildingGroupPage = new PageImpl<BuildingGroup>(list, PageRequest.of(currentPage, pageSize), buildingGroups.size());

        return buildingGroupPage;
    }

    public void saveBuildingGroup(BuildingGroup buildingGroup) {
    	repoBuildingGroupRepo.save(buildingGroup);
    }

    public BuildingGroup getBuildingGroup(long id) {
        return repoBuildingGroupRepo.findById(id).get();
    }

    public void deleteBuildingGroup(long id) {
    	repoBuildingGroupRepo.deleteById(id);
    }
    
    
    //BUILDING SUB GROUP:
    public List<BuildingSubGroup> listAllBuildingSubGroups() {
        return repoBuildingSubGroupRepo.findAll();
    }

    public Page<BuildingSubGroup> findPaginatedBuildingSubGroups(String search, Pageable pageable) {
        List<BuildingSubGroup> buildingSubGroups = repoBuildingSubGroupRepo.findAll(); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BuildingSubGroup> list;

        if (buildingSubGroups.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, buildingSubGroups.size());
            list = buildingSubGroups.subList(startItem, toIndex);
        }

        Page<BuildingSubGroup> buildingSubGroupPage = new PageImpl<BuildingSubGroup>(list, PageRequest.of(currentPage, pageSize), buildingSubGroups.size());

        return buildingSubGroupPage;
    }


    public void saveBuildingSubGroup(BuildingSubGroup buildingSubGroup) {
    	repoBuildingSubGroupRepo.save(buildingSubGroup);
    }

    public BuildingSubGroup getBuildingSubGroup(long id) {
        return repoBuildingSubGroupRepo.findById(id).get();
    }

    public void deleteBuildingSubGroup(long id) {
    	repoBuildingSubGroupRepo.deleteById(id);
    }
    
    
    //BUILDING TYPE
    public List<BuildingType> listAllBuildingType() {
        return repoBuildingTypeRepo.findAll();
    }

    public Page<BuildingType> findPaginatedBuildingType(String search, Pageable pageable) {
        List<BuildingType> buildingTypes = repoBuildingTypeRepo.findAll(); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BuildingType> list;

        if (buildingTypes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, buildingTypes.size());
            list = buildingTypes.subList(startItem, toIndex);
        }

        Page<BuildingType> buildingTypePage = new PageImpl<BuildingType>(list, PageRequest.of(currentPage, pageSize), buildingTypes.size());

        return buildingTypePage;
    }
    
    public void saveBuildingType(BuildingType buildingType) {
    	repoBuildingTypeRepo.save(buildingType);
    }

    public BuildingType getBuildingType(long id) {
        return repoBuildingTypeRepo.findById(id).get();
    }

    public void deleteBuildingType(long id) {
    	repoBuildingTypeRepo.deleteById(id);
    }
}