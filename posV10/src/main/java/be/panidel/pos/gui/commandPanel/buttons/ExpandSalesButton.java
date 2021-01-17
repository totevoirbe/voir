package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;
import be.panidel.pos.gui.commandPanel.ExpandSalesPanel;
import be.panidel.pos.gui.reports.Panel;

public class ExpandSalesButton extends AbstractButton {

	private Panel expandSalesPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6862546244366377979L;
	private static final Logger log = Logger.getLogger("ExpandSalesButton");

	public ExpandSalesButton(AbstractCashRegister cr) {
		super("<html><br>Detail<br><br></html>");
		ExpandSalesPanel esp = new ExpandSalesPanel(cr);
		expandSalesPanel = new Panel(esp, "Détail");
		esp.setThisAsPanel(expandSalesPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			expandSalesPanel.show();
		} catch (DAOException e1) {
			log.error("", e1);
		}
	}
}