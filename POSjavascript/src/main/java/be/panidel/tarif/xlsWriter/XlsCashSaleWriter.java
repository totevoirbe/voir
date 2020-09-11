package be.panidel.tarif.xlsWriter;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.core.exceptions.WrongArgumentException;

import be.panidel.businessLayer.CashsaleReport;
import be.panidel.businessLayer.ReportPeriod;
import be.panidel.businessLayer.helper.EnumHelper.PeriodType;
import be.panidel.businessLayer.model.CashSaleReportModel;
import be.panidel.businessLayer.model.ItemKey;
import be.panidel.businessLayer.model.ItemPaymentReportModel;
import be.panidel.businessLayer.model.ItemProductReportModel;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.helper.PourcentHelper;
import be.panidel.dataLayer.model.VatRateModel;
import be.panidel.tarif.exception.IllegalTypeException;

public class XlsCashSaleWriter {

	private final static Logger LOG = LoggerFactory.getLogger(XlsCashSaleWriter.class);

	public enum ReportType {
		RESULTS, WITH_ITEM_DETAILS
	};

	private final static DateFormat dfAsDate = SimpleDateFormat.getDateInstance();

	public static void writeCashsalesReport(ReportPeriod reportPeriod, ReportType reportType)
			throws JAXBException, InvalidFormatException, IOException, IllegalTypeException, DataLayerException {

		XSSFWorkbook xssfWorkbook = null;
		int mainRowIndex = 0;

		PourcentHelper pourcentHelper = null;
		ReportPeriod subReportPeriod = null;
		PeriodType mergePeriodType = null;

		switch (reportPeriod.getPeriodType()) {

		case HOUR:
			throw new WrongArgumentException("Illegal period value");
		case DAY:
			mergePeriodType = PeriodType.HOUR;
			break;
		case MONTH:
			mergePeriodType = PeriodType.DAY;
			break;
		case YEAR:
			mergePeriodType = PeriodType.MONTH;
			break;
		case CASH_SALE_UNIT:
			throw new WrongArgumentException("Cash sale unit is not a period");
		}

		long unitsInRange = ReportPeriod.getUnitsInRange(reportPeriod);
		pourcentHelper = new PourcentHelper(unitsInRange, "[writeCashsalesReport]", LOG);

		subReportPeriod = new ReportPeriod(reportPeriod.getBeginTime(), reportPeriod.getPeriodType());

		do {

			mainRowIndex = 0;
			xssfWorkbook = new XSSFWorkbook();
			String xlsFileName = DAOConfig.DOC_BASE + "Resultats"
					+ subReportPeriod.getPeriodType().getDateFormat().format(subReportPeriod.getBeginTime()) + ".xlsx";

			Sheet mainResultSheet = xssfWorkbook.createSheet("Synthèse");
			Row mainRowTitle = mainResultSheet.createRow(mainRowIndex++);
			String mainTitle = "Synthèse : " + dfAsDate.format(subReportPeriod.getBeginTime()) + " to "
					+ dfAsDate.format(subReportPeriod.getEndTime());
			writeValue(xssfWorkbook, mainRowTitle, mainTitle, 0);
			mainRowIndex = writeHeader(xssfWorkbook, mainResultSheet, mainRowIndex);

			Map<Date, CashSaleReportModel> periodCashSaleReports = CashsaleReport
					.mergeCashSalesAsReport(subReportPeriod, mergePeriodType);

			LOG.info(subReportPeriod + " / " + periodCashSaleReports.size());

			for (CashSaleReportModel cashSaleReport : periodCashSaleReports.values()) {

				mainRowIndex = writeCashSaleReportRow(xssfWorkbook, mainResultSheet, mainRowIndex, cashSaleReport);
				mainRowIndex = fulfillReport(reportType, mergePeriodType, xssfWorkbook, mainRowIndex, cashSaleReport);

			}

			writeXlsFile(xssfWorkbook, xlsFileName);

			subReportPeriod = new ReportPeriod(ReportPeriod.getContigousBeginTime(subReportPeriod),
					reportPeriod.getPeriodType());

			pourcentHelper.increment(1);

		} while (reportPeriod.isSubPeriodInPerdiodRange(subReportPeriod));

		LOG.debug("Done");

	}

	public static void writeCashsalesReport(CashSaleReportModel cashSaleReport, ReportType reportType,
			PeriodType periodType)
			throws JAXBException, InvalidFormatException, IOException, IllegalTypeException, DataLayerException {

		int mainRowIndex = 0;

		DateFormat xlsDF = new SimpleDateFormat("yyyyMMddhh");

		String xlsFileName = DAOConfig.DOC_BASE + "Resultats"
				+ xlsDF.format(cashSaleReport.getReportPeriod().getBeginTime()) + ".xlsx";

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		mainRowIndex = fulfillReport(reportType, periodType, xssfWorkbook, mainRowIndex, cashSaleReport);

		writeXlsFile(xssfWorkbook, xlsFileName);

		LOG.debug("Done");

	}

	public static void writeXlsFile(XSSFWorkbook xssfWorkbook, String xlsFileName)
			throws FileNotFoundException, IOException {
		OutputStream xlsOutputStream = null;

		try {

			xlsOutputStream = new FileOutputStream(new File(xlsFileName));
			xssfWorkbook.write(xlsOutputStream);

		} finally {
			if (xlsOutputStream != null) {
				xlsOutputStream.close();
			}
		}
	}

	public static int fulfillReport(ReportType reportType, PeriodType periodType, XSSFWorkbook xssfWorkbook,
			int mainRowIndex, CashSaleReportModel cashSaleReport) throws DataLayerException {

		int rowIndex = 0;
		String sheetName = null;

		if (PeriodType.CASH_SALE_UNIT == periodType) {
			sheetName = "Ventes" + cashSaleReport.getId();
		} else {
			sheetName = "Ventes" + periodType.getDateFormat().format(cashSaleReport.getReportPeriod().getEndTime());
		}
		Sheet resultSheet = xssfWorkbook.createSheet(sheetName);

		Row rowTitle = resultSheet.createRow(rowIndex++);
		String title = "Sales from : " + dfAsDate.format(cashSaleReport.getReportPeriod().getBeginTime()) + " to "
				+ dfAsDate.format(cashSaleReport.getReportPeriod().getEndTime());
		writeValue(xssfWorkbook, rowTitle, title, 0);
		rowIndex = writeHeader(xssfWorkbook, resultSheet, rowIndex);

		rowIndex = writeCashSaleReportRow(xssfWorkbook, resultSheet, rowIndex, cashSaleReport);

		if (ReportType.WITH_ITEM_DETAILS.equals(reportType)) {

			rowIndex = listItemPayments(xssfWorkbook, cashSaleReport, ++rowIndex, resultSheet);
			rowIndex = listItemProducts(xssfWorkbook, cashSaleReport, ++rowIndex, resultSheet);

		}
		return mainRowIndex;
	}

	public static int writeHeader(XSSFWorkbook xssfWorkbook, Sheet resultSheet, int rowIndex)
			throws DataLayerException {
		Row rowHeader = resultSheet.createRow(rowIndex++);

		writeValue(xssfWorkbook, rowHeader, "from", 0);
		writeValue(xssfWorkbook, rowHeader, "to", 1);
		writeValue(xssfWorkbook, rowHeader, "total", 2);
		writeValue(xssfWorkbook, rowHeader, "htva", 3);
		writeValue(xssfWorkbook, rowHeader, "deduced htva", 4);
		writeValue(xssfWorkbook, rowHeader, "free", 5);
		writeValue(xssfWorkbook, rowHeader, "lost", 6);
		writeValue(xssfWorkbook, rowHeader, "trash", 7);
		writeValue(xssfWorkbook, rowHeader, "Payements", 8);
		writeValue(xssfWorkbook, rowHeader, "Nb articles", 9);

		List<VatRateModel> vatRateModels = DataFacade.instance.getAllVatRates(false);

		int column = 11;
		for (VatRateModel vatRate : vatRateModels) {
			writeValue(xssfWorkbook, rowHeader, display(vatRate.getRate()), column++);
		}

		return rowIndex;
	}

	public static int writeCashSaleReportRow(XSSFWorkbook xssfWorkbook, Sheet resultSheet, int rowIndex,
			CashSaleReportModel cashSaleReport) {
		Row rowResult = resultSheet.createRow(rowIndex++);

		writeValue(xssfWorkbook, rowResult, cashSaleReport.getReportPeriod().getPeriodType().getDateFormat()
				.format(cashSaleReport.getReportPeriod().getBeginTime()), 0);
		writeValue(xssfWorkbook, rowResult, cashSaleReport.getReportPeriod().getPeriodType().getDateFormat()
				.format(cashSaleReport.getReportPeriod().getEndTime()), 1);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleTotal()), 2);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleExcludVAT()), 3);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleDeducedExcludVAT()), 4);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleFree()), 5);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleLost()), 6);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleTrash()), 7);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSalePaymentTotal()), 8);
		writeValue(xssfWorkbook, rowResult, display(cashSaleReport.getCashSaleNbArticles()), 9);

		int column = 11;
		Map<ItemKey, BigDecimal> vatSplit = cashSaleReport.getVatSplit();
		for (ItemKey itemKey : vatSplit.keySet()) {
			writeValue(xssfWorkbook, rowResult, display(vatSplit.get(itemKey)), column++);
		}

		return rowIndex;
	}

	private static Double display(BigDecimal value) {
		if (value == null) {
			return 0D;
		} else {
			return value.doubleValue();
		}
	}

	public static int listItemProducts(XSSFWorkbook xssfWorkbook, CashSaleReportModel cashSaleReport,
			int rowProductIndex, Sheet detailProductSheet) {

		for (ItemProductReportModel itemProductReport : cashSaleReport.getItemReportProducts().values()) {

			Row rowdetail = detailProductSheet.createRow(rowProductIndex);
			writeValue(xssfWorkbook, rowdetail, "" + itemProductReport.getProduct().getId(), 0);
			writeValue(xssfWorkbook, rowdetail, "" + itemProductReport.getProduct().getTicketLabel(), 1);
			writeValue(xssfWorkbook, rowdetail, display(itemProductReport.getNbreProduits()), 2);
			writeValue(xssfWorkbook, rowdetail, display(itemProductReport.getTotal()), 3);
			rowProductIndex++;
		}

		return rowProductIndex;

	}

	public static int listItemPayments(XSSFWorkbook xssfWorkbook, CashSaleReportModel cashSaleReport,
			int rowPaymentIndex, Sheet detailPaymentSheet) {
		for (ItemPaymentReportModel itemPaymentReport : cashSaleReport.getItemReportPayments().values()) {

			Row rowdetail = detailPaymentSheet.createRow(rowPaymentIndex);
			writeValue(xssfWorkbook, rowdetail, "" + itemPaymentReport.getPaymentMethod().getTicketLabel(), 1);
			writeValue(xssfWorkbook, rowdetail, display(itemPaymentReport.getOperation()), 2);
			writeValue(xssfWorkbook, rowdetail, display(itemPaymentReport.getTotal()), 3);
			rowPaymentIndex++;

		}

		return rowPaymentIndex;
	}

	public static void writeValue(XSSFWorkbook xssfWorkbook, Row row, String value, int col) {

		String GENERIC_FONT_NAME = "Times New Roman";
		double FONT_HEIGHT = 12;
		XSSFColor COLOR = new XSSFColor(new Color(0, 0, 0));
		XSSFFont xssfFont = xssfWorkbook.createFont();
		xssfFont.setFontHeight(FONT_HEIGHT);
		xssfFont.setFontName(GENERIC_FONT_NAME);
		xssfFont.setColor(COLOR);
		XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
		xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		xssfCellStyle.setAlignment(HorizontalAlignment.LEFT);
		xssfCellStyle.setFont(xssfFont);
		xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(0, 0, 0)));
		xssfCellStyle.setFillBackgroundColor(new XSSFColor(new Color(0, 0, 0)));

		Cell cell = row.createCell(col);
		cell.setCellStyle(xssfCellStyle);
		cell.setCellValue(value);

	}

	public static void writeValue(XSSFWorkbook xssfWorkbook, Row row, double value, int col) {

		String GENERIC_FONT_NAME = "Times New Roman";
		double FONT_HEIGHT = 12;
		XSSFColor COLOR = new XSSFColor(new Color(0, 0, 0));
		XSSFFont xssfFont = xssfWorkbook.createFont();
		xssfFont.setFontHeight(FONT_HEIGHT);
		xssfFont.setFontName(GENERIC_FONT_NAME);
		xssfFont.setColor(COLOR);
		XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
		xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		xssfCellStyle.setAlignment(HorizontalAlignment.LEFT);
		xssfCellStyle.setFont(xssfFont);
		xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(0, 0, 0)));
		xssfCellStyle.setFillBackgroundColor(new XSSFColor(new Color(0, 0, 0)));

		Cell cell = row.createCell(col);
		cell.setCellStyle(xssfCellStyle);
		cell.setCellValue(value);

	}

}
