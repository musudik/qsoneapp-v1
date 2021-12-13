package au.com.qsone.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ITEM", uniqueConstraints={@UniqueConstraint(columnNames = {"item_code"})})
@SequenceGenerator(name="seq_item", initialValue=1000, allocationSize=1)
public class Item extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_item")
	@Column(name = "item_id", nullable = false)
	Long itemId;
	
	@NotEmpty(message = "item code can't be empty")
	@Column(name = "item_code", nullable = false)
    private String itemCode;
	
	@NotEmpty(message = "description can't be empty")
	@Column(name = "description", nullable = false)
    private String description;
	
	@NotEmpty(message = "amount can't be empty")
	@Column(name = "amount", nullable = false)
    private BigDecimal amount;

	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
}
