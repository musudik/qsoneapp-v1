package au.com.qsone.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "INVOICE_ITEM", uniqueConstraints={@UniqueConstraint(columnNames = {"invoice_id", "item_id"})})
@SequenceGenerator(name="seq_invoice_item", initialValue=1000, allocationSize=1)
public class InvoiceItem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_invoice_item")
	@Column(name = "invoice_itemId", nullable = false)
	Long invoiceItemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Invoice invoice;
	
	@NotEmpty(message = "Item code can't be empty!")
	@Column(name = "item_id", nullable = false)
    private String itemId;
	
	@NotEmpty(message = "quantity can't be empty!")
	@Column(name = "quantity", nullable = false)
    private Long quantity;
	
	@NotEmpty(message = "Tax unit name can't be empty!")
	@Column(name = "tax_unit_name", nullable = false)
    private String taxUnitName;
	
	@NotEmpty(message = "amount can't be empty!")
	@Column(name = "amount", nullable = false)
    private BigDecimal amount;
	
	@Transient
    private List<Item> items;

	/**
	 * @return the invoiceItemId
	 */
	public Long getInvoiceItemId() {
		return invoiceItemId;
	}

	/**
	 * @param invoiceItemId the invoiceItemId to set
	 */
	public void setInvoiceItemId(Long invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the taxUnitName
	 */
	public String getTaxUnitName() {
		return taxUnitName;
	}

	/**
	 * @param taxUnitName the taxUnitName to set
	 */
	public void setTaxUnitName(String taxUnitName) {
		this.taxUnitName = taxUnitName;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
