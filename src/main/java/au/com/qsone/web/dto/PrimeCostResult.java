package au.com.qsone.web.dto;

import java.io.Serializable;
import java.util.List;

public class PrimeCostResult implements Serializable {
	private static final long serialVersionUID = 1L;

    private String method;
    private List<PrimeCost> primeCosts;
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the primeCosts
	 */
	public List<PrimeCost> getPrimeCosts() {
		return primeCosts;
	}
	/**
	 * @param primeCosts the primeCosts to set
	 */
	public void setPrimeCosts(List<PrimeCost> primeCosts) {
		this.primeCosts = primeCosts;
	}

}
