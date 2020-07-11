package be.panidel.dataLayer.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;
import be.panidel.dataLayer.helper.ModelHelper;

@Entity
@Table(name = "cashsale")
@NamedQueries({ @NamedQuery(name = DataModelInterface.POS_CASHSALE_ALL, query = "SELECT e FROM CashSaleModel e"),
		@NamedQuery(name = DataModelInterface.POS_CASHSALE_COUNT, query = "SELECT count(e) FROM CashSaleModel e"),
		@NamedQuery(name = DataModelInterface.POS_CASHSALE_BYPERIOD, query = "SELECT e FROM CashSaleModel e WHERE e.endDate BETWEEN :startDate AND :endDate"),
		@NamedQuery(name = DataModelInterface.POS_CASHSALE_BYPERIOD_COUNT, query = "SELECT count(e) FROM CashSaleModel e WHERE e.endDate BETWEEN :startDate AND :endDate") })

public class CashSaleModel implements DataModelInterface<CashSaleModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cashsale_ID")
	private Long id;

	private CashSaleStatus cashSaleStatus;
	private Date openDate;
	private Date endDate;
	private String identifier;
	private String source;
	private ConsumptionPlace consumptionPlace;
	private BigDecimal cashSaleTotal;
	private BigDecimal cashSaleExcludVAT;
	private BigDecimal cashSaleDeducedExcludVAT;
	private BigDecimal cashSaleFree;
	private BigDecimal cashSaleLost;
	private BigDecimal cashSaleTrash;
	private BigDecimal cashSaleCancelled;
	private BigDecimal cashSalePaymentTotal;
	private BigDecimal cashSaleNbArticles;

	@OneToMany
	private Collection<ItemPaymentModel> itemPayments;

	@OneToMany
	private Collection<ItemProductModel> itemProducts;

	public CashSaleModel() {
		super();
	}

	public CashSaleModel(Long id, CashSaleStatus cashSaleStatus, Date openDate, Date endDate, String identifier,
			String source, ConsumptionPlace consumptionPlace, BigDecimal cashSaleTotal, BigDecimal cashSaleExcludVAT,
			BigDecimal cashSaleDeducedExcludVAT, BigDecimal cashSaleFree, BigDecimal cashSaleLost,
			BigDecimal cashSaleTrash, BigDecimal cashSaleCancelled, BigDecimal cashSalePaymentTotal,
			BigDecimal cashSaleNbArticles, Collection<ItemPaymentModel> itemPayments,
			Collection<ItemProductModel> itemProducts) {
		super();
		this.id = id;
		this.cashSaleStatus = cashSaleStatus;
		this.openDate = openDate;
		this.endDate = endDate;
		this.identifier = identifier;
		this.source = source;
		this.consumptionPlace = consumptionPlace;
		this.cashSaleTotal = cashSaleTotal;
		this.cashSaleExcludVAT = cashSaleExcludVAT;
		this.cashSaleDeducedExcludVAT = cashSaleDeducedExcludVAT;
		this.cashSaleFree = cashSaleFree;
		this.cashSaleLost = cashSaleLost;
		this.cashSaleTrash = cashSaleTrash;
		this.cashSaleCancelled = cashSaleCancelled;
		this.cashSalePaymentTotal = cashSalePaymentTotal;
		this.cashSaleNbArticles = cashSaleNbArticles;
		this.itemPayments = itemPayments;
		this.itemProducts = itemProducts;
	}

	@Override
	public String toString() {
		return "CashSaleModel [id=" + id + ", cashSaleStatus=" + cashSaleStatus + ", openDate=" + openDate
				+ ", endDate=" + endDate + ", identifier=" + identifier + ", source=" + source + ", consumptionPlace="
				+ consumptionPlace + ", cashSaleTotal=" + cashSaleTotal + ", cashSaleExcludVAT=" + cashSaleExcludVAT
				+ ", cashSaleDeducedExcludVAT=" + cashSaleDeducedExcludVAT + ", cashSaleFree=" + cashSaleFree
				+ ", cashSaleLost=" + cashSaleLost + ", cashSaleTrash=" + cashSaleTrash + ", cashSaleCancelled="
				+ cashSaleCancelled + ", cashSalePaymentTotal=" + cashSalePaymentTotal + ", cashSaleNbArticles="
				+ cashSaleNbArticles + ", itemPayments=" + itemPayments + ", itemProducts=" + itemProducts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cashSaleCancelled == null) ? 0 : cashSaleCancelled.hashCode());
		result = prime * result + ((cashSaleDeducedExcludVAT == null) ? 0 : cashSaleDeducedExcludVAT.hashCode());
		result = prime * result + ((cashSaleExcludVAT == null) ? 0 : cashSaleExcludVAT.hashCode());
		result = prime * result + ((cashSaleFree == null) ? 0 : cashSaleFree.hashCode());
		result = prime * result + ((cashSaleLost == null) ? 0 : cashSaleLost.hashCode());
		result = prime * result + ((cashSaleNbArticles == null) ? 0 : cashSaleNbArticles.hashCode());
		result = prime * result + ((cashSalePaymentTotal == null) ? 0 : cashSalePaymentTotal.hashCode());
		result = prime * result + ((cashSaleStatus == null) ? 0 : cashSaleStatus.hashCode());
		result = prime * result + ((cashSaleTotal == null) ? 0 : cashSaleTotal.hashCode());
		result = prime * result + ((cashSaleTrash == null) ? 0 : cashSaleTrash.hashCode());
		result = prime * result + ((consumptionPlace == null) ? 0 : consumptionPlace.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((itemPayments == null) ? 0 : itemPayments.hashCode());
		result = prime * result + ((itemProducts == null) ? 0 : itemProducts.hashCode());
		result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashSaleModel other = (CashSaleModel) obj;

		if (!ModelHelper.checkEquals(cashSaleCancelled, other.cashSaleCancelled))
			return false;

		if (!ModelHelper.checkEquals(cashSaleDeducedExcludVAT, other.cashSaleDeducedExcludVAT))
			return false;

		if (!ModelHelper.checkEquals(cashSaleExcludVAT, other.cashSaleExcludVAT))
			return false;

		if (!ModelHelper.checkEquals(cashSaleFree, other.cashSaleFree))
			return false;

		if (!ModelHelper.checkEquals(cashSaleLost, other.cashSaleLost))
			return false;

		if (!ModelHelper.checkEquals(cashSaleNbArticles, other.cashSaleNbArticles))
			return false;

		if (!ModelHelper.checkEquals(cashSalePaymentTotal, other.cashSalePaymentTotal))
			return false;

		if (!ModelHelper.checkEquals(cashSaleStatus, other.cashSaleStatus))
			return false;

		if (!ModelHelper.checkEquals(cashSaleTotal, other.cashSaleTotal))
			return false;

		if (!ModelHelper.checkEquals(cashSaleTrash, other.cashSaleTrash))
			return false;

		if (!ModelHelper.checkEquals(consumptionPlace, other.consumptionPlace))
			return false;

		if (!ModelHelper.checkEquals(endDate, other.endDate))
			return false;

		if (!ModelHelper.checkEquals(id, other.id))
			return false;

		if (!ModelHelper.checkEquals(identifier, other.identifier))
			return false;

		if (!ModelHelper.checkEquals(itemPayments, other.itemPayments))
			return false;

		if (!ModelHelper.checkEquals(itemProducts, other.itemProducts))
			return false;

		if (!ModelHelper.checkEquals(openDate, other.openDate))
			return false;

		if (!ModelHelper.checkEquals(source, other.source))
			return false;

		return true;
	}

	public Long getId() {
		return id;
	}

	public CashSaleStatus getCashSaleStatus() {
		return cashSaleStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getSource() {
		return source;
	}

	public ConsumptionPlace getConsumptionPlace() {
		return consumptionPlace;
	}

	public BigDecimal getCashSaleTotal() {
		return cashSaleTotal;
	}

	public BigDecimal getCashSaleExcludVAT() {
		return cashSaleExcludVAT;
	}

	public BigDecimal getCashSaleDeducedExcludVAT() {
		return cashSaleDeducedExcludVAT;
	}

	public BigDecimal getCashSaleFree() {
		return cashSaleFree;
	}

	public BigDecimal getCashSaleLost() {
		return cashSaleLost;
	}

	public BigDecimal getCashSaleTrash() {
		return cashSaleTrash;
	}

	public BigDecimal getCashSaleCancelled() {
		return cashSaleCancelled;
	}

	public BigDecimal getCashSalePaymentTotal() {
		return cashSalePaymentTotal;
	}

	public BigDecimal getCashSaleNbArticles() {
		return cashSaleNbArticles;
	}

	public Collection<ItemPaymentModel> getItemPayments() {
		return itemPayments;
	}

	public Collection<ItemProductModel> getItemProducts() {
		return itemProducts;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCashSaleStatus(CashSaleStatus cashSaleStatus) {
		this.cashSaleStatus = cashSaleStatus;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setConsumptionPlace(ConsumptionPlace consumptionPlace) {
		this.consumptionPlace = consumptionPlace;
	}

	public void setCashSaleTotal(BigDecimal cashSaleTotal) {
		this.cashSaleTotal = cashSaleTotal;
	}

	public void setCashSaleExcludVAT(BigDecimal cashSaleExcludVAT) {
		this.cashSaleExcludVAT = cashSaleExcludVAT;
	}

	public void setCashSaleDeducedExcludVAT(BigDecimal cashSaleDeducedExcludVAT) {
		this.cashSaleDeducedExcludVAT = cashSaleDeducedExcludVAT;
	}

	public void setCashSaleFree(BigDecimal cashSaleFree) {
		this.cashSaleFree = cashSaleFree;
	}

	public void setCashSaleLost(BigDecimal cashSaleLost) {
		this.cashSaleLost = cashSaleLost;
	}

	public void setCashSaleTrash(BigDecimal cashSaleTrash) {
		this.cashSaleTrash = cashSaleTrash;
	}

	public void setCashSaleCancelled(BigDecimal cashSaleCancelled) {
		this.cashSaleCancelled = cashSaleCancelled;
	}

	public void setCashSalePaymentTotal(BigDecimal cashSalePaymentTotal) {
		this.cashSalePaymentTotal = cashSalePaymentTotal;
	}

	public void setCashSaleNbArticles(BigDecimal cashSaleNbArticles) {
		this.cashSaleNbArticles = cashSaleNbArticles;
	}

	public void setItemPayments(Collection<ItemPaymentModel> itemPayments) {
		this.itemPayments = itemPayments;
	}

	public void setItemProducts(Collection<ItemProductModel> itemProducts) {
		this.itemProducts = itemProducts;
	}

}
