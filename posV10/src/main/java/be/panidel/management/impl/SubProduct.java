package be.panidel.management.impl;

import java.math.BigDecimal;

import be.panidel.common.CoupleMessages;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.RawProduct;

public class SubProduct {

	private String rawProductId;
	private RawProduct rawProduct;
	private BigDecimal qty;

	public SubProduct(RawProduct rawProduct) {
		this.rawProduct = rawProduct;
	}

	public SubProduct(String rawProductId, BigDecimal qty) {
		this.rawProductId = rawProductId;
		this.qty = qty;
	}

	public String toString() {

		CoupleMessages cm = new CoupleMessages();
		cm.put("rawProduct", rawProduct);
		cm.put("qty", qty);
		return cm.toString();
	}

	public RawProduct getRawProduct() throws DAOException {
		if (rawProduct == null) {
			rawProduct = FacadeDAO.instance().getRawProductsDAO()
					.getById(rawProductId);
		}
		return rawProduct;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getRawProductId() {
		return rawProductId;
	}
}