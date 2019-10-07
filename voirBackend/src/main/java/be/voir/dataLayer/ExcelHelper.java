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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.voir.dataLayer.ProductDAO.ProductXlsField;

public class ExcelHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelHelper.class);

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

		try {
			Cell cell = row.createCell(productXlsField.ordinal());

			switch (productXlsField) {

			case CODE:
				if (product.getCode() != null) {
					cell.setCellValue(product.getCode());
				} else {
					cell.setBlank();
				}
				return;
			case NAME:
				if (product.getName() != null) {
					cell.setCellValue(product.getName());
				} else {
					cell.setBlank();
				}
				return;
			case PRODUCTVATEGORYTAGS:
				String tagList = null;
				if (product.getProductCategoryTags() != null && product.getProductCategoryTags().length > 0) {
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
					cell.setCellValue(tagList);
				} else {
					cell.setBlank();
				}
				return;
			case VATRATEONPLACE:
				if (product.getVatRateOnPlace() != null) {
					cell.setCellValue(product.getVatRateOnPlace().getCode());
				} else {
					cell.setBlank();
				}
				return;
			case VATRATETAKEAWAY:
				if (product.getVatRateTakeAway() != null) {
					cell.setCellValue(product.getVatRateTakeAway().getCode());
				} else {
					cell.setBlank();
				}
				return;
			case MINI:
				if (product.getMini() != null) {
					cell.setCellValue(cellValueAsString(product.getMini()));
				} else {
					cell.setBlank();
				}
				return;
			case NORMAL:
				if (product.getNormal() != null) {
					cell.setCellValue(cellValueAsString(product.getNormal()));
				} else {
					cell.setBlank();
				}
				return;
			case GEANT:
				if (product.getGeant() != null) {
					cell.setCellValue(cellValueAsString(product.getGeant()));
				} else {
					cell.setBlank();
				}
				return;
			case FITMINI:
				if (product.getFitmini() != null) {
					cell.setCellValue(cellValueAsString(product.getFitmini()));
				} else {
					cell.setBlank();
				}
				return;
			case FITNORMAL:
				if (product.getFitnormal() != null) {
					cell.setCellValue(cellValueAsString(product.getFitnormal()));
				} else {
					cell.setBlank();
				}
				return;
			case FITGEANT:
				if (product.getFitgeant() != null) {
					cell.setCellValue(cellValueAsString(product.getFitgeant()));
				} else {
					cell.setBlank();
				}
				return;
			case IMAGE:
				if (product.getImage() != null) {
					cell.setCellValue(product.getImage());
				} else {
					cell.setBlank();
				}
				return;
			case HTMLKEYLABEL:
				if (product.getHtmlKeyLabel() != null) {
					cell.setCellValue(product.getHtmlKeyLabel());
				} else {
					cell.setBlank();
				}
				return;
			case TICKETLABEL:
				if (product.getTicketLabel() != null) {
					cell.setCellValue(product.getTicketLabel());
				} else {
					cell.setBlank();
				}
				return;
			case WEBDETAIL:
				if (product.getWebDetail() != null) {
					cell.setCellValue(product.getWebDetail());
				} else {
					cell.setBlank();
				}
				return;
			case AFFICHEDETAIL:
				if (product.getAfficheDetail() != null) {
					cell.setCellValue(product.getAfficheDetail());
				} else {
					cell.setBlank();
				}
				return;
			case CANMERGE:
				if (product.getCanMerge() != null) {
					cell.setCellValue(product.getCanMerge());
				} else {
					cell.setBlank();
				}
				return;
			}
		} catch (Throwable e) {
			LOG.error("[PRODUCTXLSFIELD:" + productXlsField + "][PRODUCT:" + product + "]", e);
			throw e;
		}
		throw new IllegalArgumentException("[" + productXlsField + "][" + product + "]");

	}

	public static void readCell(Product product, Row row, ProductXlsField productXlsField, CodeTVADAO codeTVADAO) {

		Cell cell = row.getCell(productXlsField.ordinal());

		try {

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
						if (productCategoryTagCode.length() > 0 && !"null".equals(productCategoryTagCode)) {
							if (productCategoryTags == null) {
								productCategoryTags = new ArrayList<>();
							}
							ProductCategoryTag productCategoryTag = productCategoryTagDAO.get(productCategoryTagCode);
							if (productCategoryTag == null) {
								LOG.warn("Illegal tag value [PRODUCTXLSFIELD:" + productXlsField
										+ "][PRODUCTCATEGORYTAGCODE:" + productCategoryTagCode + "]" + "][PRODUCT:"
										+ product + "]");
							}
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
				CellType cellType = cell.getCellType();
				if (cellType == CellType.STRING) {
					String canMergeCellValueAsString = cell.getStringCellValue();
					if (cellValueAsString != null) {
						product.setCanMerge(Boolean.valueOf(canMergeCellValueAsString));
					}
				} else if (cellType == CellType.BOOLEAN) {
					product.setCanMerge(cell.getBooleanCellValue());
				} else if (cellType == CellType.BLANK) {
					product.setCanMerge(false);
				} else {
					throw new IllegalArgumentException("Cell type not legal for CANMERGE column :  [CELLTYPE:"
							+ cellType.name() + "][PRODUCT:" + product + "]");
				}
				break;
			}
		} catch (Throwable e) {
			LOG.error("[PRODUCTXLSFIELD:" + productXlsField + "][PRODUCT:" + product + "]", e);
			throw e;
		}
	}
}
