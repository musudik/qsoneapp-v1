package au.com.qsone.web.dto;

import java.io.Serializable;
import java.util.List;


public class PrimeCostResponse implements Serializable {
	private static final long serialVersionUID = 1L;

    private Asset asset;
    private List<PrimeCostResult> results;
	/**
	 * @return the asset
	 */
	public Asset getAsset() {
		return asset;
	}
	/**
	 * @param asset the asset to set
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	/**
	 * @return the results
	 */
	public List<PrimeCostResult> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<PrimeCostResult> results) {
		this.results = results;
	}
}