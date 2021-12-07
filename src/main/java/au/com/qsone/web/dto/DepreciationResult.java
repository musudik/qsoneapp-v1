package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DepreciationResult implements Serializable {
	private static final long serialVersionUID = 1L;

    private int year;
    private String yearCount;
    private String fromDate;
    private String toDate;
    private BigDecimal buildingCost;
    private BigDecimal depreciationAmount;
    private DepreciationAmount diminishingMethod;
    private DepreciationAmount primeCostMethod;

    @Override
    public String toString() {
        return year + ", " + buildingCost + ", " + depreciationAmount;
    }

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the yearCount
	 */
	public String getYearCount() {
		return yearCount;
	}

	/**
	 * @param yearCount the yearCount to set
	 */
	public void setYearCount(String yearCount) {
		this.yearCount = yearCount;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the buildingCost
	 */
	public BigDecimal getBuildingCost() {
		return buildingCost;
	}

	/**
	 * @param buildingCost the buildingCost to set
	 */
	public void setBuildingCost(BigDecimal buildingCost) {
		this.buildingCost = buildingCost;
	}

	/**
	 * @return the depreciationAmount
	 */
	public BigDecimal getDepreciationAmount() {
		return depreciationAmount;
	}

	/**
	 * @param depreciationAmount the depreciationAmount to set
	 */
	public void setDepreciationAmount(BigDecimal depreciationAmount) {
		this.depreciationAmount = depreciationAmount;
	}

	/**
	 * @return the diminishingMethod
	 */
	public DepreciationAmount getDiminishingMethod() {
		return diminishingMethod;
	}

	/**
	 * @param diminishingMethod the diminishingMethod to set
	 */
	public void setDiminishingMethod(DepreciationAmount diminishingMethod) {
		this.diminishingMethod = diminishingMethod;
	}

	/**
	 * @return the primeCostMethod
	 */
	public DepreciationAmount getPrimeCostMethod() {
		return primeCostMethod;
	}

	/**
	 * @param primeCostMethod the primeCostMethod to set
	 */
	public void setPrimeCostMethod(DepreciationAmount primeCostMethod) {
		this.primeCostMethod = primeCostMethod;
	}
}
