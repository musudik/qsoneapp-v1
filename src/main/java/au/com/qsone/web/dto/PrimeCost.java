package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrimeCost implements Serializable {
	private static final long serialVersionUID = 1L;

    private BigDecimal amoutOfClaims;
    private Integer claimYear;

    @Override
    public String toString() {
        return amoutOfClaims + ", " + claimYear;
    }

	/**
	 * @return the amoutOfClaims
	 */
	public BigDecimal getAmoutOfClaims() {
		return amoutOfClaims;
	}

	/**
	 * @param amoutOfClaims the amoutOfClaims to set
	 */
	public void setAmoutOfClaims(BigDecimal amoutOfClaims) {
		this.amoutOfClaims = amoutOfClaims;
	}

	/**
	 * @return the claimYear
	 */
	public Integer getClaimYear() {
		return claimYear;
	}

	/**
	 * @param claimYear the claimYear to set
	 */
	public void setClaimYear(Integer claimYear) {
		this.claimYear = claimYear;
	}
}
