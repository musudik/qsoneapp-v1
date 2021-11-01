package au.com.qsone.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "JOB")
public class Job extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotEmpty(message = "Job Type can't be empty!")
	@Column(name = "jobType", nullable = false)
    private String jobType;
	
	@NotEmpty(message = "Property Status can't be empty!")
	@Column(name = "status", nullable = false)
    private String status;
	
	@Column(name = "inspected", nullable = true)
    private Boolean inspected;
	
	@NotEmpty(message = "Property can't be empty!")
	@Column(name = "propertyId", nullable = false)
    private String propertyId;
	
	@Column(name = "mainAccessContactType", nullable = true)
    private String mainAccessContactType;
	@Column(name = "mainAccessContactName", nullable = true)
    private String mainAccessContactName;
	@Column(name = "mainAccessContactPhone", nullable = true)
    private String mainAccessContactPhone;
	
	@Column(name = "assignedDate", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date assignedDate;
	
	@Column(name = "inspectionDate", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inspectionDate;
	
	@Column(name = "dueToDepreciator", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueToDepreciator;
	
	/**
	 * @return the propertyId
	 */
	public String getPropertyId() {
		return propertyId;
	}
	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the inspected
	 */
	public Boolean getInspected() {
		return inspected;
	}
	/**
	 * @param inspected the inspected to set
	 */
	public void setInspected(Boolean inspected) {
		this.inspected = inspected;
	}
	/**
	 * @return the mainAccessContactType
	 */
	public String getMainAccessContactType() {
		return mainAccessContactType;
	}
	/**
	 * @param mainAccessContactType the mainAccessContactType to set
	 */
	public void setMainAccessContactType(String mainAccessContactType) {
		this.mainAccessContactType = mainAccessContactType;
	}
	/**
	 * @return the mainAccessContactName
	 */
	public String getMainAccessContactName() {
		return mainAccessContactName;
	}
	/**
	 * @param mainAccessContactName the mainAccessContactName to set
	 */
	public void setMainAccessContactName(String mainAccessContactName) {
		this.mainAccessContactName = mainAccessContactName;
	}
	/**
	 * @return the mainAccessContactPhone
	 */
	public String getMainAccessContactPhone() {
		return mainAccessContactPhone;
	}
	/**
	 * @param mainAccessContactPhone the mainAccessContactPhone to set
	 */
	public void setMainAccessContactPhone(String mainAccessContactPhone) {
		this.mainAccessContactPhone = mainAccessContactPhone;
	}
	/**
	 * @return the assignedDate
	 */
	public Date getAssignedDate() {
		return assignedDate;
	}
	/**
	 * @param assignedDate the assignedDate to set
	 */
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	/**
	 * @return the inspectionDate
	 */
	public Date getInspectionDate() {
		return inspectionDate;
	}
	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	/**
	 * @return the dueToDepreciator
	 */
	public Date getDueToDepreciator() {
		return dueToDepreciator;
	}
	/**
	 * @param dueToDepreciator the dueToDepreciator to set
	 */
	public void setDueToDepreciator(Date dueToDepreciator) {
		this.dueToDepreciator = dueToDepreciator;
	}
}
	