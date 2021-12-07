package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class DepreciationSummary implements Serializable {
	private static final long serialVersionUID = 1L;

    private Property property;
    private List<DepreciationResult> results;
    private BigDecimal lowValuePool;
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
	 * @return the results
	 */
	public List<DepreciationResult> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<DepreciationResult> results) {
		this.results = results;
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
