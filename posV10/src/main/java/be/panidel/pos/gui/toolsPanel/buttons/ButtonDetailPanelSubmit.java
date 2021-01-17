package be.panidel.pos.gui.toolsPanel.buttons;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Product;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.SelectButtonPanel;

public class ButtonDetailPanelSubmit extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger("ButtonDetailPanelSubmit");

	private SelectButtonPanel selectButtonPanel;

	public ButtonDetailPanelSubmit(SelectButtonPanel selectButtonPanel) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setMinimumSize(new Dimension(60, 60));
		setText("<html><center><br><br>Modifie l'action de la touche<br>comme ci-dessus<br><br><br></html>");
		this.selectButtonPanel = selectButtonPanel;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		selectButtonPanel.getButtonDetailPanel().setNewValues();

		// Always update product before key, perhaps product is another one !
		Identification identification = selectButtonPanel
				.getButtonDetailPanel().getIdentification();
		if (identification != null) {
			KeyBean key;
			try {
				identification.getDAOInstance().updateElement(identification);
				identification.getDAOInstance().saveTable();
				key = selectButtonPanel.getButtonDetailPanel().getKeybean();
				key.getDAOInstance().updateElement(key);
				key.getDAOInstance().saveTable();
				if (identification instanceof Product) {
					Product product = (Product) identification;
					Identification group = product.getGroupAsGroup();
					if (group != null) {
						group.getDAOInstance().updateElement(group);
						group.getDAOInstance().saveTable();
					}
				}
				selectButtonPanel.refreshButtons();
				selectButtonPanel.getButtonDetailPanel().refresh(key);
				selectButtonPanel.updateUI();
				AbstractCashRegister parentCashRegister = selectButtonPanel
						.getParentCashRegister();
				parentCashRegister.getConfigurablePanelButton().refreshButtons(
						parentCashRegister.getSelectedKeyboard(), true);
				parentCashRegister.updateUI();
			} catch (DAOException exp) {
				log.error(identification.getId(), exp);
			}
		}
	}
}
