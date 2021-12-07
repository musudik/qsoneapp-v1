package au.com.qsone.web.dto;

import java.io.Serializable;
import java.util.List;

public class DepreciationResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	public DepreciationResponse(Property property, List<DepreciationResult> depreciationResults) {
		this.property = property;
		this.depreciationResults = depreciationResults;
	}
	
    private Property property;
    private List<DepreciationResult> depreciationResults;
	
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
	 * @return the depreciationResults
	 */
	public List<DepreciationResult> getDepreciationResults() {
		return depreciationResults;
	}
	/**
	 * @param depreciationResults the depreciationResults to set
	 */
	public void setDepreciationResults(List<DepreciationResult> depreciationResults) {
		this.depreciationResults = depreciationResults;
	}
}
