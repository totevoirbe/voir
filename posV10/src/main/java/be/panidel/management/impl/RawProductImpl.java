package be.panidel.management.impl;

import java.math.BigDecimal;
import be.panidel.common.CoupleMessages;
import be.panidel.common.impl.IdentificationImpl;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.management.RawProduct;

public class RawProductImpl extends IdentificationImpl implements RawProduct {

	private BigDecimal prixAchat;

	public RawProductImpl() {
		super();
		this.prixAchat = BigDecimal.ZERO;
	}

	public RawProductImpl(String id, String code, String name,
			String description, String htmlKeyLabel, BigDecimal prixAchat) {
		super(id, code, name, description, htmlKeyLabel);
		this.prixAchat = prixAchat;
	}

	@Override
	public String toString() {
		CoupleMessages cm = new CoupleMessages();
		cm.setPrefixString(super.toString());
		cm.put("prixAchat", prixAchat);
		return cm.toString();
	}

	@Override
	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	@Override
	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	@Override
	public DAO getDAOInstance() {
		return FacadeDAO.instance().getProductsDAO();
	}

	@Override
	public String getValueLabel() {
		return prixAchat.setScale(2).toPlainString();
	}

}