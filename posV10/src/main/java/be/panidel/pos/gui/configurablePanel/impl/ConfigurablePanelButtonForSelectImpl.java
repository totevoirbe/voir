package be.panidel.pos.gui.configurablePanel.impl;

import java.awt.Color;

import javax.swing.BorderFactory;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.KeyboardLayoutDAO;
import be.panidel.dao.PayementModesDAO;
import be.panidel.dao.ProductsDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.NothingImpl;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.common.Clignote;
import be.panidel.pos.gui.configurablePanel.ConfigurableButton;
import be.panidel.pos.gui.configurablePanel.ConfigurablePanelButton;
import be.panidel.pos.gui.configurablePanel.KbArray;
import be.panidel.pos.gui.toolsPanel.ButtonConfigPanelAction;
import be.panidel.tools.Tools;

public class ConfigurablePanelButtonForSelectImpl extends
		ConfigurablePanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2235349044760483279L;

	private static final Logger log = Logger
			.getLogger("ConfigurablePanelButtonImpl");

	private SelectButtonPanel selectButtonPanel;
	private Clignote flashSelectedButton;
	private Integer selectedButtonPositionOnKeyboard;

	public ConfigurablePanelButtonForSelectImpl(int[] defaultsKeyboards,
			SelectButtonPanel selectButtonPanel) {

		super(defaultsKeyboards);

		this.selectButtonPanel = selectButtonPanel;

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

		ButtonConfigPanelAction buttonAction = null;
		KbArray kbArray = new KbArray();

		KeyboardLayoutDAO keyboardLayoutDAO = FacadeDAO.instance()
				.getKeyboardLayoutDAO();
		ProductsDAO productsDAO = FacadeDAO.instance().getProductsDAO();
		PayementModesDAO payementModesDAO = FacadeDAO.instance()
				.getPayementModesDAO();
		for (int vIndex = 0; vIndex < VKEYBOARDSIZE; vIndex++) {
			for (int hIndex = 0; hIndex < HKEYBOARDSIZE; hIndex++) {
				Integer id = keyboardID * 10000 + (vIndex + 1) * 100 + hIndex;
				ConfigurableButton button = null;
				try {
					KeyBean keyBean = null;
					keyBean = keyboardLayoutDAO.getById(Integer.toString(id));
					if (keyBean == null
							|| Tools.isNullOrEmpty(keyBean.getOperationCode())) {
						keyBean = new KeyBean(Integer.toString(id));
					}
					Identification content = null;
					if (POSConstants.ADD_PRODUCT.equals(keyBean
							.getOperationType())) {
						content = productsDAO.getById(keyBean
								.getOperationCode());
					} else if (POSConstants.ADD_PAYE.equals(keyBean
							.getOperationType())) {
						content = payementModesDAO.getById(keyBean
								.getOperationCode());
					}
					if (content == null) {
						content = new NothingImpl();
						// String CANCEL = "-1";
						// content = new ProductImpl(CANCEL, "CANCEL",
						// "Cancelled", "Cancelled", "Cancelled", new
						// BigDecimal(0), new BigDecimal(0), null);
					}
					buttonAction = new ButtonConfigPanelAction(
							selectButtonPanel);
					buttonAction.setContent(content);
					buttonAction.setKeybean(keyBean);
					button = new ConfigurableButton(content, buttonAction);
					buttonAction.setAssociatedButton(button);
					if (id.equals(selectedButtonPositionOnKeyboard)) {
						setSelectedButton(button, id);
					}
				} catch (DAOException e) {
					log.error("Key[" + vIndex + "][" + hIndex + "]", e);
				}
				kbArray.set(hIndex, vIndex, button);
			}
		}
		log.debug("END initButtonMap - " + keyboardID);
		return kbArray;
	}

	public void setSelectedButton(ConfigurableButton selectedButton,
			Integer buttonPositionOnKeyboard) {

		selectedButtonPositionOnKeyboard = buttonPositionOnKeyboard;

		if (flashSelectedButton != null) {
			flashSelectedButton.stop();
			flashSelectedButton = null;
		}
		flashSelectedButton = new Clignote(selectedButton);
		flashSelectedButton.start(Color.LIGHT_GRAY);
	}
}