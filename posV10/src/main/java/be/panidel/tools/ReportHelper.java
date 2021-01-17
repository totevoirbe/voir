package be.panidel.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ReportHelper {

	private static DecimalFormatSymbols numberSymbols;

	private static DecimalFormat formatItemQteInstance;
	private static DecimalFormat formatItemUnitPriceInstance;
	private static DecimalFormat formatItemTotalTVACInstance;

	private static DecimalFormat formatTotalQteInstance;
	private static DecimalFormat formatTotalUnitPriceInstance;
	private static DecimalFormat formatTotalTotalTVACInstance;

	private static DecimalFormatSymbols getNumberSymbols() {
		if (numberSymbols == null) {
			numberSymbols = new DecimalFormatSymbols(Locale.getDefault());
			numberSymbols.setDecimalSeparator(',');
			numberSymbols.setGroupingSeparator('.');
		}
		return numberSymbols;
	}

	public static String formatItemQte(BigDecimal bd) {
		if (formatItemQteInstance == null) {
			formatItemQteInstance = new DecimalFormat();
			formatItemQteInstance.setDecimalFormatSymbols(getNumberSymbols());
			formatItemQteInstance.setMinimumFractionDigits(2);
			formatItemQteInstance.setMaximumFractionDigits(2);
			formatItemQteInstance.setMinimumIntegerDigits(1);
			formatItemQteInstance.setMaximumIntegerDigits(15);
			formatItemQteInstance.setGroupingSize(30);
		}
		return formatItemQteInstance.format(bd);
	}

	public static String formatItemUnitPrice(BigDecimal bd) {
		if (formatItemUnitPriceInstance == null) {
			formatItemUnitPriceInstance = new DecimalFormat();
			formatItemUnitPriceInstance
					.setDecimalFormatSymbols(getNumberSymbols());
			formatItemUnitPriceInstance.setMinimumFractionDigits(2);
			formatItemUnitPriceInstance.setMaximumFractionDigits(2);
			formatItemUnitPriceInstance.setMinimumIntegerDigits(1);
			formatItemUnitPriceInstance.setMaximumIntegerDigits(15);
			formatItemUnitPriceInstance.setGroupingSize(30);
		}
		return formatItemUnitPriceInstance.format(bd);
	}

	public static String formatItemTotalTVAC(BigDecimal bd) {
		if (formatItemTotalTVACInstance == null) {
			formatItemTotalTVACInstance = new DecimalFormat();
			formatItemTotalTVACInstance
					.setDecimalFormatSymbols(getNumberSymbols());
			formatItemTotalTVACInstance.setMinimumFractionDigits(2);
			formatItemTotalTVACInstance.setMaximumFractionDigits(2);
			formatItemTotalTVACInstance.setMinimumIntegerDigits(1);
			formatItemTotalTVACInstance.setMaximumIntegerDigits(15);
			formatItemTotalTVACInstance.setGroupingSize(30);
		}
		return formatItemTotalTVACInstance.format(bd);
	}

	public static String formatTotalQte(BigDecimal bd) {
		if (formatTotalQteInstance == null) {
			formatTotalQteInstance = new DecimalFormat();
			formatTotalQteInstance.setDecimalFormatSymbols(getNumberSymbols());
			formatTotalQteInstance.setMinimumFractionDigits(2);
			formatTotalQteInstance.setMaximumFractionDigits(2);
			formatTotalQteInstance.setMinimumIntegerDigits(1);
			formatTotalQteInstance.setMaximumIntegerDigits(15);
			formatTotalQteInstance.setGroupingSize(30);
		}
		return formatTotalQteInstance.format(bd);
	}

	public static String formatTotalUnitPrice(BigDecimal bd) {
		if (formatTotalUnitPriceInstance == null) {
			formatTotalUnitPriceInstance = new DecimalFormat();
			formatTotalUnitPriceInstance
					.setDecimalFormatSymbols(getNumberSymbols());
			formatTotalUnitPriceInstance.setMinimumFractionDigits(2);
			formatTotalUnitPriceInstance.setMaximumFractionDigits(2);
			formatTotalUnitPriceInstance.setMinimumIntegerDigits(1);
			formatTotalUnitPriceInstance.setMaximumIntegerDigits(15);
			formatTotalUnitPriceInstance.setGroupingSize(30);
		}
		return formatTotalUnitPriceInstance.format(bd);
	}

	public static String formatTotalTotalTVAC(BigDecimal bd) {
		if (formatTotalTotalTVACInstance == null) {
			formatTotalTotalTVACInstance = new DecimalFormat();
			formatTotalTotalTVACInstance
					.setDecimalFormatSymbols(getNumberSymbols());
			formatTotalTotalTVACInstance.setMinimumFractionDigits(2);
			formatTotalTotalTVACInstance.setMaximumFractionDigits(2);
			formatTotalTotalTVACInstance.setMinimumIntegerDigits(1);
			formatTotalTotalTVACInstance.setMaximumIntegerDigits(15);
			formatTotalTotalTVACInstance.setGroupingSize(30);
		}
		return formatTotalTotalTVACInstance.format(bd);
	}
}
