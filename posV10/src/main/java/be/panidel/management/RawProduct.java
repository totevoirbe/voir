package be.panidel.management;

import java.math.BigDecimal;
import be.panidel.common.Identification;

public interface RawProduct extends Identification {

	BigDecimal getPrixAchat();

	void setPrixAchat(BigDecimal prixAchat);

}