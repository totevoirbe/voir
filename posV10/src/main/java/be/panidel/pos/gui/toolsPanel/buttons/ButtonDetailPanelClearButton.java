package be.panidel.pos.gui.toolsPanel.buttons;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.SelectButtonPanel;

public class ButtonDetailPanelClearButton extends JButton implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SelectButtonPanel selectButtonPanel;

	private static final Logger log = Logger
			.getLogger("ButtonDetailPanelClearButton");

	public ButtonDetailPanelClearButton(SelectButtonPanel selectButtonPanel) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		// setMinimumSize(new Dimension(60, 60));
		setText("<html><center><br>Supprime l'action du bouton sélectionné<br><br></html>");
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

		// clear select list
		selectButtonPanel.getButtonDetailPanel().getProductList()
				.setSelectedIndex(-1);
		selectButtonPanel.getButtonDetailPanel().getPaymentList()
				.setSelectedIndex(-1);

		key.removeAssociation();
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
