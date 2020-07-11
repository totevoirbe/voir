package be.panidel.dataLayer.helper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;
import be.panidel.tarif.exception.IllegalTypeException;

public class XlsProductDataReaderHelper {

	private final static Logger LOG = LoggerFactory.getLogger(XlsProductDataReaderHelper.class);

	/**
	 * read product data from excel
	 * 
	 * @param xlsFileName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws IllegalTypeException
	 * @throws DataLayerException
	 */
	public static Collection<ProductModel> parseXlsxfile()
			throws InvalidFormatException, IOException, IllegalTypeException, DataLayerException {

		InputStream inputStream = XlsProductDataReaderHelper.class.getClassLoader()
				.getResourceAsStream(DAOConfig.POS10_PRODUCTS);
		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0);

		Collection<ProductModel> products = new ArrayList<>();

		Row rowHeader = sheet.getRow(0);

		List<String> headers = new ArrayList<>();

		for (int cellIndex = 0; cellIndex < rowHeader.getLastCellNum(); cellIndex++) {
			Cell cell = rowHeader.getCell(cellIndex);
			String label = "" + parseCell(cell);
			headers.add(label);
			LOG.debug("Header[" + cell.getColumnIndex() + "," + label + "]");
		}

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

			Row row = sheet.getRow(rowIndex);

			long id = row.getRowNum();
			String label = parseXlsCellAsString("name", row, headers);
			String ticketLabel = parseXlsCellAsString("name", row, headers);
			String code = parseXlsCellAsString("id", row, headers);
			String name = parseXlsCellAsString("name", row, headers);
			String htmlKeyLabel = parseXlsCellAsString("htmlKeyLabel", row, headers);
			String type = parseXlsCellAsString("type", row, headers);
			String image = parseXlsCellAsString("image", row, headers);
			String vatAway = "" + parseCell(row.getCell(headers.indexOf("codeTva")));
			String vatOnplace = "" + parseCell(row.getCell(headers.indexOf("codeTva")));
//			String vatAway = "" + parseCell(row.getCell(headers.indexOf("vatAway")));
//			String vatOnplace = "" + parseCell(row.getCell(headers.indexOf("vatOnPlace")));
			BigDecimal mini = parseXlsCellAsBigDecimal("mini", row, headers);
			BigDecimal normal = parseXlsCellAsBigDecimal("normal", row, headers);
			BigDecimal geant = parseXlsCellAsBigDecimal("geant", row, headers);
			BigDecimal fitmini = parseXlsCellAsBigDecimal("fitmini", row, headers);
			BigDecimal fitnormal = parseXlsCellAsBigDecimal("fitnormal", row, headers);
			BigDecimal fitgeant = parseXlsCellAsBigDecimal("fitgeant", row, headers);
			String webDetail = parseXlsCellAsString("webDetail", row, headers);
			String afficheDetail = parseXlsCellAsString("afficheDetail", row, headers);
			Boolean canMerge = true;

			VatRateModel vatRateOnPlace = DataFacade.instance.getVatRateByCode(vatOnplace);
			VatRateModel vatRateTakeAway = DataFacade.instance.getVatRateByCode(vatAway);

			ProductModel product = new ProductModel(id, label, ticketLabel, code, name, htmlKeyLabel, type, image,
					vatRateOnPlace, vatRateTakeAway, mini, normal, geant, fitmini, fitnormal, fitgeant, webDetail,
					afficheDetail, canMerge);

			products.add(product);

			LOG.debug("Read xlsValue : " + product);

		}

		return products;

	}

	/**
	 * @param columnName
	 * @param rowCells
	 * @return integer of null if not an integer
	 */
	public static Integer parseXlsCellAsInteger(String columnName, Row row, List<String> headers) {

		Cell cell = row.getCell(headers.indexOf(columnName));

		if (cell == null) {
			return null;
		}

		Double value = cell.getNumericCellValue();

		return value.intValue();

	}

	/**
	 * @param columnName
	 * @param rowCells
	 * @return integer of null if not an integer
	 */
	private static BigDecimal parseXlsCellAsBigDecimal(String columnName, Row row, List<String> headers) {

		Cell cell = row.getCell(headers.indexOf(columnName));

		if (cell == null) {
			return null;
		}

		Double value = cell.getNumericCellValue();

		BigDecimal valueAsBigDecimal = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);

		return valueAsBigDecimal;

	}

	private static String parseXlsCellAsString(String columnName, Row row, List<String> headers) {

		Cell cell = row.getCell(headers.indexOf(columnName));

		if (cell == null) {
			return null;
		}

		String value = cell.getStringCellValue();

		return value;

	}

	public static Object parseCell(Cell cell) throws IllegalTypeException {

		if (cell.getCellTypeEnum() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			return cell.getNumericCellValue();
		} else if (cell.getCellTypeEnum() == CellType.BLANK) {
			return null;
		} else if (cell.getCellTypeEnum() == CellType.ERROR) {
			return null;
		} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
			return cell.getBooleanCellValue();
		} else if (cell.getCellTypeEnum() == CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell)) {
			Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			return date;
		} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
			return null;
		} else {
			throw new IllegalTypeException("" + cell.getCellTypeEnum());
		}

	}

}
