package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

    private String assetName;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate purchanseDate;
    
	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}
	/**
	 * @param assetName the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the purchanseDate
	 */
	public LocalDate getPurchanseDate() {
		return purchanseDate;
	}
	/**
	 * @param purchanseDate the purchanseDate to set
	 */
	public void setPurchanseDate(LocalDate purchanseDate) {
		this.purchanseDate = purchanseDate;
	}

}
