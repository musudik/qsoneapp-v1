package au.com.qsone.web.dto;

import java.io.Serializable;
import java.util.List;

import au.com.qsone.entity.BuildingGroup;
import au.com.qsone.entity.BuildingSubGroup;
import au.com.qsone.entity.BuildingType;

public class BuildingTypeConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Long buildingGroup;
    private Long buildingSubGroup;
    private Long buildingType;
    private List<BuildingGroup> buildingGroups;
    private List<BuildingSubGroup> buildingSubGroups;
    private List<BuildingType> buildingTypes;
	/**
	 * @return the buildingGroup
	 */
	public Long getBuildingGroup() {
		return buildingGroup;
	}
	/**
	 * @param buildingGroup the buildingGroup to set
	 */
	public void setBuildingGroup(Long buildingGroup) {
		this.buildingGroup = buildingGroup;
	}
	/**
	 * @return the buildingSubGroup
	 */
	public Long getBuildingSubGroup() {
		return buildingSubGroup;
	}
	/**
	 * @param buildingSubGroup the buildingSubGroup to set
	 */
	public void setBuildingSubGroup(Long buildingSubGroup) {
		this.buildingSubGroup = buildingSubGroup;
	}
	/**
	 * @return the buildingType
	 */
	public Long getBuildingType() {
		return buildingType;
	}
	/**
	 * @param buildingType the buildingType to set
	 */
	public void setBuildingType(Long buildingType) {
		this.buildingType = buildingType;
	}
	/**
	 * @return the buildingGroups
	 */
	public List<BuildingGroup> getBuildingGroups() {
		return buildingGroups;
	}
	/**
	 * @param buildingGroups the buildingGroups to set
	 */
	public void setBuildingGroups(List<BuildingGroup> buildingGroups) {
		this.buildingGroups = buildingGroups;
	}
	/**
	 * @return the buildingSubGroups
	 */
	public List<BuildingSubGroup> getBuildingSubGroups() {
		return buildingSubGroups;
	}
	/**
	 * @param buildingSubGroups the buildingSubGroups to set
	 */
	public void setBuildingSubGroups(List<BuildingSubGroup> buildingSubGroups) {
		this.buildingSubGroups = buildingSubGroups;
	}
	/**
	 * @return the buildingTypes
	 */
	public List<BuildingType> getBuildingTypes() {
		return buildingTypes;
	}
	/**
	 * @param buildingTypes the buildingTypes to set
	 */
	public void setBuildingTypes(List<BuildingType> buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
    
	
}
