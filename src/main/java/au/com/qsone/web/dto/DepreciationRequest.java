package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


public class DepreciationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    private Property property;
    private BigDecimal openingValueCost;
    private BigDecimal lowValuePool;
    private String constructionYear;
    private int estimatedNoOfYears;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate firstTaxableEndDate;
	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}
	/**
	 * @return the openingValueCost
	 */
	public BigDecimal getOpeningValueCost() {
		return openingValueCost;
	}
	/**
	 * @param openingValueCost the openingValueCost to set
	 */
	public void setOpeningValueCost(BigDecimal openingValueCost) {
		this.openingValueCost = openingValueCost;
	}
	/**
	 * @return the lowValuePool
	 */
	public BigDecimal getLowValuePool() {
		return lowValuePool;
	}
	/**
	 * @param lowValuePool the lowValuePool to set
	 */
	public void setLowValuePool(BigDecimal lowValuePool) {
		this.lowValuePool = lowValuePool;
	}
	/**
	 * @return the constructionYear
	 */
	public String getConstructionYear() {
		return constructionYear;
	}
	/**
	 * @param constructionYear the constructionYear to set
	 */
	public void setConstructionYear(String constructionYear) {
		this.constructionYear = constructionYear;
	}
	/**
	 * @return the estimatedNoOfYears
	 */
	public int getEstimatedNoOfYears() {
		return estimatedNoOfYears;
	}
	/**
	 * @param estimatedNoOfYears the estimatedNoOfYears to set
	 */
	public void setEstimatedNoOfYears(int estimatedNoOfYears) {
		this.estimatedNoOfYears = estimatedNoOfYears;
	}
	/**
	 * @return the firstTaxableEndDate
	 */
	public LocalDate getFirstTaxableEndDate() {
		return firstTaxableEndDate;
	}
	/**
	 * @param firstTaxableEndDate the firstTaxableEndDate to set
	 */
	public void setFirstTaxableEndDate(LocalDate firstTaxableEndDate) {
		this.firstTaxableEndDate = firstTaxableEndDate;
	}
}
