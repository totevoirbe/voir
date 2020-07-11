package be.panidel.businessLayer.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EnumHelper {

	public enum PeriodType {

		YEAR(365L * 30L * 24L * 60L * 60L * 1000L, "yyyy"), MONTH(30L * 24L * 60L * 60L * 1000L, "yyyyMM"), DAY(
				24L * 60L * 60L * 1000L,
				"yyyy_MM_dd"), HOUR(60L * 60L * 1000L, "yyyyMMddHH"), CASH_SALE_UNIT(1L, "yyyyMMddHHmm");

		private long milliseconds;
		private DateFormat dateFormat;

		PeriodType(long milliseconds, String datePatern) {
			this.milliseconds = milliseconds;
			this.dateFormat = new SimpleDateFormat(datePatern);
		}

		public long getMilliseconds() {
			return milliseconds;
		}

		public DateFormat getDateFormat() {
			return dateFormat;
		}

	};

	public enum CashSaleStatus {

		OPEN("open"), CANCEL("cancel"), DONE("done");

		private String state;

		CashSaleStatus(String state) {
			this.state = state;
		}

		public String getState() {
			return state;
		}
	}

	public enum PaymentMethodComputation {

		FREE("free"), ADD("add"), LOST("lost"), TRASH("trash");

		private String paymentMethodComputation;

		private PaymentMethodComputation(String paymentMethodComputation) {
			this.paymentMethodComputation = paymentMethodComputation;
		}

		public String getPaymentMethodComputation() {
			return paymentMethodComputation;
		}

	}

	public enum PriceCategory {

		SDWMINI("sdwmini"), SDWNORMAL("sdwnormal"), SDWGEANT("sdwgeant"), FITMINI("fitmini"), FITNORMAL(
				"fitnormal"), FITGEANT("fitgeant");

		private String type;

		PriceCategory(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

	}
}
