package au.com.qsone.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "INVOICE", uniqueConstraints={@UniqueConstraint(columnNames = {"job_id", "invoice_type"})})
@SequenceGenerator(name="seq_invoice", initialValue=1000, allocationSize=1)
public class Invoice extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");  
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_invoice")
	@Column(name = "invoice_id")
	Long invoiceId;
	
	@Column(name = "job_id", nullable = false)
    private String jobId;
	
	@Column(name = "invoice_type", nullable = false)
    private String invoiceType;
	
	@Column(name = "invoice_title", nullable = false)
    private String invoiceTitle;
	
	@Column(name = "contact_type", nullable = false)
    private String contactTypeName;
	
	@Column(name = "contact_type_value", nullable = false)
    private String contactTypeValue;
	
	@Column(name = "invoice_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date invoiceDate;
	
	@Column(name = "expiry_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	
	@Column(name = "reference", nullable = false)
    private String reference;
	
	@Column(name = "title", nullable = false)
    private String title;
	
	@Column(name = "summary", nullable = false)
    private String summary;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
	
	@Column(name = "sub_total", nullable = true)
    private BigDecimal subTotal;
	
	@Column(name = "tax_code", nullable = true)
    private String taxCode;
	
	@Column(name = "tax_total", nullable = true)
    private BigDecimal taxTotal;
	
	@Column(name = "amount_due", nullable = false)
    private BigDecimal amountDue;
	
	@Column(name = "terms", nullable = true)
    private String terms;

	@Transient
	private String invoiceDateFormated;
	
	@Transient
	private String expiryDateFormated;
	/**
	 * @return the invoiceId
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the invoiceTitle
	 */
	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	/**
	 * @param invoiceTitle the invoiceTitle to set
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	/**
	 * @return the contactTypeName
	 */
	public String getContactTypeName() {
		return contactTypeName;
	}

	/**
	 * @param contactTypeName the contactTypeName to set
	 */
	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}

	/**
	 * @return the contactTypeValue
	 */
	public String getContactTypeValue() {
		return contactTypeValue;
	}

	/**
	 * @param contactTypeValue the contactTypeValue to set
	 */
	public void setContactTypeValue(String contactTypeValue) {
		this.contactTypeValue = contactTypeValue;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
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
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}


	/**
	 * @return the invoiceItems
	 */
	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	/**
	 * @param invoiceItems the invoiceItems to set
	 */
	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return the taxTotal
	 */
	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	/**
	 * @param taxTotal the taxTotal to set
	 */
	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	/**
	 * @return the amountDue
	 */
	public BigDecimal getAmountDue() {
		return amountDue;
	}

	/**
	 * @param amountDue the amountDue to set
	 */
	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	/**
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getInvoiceDateFormated() {
		return DATE_FORMAT.format(invoiceDate);
	}

	public void setInvoiceDateFormated(String invoiceDateFormated) {
		this.invoiceDateFormated = invoiceDateFormated;
	}

	public String getExpiryDateFormated() {
		return DATE_FORMAT.format(expiryDate);
	}

	public void setExpiryDateFormated(String expiryDateFormated) {
		this.expiryDateFormated = expiryDateFormated;
	}
	
	
}
