package be.panidel.dao;

import java.math.BigDecimal;

import org.w3c.dom.Element;

import be.panidel.common.CoupleMessages;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.Payement;
import be.panidel.management.impl.PayementItemImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.Tools;
import be.panidel.tools.XMLHelper;

public class PayementItemDAO implements ItemDAO {

	public final static String TAG_SALES_PAY = "payement";
	public final static String TAG_SALES_PAY_QUANTITY = "quantity";
	public final static String TAG_SALES_PAY_MODE = "mode";
	public final static String TAG_SALES_PAY_VALUE = "value";
	public final static String TAG_SALES_PAY_DESCRIPTION = "description";

	PayementItemDAO() {
		super();
	}

	public PayementItemImpl extract(Element itemNode) throws DAOException {

		PayementItemImpl item = null;
		BigDecimal quantity = null;
		String description = null;
		String PayementModeImplId = null;
		BigDecimal payValue = null;
		try {
			quantity = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_PAY_QUANTITY, itemNode);

			description = DOMUtils.getElementValueByTagName(
					TAG_SALES_PAY_DESCRIPTION, itemNode);

			PayementModeImplId = DOMUtils.getElementValueByTagName(
					TAG_SALES_PAY_MODE, itemNode);
			Payement payementMode = FacadeDAO.instance().getPayementModesDAO()
					.getById(PayementModeImplId);

			payValue = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_PAY_VALUE, itemNode);

			if (Tools.isNullOrEmpty(description) && payementMode != null) {
				description = payementMode.getDescription();
			}

			item = new PayementItemImpl(payementMode, quantity,
					BigDecimal.ZERO, BigDecimal.ZERO, payValue, description);
		} catch (ParameterException e) {

			CoupleMessages cm = new CoupleMessages();
			cm.put("PayementModeImplId", PayementModeImplId);
			cm.put("quantity", quantity);
			cm.put("description", description);
			cm.put("payValue", payValue);

			throw new DAOException(cm.toString(), e);

		}

		return item;
	}

	public void append(Item item, StringBuffer xml) throws DAOException {

		XMLHelper.openTag(TAG_SALES_PAY, xml);
		XMLHelper.addTag(item.getQuantity().multiply(new BigDecimal(100))
				.toPlainString(), TAG_SALES_PAY_QUANTITY, xml);
		XMLHelper.addTag(item.getDescription(), TAG_SALES_PAY_DESCRIPTION, xml);
		if (item.getItem() != null) {
			XMLHelper.addTag(item.getItem().getId(), TAG_SALES_PAY_MODE, xml);

		} else {
			throw new DAOException(item.getDescription() + " item is null");
		}

		XMLHelper.addTag(item.getUnitPrice().multiply(new BigDecimal(100))
				.toPlainString(), TAG_SALES_PAY_VALUE, xml);
		XMLHelper.closeTag(TAG_SALES_PAY, xml);
	}
}