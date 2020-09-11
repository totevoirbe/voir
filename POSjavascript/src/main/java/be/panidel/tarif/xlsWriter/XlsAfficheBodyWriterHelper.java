package be.panidel.tarif.xlsWriter;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.ProductModel;

public class XlsAfficheBodyWriterHelper implements XslAfficheBodyA3 {

	private final static Logger LOG = LoggerFactory.getLogger(XlsAfficheBodyWriterHelper.class);

	public static void createHeaderRow(XSSFWorkbook xssfWorkbook, Sheet sheet, int rowIndex) throws IOException {

		Row row = sheet.createRow(rowIndex);
		row.setHeightInPoints(RowDef.HEADER.getHeight());

		formatCell(xssfWorkbook, row, ColDef.MARGE);
		XlsAfficheBodyWriterHelper.setLogo(xssfWorkbook, null, row, null);
		formatCell(xssfWorkbook, row, ColDef.LABEL).setCellValue("");
		formatCell(xssfWorkbook, row, ColDef.PRICE_1_HEADER).setCellValue("normal");
		formatCell(xssfWorkbook, row, ColDef.PRICE_2_HEADER).setCellValue("géant");

	}

	public static void createProductRow(XSSFWorkbook xssfWorkbook, Sheet sheet, int rowIndex, ProductModel productModel)
			throws IOException {

		Row row = sheet.createRow(rowIndex);
		row.setHeightInPoints(RowDef.PRODUCT.getHeight());

		formatCell(xssfWorkbook, row, ColDef.MARGE);
		setLogo(xssfWorkbook, sheet, row, productModel.getImage());
		formatCell(xssfWorkbook, row, ColDef.LABEL).setCellValue(productModel.getName());

		if (productModel.getGeant() == null || productModel.getGeant().compareTo(BigDecimal.ZERO) <= 0) {
			setPriceCell(xssfWorkbook, row, ColDef.PRICE_1, null);
			try {
				setPriceCell(xssfWorkbook, row, ColDef.PRICE_2, productModel.getNormal());
			} catch (Exception e) {
				LOG.error("", e);
			}
			sheet.addMergedRegion(
					new CellRangeAddress(rowIndex, rowIndex, ColDef.LABEL.getColIndex(), ColDef.PRICE_1.getColIndex()));
		} else {
			setPriceCell(xssfWorkbook, row, ColDef.PRICE_1, productModel.getNormal());
			setPriceCell(xssfWorkbook, row, ColDef.PRICE_2, productModel.getGeant());
		}

	}

	public static void createTitleRow(XSSFWorkbook xssfWorkbook, Sheet sheet, int rowIndex, String value) {

		Row row = sheet.createRow(rowIndex);
		row.setHeightInPoints(RowDef.TITLE.getHeight());
		Cell cell = formatCell(xssfWorkbook, row, ColDef.TITLE);
		value = value.substring(1, value.length() - 1);
		cell.setCellValue(value);
		sheet.addMergedRegion(
				new CellRangeAddress(rowIndex, rowIndex, ColDef.TITLE.getColIndex(), ColDef.PRICE_2.getColIndex()));

	}

	public static void createDescriptionRow(XSSFWorkbook xssfWorkbook, Sheet sheet, int rowIndex, String text)
			throws IOException {

		Row row = sheet.createRow(rowIndex);
		row.setHeightInPoints(RowDef.DESCRIPTION.getHeight());

		formatCell(xssfWorkbook, row, ColDef.MARGE);
		setLogo(xssfWorkbook, sheet, row, null);
		Cell cell = formatCell(xssfWorkbook, row, ColDef.DESCRIPTION);
		text = text.substring(1, text.length() - 1);
		cell.setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, ColDef.DESCRIPTION.getColIndex(),
				ColDef.PRICE_2.getColIndex()));

	}

	public static void createEmptyRow(XSSFWorkbook xssfWorkbook, Sheet sheet, int rowIndex) throws IOException {

		Row row = sheet.createRow(rowIndex);
		row.setHeightInPoints(RowDef.EMPTY.getHeight());

		formatCell(xssfWorkbook, row, ColDef.MARGE);
		setLogo(xssfWorkbook, sheet, row, null);
		Cell cell = formatCell(xssfWorkbook, row, ColDef.DESCRIPTION);
		cell.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, ColDef.DESCRIPTION.getColIndex(),
				ColDef.PRICE_2.getColIndex()));

	}

	public static void setPriceCell(XSSFWorkbook xssfWorkbook, Row row, ColDef colDef, BigDecimal value) {
		Cell cell = formatCell(xssfWorkbook, row, colDef);
		if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
			cell.setCellValue(value.doubleValue());
			XSSFDataFormat xssfDataFormat = xssfWorkbook.createDataFormat();
			cell.getCellStyle().setDataFormat(xssfDataFormat.getFormat("#,##0.00"));
		}
	}

	public static void setLogo(XSSFWorkbook xssfWorkbook, Sheet sheet, Row row, String imagePath) throws IOException {

		formatCell(xssfWorkbook, row, ColDef.LOGO);

		if (imagePath != null && !imagePath.isEmpty() && !"NULL".equals(imagePath.toUpperCase())) {

			imagePath = "image/" + imagePath + ".png";

			final InputStream stream = XlsAfficheBodyWriterHelper.class.getClassLoader().getResourceAsStream(imagePath);
			final CreationHelper helper = xssfWorkbook.getCreationHelper();
			final Drawing<?> drawing = sheet.createDrawingPatriarch();

			final ClientAnchor anchor = helper.createClientAnchor();
			anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

			final int pictureIndex = xssfWorkbook.addPicture(IOUtils.toByteArray(stream), Workbook.PICTURE_TYPE_PNG);

			anchor.setCol1(ColDef.LOGO.getColIndex());
			anchor.setCol2(ColDef.LOGO.getColIndex());
			anchor.setRow1(row.getRowNum());
			anchor.setRow2(row.getRowNum());

			final Picture pict = drawing.createPicture(anchor, pictureIndex);
			pict.resize(1, 0.5);

		}
	}

	private static Cell formatCell(XSSFWorkbook xssfWorkbook, Row row, ColDef colDef) {
		Cell cell = row.createCell(colDef.getColIndex());
		XSSFFont xssfFont = setFont(xssfWorkbook, colDef.getFontName(), colDef.getFontHeight(), colDef.getItalicFont(),
				colDef.getFontColor());
		XSSFCellStyle xssfCellStyle = setBackground(xssfWorkbook, xssfFont, colDef.getHorizontalAlignment());
		xssfCellStyle.setWrapText(true);
		setBorder(xssfCellStyle);
		setBorder(xssfCellStyle);
		cell.setCellStyle(xssfCellStyle);
		return cell;
	}

	public static XSSFFont setFont(XSSFWorkbook xssfWorkbook, String fontName, double fontHeight, ItalicFont italicFont,
			XSSFColor xssfColor) {
		XSSFFont xssfFont = xssfWorkbook.createFont();
		xssfFont.setFontHeight(fontHeight);
		xssfFont.setFontName(fontName);
		xssfFont.setColor(xssfColor);
		xssfFont.setItalic(italicFont.istrue());
		return xssfFont;
	}

	public static XSSFCellStyle setBackground(XSSFWorkbook xssfWorkbook, XSSFFont xssfFont,
			HorizontalAlignment horizontalAlignment) {
		XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
		xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		xssfCellStyle.setAlignment(horizontalAlignment);
		xssfCellStyle.setFont(xssfFont);
		xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//		xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(252, 238, 228)));
//		xssfCellStyle.setFillBackgroundColor(new XSSFColor(new Color(252, 238, 228)));
		xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(0, 0, 0)));
		xssfCellStyle.setFillBackgroundColor(new XSSFColor(new Color(0, 0, 0)));
		return xssfCellStyle;
	}

	public static void setBorder(XSSFCellStyle xssfCellStyle) {

		xssfCellStyle.setBorderBottom(BorderStyle.NONE);
		xssfCellStyle.setBorderTop(BorderStyle.NONE);
		xssfCellStyle.setBorderRight(BorderStyle.NONE);
		xssfCellStyle.setBorderLeft(BorderStyle.NONE);
		xssfCellStyle.setBottomBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
		xssfCellStyle.setTopBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0, 0)));
		xssfCellStyle.setRightBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
		xssfCellStyle.setLeftBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));

	}

}