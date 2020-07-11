package be.panidel.frontLayer.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;

public class CashSaleJsonModel implements JsonModelInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private BigDecimal cashSalePaymentTotal;
	private BigDecimal cashSaleNbArticles;

	private Collection<ItemPaymentJsonModel> itemPayments;
	private Collection<ItemProductJsonModel> itemProducts;

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();

		description.append("id[" + id + "]; ");
		description.append("cashSaleStatus[" + cashSaleStatus + "]; ");
		description.append("openDate[" + openDate + "]; ");
		description.append("endDate[" + endDate + "]; ");
		description.append("identifier[" + identifier + "]; ");
		description.append("source[" + source + "]; ");
		description.append("consumptionPlace[" + consumptionPlace + "]; ");
		description.append("cashSaleTotal[" + cashSaleTotal + "]; ");
		description.append("cashSaleExcludVAT[" + cashSaleExcludVAT + "]; ");
		description.append("cashSaleDeducedExcludVAT[" + cashSaleDeducedExcludVAT + "]; ");
		description.append("cashSaleFree[" + cashSaleFree + "]; ");
		description.append("cashSaleLost[" + cashSaleLost + "]; ");
		description.append("cashSaleTrash[" + cashSaleTrash + "]; ");
		description.append("cashSalePaymentTotal[" + cashSalePaymentTotal + "]; ");
		description.append("cashSaleNbArticles[" + cashSaleNbArticles + "]; ");

		if (itemProducts != null) {
			description.append("itemProducts[" + itemProducts.size() + "]; ");
			description.append("itemProducts[");
			for (ItemProductJsonModel itemProduct : itemProducts) {
				description.append("itemProduct[" + itemProduct + "]; ");
			}
			description.append("]; ");
		} else {
			description.append("itemProducts[NULL]; ");
		}

		if (itemPayments != null) {
			description.append("itemPayments[" + itemPayments.size() + "]; ");
			description.append("itemPayments[");
			for (ItemPaymentJsonModel itemPayment : itemPayments) {
				description.append("itemPayment[" + itemPayment + "]; ");
			}
			description.append("]; ");
		} else {
			description.append("itemPayments[NULL]; ");
		}

		return description.toString();
	}

	public CashSaleJsonModel() {
	}

	public CashSaleJsonModel(Long id, CashSaleStatus cashSaleStatus, Date openDate, Date endDate, String identifier,
			String source, ConsumptionPlace consumptionPlace, BigDecimal cashSaleTotal, BigDecimal cashSaleExcludVAT,
			BigDecimal cashSaleDeducedExcludVAT, BigDecimal cashSaleFree, BigDecimal cashSaleLost,
			BigDecimal cashSaleTrash, BigDecimal cashSalePaymentTotal, BigDecimal cashSaleNbArticles,
			Collection<ItemPaymentJsonModel> itemPayments, Collection<ItemProductJsonModel> itemProducts) {
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
		this.cashSalePaymentTotal = cashSalePaymentTotal;
		this.cashSaleNbArticles = cashSaleNbArticles;
		this.itemPayments = itemPayments;
		this.itemProducts = itemProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CashSaleStatus getCashSaleStatus() {
		return cashSaleStatus;
	}

	public void setCashSaleStatus(CashSaleStatus cashSaleStatus) {
		this.cashSaleStatus = cashSaleStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ConsumptionPlace getConsumptionPlace() {
		return consumptionPlace;
	}

	public void setConsumptionPlace(ConsumptionPlace consumptionPlace) {
		this.consumptionPlace = consumptionPlace;
	}

	public BigDecimal getCashSaleTotal() {
		return cashSaleTotal;
	}

	public void setCashSaleTotal(BigDecimal cashSaleTotal) {
		this.cashSaleTotal = cashSaleTotal;
	}

	public BigDecimal getCashSaleExcludVAT() {
		return cashSaleExcludVAT;
	}

	public void setCashSaleExcludVAT(BigDecimal cashSaleExcludVAT) {
		this.cashSaleExcludVAT = cashSaleExcludVAT;
	}

	public BigDecimal getCashSaleDeducedExcludVAT() {
		return cashSaleDeducedExcludVAT;
	}

	public void setCashSaleDeducedExcludVAT(BigDecimal cashSaleDeducedExcludVAT) {
		this.cashSaleDeducedExcludVAT = cashSaleDeducedExcludVAT;
	}

	public BigDecimal getCashSaleFree() {
		return cashSaleFree;
	}

	public void setCashSaleFree(BigDecimal cashSaleFree) {
		this.cashSaleFree = cashSaleFree;
	}

	public BigDecimal getCashSaleLost() {
		return cashSaleLost;
	}

	public void setCashSaleLost(BigDecimal cashSaleLost) {
		this.cashSaleLost = cashSaleLost;
	}

	public BigDecimal getCashSaleTrash() {
		return cashSaleTrash;
	}

	public void setCashSaleTrash(BigDecimal cashSaleTrash) {
		this.cashSaleTrash = cashSaleTrash;
	}

	public BigDecimal getCashSalePaymentTotal() {
		return cashSalePaymentTotal;
	}

	public void setCashSalePaymentTotal(BigDecimal cashSalePaymentTotal) {
		this.cashSalePaymentTotal = cashSalePaymentTotal;
	}

	public BigDecimal getCashSaleNbArticles() {
		return cashSaleNbArticles;
	}

	public void setCashSaleNbArticles(BigDecimal cashSaleNbArticles) {
		this.cashSaleNbArticles = cashSaleNbArticles;
	}

	public Collection<ItemPaymentJsonModel> getItemPayments() {
		return itemPayments;
	}

	public void setItemPayments(Collection<ItemPaymentJsonModel> itemPayments) {
		this.itemPayments = itemPayments;
	}

	public Collection<ItemProductJsonModel> getItemProducts() {
		return itemProducts;
	}

	public void setItemProducts(Collection<ItemProductJsonModel> itemProducts) {
		this.itemProducts = itemProducts;
	}

}
