package be.panidel.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.OperationUnitList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.Payement;
import be.panidel.management.PeriodValue;
import be.panidel.management.Product;
import be.panidel.management.impl.PayementItemImpl;
import be.panidel.management.impl.ProductItemImpl;
import be.panidel.management.impl.SubProduct;
import be.panidel.pos.exception.ParameterException;

public class ComputeHelper {

	private static final Logger log = Logger.getLogger("ComputeHelper");

	public static final String REJECT = "Reject";
	public static final String FIDELITY = "Fidelity";

	public static Map<String, BigDecimal> getPaymentsByType(OperationUnitList operationUnitList) {
		return getPaymentsByType(operationUnitList, null);
	}

	public static Map<String, BigDecimal> getPaymentsByType(OperationUnitList operationUnitList,
			Identification identification) {

		Map<String, BigDecimal> paymentsByType = new HashMap<String, BigDecimal>();

		for (OperationUnit operationUnit : operationUnitList.values()) {
			getPayementsByPayType(paymentsByType, operationUnit, identification);
		}

		return paymentsByType;
	}

	public static Map<String, BigDecimal> getPaymentsByType(OperationUnit operationUnit) {

		Map<String, BigDecimal> paymentsByType = new HashMap<String, BigDecimal>();

		getPayementsByPayType(paymentsByType, operationUnit, null);

		return paymentsByType;
	}

	private static void getPayementsByPayType(Map<String, BigDecimal> paymentsByType, OperationUnit operationUnit,
			Identification identification) {
		if (!operationUnit.isCancelled()) {
			if (identification == null || identification.getId().equals(operationUnit.getEmployee().getId())) {
				List<Item> payList = operationUnit.getPayList();
				for (Iterator<Item> iterator = payList.iterator(); iterator.hasNext();) {
					PayementItemImpl pii = (PayementItemImpl) iterator.next();
					BigDecimal pay = pii.getUnitPrice().multiply(pii.getQuantity());

					// if cash or cheque repas
					if (pay != null && pay.compareTo(BigDecimal.ZERO) != 0) {
						Payement payment = (Payement) pii.getItem();

						BigDecimal payValue = null;
						String payType = null;

						for (Iterator<String> payTypeIterator = paymentsByType.keySet().iterator(); payType == null
								&& payTypeIterator.hasNext();) {

							String payTypeComparator = payTypeIterator.next();
							if (payTypeComparator.equals(payment.getId())) {
								payType = payTypeComparator;
								payValue = paymentsByType.get(payType);
							}

						}

						if (null == payType) {
							payType = payment.getId();
						}
						if (null == payValue) {
							payValue = new BigDecimal(0);
						}
						payValue = payValue.add(pay);
						paymentsByType.put(payType, payValue);
					}
				}
			}
		}
	}

	public static Map<String, PeriodValue> getSalesByPeriod(OperationUnitList operationUnitList)
			throws ParameterException {
		return getSalesByPeriod(operationUnitList, null);
	}

	public static Map<String, PeriodValue> getSalesByPeriod(OperationUnitList operationUnitList,
			Identification identification) throws ParameterException {

		Map<String, PeriodValue> salesByPeriod = new HashMap<String, PeriodValue>();
		List<Item> payList = null;
		Date dateOfOperations = null;

		for (OperationUnit operationUnit : operationUnitList.values()) {

			if (!operationUnit.isCancelled()) {

				if (identification == null || identification.getId().equals(operationUnit.getEmployee().getId())) {

					payList = operationUnit.getPayList();

					dateOfOperations = operationUnit.getBeginTime();

					for (Item payItem : payList) {

						if (isCA(payItem.getItem().getId())) {

							BigDecimal totalTVAC = payItem.getTotalTVAC();

							Calendar cal = new GregorianCalendar();
							cal.setTime(dateOfOperations);
							String periodHour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));

							PeriodValue periodValue = salesByPeriod.get(periodHour);

							if (periodValue == null) {
								periodValue = new PeriodValue(periodHour);
								salesByPeriod.put(periodHour, periodValue);
							}
							periodValue.addSale(totalTVAC);
							periodValue.incrementSalesQuantity();
						}
					}
				}
			}
		}
		return salesByPeriod;
	}

	public static boolean isCA(String type) throws ParameterException {
		return POSParameters.instance().getPayidAsCa().contains(type);
	}

	public static boolean isSales(String type) throws ParameterException {
		return POSParameters.instance().getPayidAsSale().contains(type);
	}

	public static Map<String, Item> getProductSales(OperationUnitList operationUnitList) {

		Map<String, Item> productSales = new HashMap<String, Item>();
		Item pii = null;
		Item piiResult = null;
		List<Item> itemList = null;
		for (OperationUnit operationUnit : operationUnitList.values()) {
			if (!operationUnit.isCancelled()) {
				itemList = operationUnit.getItemList();
				for (Iterator<Item> iterator = itemList.iterator(); iterator.hasNext();) {
					pii = iterator.next();
					String productId = pii.getItem().getId();
					piiResult = productSales.get(productId);
					if (piiResult == null) {
						piiResult = new ProductItemImpl((Product) pii.getItem(), pii.getItem().getDescription(),
								BigDecimal.ZERO, BigDecimal.ZERO, ((Product) pii.getItem()).getTvaTakeAway(),
								((Product) pii.getItem()).getTvaTakeOnPlace());
						productSales.put(productId, piiResult);
					}
					piiResult.setQuantity(piiResult.getQuantity().add(pii.getQuantity()));
					piiResult.setUnitPrice(piiResult.getUnitPrice().add(pii.getTotalTVAC()));

				}
			}
		}
		return productSales;
	}

	public static Map<String, Item> getRejectedProductList(OperationUnitList operationUnitList) {

		Map<String, Item> rejectedList = new HashMap<String, Item>();
		for (OperationUnit operationUnit : operationUnitList.values()) {
			if (!operationUnit.isCancelled()) {
				BigDecimal rejectedPay = BigDecimal.ZERO;
				for (Item item : operationUnit.getPayList()) {
					if (POSConstants.REJECTED_PAY_TYPE.equals(item.getItem().getId())
							&& !BigDecimal.ZERO.equals(item.getTotalTVAC())) {
						rejectedPay = rejectedPay.add(item.getTotalTVAC());
					}
				}
				if (BigDecimal.ZERO.compareTo(rejectedPay) != 0) {
					for (Item pii : operationUnit.getItemList()) {
						String productId = pii.getItem().getId();
						Item piiResult = rejectedList.get(productId);
						if (piiResult == null) {
							piiResult = new ProductItemImpl((Product) pii.getItem(), pii.getItem().getDescription(),
									BigDecimal.ZERO, BigDecimal.ZERO, ((Product) pii.getItem()).getTvaTakeAway(),
									((Product) pii.getItem()).getTvaTakeOnPlace());
							rejectedList.put(productId, piiResult);
						}
						piiResult.setQuantity(piiResult.getQuantity().add(pii.getQuantity()));
						piiResult.setUnitPrice(piiResult.getUnitPrice().add(pii.getTotalTVAC()));
					}
				}
			}
		}
		return rejectedList;
	}
//
//	public static Map<String, Item> getBySupplies(
//			OperationUnitList operationUnitList) throws DAOException {
//
//		Map<String, Item> supplies = new HashMap<String, Item>();
//
//		List<Item> itemList = null;
//
//		for (OperationUnit operationUnit : operationUnitList.values()) {
//
//			if (!operationUnit.isCancelled()) {
//
//				itemList = operationUnit.getItemList();
//
//				getSupplies(supplies, itemList);
//			}
//		}
//
//		return supplies;
//	}
//
//	private static void getSupplies(Map<String, Item> supplies,
//			List<Item> itemList) throws DAOException {
//		Item pii;
//		Item piiResult;
//		for (Iterator<Item> iterator = itemList.iterator(); iterator.hasNext();) {
//
//			pii = iterator.next();
//
//			List<SubProduct> subProductList = ((Product) (pii.getItem()))
//					.getSubProductList();
//
//			if (subProductList != null) {
//				for (SubProduct subProduct : subProductList) {
//
//					String rawProductId = subProduct.getRawProductId();
//
//					piiResult = supplies.get(rawProductId);
//
//					if (piiResult == null) {
//						piiResult = new RawProductItemImpl(
//								subProduct.getRawProduct(), pii.getItem()
//										.getDescription(), BigDecimal.ZERO);
//						supplies.put(rawProductId, piiResult);
//					}
//
//					BigDecimal qty = pii.getQuantity().multiply(
//							subProduct.getQty());
//
//					piiResult.setQuantity(piiResult.getQuantity().add(qty));
//					piiResult.setAchatHtva(piiResult.getAchatHtva().add(
//							qty.multiply(subProduct.getRawProduct()
//									.getPrixAchat())));
//				}
//			}
//		}
//	}

	public static Map<String, BigDecimal> fidelityAndRejectedCorrection(OperationUnit operationUnit)
			throws ParameterException, DAOException {

		Map<String, BigDecimal> fidelityAndRejectedRest = new HashMap<String, BigDecimal>();

		BigDecimal rejectedRest = clearRejectedProductUnitPrice(operationUnit);
		if (BigDecimal.ZERO.compareTo(rejectedRest) != 0) {
			fidelityAndRejectedRest.put(REJECT, rejectedRest);
		}

		List<Item> productItemSplit = new ArrayList<Item>();
		BigDecimal fidelityTempValue = fidelityCorrection(operationUnit, productItemSplit);
		for (Item item : productItemSplit) {
			operationUnit.getItemList().add(item);
		}
		if (BigDecimal.ZERO.compareTo(fidelityTempValue) != 0) {
			fidelityTempValue = fidelityPaymentCorrection(operationUnit.getPayList(), fidelityTempValue,
					operationUnit.getFileName());
		}
		if (BigDecimal.ZERO.compareTo(fidelityTempValue) != 0) {
			log.error("Acompenser:" + fidelityTempValue + "/CATVAC:" + operationUnit.getCaTVAC() + "/TOTALTVAC:"
					+ operationUnit.getTotalTVAC() + "-" + operationUnit);
		}
		if (BigDecimal.ZERO.compareTo(fidelityTempValue) != 0) {
			fidelityAndRejectedRest.put(FIDELITY, fidelityTempValue);
		}

		return fidelityAndRejectedRest;
	}

	private static BigDecimal fidelityPaymentCorrection(List<Item> payList, BigDecimal fidelityRest, String fileName)
			throws DAOException, ParameterException {

		BigDecimal cashCompensation = BigDecimal.ZERO;
		cashCompensation = cashCompensation.add(fidelityRest);
		for (Item item : payList) {
			if (POSConstants.FIDELITY_PAY_TYPE.equals(item.getItem().getId())
					&& BigDecimal.ZERO.compareTo(item.getTotalTVAC()) != 0) {
				if (fidelityRest.compareTo(item.getTotalTVAC()) >= 0) {
					fidelityRest = fidelityRest.subtract(item.getTotalTVAC());
					item.setUnitPrice(BigDecimal.ZERO);
				} else {
					if (BigDecimal.ZERO.compareTo(item.getQuantity()) != 0) {
						BigDecimal valueByUnit = fidelityRest.divide(item.getQuantity(), 4, RoundingMode.HALF_DOWN);
						item.setUnitPrice(item.getUnitPrice().subtract(valueByUnit));
						fidelityRest = BigDecimal.ZERO;
					}
				}

			}
		}
		cashCompensation = cashCompensation.subtract(fidelityRest);
		payList.add(new PayementItemImpl(
				FacadeDAO.instance().getPayementModesDAO().getById(POSParameters.instance().getPayidAsCash()),
				BigDecimal.ONE, POSParameters.instance().getTVATakeAway(), POSParameters.instance().getTVATakeOnPlace(),
				cashCompensation, "Auto Fidelity Compensation"));
		return fidelityRest;
	}

	private static BigDecimal fidelityCorrection(OperationUnit operationUnit, List<Item> productItemSplit) {

		BigDecimal fidelityTempValue = BigDecimal.ZERO;
		BigDecimal totalFidelityTempValue = BigDecimal.ZERO;

		for (Item fidelityItem : operationUnit.getPayList()) {
			if (POSConstants.FIDELITY_PAY_TYPE.equals(fidelityItem.getItem().getId())) {
				totalFidelityTempValue = totalFidelityTempValue.add(fidelityItem.getTotalTVAC());
			}
		}

		for (Item fidelityItem : operationUnit.getPayList()) {
			if (POSConstants.FIDELITY_PAY_TYPE.equals(fidelityItem.getItem().getId())
					&& BigDecimal.ZERO.compareTo(fidelityItem.getTotalTVAC()) != 0) {
				fidelityTempValue = fidelityTempValue.add(fidelityItem.getTotalTVAC());
				for (Item productItem : operationUnit.getItemList()) {
					for (BigDecimal qte = productItem.getQuantity(); BigDecimal.ZERO.compareTo(qte) <= 0; qte = qte
							.subtract(BigDecimal.ONE)) {
						if (fidelityTempValue.compareTo(BigDecimal.ZERO) != 0) {
							BigDecimal testValue = qte.multiply(productItem.getUnitPrice());
							if (fidelityTempValue.compareTo(testValue) == 0) {
								if (qte.compareTo(productItem.getQuantity()) == 0) {
									productItem.setUnitPrice(BigDecimal.ZERO);
								} else {
									Item newProductItem = productItem.duplicate();
									productItem.setQuantity(productItem.getQuantity().subtract(qte));
									newProductItem.setQuantity(qte);
									newProductItem.setUnitPrice(BigDecimal.ZERO);
									productItemSplit.add(newProductItem);
								}
								fidelityTempValue = fidelityTempValue.subtract(testValue);
								totalFidelityTempValue = totalFidelityTempValue.subtract(testValue);
							}
						}
					}
				}
			}
		}

		String[] supplies = { "164", "115", "116", "163", "165", "117", "160", "161", "162" };
		if (BigDecimal.ZERO.compareTo(totalFidelityTempValue) != 0) {
			totalFidelityTempValue = compensationBySupply(operationUnit, totalFidelityTempValue, supplies);
		}
		if (BigDecimal.ZERO.compareTo(totalFidelityTempValue) != 0) {
			totalFidelityTempValue = compensation(operationUnit, totalFidelityTempValue);
		}
		return totalFidelityTempValue;
	}

	private static BigDecimal compensationBySupply(OperationUnit operationUnit, BigDecimal fidelityTempValue,
			String[] supplies) {
		for (String supply : supplies) {
			for (Item productItem : operationUnit.getItemList()) {
				if (((Product) productItem.getItem()).getSubProductList() != null) {
					for (SubProduct subProduct : ((Product) productItem.getItem()).getSubProductList()) {
						if (BigDecimal.ZERO.compareTo(fidelityTempValue) == 0) {
							return fidelityTempValue;
						}
						if (supply.compareTo(subProduct.getRawProductId()) == 0) {
							fidelityTempValue = doCompensation(fidelityTempValue, productItem);
							break;
						}
					}
				}
			}
		}
		return fidelityTempValue;
	}

	private static BigDecimal compensation(OperationUnit operationUnit, BigDecimal fidelityTempValue) {
		for (Item productItem : operationUnit.getItemList()) {
			if (BigDecimal.ZERO.compareTo(fidelityTempValue) == 0) {
				return fidelityTempValue;
			}
			if (BigDecimal.ZERO.compareTo(productItem.getTotalTVAC()) != 0) {
				fidelityTempValue = doCompensation(fidelityTempValue, productItem);
			}
		}
		return fidelityTempValue;
	}

	private static BigDecimal doCompensation(BigDecimal fidelityTempValue, Item productItem) {
		BigDecimal valueTVAC = productItem.getTotalTVAC();
		if (valueTVAC.compareTo(fidelityTempValue) <= 0) {
			productItem.setUnitPrice(BigDecimal.ZERO);
			fidelityTempValue = fidelityTempValue.subtract(valueTVAC);
		} else {
			if (BigDecimal.ZERO.compareTo(productItem.getQuantity()) != 0) {
				BigDecimal valueByUnit = fidelityTempValue.divide(productItem.getQuantity(), 4, RoundingMode.HALF_DOWN);
				productItem.setUnitPrice(productItem.getUnitPrice().subtract(valueByUnit));
				fidelityTempValue = BigDecimal.ZERO;
			}
		}
		return fidelityTempValue;
	}

	public static BigDecimal clearRejectedProductUnitPrice(OperationUnit operationUnit) {
		BigDecimal rejectedPay = BigDecimal.ZERO;
		BigDecimal totalPay = BigDecimal.ZERO;
		String message = operationUnit.toString();
		for (Item item : operationUnit.getPayList()) {
			totalPay = totalPay.add(item.getTotalTVAC());
			if (POSConstants.REJECTED_PAY_TYPE.equals(item.getItem().getId())) {
				rejectedPay = rejectedPay.add(item.getTotalTVAC());
			}
		}
		if (BigDecimal.ZERO.compareTo(rejectedPay) != 0 && rejectedPay.compareTo(totalPay) == 0) {
			for (Item pii : operationUnit.getItemList()) {
				rejectedPay = rejectedPay.subtract(pii.getTotalTVAC());
				pii.setUnitPrice(BigDecimal.ZERO);
			}
		}
		if (BigDecimal.ZERO.compareTo(rejectedPay) != 0) {
			log.error(rejectedPay.toPlainString() + "-" + message);
		}
		return rejectedPay;
	}
}