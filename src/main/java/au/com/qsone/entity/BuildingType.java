package au.com.qsone.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "BUILDING_TYPE", uniqueConstraints={@UniqueConstraint(columnNames = {"building_type", "building_sub_group_id"})})
@SequenceGenerator(name="seq_building_type", initialValue=1000, allocationSize=1)
public class BuildingType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_building_type")
	@Column(name = "building_type_id")
	Long buildingTypeId;
	
	@Column(name = "building_type", nullable = false)
    private String buildingTypeName;
	
	@Column(name = "building_type_note", nullable = true)
    private String buildingTypeNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_sub_group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BuildingSubGroup buildingSubGroup;
	
	@Transient
	private String display;

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return "["+this.getBuildingTypeId()+"] " + this.getBuildingTypeName();
	}
	

	/**
	 * @return the buildingTypeId
	 */
	public Long getBuildingTypeId() {
		return buildingTypeId;
	}

	/**
	 * @param buildingTypeId the buildingTypeId to set
	 */
	public void setBuildingTypeId(Long buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	/**
	 * @return the buildingTypeName
	 */
	public String getBuildingTypeName() {
		return buildingTypeName;
	}

	/**
	 * @param buildingTypeName the buildingTypeName to set
	 */
	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}

	/**
	 * @return the buildingTypeNote
	 */
	public String getBuildingTypeNote() {
		return buildingTypeNote;
	}

	/**
	 * @param buildingTypeNote the buildingTypeNote to set
	 */
	public void setBuildingTypeNote(String buildingTypeNote) {
		this.buildingTypeNote = buildingTypeNote;
	}

	/**
	 * @return the buildingSubGroup
	 */
	public BuildingSubGroup getBuildingSubGroup() {
		return buildingSubGroup;
	}

	/**
	 * @param buildingSubGroup the buildingSubGroup to set
	 */
	public void setBuildingSubGroup(BuildingSubGroup buildingSubGroup) {
		this.buildingSubGroup = buildingSubGroup;
	}
	
}
