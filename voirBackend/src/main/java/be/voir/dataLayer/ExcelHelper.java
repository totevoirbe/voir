package be.voir.dataLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import be.voir.dataLayer.ProductDAO.ProductXlsField;

public class ExcelHelper {

	private static CodeTVADAO codeTVADAO;
	private static ProductCategoryTagDAO productCategoryTagDAO;

	public static void injectDAO(CodeTVADAO codeTVADAOInj, ProductCategoryTagDAO productCategoryTagDAOInj) {
		codeTVADAO = codeTVADAOInj;
		productCategoryTagDAO = productCategoryTagDAOInj;
	}

	public static void writeFile(String FILE_NAME, String SHEET_NAME, List<Product> products) throws IOException {
		Workbook workbook = buildWorkbook(SHEET_NAME, products);
		FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

	private static Workbook buildWorkbook(String sheetName, List<Product> products) {

		// 1. Workbook creation
		Workbook workbook = new XSSFWorkbook();
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setColor(Font.COLOR_NORMAL);
		XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
		cellStyle.setFont(font);

		// 2. Sheet creation
		Sheet sheet = workbook.createSheet();
		sheet.setColumnWidth((short) 0, (short) ((50 * 8) / ((double) 1 / 20)));
		sheet.setColumnWidth((short) 1, (short) ((50 * 8) / ((double) 1 / 20)));
		workbook.setSheetName(0, sheetName);

		int rowNum = 0;
		Row headerRow = sheet.createRow(rowNum++);

		for (ProductXlsField productXlsField : ProductXlsField.values()) {
			int colNum = productXlsField.ordinal();
			Cell cell = headerRow.createCell(colNum);
			cell.setCellValue(productXlsField.getColHeader());
			cell.setCellStyle(cellStyle);
		}

		for (Product product : products) {

			Row row = sheet.createRow(rowNum++);
			for (ProductXlsField productXlsField : ProductXlsField.values()) {
				setCell(product, productXlsField, row);
			}

		}
		return workbook;
	}

	public static List<Product> readFile(String fileName, String SHEET_NAME) throws IOException {
		List<Product> products = null;
		FileInputStream excelInputStream = new FileInputStream(new File(fileName));
		Workbook workbook = new XSSFWorkbook(excelInputStream);
		Sheet sheet = workbook.getSheet(SHEET_NAME);
		Iterator<Row> rowItr = sheet.iterator();
		if (rowItr.hasNext()) {
			rowItr.next();
		}
		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			Product product = new Product();
			if (products == null) {
				products = new ArrayList<Product>();
			}
			products.add(product);
			for (ProductXlsField productXlsField : ProductXlsField.values()) {
				readCell(product, row, productXlsField, codeTVADAO);
			}
		}
		workbook.close();
		excelInputStream.close();
		return products;
	}

	public static String cellValueAsString(BigDecimal value) {
		if (value != null) {
			value = value.setScale(2, RoundingMode.HALF_UP);
			return value.toPlainString();
		} else {
			return null;
		}
	}

	public static BigDecimal cellValueAsBigdecimal(Cell cell) {

		BigDecimal value = null;

		if (cell != null) {

			String cellAsString = getCellValueAsString(cell);
			if (cellAsString != null && cellAsString.trim().length() > 0) {
				cellAsString = cellAsString.trim();
				value = new BigDecimal(cellAsString);
			}
		}

		return value;
	}

	private static String getCellValueAsString(Cell cell) {
		String cellValueAsString = null;
		CellType cellType = cell.getCellType();
		if (cellType == CellType.STRING) {
			cellValueAsString = cell.getStringCellValue();
		} else if (cellType == CellType.NUMERIC) {
			cellValueAsString = "" + cell.getNumericCellValue();
		} else if (cellType == CellType.BOOLEAN) {
			cellValueAsString = "" + cell.getBooleanCellValue();
		}
		return cellValueAsString;
	}

	public static void setCell(Product product, ProductXlsField productXlsField, Row row) {

		Cell cell = row.createCell(productXlsField.ordinal());

		switch (productXlsField) {

		case CODE:
			cell.setCellValue(product.getCode());
			return;
		case NAME:
			cell.setCellValue(product.getName());
			return;
		case PRODUCTVATEGORYTAGS:
			String tagList = null;
			if (product.getProductCategoryTags() != null && product.getProductCategoryTags().length > 0) {
				for (ProductCategoryTag productCategoryTag : product.getProductCategoryTags()) {
					if (tagList == null) {
						tagList = "";
					} else {
						tagList += ",";
					}
					tagList += productCategoryTag.getCode();
				}
				cell.setCellValue(tagList);
			}
			return;
		case VATRATEONPLACE:
			cell.setCellValue(product.getVatRateOnPlace().getCode());
			return;
		case VATRATETAKEAWAY:
			cell.setCellValue(product.getVatRateTakeAway().getCode());
			return;
		case MINI:
			cell.setCellValue(cellValueAsString(product.getMini()));
			return;
		case NORMAL:
			cell.setCellValue(cellValueAsString(product.getNormal()));
			return;
		case GEANT:
			cell.setCellValue(cellValueAsString(product.getGeant()));
			return;
		case FITMINI:
			cell.setCellValue(cellValueAsString(product.getFitmini()));
			return;
		case FITNORMAL:
			cell.setCellValue(cellValueAsString(product.getFitnormal()));
			return;
		case FITGEANT:
			cell.setCellValue(cellValueAsString(product.getFitgeant()));
			return;
		case IMAGE:
			cell.setCellValue(product.getImage());
			return;
		case HTMLKEYLABEL:
			cell.setCellValue(product.getHtmlKeyLabel());
			return;
		case TICKETLABEL:
			cell.setCellValue(product.getTicketLabel());
			return;
		case WEBDETAIL:
			cell.setCellValue(product.getWebDetail());
			return;
		case AFFICHEDETAIL:
			cell.setCellValue(product.getAfficheDetail());
			return;
		case CANMERGE:
			cell.setCellValue(product.getCanMerge());
			return;
		}
		throw new IllegalArgumentException("[" + productXlsField + "][" + product + "]");

	}

	public static void readCell(Product product, Row row, ProductXlsField productXlsField, CodeTVADAO codeTVADAO) {

		Cell cell = row.getCell(productXlsField.ordinal());

		if (cell == null) {
			return;
		}

		String cellValueAsString = getCellValueAsString(cell);

		switch (productXlsField) {

		case CODE:
			product.setCode(cellValueAsString);
			break;
		case NAME:
			product.setName(cellValueAsString);
			break;
		case PRODUCTVATEGORYTAGS:

			List<ProductCategoryTag> productCategoryTags = null;
			String productCategoryTagsAsString = cellValueAsString;
			if (productCategoryTagsAsString != null && productCategoryTagsAsString.trim().length() > 0) {
				productCategoryTagsAsString.trim();
				String[] productCategoryTagsAsArray = productCategoryTagsAsString.split(",");
				for (String productCategoryTagCode : productCategoryTagsAsArray) {
					productCategoryTagCode = productCategoryTagCode.trim();

					if (productCategoryTagCode.length() > 0) {

						if (productCategoryTags == null) {
							productCategoryTags = new ArrayList<>();
						}

						ProductCategoryTag productCategoryTag = productCategoryTagDAO.get(productCategoryTagCode);
						productCategoryTags.add(productCategoryTag);
					}
				}
				product.setProductCategoryTags(null);
				if (productCategoryTags != null) {
					ProductCategoryTag[] p = new ProductCategoryTag[] {};
					product.setProductCategoryTags(productCategoryTags.toArray(p));
				}
			}
			break;
		case VATRATEONPLACE:
			String code = cellValueAsString;
			CodeTVA codeTVA = codeTVADAO.get(code);
			product.setVatRateOnPlace(codeTVA);
			break;
		case VATRATETAKEAWAY:
			code = cellValueAsString;
			codeTVA = codeTVADAO.get(code);
			product.setVatRateTakeAway(codeTVA);
			break;
		case MINI:
			product.setMini(cellValueAsBigdecimal(cell));
			break;
		case NORMAL:
			product.setNormal(cellValueAsBigdecimal(cell));
			break;
		case GEANT:
			product.setGeant(cellValueAsBigdecimal(cell));
			break;
		case FITMINI:
			product.setFitmini(cellValueAsBigdecimal(cell));
			break;
		case FITNORMAL:
			product.setFitnormal(cellValueAsBigdecimal(cell));
			break;
		case FITGEANT:
			product.setFitgeant(cellValueAsBigdecimal(cell));
			break;
		case IMAGE:
			product.setImage(cellValueAsString);
			break;
		case HTMLKEYLABEL:
			product.setHtmlKeyLabel(cellValueAsString);
			break;
		case TICKETLABEL:
			product.setTicketLabel(cellValueAsString);
			break;
		case WEBDETAIL:
			product.setWebDetail(cellValueAsString);
			break;
		case AFFICHEDETAIL:
			product.setAfficheDetail(cellValueAsString);
			break;
		case CANMERGE:
			product.setCanMerge(new Boolean(getCellValueAsString(cell)));
			break;
		}
	}
}
