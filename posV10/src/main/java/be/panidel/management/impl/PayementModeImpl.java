package be.panidel.management.impl;

import java.math.BigDecimal;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.management.Payement;
import be.panidel.tools.Tools;

public class PayementModeImpl implements Payement {

	private String id;
	private String description;
	private String code;
	private String name;
	private String htmlKeyLabel;
	private boolean needSomeValue;
	private BigDecimal maxTotalAmount;
	private BigDecimal maxQuantity;
	private boolean beAlone;

	public PayementModeImpl() {
		super();
		id = "0";
		description = new String();
		code = new String();
		name = new String();
		htmlKeyLabel = new String();
		needSomeValue = false;
		maxTotalAmount = new BigDecimal(-1);
		maxQuantity = new BigDecimal(-1);
		beAlone = false;
	}

	public PayementModeImpl(String id, String description, String code,
			String name, String htmlKeyLabel, boolean needSomeValue,
			BigDecimal maxTotalAmount, BigDecimal maxQuantity, boolean beAlone) {
		super();
		this.id = id;
		this.description = description;
		this.code = code;
		this.name = name;
		setHtmlKeyLabel(htmlKeyLabel);
		this.needSomeValue = needSomeValue;
		this.maxQuantity = maxQuantity;
		this.maxTotalAmount = maxTotalAmount;
		this.beAlone = beAlone;
	}

	public String toString() {
		CoupleMessages cm = new CoupleMessages();
		cm.put("id", id);
		cm.put("description", description);
		cm.put("code", code);
		cm.put("name", name);
		cm.put("htmlKeyLabel", htmlKeyLabel);
		cm.put("needSomeValue", needSomeValue);
		cm.put("maxQuantity", maxQuantity.toPlainString());
		cm.put("maxTotalAmount", maxTotalAmount.toPlainString());
		cm.put("beAlone", beAlone);
		return cm.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNeedSomeValue() {
		return needSomeValue;
	}
//
//	public void setNeedSomeValue(String needSomeValue) {
//		this.needSomeValue = Tools.toBoolean(needSomeValue);
//	}

	public void setNeedSomeValue(boolean needSomeValue) {
		this.needSomeValue = needSomeValue;
	}

	@Override
	public String getValueLabel() {
		return new String();
	}

	@Override
	public int compareTo(Identification id1) {
		return Tools.Compare(this.getDescription(), id1.getDescription());
	}

	@Override
	public DAO getDAOInstance() {
		return FacadeDAO.instance().getPayementModesDAO();
	}

	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	public BigDecimal getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public void setMaxQuantity(BigDecimal maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		if (htmlKeyLabel == null) {
			this.htmlKeyLabel = new String();
		} else {
			this.htmlKeyLabel = htmlKeyLabel;
		}
	}

	public boolean isBeAlone() {
		return beAlone;
	}

	public void setBeAlone(boolean beAlone) {
		this.beAlone = beAlone;
	}
}