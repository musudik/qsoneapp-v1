package au.com.qsone.web.dto;

import java.util.List;

public class JobState {
	
	private int id;
	private String state;
	private String reason;
	private String display;
	private List<JobState>  nextStates;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	/**
	 * @return the nextStates
	 */
	public List<JobState> getNextStates() {
		return nextStates;
	}
	/**
	 * @param nextStates the nextStates to set
	 */
	public void setNextStates(List<JobState> nextStates) {
		this.nextStates = nextStates;
	}

}
