package be.voir.dataLayer;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CodeTVADAO {

	Map<String, CodeTVA> codeTVAs;

	public enum CodeTVAEnum {

		VATONPLACENORMAL("TVA sur place", new BigDecimal("12")),
		VATONPLACEBOISSONS("TVA boissons sur place", new BigDecimal("21")),
		VATTAKEAWAYNORMAL("TVA emporté", new BigDecimal("6")),
		VATTAKEAWAYLUX("TVA Alcool emporté", new BigDecimal("12"));

		private final String label;
		private final BigDecimal rate;

		private CodeTVAEnum(String label, BigDecimal rate) {
			this.label = label;
			this.rate = rate;
		}

		public String getLabel() {
			return label;
		}

		public BigDecimal getRate() {
			return rate;
		}

	};

	protected void mock() {

		codeTVAs = new HashMap<String, CodeTVA>();

		for (CodeTVAEnum codeTVAEnum : CodeTVAEnum.values()) {

			String code = codeTVAEnum.name().trim().toUpperCase();
			String label = codeTVAEnum.getLabel();
			BigDecimal rate = codeTVAEnum.getRate();
			CodeTVA codeTVA = new CodeTVA(code, label, rate);
			codeTVAs.put(code, codeTVA);

		}

	}

	public CodeTVADAO() {

		mock();

	}

	public CodeTVA get(String code) {
		if (code != null && code.trim().length() > 0) {
			code = code.trim().toUpperCase();
			return codeTVAs.get(code);
		} else {
			return null;
		}
	}

	public Collection<CodeTVA> getCodeTVAs() {
		return codeTVAs.values();
	}

}
