package be.voir.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.voir.referential.model.Product;
import be.voir.referential.model.ProductCategoryTag;
import be.voir.referential.service.CodeTVAService;
import be.voir.referential.service.ProductCategoryTagService;
import be.voir.referential.service.ProductService;

public class ExcelHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelHelper.class);

	public enum ProductXlsField {

		CODE("code"), NAME("name"), HTMLKEYLABEL("htmlKeyLabel"), PRODUCTVATEGORYTAGS("productCategoryTags"),
		IMAGE("image"), VATRATETAKEAWAY("vatRateTakeAway"), VATRATEONPLACE("vatRateOnPlace"), MINI("mini"),
		NORMAL("normal"), GEANT("geant"), FITMINI("fitmini"), FITNORMAL("fitnormal"), FITGEANT("fitgeant"),
		TICKETLABEL("ticketLabel"), WEBDETAIL("webDetail"), AFFICHEDETAIL("afficheDetail"), CANMERGE("canMerge");

		public String colHeader;

		ProductXlsField(String colHeader) {
			this.colHeader = colHeader;
		}

		public String getColHeader() {
			return colHeader;
		}

	}

	public static void writeFile(String FILE_NAME, String SHEET_NAME, List<Product> products) throws IOException {
		Workbook workbook = null;
		FileOutputStream outputStream = null;
		try {
			workbook = buildWorkbook(SHEET_NAME, products);
			outputStream = new FileOutputStream(FILE_NAME);
			workbook.write(outputStream);
		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}

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

	public static void readFile(String fileName, String SHEET_NAME, ProductService productService,
			CodeTVAService codeTVAService, ProductCategoryTagService productCategoryTagService) throws IOException {
		FileInputStream excelInputStream = null;
		Workbook workbook = null;
		try {
			excelInputStream = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(excelInputStream);
			Sheet sheet = workbook.getSheet(SHEET_NAME);
			Iterator<Row> rowItr = sheet.iterator();
			if (rowItr.hasNext()) {
				rowItr.next();
			}
			while (rowItr.hasNext()) {
				Row row = rowItr.next();
				Product product = new Product();
				for (ProductXlsField productXlsField : ProductXlsField.values()) {
					readCell(product, productXlsField, row, codeTVAService, productCategoryTagService);
				}
				productService.save(product);
				LOG.info("" + product);
			}
		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (excelInputStream != null) {
				excelInputStream.close();
			}
		}
	}

	private static void setCell(Product product, ProductXlsField productXlsField, Row row) {

		try {

			switch (productXlsField) {

			case CODE:
				setStringCellValue(product.getCode(), productXlsField, row);
				return;
			case NAME:
				setStringCellValue(product.getName(), productXlsField, row);
				return;
			case PRODUCTVATEGORYTAGS:
				String tagList = null;
				if (product.getProductCategoryTags() != null && product.getProductCategoryTags().size() > 0) {
					for (ProductCategoryTag productCategoryTag : product.getProductCategoryTags()) {
						if (productCategoryTag != null) {
							if (tagList == null) {
								tagList = "";
							} else {
								tagList += ",";
							}
							tagList += productCategoryTag.getCode();

						} else {
							LOG.warn("Illegal tag value [PRODUCTXLSFIELD:" + productXlsField + "][PRODUCT:" + product
									+ "]");
						}
					}
				}
				setStringCellValue(tagList, productXlsField, row);
				return;
			case VATRATEONPLACE:
				setStringCellValue(product.getVatRateOnPlace().getCode(), productXlsField, row);
				return;
			case VATRATETAKEAWAY:
				setStringCellValue(product.getVatRateTakeAway().getCode(), productXlsField, row);
				return;
			case MINI:
				setBigDecimalCellValue(product.getMini(), productXlsField, row);
				return;
			case NORMAL:
				setBigDecimalCellValue(product.getNormal(), productXlsField, row);
				return;
			case GEANT:
				setBigDecimalCellValue(product.getGeant(), productXlsField, row);
				return;
			case FITMINI:
				setBigDecimalCellValue(product.getFitmini(), productXlsField, row);
				return;
			case FITNORMAL:
				setBigDecimalCellValue(product.getFitnormal(), productXlsField, row);
				return;
			case FITGEANT:
				setBigDecimalCellValue(product.getFitgeant(), productXlsField, row);
				return;
			case IMAGE:
				setStringCellValue(product.getImage(), productXlsField, row);
				return;
			case HTMLKEYLABEL:
				setStringCellValue(product.getHtmlKeyLabel(), productXlsField, row);
				return;
			case TICKETLABEL:
				setStringCellValue(product.getTicketLabel(), productXlsField, row);
				return;
			case WEBDETAIL:
				setStringCellValue(product.getWebDetail(), productXlsField, row);
				return;
			case AFFICHEDETAIL:
				setStringCellValue(product.getAfficheDetail(), productXlsField, row);
				return;
			case CANMERGE:
				setBooleanCellValue(product.getCanMerge(), productXlsField, row);
				return;
			}
		} catch (Throwable e) {
			LOG.error("[PRODUCTXLSFIELD:" + productXlsField + "][PRODUCT:" + product + "]", e);
			throw e;
		}
		throw new IllegalArgumentException("[" + productXlsField + "][" + product + "]");
	}

	private static void readCell(Product product, ProductXlsField productXlsField, Row row,
			CodeTVAService codeTVAService, ProductCategoryTagService productCategoryTagService) {
		try {
			switch (productXlsField) {
			case CODE:
				product.setCode(getCellValueAsString(productXlsField, row));
				break;
			case NAME:
				product.setName(getCellValueAsString(productXlsField, row));
				break;
			case VATRATEONPLACE:
				product.setVatRateOnPlace(
						codeTVAService.getByCode("" + row.getCell(ProductXlsField.VATRATEONPLACE.ordinal())));
				break;
			case VATRATETAKEAWAY:
				product.setVatRateTakeAway(
						codeTVAService.getByCode("" + row.getCell(ProductXlsField.VATRATETAKEAWAY.ordinal())));
				break;
			case MINI:
				product.setMini(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case NORMAL:
				product.setNormal(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case GEANT:
				product.setGeant(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case FITMINI:
				product.setFitmini(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case FITNORMAL:
				product.setFitnormal(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case FITGEANT:
				product.setFitgeant(getCellValueAsBigdecimal(productXlsField, row));
				break;
			case IMAGE:
				product.setImage(getCellValueAsString(productXlsField, row));
				break;
			case HTMLKEYLABEL:
				product.setHtmlKeyLabel(getCellValueAsString(productXlsField, row));
				break;
			case TICKETLABEL:
				product.setTicketLabel(getCellValueAsString(productXlsField, row));
				break;
			case WEBDETAIL:
				product.setWebDetail(getCellValueAsString(productXlsField, row));
				break;
			case AFFICHEDETAIL:
				product.setAfficheDetail(getCellValueAsString(productXlsField, row));
				break;
			case CANMERGE:
				product.setCanMerge(getCellValueAsBoolean(productXlsField, row));
				break;
			case PRODUCTVATEGORYTAGS:
				String productCategoryTagsAsString = getCellValueAsString(productXlsField, row);
				if (productCategoryTagsAsString != null && productCategoryTagsAsString.trim().length() > 0) {
					productCategoryTagsAsString.trim();
					String[] productCategoryTagsAsArray = productCategoryTagsAsString.split(",");
					for (String productCategoryTagCode : productCategoryTagsAsArray) {
						productCategoryTagCode = productCategoryTagCode.trim();
						if (productCategoryTagCode.length() > 0 && !"null".equals(productCategoryTagCode)) {
							ProductCategoryTag productCategoryTag = productCategoryTagService
									.getByCode(productCategoryTagCode);
							if (productCategoryTag == null) {
								LOG.warn("Illegal tag value [PRODUCTXLSFIELD:" + productXlsField
										+ "][PRODUCTCATEGORYTAGCODE:" + productCategoryTagCode + "]" + "][PRODUCT:"
										+ product + "]");
							}
							product.getProductCategoryTags().add(productCategoryTag);
						}
					}
				}
				break;
			}
		} catch (Throwable e) {
			LOG.error("[PRODUCTXLSFIELD:" + productXlsField + "][PRODUCT:" + product + "]", e);
			throw e;
		}
	}

	private static String getCellValueAsString(ProductXlsField productXlsField, Row row) {
		String cellValue = null;
		Cell cell = row.getCell(productXlsField.ordinal());
		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				cellValue = cell.getStringCellValue();
				break;
			case NUMERIC:
				LOG.warn("Wait String type [<NUMERIC>ProductXlsField:" + productXlsField.name() + "]");
				cellValue = "" + cell.getNumericCellValue();
				break;
			case BOOLEAN:
				LOG.warn("Wait String type [<BOOLEAN>ProductXlsField:" + productXlsField.name() + "]");
				cellValue = "" + cell.getBooleanCellValue();
				break;
			case BLANK:
				break;
			case FORMULA:
				LOG.error("Wait String type [<FORMULA>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case ERROR:
				LOG.error("Wait String type [<ERROR>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case _NONE:
				LOG.error("Wait String type [<_NONE>ProductXlsField:" + productXlsField.name() + "]");
				break;
			}
		}
		return cellValue;
	}

	private static BigDecimal getCellValueAsBigdecimal(ProductXlsField productXlsField, Row row) {
		BigDecimal cellValue = null;
		Cell cell = row.getCell(productXlsField.ordinal());
		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				String cellAsString = getCellValueAsString(productXlsField, row);
				if (cellAsString != null && cellAsString.trim().length() > 0) {
					cellAsString = cellAsString.trim();
					cellValue = new BigDecimal(cellAsString);
				}
				break;
			case NUMERIC:
				cellValue = new BigDecimal(cell.getNumericCellValue());
				break;
			case BOOLEAN:
				LOG.error("Wait numerical type [<BOOLEAN>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case BLANK:
				break;
			case FORMULA:
				LOG.error("Wait numerical type [<FORMULA>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case ERROR:
				LOG.error("Wait numerical type [<ERROR>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case _NONE:
				LOG.error("Wait numerical type [<_NONE>ProductXlsField:" + productXlsField.name() + "]");
				break;
			}
		}
		return cellValue;
	}

	private static Boolean getCellValueAsBoolean(ProductXlsField productXlsField, Row row) {
		Boolean cellValue = null;
		Cell cell = row.getCell(productXlsField.ordinal());
		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				String cellAsString = getCellValueAsString(productXlsField, row);
				if (cellAsString != null && cellAsString.trim().length() > 0) {
					cellAsString = cellAsString.trim();
					cellValue = Boolean.valueOf(cellAsString);
				}
				break;
			case NUMERIC:
				LOG.error("Wait bigdecimal type [<BOOLEAN>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case BOOLEAN:
				cellValue = Boolean.valueOf(cell.getBooleanCellValue());
				break;
			case BLANK:
				break;
			case FORMULA:
				LOG.error("Wait bigdecimal type [<FORMULA>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case ERROR:
				LOG.error("Wait bigdecimal type [<ERROR>ProductXlsField:" + productXlsField.name() + "]");
				break;
			case _NONE:
				LOG.error("Wait bigdecimal type [<_NONE>ProductXlsField:" + productXlsField.name() + "]");
				break;
			}
		}
		return cellValue;
	}

	private static void setStringCellValue(String value, ProductXlsField productXlsField, Row row) {
		Cell cell = row.createCell(productXlsField.ordinal());
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setBlank();
		}
	}

	private static void setBigDecimalCellValue(BigDecimal value, ProductXlsField productXlsField, Row row) {
		if (value != null) {
			value = value.setScale(2, RoundingMode.HALF_UP);
			setStringCellValue(value.toPlainString(), productXlsField, row);
		} else {
			setStringCellValue(null, productXlsField, row);
		}
	}

	private static void setBooleanCellValue(Boolean value, ProductXlsField productXlsField, Row row) {
		if (value != null) {
			setStringCellValue("" + value, productXlsField, row);
		} else {
			setStringCellValue(null, productXlsField, row);
		}
	}

//	private static BigDecimal cellValueAsBigdecimal(Cell cell) {
//		value = value.setScale(2, RoundingMode.HALF_UP);
//		return value.toPlainString();
//		BigDecimal value = null;
//		if (cell != null) {
//		}
//		return value;
//	}

}