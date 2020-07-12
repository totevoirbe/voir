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

		LABEL(2, 12, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 5000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		PRICE_1(3, 10, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 500, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.CENTER),
		PRICE_2(4, 8, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 500, ItalicFont.ITALIC_FONT,
				HorizontalAlignment.CENTER),
		PRICE_1_HEADER(3, 12, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 500,
				ItalicFont.NOT_ITALIC_FONT, HorizontalAlignment.CENTER),
		PRICE_2_HEADER(4, 10, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 500, ItalicFont.ITALIC_FONT,
				HorizontalAlignment.CENTER),

		TITLE(2, 14, "Times New Roman", new XSSFColor(new Color(127, 127, 127)), 5000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		DESCRIPTION(2, 10, "Times New Roman", new XSSFColor(new Color(0, 0, 0)), 5000, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),

		MARGE(0, 18, "Times New Roman", new XSSFColor(new Color(0, 0, 0)), 100, ItalicFont.NOT_ITALIC_FONT,
				HorizontalAlignment.LEFT),
		LOGO(1, 18, "Times New Roman", new XSSFColor(new Color(0, 0, 0)), 100, ItalicFont.NOT_ITALIC_FONT,
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

		HEADER(16, new XSSFColor(new Color(127, 127, 127))), TITLE(14, new XSSFColor(new Color(127, 127, 127))),
		DESCRIPTION(10, new XSSFColor(new Color(127, 127, 127))), PRODUCT(14, new XSSFColor(new Color(56, 87, 35))),
		EMPTY(5, new XSSFColor(new Color(127, 127, 127)));

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