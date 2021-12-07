package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DepreciationAmount implements Serializable {
	private static final long serialVersionUID = 1L;

    private BigDecimal plantAndEquipment;
    private BigDecimal captial;
    private BigDecimal total;
    
	public DepreciationAmount(BigDecimal plantAndEquipment, BigDecimal capital, BigDecimal total) {
		this.plantAndEquipment = plantAndEquipment;
		this.captial = capital;
		this.total = total;
	}
	public DepreciationAmount() {}
	
	/**
	 * @return the plantAndEquipment
	 */
	public BigDecimal getPlantAndEquipment() {
		return plantAndEquipment;
	}
	/**
	 * @param plantAndEquipment the plantAndEquipment to set
	 */
	public void setPlantAndEquipment(BigDecimal plantAndEquipment) {
		this.plantAndEquipment = plantAndEquipment;
	}
	/**
	 * @return the captial
	 */
	public BigDecimal getCaptial() {
		return captial;
	}
	/**
	 * @param captial the captial to set
	 */
	public void setCaptial(BigDecimal captial) {
		this.captial = captial;
	}
	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
