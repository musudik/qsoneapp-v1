package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

    private Integer jobNumber;
    private String propertyAddress;
    private String propertyType;
    private Integer propertyCode;
    private String region;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate yearOfConstruction;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfContractExchange;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate firstLeaseDate;
    private String reportCalculationYear;
    private BigDecimal purchasePrice;
    private BigDecimal construtionPrice;
    private BigDecimal floorArea;
    private int usefulLifeInYears;
    private int remainingLifeYears;
	/**
	 * @return the jobNumber
	 */
	public Integer getJobNumber() {
		return jobNumber;
	}
	/**
	 * @param jobNumber the jobNumber to set
	 */
	public void setJobNumber(Integer jobNumber) {
		this.jobNumber = jobNumber;
	}
	/**
	 * @return the propertyAddress
	 */
	public String getPropertyAddress() {
		return propertyAddress;
	}
	/**
	 * @param propertyAddress the propertyAddress to set
	 */
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
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
	 * @return the propertyCode
	 */
	public Integer getPropertyCode() {
		return propertyCode;
	}
	/**
	 * @param propertyCode the propertyCode to set
	 */
	public void setPropertyCode(Integer propertyCode) {
		this.propertyCode = propertyCode;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the yearOfConstruction
	 */
	public LocalDate getYearOfConstruction() {
		return yearOfConstruction;
	}
	/**
	 * @param yearOfConstruction the yearOfConstruction to set
	 */
	public void setYearOfConstruction(LocalDate yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}
	/**
	 * @return the dateOfContractExchange
	 */
	public LocalDate getDateOfContractExchange() {
		return dateOfContractExchange;
	}
	/**
	 * @param dateOfContractExchange the dateOfContractExchange to set
	 */
	public void setDateOfContractExchange(LocalDate dateOfContractExchange) {
		this.dateOfContractExchange = dateOfContractExchange;
	}
	/**
	 * @return the firstLeaseDate
	 */
	public LocalDate getFirstLeaseDate() {
		return firstLeaseDate;
	}
	/**
	 * @param firstLeaseDate the firstLeaseDate to set
	 */
	public void setFirstLeaseDate(LocalDate firstLeaseDate) {
		this.firstLeaseDate = firstLeaseDate;
	}
	/**
	 * @return the reportCalculationYear
	 */
	public String getReportCalculationYear() {
		return reportCalculationYear;
	}
	/**
	 * @param reportCalculationYear the reportCalculationYear to set
	 */
	public void setReportCalculationYear(String reportCalculationYear) {
		this.reportCalculationYear = reportCalculationYear;
	}
	/**
	 * @return the purchasePrice
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * @return the construtionPrice
	 */
	public BigDecimal getConstrutionPrice() {
		return construtionPrice;
	}
	/**
	 * @param construtionPrice the construtionPrice to set
	 */
	public void setConstrutionPrice(BigDecimal construtionPrice) {
		this.construtionPrice = construtionPrice;
	}
	/**
	 * @return the floorArea
	 */
	public BigDecimal getFloorArea() {
		return floorArea;
	}
	/**
	 * @param floorArea the floorArea to set
	 */
	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * @return the usefulLifeInYears
	 */
	public int getUsefulLifeInYears() {
		return usefulLifeInYears;
	}
	/**
	 * @param usefulLifeInYears the usefulLifeInYears to set
	 */
	public void setUsefulLifeInYears(int usefulLifeInYears) {
		this.usefulLifeInYears = usefulLifeInYears;
	}
	/**
	 * @return the remainingLifeYears
	 */
	public int getRemainingLifeYears() {
		return remainingLifeYears;
	}
	/**
	 * @param remainingLifeYears the remainingLifeYears to set
	 */
	public void setRemainingLifeYears(int remainingLifeYears) {
		this.remainingLifeYears = remainingLifeYears;
	}
}
