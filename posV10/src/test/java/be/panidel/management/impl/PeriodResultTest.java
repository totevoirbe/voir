package be.panidel.management.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.OperationUnitList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.PeriodValue;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.ComputeHelper;
import be.panidel.tools.ReportHelper;
import be.panidel.tools.Tools;

public class PeriodResultTest {

	private static String startDate = "20110317";
	private static String endDate = "20110317";

	@Test
	public void testGetDayResultList() {

		Date from = null;
		Date to = null;
		BigDecimal globalDelta = BigDecimal.ZERO;

		try {
			from = Tools.startOfYear(POSConstants.SDF_DAY.parse(startDate));
			to = Tools.endOfYear(POSConstants.SDF_DAY.parse(endDate));
			// from = Tools.startOfDay(POSConstants.SDF_DAY.parse(startDate),
			// 0);
			// to = Tools.endOfDay(POSConstants.SDF_DAY.parse(endDate));

		} catch (ParseException e) {
			e.printStackTrace();
			fail("SAXException");
		} catch (ParameterException e) {
			e.printStackTrace();
			fail("SAXException");
		}

		try {

			System.out.println("Start testGetDayResultList");

			PeriodBean periodBean = new PeriodBean(from, to);

			Map<StringReverseOrder, PeriodResult> byDaySales = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.getDayResultListForStat(
							periodBean,
							POSParameters.instance()
									.getResultsStorageCaissesGroupbyday());

			CollectDataBean globalCollectDataBean = new CollectDataBean(
					periodBean, byDaySales.size());

			for (StringReverseOrder dateAsString : byDaySales.keySet()) {

				CollectDataBean collectDataBean = new CollectDataBean(
						byDaySales.get(dateAsString).getPeriod(), byDaySales
								.get(dateAsString).getSalesQuantity());

				PeriodResult dayResult = byDaySales.get(dateAsString);

				collectDataBean.addSalesByPeriod(dayResult.getValuesByPeriod());

				collectDataBean.addPaymentsList(dayResult.getPaymentsByType());

				collectDataBean.addItemList(dayResult.getProductSales());

				collectDataBean.addRejectedDivTotal(dayResult
						.getOperationUnitList());

				collectDataBean.addFidelityAndRejectedRest(dayResult
						.getGlobalFidelityAndRejectedRest());

				globalCollectDataBean.add(collectDataBean);

				BigDecimal delta = collectDataBean.isOnError();

				if (delta.compareTo(BigDecimal.ZERO) != 0) {
					CollectDataBeanHelper.display(collectDataBean);
					System.out.println("ERROR = " + delta.toPlainString());
					globalDelta = globalDelta.add(delta);
				}

			}
			System.out.println("Global" + "-----------------------------");

			CollectDataBeanHelper.display(globalCollectDataBean);

			System.out.println("ERROR = " + globalDelta.toPlainString());

			System.out.println("End testGetDayResultList");
		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (ParameterException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}

class CollectDataBean {

	private Map<String, BigDecimal> fidelityAndRejectedRest = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> paymentsList = new HashMap<String, BigDecimal>();
	private Map<String, PeriodValue> salesByPeriod = new HashMap<String, PeriodValue>();
	private Map<String, BigDecimal> itemList = new HashMap<String, BigDecimal>();
	private List<String> warning = new ArrayList<String>();

	private BigDecimal totalArticlesTVAC = BigDecimal.ZERO;
	private BigDecimal totalArticlesQTE = BigDecimal.ZERO;
	private BigDecimal totalArticlesByPeriod = BigDecimal.ZERO;

	BigDecimal totalPay = BigDecimal.ZERO;
	BigDecimal totalPaySales = BigDecimal.ZERO;
	BigDecimal totalPayNotSales = BigDecimal.ZERO;
	BigDecimal totalPayCA = BigDecimal.ZERO;
	BigDecimal totalPayNotCA = BigDecimal.ZERO;
	BigDecimal totalPayRejected = BigDecimal.ZERO;
	BigDecimal totalPayFidelity = BigDecimal.ZERO;

	String payDetail;

	private PeriodBean periodBean;
	private int byDaySales;

	public CollectDataBean(PeriodBean periodBean, int byDaySales) {
		this.periodBean = periodBean;
		this.byDaySales = byDaySales;
	}

	public void add(CollectDataBean collectDataBean) {
		addFidelityAndRejectedRest(collectDataBean.getFidelityAndRejectedRest());
		addPaymentsList(collectDataBean.getPaymentsList());
		addSalesByPeriod(collectDataBean.getSalesByPeriod());
		CollectDataBeanHelper
				.addValues(collectDataBean.getItemList(), itemList);

		warning.addAll(collectDataBean.getWarning());

		addTotalArticlesTVAC(collectDataBean.getTotalArticlesTVAC());
		addTotalArticlesQTE(collectDataBean.getTotalArticlesQTE());

	}

	public void addFidelityAndRejectedRest(
			Map<String, BigDecimal> addedFidelityAndRejectedRest) {
		CollectDataBeanHelper.addValues(addedFidelityAndRejectedRest,
				fidelityAndRejectedRest);
	}

	public void addPayementModesValue(String key, BigDecimal value,
			Map<String, BigDecimal> payementModesValue) {
		CollectDataBeanHelper.addValues(key, value, payementModesValue);
	}

	public void addTotalArticlesTVAC(BigDecimal value) {
		totalArticlesTVAC = totalArticlesTVAC.add(value);
	}

	public void addTotalArticlesQTE(BigDecimal value) {
		totalArticlesQTE = totalArticlesQTE.add(value);
	}

	public void addTotalArticlesByPeriod(BigDecimal value) {
		totalArticlesByPeriod = totalArticlesByPeriod.add(value);
	}

	public void addSalesByPeriod(Map<String, PeriodValue> salesByPeriodToAdd) {
		CollectDataBeanHelper.addSalesByPeriod(this, salesByPeriodToAdd);
	}

	public Map<String, BigDecimal> getFidelityAndRejectedRest() {
		return fidelityAndRejectedRest;
	}

	public Map<String, PeriodValue> getSalesByPeriod() {
		return salesByPeriod;
	}

	public BigDecimal getTotalArticlesTVAC() {
		return totalArticlesTVAC;
	}

	public BigDecimal getTotalArticlesQTE() {
		return totalArticlesQTE;
	}

	public BigDecimal getTotalArticlesByPeriod() {
		return totalArticlesByPeriod;
	}

	public PeriodBean getPeriodBean() {
		return periodBean;
	}

	public int getByDaySales() {
		return byDaySales;
	}

	public void addPaymentsList(Map<String, BigDecimal> addedPaymentsList) {
		CollectDataBeanHelper.addValues(addedPaymentsList, paymentsList);
	}

	public void addItemList(Map<String, Item> itemList) {
		CollectDataBeanHelper.addItemList(this, itemList);
	}

	public Map<String, BigDecimal> getItemList() {
		return itemList;
	}

	public void addRejectedDivTotal(OperationUnitList operationUnitList) {
		CollectDataBeanHelper.addRejectedDivTotal(warning, operationUnitList);
	}

	public Map<String, BigDecimal> getPaymentsList() {
		return paymentsList;
	}

	public List<String> getWarning() {
		return warning;
	}

	public boolean hasMessage() {
		return warning.size() > 0 || fidelityAndRejectedRest.size() > 0;

	}

	public BigDecimal isOnError() throws ParameterException, DAOException {

		CollectDataBeanHelper.computePayData(this);

		return CollectDataBeanHelper.isOnError(this);
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public BigDecimal getTotalPaySales() {
		return totalPaySales;
	}

	public BigDecimal getTotalPayNotSales() {
		return totalPayNotSales;
	}

	public BigDecimal getTotalPayCA() {
		return totalPayCA;
	}

	public BigDecimal getTotalPayNotCA() {
		return totalPayNotCA;
	}

	public BigDecimal getTotalPayRejected() {
		return totalPayRejected;
	}

	public BigDecimal getTotalPayFidelity() {
		return totalPayFidelity;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public void setTotalPaySales(BigDecimal totalPaySales) {
		this.totalPaySales = totalPaySales;
	}

	public void setTotalPayNotSales(BigDecimal totalPayNotSales) {
		this.totalPayNotSales = totalPayNotSales;
	}

	public void setTotalPayCA(BigDecimal totalPayCA) {
		this.totalPayCA = totalPayCA;
	}

	public void setTotalPayNotCA(BigDecimal totalPayNotCA) {
		this.totalPayNotCA = totalPayNotCA;
	}

	public void setTotalPayRejected(BigDecimal totalPayRejected) {
		this.totalPayRejected = totalPayRejected;
	}

	public void setTotalPayFidelity(BigDecimal totalPayFidelity) {
		this.totalPayFidelity = totalPayFidelity;
	}

	public String getPayDetail() {
		return payDetail;
	}

	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}
}

class CollectDataBeanHelper {

	public static BigDecimal isOnError(CollectDataBean collectDataBean)
			throws ParameterException, DAOException {

		BigDecimal totalWithoutRejected = collectDataBean.getTotalPay()
				.subtract(collectDataBean.getTotalPayRejected());
		BigDecimal totalWithoutRejectedAndFidelity = totalWithoutRejected
				.subtract(collectDataBean.getTotalPayFidelity());

		return collectDataBean.getTotalArticlesTVAC().subtract(
				totalWithoutRejectedAndFidelity);

	}

	public static void addRejectedDivTotal(List<String> warning,
			OperationUnitList operationUnitList) {
		for (OperationUnit operationUnit : operationUnitList.values()) {
			if (!operationUnit.isCancelled()) {
				BigDecimal unitTotal = BigDecimal.ZERO;
				BigDecimal unitJete = BigDecimal.ZERO;
				for (Item item : operationUnit.getPayList()) {
					if (POSConstants.REJECTED_PAY_TYPE.equals(item.getItem()
							.getId())) {
						unitJete = unitJete.add(item.getTotalTVAC());
						// } else if (POSConstants.FIDELITY_PAY_TYPE
						// .equals(item.getItem().getId())) {
						// fidelite.add(item.getTotalTVAC());
					}
					unitTotal = unitTotal.add(item.getTotalTVAC());
				}
				if (BigDecimal.ZERO.compareTo(unitJete) != 0
						&& unitJete.compareTo(unitTotal) != 0) {
					warning.add(operationUnit.getFileName() + "/[jete="
							+ unitJete + "][total=" + unitTotal + "]");
				}
			}
		}

	}

	public static void addValues(Map<String, BigDecimal> addedValues,
			Map<String, BigDecimal> values) {
		for (String key : addedValues.keySet()) {
			addValues(key, addedValues.get(key), values);
		}
	}

	public static void addValues(String key, BigDecimal value,
			Map<String, BigDecimal> values) {
		BigDecimal addedValue = values.get(key);
		if (addedValue == null) {
			values.put(key, value);
		} else {
			values.put(key, addedValue.add(value));
		}
	}

	public static void addSalesByPeriod(CollectDataBean collectDataBean,
			Map<String, PeriodValue> salesByPeriodToAdd) {

		Map<String, PeriodValue> salesByPeriod = collectDataBean
				.getSalesByPeriod();

		for (String period : salesByPeriodToAdd.keySet()) {
			PeriodValue exsistingSale = salesByPeriod.get(period);
			PeriodValue addedSale = salesByPeriodToAdd.get(period);
			if (exsistingSale == null) {
				salesByPeriod.put(period, addedSale);
			} else {
				exsistingSale.addPeriodValue(addedSale);
				salesByPeriod.put(period, exsistingSale);
			}
			collectDataBean.addTotalArticlesByPeriod(addedSale
					.getSalesByPeriod());
		}
	}

	public static void addItemList(CollectDataBean collectDataBean,
			Map<String, Item> addedItemList) {
		Map<String, BigDecimal> addedItemListValues = new HashMap<String, BigDecimal>();
		for (String id : addedItemList.keySet()) {
			Item salesTemp = addedItemList.get(id);
			BigDecimal tvac = salesTemp.getUnitPrice();
			collectDataBean.addTotalArticlesTVAC(tvac);
			collectDataBean.addTotalArticlesQTE(salesTemp.getQuantity());
			addedItemListValues.put(id, tvac);
		}
		addValues(addedItemListValues, collectDataBean.getItemList());
	}

	public static void computePayData(CollectDataBean collectDataBean)
			throws ParameterException, DAOException {

		collectDataBean.setTotalPay(BigDecimal.ZERO);
		collectDataBean.setTotalPaySales(BigDecimal.ZERO);
		collectDataBean.setTotalPayNotSales(BigDecimal.ZERO);
		collectDataBean.setTotalPayCA(BigDecimal.ZERO);
		collectDataBean.setTotalPayNotCA(BigDecimal.ZERO);
		collectDataBean.setTotalPayRejected(BigDecimal.ZERO);
		collectDataBean.setTotalPayFidelity(BigDecimal.ZERO);

		StringBuffer sb = new StringBuffer();

		for (String pay : collectDataBean.getPaymentsList().keySet()) {
			BigDecimal payValue = collectDataBean.getPaymentsList().get(pay);
			collectDataBean.setTotalPay(collectDataBean.getTotalPay().add(
					payValue));
			if (ComputeHelper.isSales(pay)) {
				collectDataBean.setTotalPaySales(collectDataBean
						.getTotalPaySales().add(payValue));
			} else {
				collectDataBean.setTotalPayNotSales(collectDataBean
						.getTotalPayNotSales().add(payValue));
			}
			if (ComputeHelper.isCA(pay)) {
				collectDataBean.setTotalPayCA(collectDataBean.getTotalPayCA()
						.add(payValue));
			} else {
				collectDataBean.setTotalPayNotCA(collectDataBean
						.getTotalPayNotCA().add(payValue));
			}
			if (POSConstants.REJECTED_PAY_TYPE.equals(pay)) {
				collectDataBean.setTotalPayRejected(collectDataBean
						.getTotalPayRejected().add(payValue));
			}
			if (POSConstants.FIDELITY_PAY_TYPE.equals(pay)) {
				collectDataBean.setTotalPayFidelity(collectDataBean
						.getTotalPayFidelity().add(payValue));
			}
			sb.append("["
					+ FacadeDAO.instance().getPayementModesDAO().getById(pay)
							.getCode() + "=" + payValue.toPlainString() + "]");
		}
		collectDataBean.setPayDetail(sb.toString());
	}

	public static void display(CollectDataBean collectDataBean)
			throws DAOException, ParameterException {

		CollectDataBeanHelper.computePayData(collectDataBean);

		System.out.println("--->"
				+ POSConstants.SDF_FULLDAY.format(collectDataBean
						.getPeriodBean().getStartDate())
				+ "->"
				+ POSConstants.SDF_FULLDAY.format(collectDataBean
						.getPeriodBean().getEndDate()));
		System.out.println("Have byDaySales : size = "
				+ collectDataBean.getByDaySales());

		System.out.println("Payements : "
				+ collectDataBean.getTotalPay().toPlainString() + "-"
				+ collectDataBean.getPayDetail());
		System.out.println("Sale : "
				+ collectDataBean.getTotalPaySales().toPlainString()
				+ "/not sale : "
				+ collectDataBean.getTotalPayNotSales().toPlainString()
				+ " (sales=CA+fidélité+factures)");
		System.out.println("CA : "
				+ collectDataBean.getTotalPayCA().toPlainString()
				+ "/not CA : "
				+ collectDataBean.getTotalPayNotCA().toPlainString()
				+ " (CA=Chèques repas + cash)");
		BigDecimal totalWithoutRejected = collectDataBean.getTotalPay()
				.subtract(collectDataBean.getTotalPayRejected());
		System.out.println("Total payements-Jeté : "
				+ totalWithoutRejected.toPlainString() + "/"
				+ collectDataBean.getTotalPayRejected().toPlainString());
		System.out.println("Total payements-Jeté-fidélité : "
				+ totalWithoutRejected.subtract(
						collectDataBean.getTotalPayFidelity()).toPlainString()
				+ "/" + collectDataBean.getTotalPayFidelity().toPlainString());

		System.out.print("Total articles comptabilisés : "
				+ ReportHelper.formatTotalQte(collectDataBean
						.getTotalArticlesQTE())
				+ "/"
				+ ReportHelper.formatTotalUnitPrice(collectDataBean
						.getTotalArticlesTVAC()) + "-");
		for (String id : collectDataBean.getItemList().keySet()) {
			BigDecimal tvac = collectDataBean.getItemList().get(id);
			System.out.print("["
					+ FacadeDAO.instance().getProductsDAO().getById(id)
							.getCode() + "="
					// + salesTemp.getQuantity().toPlainString() + "/"
					+ tvac.toPlainString() + "]");
		}
		System.out.println();

		System.out.print("Total articles comptabilisés par périodes : "
				+ ReportHelper.formatTotalUnitPrice(collectDataBean
						.getTotalArticlesByPeriod()) + "-");
		for (String period : collectDataBean.getSalesByPeriod().keySet()) {
			System.out.print("["
					+ period
					+ "/"
					+ ReportHelper.formatTotalUnitPrice(collectDataBean
							.getSalesByPeriod().get(period).getSalesByPeriod())
					+ "]");
		}
		System.out.println();
		if (collectDataBean.getWarning().size() > 0) {
			System.out.println("Messages");
			for (String message : collectDataBean.getWarning()) {
				System.out.println(message);
			}
		} else {
			System.out.println("Aucun warning");
		}
		System.out.print("Non résolus = ");
		for (String key : collectDataBean.getFidelityAndRejectedRest().keySet()) {
			System.out.print("["
					+ key
					+ "/"
					+ ReportHelper.formatTotalUnitPrice(collectDataBean
							.getFidelityAndRejectedRest().get(key)) + "]");
		}
		System.out.println();
		System.out.println();
	}

}
