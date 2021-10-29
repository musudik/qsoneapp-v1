package au.com.qsone.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROPERTY")
public class Property extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(name = "propertyType", nullable = false)
    private String propertyType;
	@Column(name = "owner", nullable = false)
    private String owner;
	@Column(name = "forAndBehalfOfOwner", nullable = true)
    private String forAndBehalfOfOwner;
    @Column(name = "nameOfAuthorizedPerson", nullable = true)
    private String nameOfAuthorizedPerson;
	@Column(name = "phone", nullable = true)
    private String phone;
    @Column(name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "comments", nullable = true)
    private String comments;
    
    //Address Details:
    @Column(name = "flatNumber", nullable = false)
    private String flatNumber;
    @Column(name = "streetName", nullable = false)
    private String streetName;
    @Column(name = "suburb", nullable = false)
    private String suburb;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "postcode", nullable = false)
    private String postcode;
    @Column(name = "country", nullable = false)
    private String country;
    
    //Cost Details:
    @Column(name = "purchasePrice", nullable = true)
    private String purchasePrice;
    @Column(name = "stampDuty", nullable = true)
    private String stampDuty;
    @Column(name = "legalCosts", nullable = true)
    private String legalCosts;
    @Column(name = "additionalExpenses", nullable = true)
    private String additionalExpenses;
    @Column(name = "originalBuildingCost", nullable = true)
    private String originalBuildingCost;
    
    //Property TimeLine Details:
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date constructionStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date constructionEnd;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfContractExchange;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSettlement;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstLeaseDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstTaxableEndDate;
	
	@Column(name = "jobId", nullable = true)
	private Long jobId;

	
	public String getPropertyMap() {
//		Map<Long, String> map = new HashMap<>();
//		map.put(this.getId(), this.getFlatNumber() +"-"+ this.getStreetName() +"-"+ this.getPostcode());
//		return map;
		return this.getFlatNumber() +"-"+ this.getStreetName() +"-"+ this.getPostcode();
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
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the forAndBehalfOfOwner
	 */
	public String getForAndBehalfOfOwner() {
		return forAndBehalfOfOwner;
	}
	/**
	 * @param forAndBehalfOfOwner the forAndBehalfOfOwner to set
	 */
	public void setForAndBehalfOfOwner(String forAndBehalfOfOwner) {
		this.forAndBehalfOfOwner = forAndBehalfOfOwner;
	}
	/**
	 * @return the nameOfAuthorizedPerson
	 */
	public String getNameOfAuthorizedPerson() {
		return nameOfAuthorizedPerson;
	}
	/**
	 * @param nameOfAuthorizedPerson the nameOfAuthorizedPerson to set
	 */
	public void setNameOfAuthorizedPerson(String nameOfAuthorizedPerson) {
		this.nameOfAuthorizedPerson = nameOfAuthorizedPerson;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the flatNumber
	 */
	public String getFlatNumber() {
		return flatNumber;
	}
	/**
	 * @param flatNumber the flatNumber to set
	 */
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the suburb
	 */
	public String getSuburb() {
		return suburb;
	}
	/**
	 * @param suburb the suburb to set
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the purchasePrice
	 */
	public String getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * @return the stampDuty
	 */
	public String getStampDuty() {
		return stampDuty;
	}
	/**
	 * @param stampDuty the stampDuty to set
	 */
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	/**
	 * @return the legalCosts
	 */
	public String getLegalCosts() {
		return legalCosts;
	}
	/**
	 * @param legalCosts the legalCosts to set
	 */
	public void setLegalCosts(String legalCosts) {
		this.legalCosts = legalCosts;
	}
	/**
	 * @return the additionalExpenses
	 */
	public String getAdditionalExpenses() {
		return additionalExpenses;
	}
	/**
	 * @param additionalExpenses the additionalExpenses to set
	 */
	public void setAdditionalExpenses(String additionalExpenses) {
		this.additionalExpenses = additionalExpenses;
	}
	/**
	 * @return the originalBuildingCost
	 */
	public String getOriginalBuildingCost() {
		return originalBuildingCost;
	}
	/**
	 * @param originalBuildingCost the originalBuildingCost to set
	 */
	public void setOriginalBuildingCost(String originalBuildingCost) {
		this.originalBuildingCost = originalBuildingCost;
	}
	/**
	 * @return the constructionStart
	 */
	public Date getConstructionStart() {
		return constructionStart;
	}
	/**
	 * @param constructionStart the constructionStart to set
	 */
	public void setConstructionStart(Date constructionStart) {
		this.constructionStart = constructionStart;
	}
	/**
	 * @return the constructionEnd
	 */
	public Date getConstructionEnd() {
		return constructionEnd;
	}
	/**
	 * @param constructionEnd the constructionEnd to set
	 */
	public void setConstructionEnd(Date constructionEnd) {
		this.constructionEnd = constructionEnd;
	}
	/**
	 * @return the dateOfContractExchange
	 */
	public Date getDateOfContractExchange() {
		return dateOfContractExchange;
	}
	/**
	 * @param dateOfContractExchange the dateOfContractExchange to set
	 */
	public void setDateOfContractExchange(Date dateOfContractExchange) {
		this.dateOfContractExchange = dateOfContractExchange;
	}
	/**
	 * @return the dateSettlement
	 */
	public Date getDateSettlement() {
		return dateSettlement;
	}
	/**
	 * @param dateSettlement the dateSettlement to set
	 */
	public void setDateSettlement(Date dateSettlement) {
		this.dateSettlement = dateSettlement;
	}
	/**
	 * @return the firstLeaseDate
	 */
	public Date getFirstLeaseDate() {
		return firstLeaseDate;
	}
	/**
	 * @param firstLeaseDate the firstLeaseDate to set
	 */
	public void setFirstLeaseDate(Date firstLeaseDate) {
		this.firstLeaseDate = firstLeaseDate;
	}
	/**
	 * @return the firstTaxableEndDate
	 */
	public Date getFirstTaxableEndDate() {
		return firstTaxableEndDate;
	}
	/**
	 * @param firstTaxableEndDate the firstTaxableEndDate to set
	 */
	public void setFirstTaxableEndDate(Date firstTaxableEndDate) {
		this.firstTaxableEndDate = firstTaxableEndDate;
	}
	/**
	 * @return the jobId
	 */
	public long getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	
}
