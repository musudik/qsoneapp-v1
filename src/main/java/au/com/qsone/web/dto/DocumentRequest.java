package au.com.qsone.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import au.com.qsone.entity.Invoice;
import au.com.qsone.entity.Job;


public class DocumentRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    private String template;
    private String createdBy;
    private String title;
    private String fileName;
    private Property property;
    private Invoice invoice;
    private Job job;
    private Asset asset;
    private Object body;
    private BigDecimal lowValuePool;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate firstTaxableEndDate;
    private BigDecimal openingValueCost;
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
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
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
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
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
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
	/**
	 * @return the openingValueCost
	 */
	public BigDecimal getOpeningValueCost() {
		return openingValueCost;
	}
	/**
	 * @param openingValueCost the openingValueCost to set
	 */
	public void setOpeningValueCost(BigDecimal openingValueCost) {
		this.openingValueCost = openingValueCost;
	}
}
