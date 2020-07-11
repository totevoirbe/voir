package be.panidel.businessLayer.model;

import java.math.BigDecimal;
import java.util.Map;

import be.panidel.businessLayer.ReportPeriod;

public class CashSaleReportModel {

	private String id;
	private BigDecimal cashSaleTotal;
	private BigDecimal cashSaleExcludVAT;
	private BigDecimal cashSaleDeducedExcludVAT;
	private BigDecimal cashSaleFree;
	private BigDecimal cashSaleLost;
	private BigDecimal cashSaleTrash;
	private BigDecimal cashSaleCancelled;
	private BigDecimal cashSalePaymentTotal;
	private BigDecimal cashSaleNbArticles;
	private ReportPeriod reportPeriod;

	Map<ItemKey, ItemProductReportModel> itemReportProducts;
	Map<ItemKey, ItemPaymentReportModel> itemReportPayments;

	Map<ItemKey, BigDecimal> vatSplit;

	public CashSaleReportModel(String id, BigDecimal cashSaleTotal, BigDecimal cashSaleExcludVAT,
			BigDecimal cashSaleDeducedExcludVAT, BigDecimal cashSaleFree, BigDecimal cashSaleLost,
			BigDecimal cashSaleTrash, BigDecimal cashSaleCancelled, BigDecimal cashSalePaymentTotal,
			BigDecimal cashSaleNbArticles, ReportPeriod reportPeriod,
			Map<ItemKey, ItemProductReportModel> itemReportProducts,
			Map<ItemKey, ItemPaymentReportModel> itemReportPayments, Map<ItemKey, BigDecimal> vatSplit) {
		super();
		this.id = id;
		this.cashSaleTotal = cashSaleTotal;
		this.cashSaleExcludVAT = cashSaleExcludVAT;
		this.cashSaleDeducedExcludVAT = cashSaleDeducedExcludVAT;
		this.cashSaleFree = cashSaleFree;
		this.cashSaleLost = cashSaleLost;
		this.cashSaleTrash = cashSaleTrash;
		this.cashSaleCancelled = cashSaleCancelled;
		this.cashSalePaymentTotal = cashSalePaymentTotal;
		this.cashSaleNbArticles = cashSaleNbArticles;
		this.reportPeriod = reportPeriod;
		this.itemReportProducts = itemReportProducts;
		this.itemReportPayments = itemReportPayments;
		this.vatSplit = vatSplit;
	}

	@Override
	public String toString() {
		return "CashSaleReportModel [id=" + id + ", cashSaleTotal=" + cashSaleTotal + ", cashSaleExcludVAT="
				+ cashSaleExcludVAT + ", cashSaleDeducedExcludVAT=" + cashSaleDeducedExcludVAT + ", cashSaleFree="
				+ cashSaleFree + ", cashSaleLost=" + cashSaleLost + ", cashSaleTrash=" + cashSaleTrash
				+ ", cashSaleCancelled=" + cashSaleCancelled + ", cashSalePaymentTotal=" + cashSalePaymentTotal
				+ ", cashSaleNbArticles=" + cashSaleNbArticles + ", reportPeriod=" + reportPeriod
				+ ", itemReportProducts=" + itemReportProducts + ", itemReportPayments=" + itemReportPayments
				+ ", vatSplit=" + vatSplit + "]";
	}

	public String getId() {
		return id;
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

	public ReportPeriod getReportPeriod() {
		return reportPeriod;
	}

	public Map<ItemKey, ItemProductReportModel> getItemReportProducts() {
		return itemReportProducts;
	}

	public Map<ItemKey, ItemPaymentReportModel> getItemReportPayments() {
		return itemReportPayments;
	}

	public Map<ItemKey, BigDecimal> getVatSplit() {
		return vatSplit;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setReportPeriod(ReportPeriod reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	public void setItemReportProducts(Map<ItemKey, ItemProductReportModel> itemReportProducts) {
		this.itemReportProducts = itemReportProducts;
	}

	public void setItemReportPayments(Map<ItemKey, ItemPaymentReportModel> itemReportPayments) {
		this.itemReportPayments = itemReportPayments;
	}

	public void setVatSplit(Map<ItemKey, BigDecimal> vatSplit) {
		this.vatSplit = vatSplit;
	}

}
