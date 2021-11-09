package au.com.qsone.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "BUILDING_SUB_GROUP", uniqueConstraints={@UniqueConstraint(columnNames = {"building_sub_group", "building_group_id"})})
@SequenceGenerator(name="seq_building_sub_group", initialValue=1000, allocationSize=1)
public class BuildingSubGroup extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_building_sub_group")
	@Column(name = "building_sub_group_id")
	Long buildingSubGroupId;
	
	@Column(name = "building_sub_group", nullable = false)
    private String buildingSubGroupName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BuildingGroup buildingGroup;
	
	@OneToMany(mappedBy = "buildingSubGroup", fetch = FetchType.LAZY)
    private List<BuildingType> buildingTypes = new ArrayList<>();
	
	@Transient
	private String display;

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return "["+this.getBuildingSubGroupId()+"] " + this.getBuildingSubGroupName();
	}

	/**
	 * @return the buildingSubGroupId
	 */
	public Long getBuildingSubGroupId() {
		return buildingSubGroupId;
	}

	/**
	 * @param buildingSubGroupId the buildingSubGroupId to set
	 */
	public void setBuildingSubGroupId(Long buildingSubGroupId) {
		this.buildingSubGroupId = buildingSubGroupId;
	}

	/**
	 * @return the buildingSubGroupName
	 */
	public String getBuildingSubGroupName() {
		return buildingSubGroupName;
	}

	/**
	 * @param buildingSubGroupName the buildingSubGroupName to set
	 */
	public void setBuildingSubGroupName(String buildingSubGroupName) {
		this.buildingSubGroupName = buildingSubGroupName;
	}

	/**
	 * @return the buildingGroup
	 */
	public BuildingGroup getBuildingGroup() {
		return buildingGroup;
	}

	/**
	 * @param buildingGroup the buildingGroup to set
	 */
	public void setBuildingGroup(BuildingGroup buildingGroup) {
		this.buildingGroup = buildingGroup;
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
