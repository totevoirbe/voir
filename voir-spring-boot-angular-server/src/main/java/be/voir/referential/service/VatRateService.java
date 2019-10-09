package be.voir.referential.service;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.VatRate;

@Validated
public interface VatRateService {

	public enum VatRateEnum {

		VATONPLACENORMAL("TVA sur place", new BigDecimal("12")),
		VATONPLACEBOISSONS("TVA boissons sur place", new BigDecimal("21")),
		VATTAKEAWAYNORMAL("TVA emporté", new BigDecimal("6")),
		VATTAKEAWAYLUX("TVA Alcool emporté", new BigDecimal("12"));

		private final String label;
		private final BigDecimal rate;

		private VatRateEnum(String label, BigDecimal rate) {
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

	@NotNull
	Iterable<VatRate> getAll();

	VatRate get(@Min(value = 1L, message = "ID code TVA invalide.") long id);

	VatRate getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code);

	VatRate save(VatRate product);

}
