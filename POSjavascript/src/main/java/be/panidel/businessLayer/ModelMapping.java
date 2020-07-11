package be.panidel.businessLayer;

import java.math.BigDecimal;

import be.panidel.dataLayer.model.ProductModel;
import be.panidel.frontLayer.model.ProductJsonModel;

public class ModelMapping {

	public static ProductJsonModel map(ProductModel product) {

		Long id = product.getId();
		String label = product.getLabel();
		String ticketLabel = product.getTicketLabel();
		String code = product.getCode();
		String name = product.getName();
		String htmlKeyLabel = product.getHtmlKeyLabel();
		String type = product.getType();
		String image = product.getImage();
		BigDecimal vatRateOnPlace = product.getVatRateOnPlace().getRate();
		BigDecimal vatRateTakeAway = product.getVatRateTakeAway().getRate();
		BigDecimal mini = product.getMini();
		BigDecimal normal = product.getNormal();
		BigDecimal geant = product.getGeant();
		BigDecimal fitmini = product.getFitmini();
		BigDecimal fitnormal = product.getFitnormal();
		BigDecimal fitgeant = product.getFitgeant();
		String webDetail = product.getWebDetail();
		String afficheDetail = product.getAfficheDetail();
		Boolean canMerge = product.getCanMerge();

		return new ProductJsonModel(id, label, ticketLabel, code, name, htmlKeyLabel, type, image, vatRateOnPlace,
				vatRateTakeAway, mini, normal, geant, fitmini, fitnormal, fitgeant, webDetail, afficheDetail, canMerge);

	}

}