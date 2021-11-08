package au.com.qsone.web.dto;

import java.io.Serializable;

public class AddressFinderObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String highlighted;
	private String address;
	private String location;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the highlighted
	 */
	public String getHighlighted() {
		return highlighted;
	}
	/**
	 * @param highlighted the highlighted to set
	 */
	public void setHighlighted(String highlighted) {
		this.highlighted = highlighted;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
