package be.panidel.dao;

import static org.junit.Assert.fail;

import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import be.panidel.common.POSConstants;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.PeriodValue;
import be.panidel.management.impl.PeriodResult;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.ComputeHelper;
import be.panidel.tools.FileHelper;
import be.panidel.tools.SalesFilesGroupingTool;
import be.panidel.tools.TESTConstants;

public class OperationUnitImplTest {

	List<File> rejectedFiles = new ArrayList<File>();

	@Test
	public void testGetById() {

		try {

			System.out.println("-> Start testGetById");
			String idFileNameTest = "20091127115006595";
			String fileNameTest = idFileNameTest + ".xml";

			URL urlFile = this.getClass().getResource("/" + fileNameTest);
			File testFile = new File(urlFile.toURI());
			File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
					fileNameTest);

			FileHelper.copyFile(testFile, destFile);

			OperationUnitList operationUnitList = FacadeDAO.instance()
					.getOperationUnitDAO()
					.getById(fileNameTest, TESTConstants.STORAGE_CAISSES_TEST);

			if (operationUnitList.size() != 1) {
				fail("Number of operations error : " + operationUnitList.size());
			}

			for (StringReverseOrder nameOfTheFile : operationUnitList.keySet()) {
				OperationUnit operationUnit = operationUnitList
						.get(nameOfTheFile);
				Date date = new Date();
				date.setTime(1259318982329l);
				if (!date.equals(operationUnit.getBeginTime())) {
					fail(operationUnit.getComputerName());
				}
				if (operationUnit.getItemList().size() != 2) {
					fail("Error item size : "
							+ operationUnit.getItemList().size());
				}
				if (operationUnit.getPayList().size() != 1) {
					fail("Error item size : "
							+ operationUnit.getPayList().size());
				}

			}

			destFile.delete();

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}

		System.out.println("---> End testGetById with success");

	}

	@Test
	public void testGetList() {

		Date startDate = null;

		try {
			startDate = POSConstants.SDF_DAY.parse("20090101");
		} catch (ParseException e) {
			fail(e.getMessage());
		}

		try {

			System.out.println("Start testGetList");

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			OperationUnitList operationUnitList = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.extractSalesOfPeriod(TESTConstants.STORAGE_CAISSES_TEST,
							new PeriodBean(startDate, new Date()));

			for (StringReverseOrder dateOfDay : operationUnitList.keySet()) {
				OperationUnit operationUnit = operationUnitList.get(dateOfDay);
				String operationId = operationUnit.getFileName() + "/"
						+ operationUnit.isCancelled() + "/";
				List<Item> itemList = operationUnit.getItemList();
				for (Item item : itemList) {
					System.out.print(operationId);
					System.out.print(item.getItem().getId() + "/");
					System.out.print(item.getQuantity() + "/");
					System.out.print(item.getUnitPrice() + "/");
					System.out.println(item.getTotalTVAC());
				}
			}

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);
				destFile.delete();

			}

			System.out.println("End testGetList");

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetSalesByPeriod() {

		Date startDate = null;

		try {
			startDate = POSConstants.SDF_DAY.parse("20090101");
		} catch (ParseException e) {
			fail(e.getMessage());
		}

		try {

			System.out.println("Start testGetSalesByPeriod");

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			OperationUnitList operationUnitList = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.extractSalesOfPeriod(TESTConstants.STORAGE_CAISSES_TEST,
							new PeriodBean(startDate, new Date()));

			Map<String, PeriodValue> sales = ComputeHelper
					.getSalesByPeriod(operationUnitList);

			BigDecimal total = BigDecimal.ZERO;

			for (String period : sales.keySet()) {
				PeriodValue salesTemp = sales.get(period);
				total = total.add(salesTemp.getSalesByPeriod());
				System.out.println(period + "="
						+ salesTemp.getSalesByPeriod().toPlainString());
			}
			System.out.println("Total =" + total.toPlainString());

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);
				destFile.delete();

			}

			System.out.println("End testGetSalesByPeriod");

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetPaymentsByType() {

		Date startDate = null;

		try {
			startDate = POSConstants.SDF_DAY.parse("20090101");
		} catch (ParseException e) {
			fail(e.getMessage());
		}

		try {

			System.out.println("Start testGetPaymentsByType");

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			OperationUnitList operationUnitList = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.extractSalesOfPeriod(TESTConstants.STORAGE_CAISSES_TEST,
							new PeriodBean(startDate, new Date()));

			Map<String, BigDecimal> payments = ComputeHelper
					.getPaymentsByType(operationUnitList);

			BigDecimal total = BigDecimal.ZERO;

			for (String pay : payments.keySet()) {
				BigDecimal payTemp = payments.get(pay);
				total = total.add(payTemp);
				System.out.println(pay + "=" + payTemp.toPlainString());
			}
			System.out.println("Total =" + total.toPlainString());

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);
				destFile.delete();

			}

			System.out.println("End testGetPaymentsByType");

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetProductSales() {

		Date startDate = null;

		try {
			startDate = POSConstants.SDF_DAY.parse("20090101");
		} catch (ParseException e) {
			fail(e.getMessage());
		}

		try {
			System.out.println("Start testGetProductSales");

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			OperationUnitList operationUnitList = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.extractSalesOfPeriod(TESTConstants.STORAGE_CAISSES_TEST,
							new PeriodBean(startDate, new Date()));

			Map<String, Item> itemList = ComputeHelper
					.getProductSales(operationUnitList);

			BigDecimal total = BigDecimal.ZERO;

			for (String id : itemList.keySet()) {
				Item salesTemp = itemList.get(id);
				BigDecimal tvac = salesTemp.getUnitPrice();
				total = total.add(tvac);
				System.out.println(id + "="
						+ salesTemp.getQuantity().toPlainString() + "/"
						+ tvac.toPlainString());
			}
			System.out.println("Total =" + total.toPlainString());

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				destFile.delete();

			}

			System.out.println("End testGetProductSales");

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGroupSalesByDay() {

		System.out.println("-> Start testGroupSalesByDay");

		try {

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			SalesFilesGroupingTool.doIt(TESTConstants.STORAGE_CAISSES_TEST,
					TESTConstants.STORAGE_CAISSES_GROUP,
					TESTConstants.STORAGE_CAISSES_ARCHIVES,
					TESTConstants.STORAGE_CAISSES_REJECTED);
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		} catch (DAOException e) {
			fail(e.getMessage());
		}
		System.out.println("---> Start testGroupSalesByDay");
	}

	@Test
	public void testGetDayResultList() {

		Date startDate = null;

		try {
			startDate = POSConstants.SDF_DAY.parse("20090101");
		} catch (ParseException e) {
			fail(e.getMessage());
		}

		try {

			System.out.println("Start testGetDayResultList");

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				URL urlFile = this.getClass().getResource(
						"/" + TESTConstants.NAMES_OF_SALES_TEST[i]);
				File testFile = new File(urlFile.toURI());
				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);

				FileHelper.copyFile(testFile, destFile);

			}

			SalesFilesGroupingTool.doIt(TESTConstants.STORAGE_CAISSES_TEST,
					TESTConstants.STORAGE_CAISSES_GROUP,
					TESTConstants.STORAGE_CAISSES_ARCHIVES,
					TESTConstants.STORAGE_CAISSES_REJECTED);

			Map<StringReverseOrder, PeriodResult> byDaySales = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.getDayResultList(new PeriodBean(startDate, new Date()),
							TESTConstants.STORAGE_CAISSES_GROUP);

			BigDecimal total = BigDecimal.ZERO;
			BigDecimal ca = BigDecimal.ZERO;
			for (StringReverseOrder dateAsString : byDaySales.keySet()) {
				PeriodResult dayResult = byDaySales.get(dateAsString);

				System.out.println();
				System.out.println();
				System.out.println("---> date --->"
						+ POSConstants.SDF_FULLDAY.format(dayResult.getPeriod()
								.getStartDate()));

				System.out.println("---> Start dayResult.getSalesByPeriod");

				Map<String, PeriodValue> sales = dayResult.getValuesByPeriod();

				total = BigDecimal.ZERO;

				for (String period : sales.keySet()) {
					PeriodValue salesTemp = sales.get(period);
					total = total.add(salesTemp.getSalesByPeriod());
					System.out.println(period + "="
							+ salesTemp.getSalesByPeriod().toPlainString());
				}
				System.out.println("Total of sales by period ="
						+ total.toPlainString());

				System.out.println("---> Start testGetPaymentsByType");

				Map<String, BigDecimal> payments = dayResult
						.getPaymentsByType();

				total = BigDecimal.ZERO;

				for (String pay : payments.keySet()) {
					BigDecimal payTemp = payments.get(pay);
					total = total.add(payTemp);
					System.out.println(pay + "=" + payTemp.toPlainString());
				}
				System.out.println("Total of detail payements ="
						+ total.toPlainString());

				System.out.println("---> Start testGetProductSales");

				Map<String, Item> itemList = dayResult.getProductSales();

				total = BigDecimal.ZERO;

				for (String id : itemList.keySet()) {
					Item salesTemp = itemList.get(id);
					BigDecimal tvac = salesTemp.getUnitPrice();
					total = total.add(tvac);
					System.out.println(id + "="
							+ salesTemp.getQuantity().toPlainString() + "/"
							+ tvac.toPlainString());
				}
				System.out.println("Total of product sales ="
						+ total.toPlainString());

				System.out.println("---> Start total");

				total = total.add(dayResult.getTotalTVAC());
				ca = ca.add(dayResult.getCaTVAC());
				System.out.println(dateAsString + "=" + dayResult);

			}

			System.out.print("Total =" + total.toPlainString());
			System.out.println(" CA =" + ca.toPlainString());

			for (int i = 0; i < TESTConstants.NAMES_OF_SALES_TEST.length; i++) {

				File destFile = new File(TESTConstants.STORAGE_CAISSES_TEST,
						TESTConstants.NAMES_OF_SALES_TEST[i]);
				destFile.delete();

			}

			System.out.println("End testGetDayResultList");

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (URISyntaxException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}
}
