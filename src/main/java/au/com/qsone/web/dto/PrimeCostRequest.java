package au.com.qsone.web.dto;

import java.io.Serializable;

public class PrimeCostRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    private Property property;
    private Asset asset;
    private Integer daysHeld;
    private Integer assetEffectiveLife;
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
	 * @return the daysHeld
	 */
	public Integer getDaysHeld() {
		return daysHeld;
	}
	/**
	 * @param daysHeld the daysHeld to set
	 */
	public void setDaysHeld(Integer daysHeld) {
		this.daysHeld = daysHeld;
	}
	/**
	 * @return the assetEffectiveLife
	 */
	public Integer getAssetEffectiveLife() {
		return assetEffectiveLife;
	}
	/**
	 * @param assetEffectiveLife the assetEffectiveLife to set
	 */
	public void setAssetEffectiveLife(Integer assetEffectiveLife) {
		this.assetEffectiveLife = assetEffectiveLife;
	}
}
