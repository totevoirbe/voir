package be.panidel.tarif.xlsWriter;

import java.awt.Color;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFColor;

public interface XslAfficheBodyA3 {

	public enum ItalicFont {

		ITALIC_FONT(true), NOT_ITALIC_FONT(false);

		private boolean value;

		ItalicFont(boolean value) {
			this.value = value;
		}

		public boolean istrue() {
			return value;
		}
	};

	enum ColDef {

		LABEL(2, 20, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 10000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		PRICE_1(3, 16, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 1000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.CENTER),
		PRICE_2(4, 14, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 1000, ItalicFont.ITALIC_FONT,
				HorizontalAlignment.CENTER),
		PRICE_1_HEADER(3, 16, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 1000,
				ItalicFont.NOT_ITALIC_FONT, HorizontalAlignment.CENTER),
		PRICE_2_HEADER(4, 14, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 1000, ItalicFont.ITALIC_FONT,
				HorizontalAlignment.CENTER),

		TITLE(2, 20, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 10000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		DESCRIPTION(2, 14, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 10000,
				ItalicFont.NOT_ITALIC_FONT, HorizontalAlignment.LEFT),

		MARGE(0, 20, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 100, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		LOGO(1, 20, "Times New Roman", new XSSFColor(new Color(255, 255, 255)), 100, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT);

		private int colIndex;
		private double fontHeight;
		private String fontName;
		private XSSFColor fontColor;
		private int columnWidth;
		private ItalicFont italicFont;
		private HorizontalAlignment horizontalAlignment;

		private ColDef(int colIndex, double fontHeight, String fontName, XSSFColor fontColor, int columnWidth,
				ItalicFont italicFont, HorizontalAlignment horizontalAlignment) {
			this.colIndex = colIndex;
			this.fontHeight = fontHeight;
			this.fontName = fontName;
			this.fontColor = fontColor;
			this.columnWidth = columnWidth;
			this.italicFont = italicFont;
			this.horizontalAlignment = horizontalAlignment;
		}

		public int getColIndex() {
			return colIndex;
		}

		public double getFontHeight() {
			return fontHeight;
		}

		public String getFontName() {
			return fontName;
		}

		public XSSFColor getFontColor() {
			return fontColor;
		}

		public int getColumnWidth() {
			return columnWidth;
		}

		public ItalicFont getItalicFont() {
			return italicFont;
		}

		public HorizontalAlignment getHorizontalAlignment() {
			return horizontalAlignment;
		}
	};

	enum RowDef {

		HEADER(16, new XSSFColor(new Color(255, 255, 255))), TITLE(14, new XSSFColor(new Color(255, 255, 255))),
		DESCRIPTION(10, new XSSFColor(new Color(255, 255, 255))), PRODUCT(14, new XSSFColor(new Color(255, 255, 255))),
		EMPTY(5, new XSSFColor(new Color(255, 255, 255)));

		private float height;
		private XSSFColor color;

		private RowDef(float height, XSSFColor color) {
			this.height = height;
			this.color = color;
		}

		public float getHeight() {
			return height;
		}

		public XSSFColor getColor() {
			return color;
		}
	}

}