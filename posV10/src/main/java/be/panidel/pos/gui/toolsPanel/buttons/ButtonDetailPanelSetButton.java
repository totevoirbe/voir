package be.panidel.pos.gui.toolsPanel.buttons;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.impl.IdentificationList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.SelectButtonPanel;

public class ButtonDetailPanelSetButton extends JButton implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger("ButtonDetailPanelSetButton");

	private SelectButtonPanel selectButtonPanel;

	public ButtonDetailPanelSetButton(SelectButtonPanel selectButtonPanel) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setMinimumSize(new Dimension(60, 60));
		setText("<html><center><br>Positionne l'action sélectionnée ci-dessus<br>sur le bouton sélectionné<br><br></html>");
		this.selectButtonPanel = selectButtonPanel;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		KeyBean key = selectButtonPanel.getButtonDetailPanel().getKeybean();
		if (key == null) {
			selectButtonPanel.getButtonDetailPanel().writeMessage(
					"Sélectionner une touche");
			return;
		}

		// Always update product before key, perhaps product is another one !
		IdentificationList identificationList = null;
		Identification identification = null;
		// set product
		identificationList = (IdentificationList) selectButtonPanel
				.getButtonDetailPanel().getProductList().getModel();
		identification = identificationList.getSelectedIdentification();
		// if not a product, set payment
		if (identification == null) {
			identificationList = (IdentificationList) selectButtonPanel
					.getButtonDetailPanel().getPaymentList().getModel();
			identification = identificationList.getSelectedIdentification();
		}
		// a payment or a product is selected.
		if (identification != null) {
			// clear select list
			selectButtonPanel.getButtonDetailPanel().getProductList()
					.setSelectedIndex(-1);
			selectButtonPanel.getButtonDetailPanel().getPaymentList()
					.setSelectedIndex(-1);

			key.setKeyCode("");
			key.setOperationCode(identification.getId());
			key.setOperationType(identificationList.getType());
			try {
				key.getDAOInstance().updateElement(key);
				key.getDAOInstance().saveTable();
			} catch (DAOException exp) {
				log.error("Key" + key.getId(), exp);
			}
			selectButtonPanel.getButtonDetailPanel().refresh(key);
			selectButtonPanel.refreshButtons();
			selectButtonPanel.updateUI();
			AbstractCashRegister parentCashRegister = selectButtonPanel
					.getParentCashRegister();
			parentCashRegister.getConfigurablePanelButton().refreshButtons(
					parentCashRegister.getSelectedKeyboard(), true);
			parentCashRegister.updateUI();
		}
	}
}
