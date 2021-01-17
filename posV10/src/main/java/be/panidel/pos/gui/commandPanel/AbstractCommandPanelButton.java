package be.panidel.pos.gui.commandPanel;

import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

import org.apache.log4j.Logger;

import be.panidel.dao.FacadeDAO;
import be.panidel.dao.PayementModesDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Payement;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.buttons.KeyboardButton;
import be.panidel.pos.gui.common.ButtonAction;
import be.panidel.pos.gui.common.ButtonTools;
import be.panidel.pos.gui.configurablePanel.ConfigurableButton;
import be.panidel.pos.gui.toolsPanel.ButtonPayementAction;

public class AbstractCommandPanelButton extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4117517681098491494L;
	private static final Logger log = Logger
			.getLogger("AbstractCommandPanelButton");

	protected ButtonGroup keyboardButtonGroup;

	public AbstractCommandPanelButton(String text) {

		super();

		setBorder(BorderFactory.createTitledBorder(text));

		// Add several labels to the layered pane.
		setLayout(new GridLayout(12, 1));
	}

	public void selectButton(int keyboardId) {
		Enumeration<AbstractButton> buttons = keyboardButtonGroup.getElements();
		while (buttons.hasMoreElements()) {
			AbstractButton button = (AbstractButton) buttons.nextElement();
			if (keyboardId == ((KeyboardButton) button).getKeyBoardID()) {
				keyboardButtonGroup.setSelected(((JToggleButton) button)
						.getModel(), true);
			}
		}
	}

	protected JButton addPayButton(AbstractCashRegister cashRegister, String id) {
		PayementModesDAO payementModesDAO = FacadeDAO.instance()
				.getPayementModesDAO();
		Payement payement = null;
		JButton jButton = null;
		try {
			payement = payementModesDAO.getById(id);
			ButtonAction buttonAction = new ButtonPayementAction(cashRegister);
			buttonAction.setContent(payement);
			jButton = new ConfigurableButton(payement, buttonAction);
			ButtonTools.drawCommandButton(jButton, null);
			add(jButton);
		} catch (DAOException e) {
			log.error("Erreur lecture table employés", e);
		}
		return jButton;
	}
}
