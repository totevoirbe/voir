package be.panidel.dataLayer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import be.panidel.dataLayer.helper.ModelHelper;

@Entity
@Table(name = "itempayment")
@NamedQueries({
		@NamedQuery(name = DataModelInterface.POS_ITEMPAYMENT_ALL, query = "SELECT e FROM ItemPaymentModel e") })
public class ItemPaymentModel implements ItemModelInterface<ItemPaymentModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemPayment_generator")
	@SequenceGenerator(name = "itemPayment_generator", sequenceName = "itemPayment_seq", allocationSize = 50)
	@Column(name = "itempayment_id")
	private Long id;

	@ManyToOne
	private PaymentMethodModel paymentMethod;

	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private Boolean deleted;

	@Override
	public boolean equals(Object ItemAsObject) {

		if (ItemAsObject instanceof ItemPaymentModel) {

			ItemPaymentModel itemEval = (ItemPaymentModel) ItemAsObject;

			// if (!ModelUtil.checkEquals(this.id, itemEval.id)) {
			// return false;
			// }
			if (!ModelHelper.checkEquals(this.paymentMethod, itemEval.paymentMethod)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.quantity, itemEval.quantity)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.unitPrice, itemEval.unitPrice)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.deleted, itemEval.deleted)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer("id[" + id + "];");
		description.append("paymentMethod[" + paymentMethod + "]; quantity[" + quantity + "]; unitPrice[" + unitPrice
				+ "]; deleted[" + deleted + "]");

		return description.toString();
	}

	public ItemPaymentModel() {
	}

	public ItemPaymentModel(PaymentMethodModel paymentMethod, BigDecimal quantity, BigDecimal unitPrice,
			Boolean deleted) {
		super();
		this.paymentMethod = paymentMethod;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethodModel getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodModel paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}