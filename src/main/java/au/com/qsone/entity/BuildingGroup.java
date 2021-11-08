package au.com.qsone.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BUILDING_GROUP", uniqueConstraints={@UniqueConstraint(columnNames = {"building_group"})})
@SequenceGenerator(name="seq_building_group", initialValue=1000, allocationSize=1)
public class BuildingGroup extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_building_group")
	@Column(name = "building_group_id")
	private Long buildingGroupId;

	@Column(name = "building_group", nullable = false)
    private String buildingGroupName;
	
	@OneToMany(mappedBy = "buildingGroup", fetch = FetchType.LAZY)
    private List<BuildingSubGroup> buildingSubGroups = new ArrayList<>();
	
	@Transient
	private String display;

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return "["+this.getBuildingGroupId()+"] " + this.getBuildingGroupName();
	}

	/**
	 * @return the buildingGroupId
	 */
	public Long getBuildingGroupId() {
		return buildingGroupId;
	}

	/**
	 * @param buildingGroupId the buildingGroupId to set
	 */
	public void setBuildingGroupId(Long buildingGroupId) {
		this.buildingGroupId = buildingGroupId;
	}

	/**
	 * @return the buildingGroupName
	 */
	public String getBuildingGroupName() {
		return buildingGroupName;
	}

	/**
	 * @param buildingGroupName the buildingGroupName to set
	 */
	public void setBuildingGroupName(String buildingGroupName) {
		this.buildingGroupName = buildingGroupName;
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
	
	/*
	 * @Override public String toString() { return "["+this.getBuildingGroupId()+"]"
	 * + this.getBuildingGroupName(); }
	 */
	
}
