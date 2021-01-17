package be.panidel.pos.gui.configurablePanel.impl;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.KeyboardLayoutDAO;
import be.panidel.dao.PayementModesDAO;
import be.panidel.dao.ProductsDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.common.ButtonAction;
import be.panidel.pos.gui.configurablePanel.ConfigurableButton;
import be.panidel.pos.gui.configurablePanel.ConfigurablePanelButton;
import be.panidel.pos.gui.configurablePanel.KbArray;
import be.panidel.pos.gui.toolsPanel.ButtonPayementAction;
import be.panidel.pos.gui.toolsPanel.ButtonProductAction;
import be.panidel.tools.Tools;

public class ConfigurablePanelButtonImpl extends ConfigurablePanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2235349044760483279L;

	private static final Logger log = Logger
			.getLogger("ConfigurablePanelButtonImpl");

	private AbstractCashRegister cashRegister;

	public ConfigurablePanelButtonImpl(AbstractCashRegister cashRegister,
			int[] defaultsKeyboards) {

		super(defaultsKeyboards);

		if (cashRegister == null) {
			log.error("cashRegister is null");
			return;
		}

		this.cashRegister = cashRegister;

		// init all keyboards
		for (int i = 0; i <= defaultsKeyboards.length; i++) {
			KbArray kbArray = initButtonMap(i);
			kbArrays.put(i, kbArray);
		}

		setBorder(BorderFactory.createTitledBorder("Produits"));

		log.debug("End ConfigurablePanelButton constructor");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.panidel.pos.gui.configurablePanel.ConfigurablePanelButton#initButtonMap
	 * (int)
	 */
	public KbArray initButtonMap(int keyboardID) {

		log.debug("START initButtonMap - " + keyboardID);

		if (cashRegister == null) {
			log.error("cashRegister is null");
			return null;
		}

		ButtonAction buttonAction = null;
		KbArray kbArray = new KbArray();

		KeyboardLayoutDAO keyboardLayoutDAO = FacadeDAO.instance()
				.getKeyboardLayoutDAO();
		ProductsDAO productsDAO = FacadeDAO.instance().getProductsDAO();
		PayementModesDAO payementModesDAO = FacadeDAO.instance()
				.getPayementModesDAO();
		for (int vIndex = 0; vIndex < VKEYBOARDSIZE; vIndex++) {
			for (int hIndex = 0; hIndex < HKEYBOARDSIZE; hIndex++) {
				int id = keyboardID * 10000 + (vIndex + 1) * 100 + hIndex;
				JButton button = null;
				try {
					KeyBean keyBean = null;
					keyBean = keyboardLayoutDAO.getById(Integer.toString(id));
					if (keyBean != null
							&& !Tools.isNullOrEmpty(keyBean.getOperationCode())) {
						Identification content = null;
						if (POSConstants.ADD_PRODUCT.equals(keyBean
								.getOperationType())) {
							content = productsDAO.getById(keyBean
									.getOperationCode());
							buttonAction = new ButtonProductAction(cashRegister);
						} else if (POSConstants.ADD_PAYE.equals(keyBean
								.getOperationType())) {
							content = payementModesDAO.getById(keyBean
									.getOperationCode());
							buttonAction = new ButtonPayementAction(
									cashRegister);
						}
						buttonAction.setContent(content);
						buttonAction.setKeybean(keyBean);
						button = new ConfigurableButton(content, buttonAction);
					}
				} catch (DAOException e) {
					log.error("Key[" + vIndex + "][" + hIndex + "]", e);
				}

				if (button == null) {
					button = new JButton();
					button.setEnabled(false);
				}
				kbArray.set(hIndex, vIndex, button);
			}
		}
		log.debug("END initButtonMap - " + keyboardID);
		return kbArray;
	}
}
