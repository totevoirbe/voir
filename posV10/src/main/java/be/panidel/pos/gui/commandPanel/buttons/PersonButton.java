package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Person;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.commandPanel.AbstractJToggleButton;
import be.panidel.pos.gui.reports.CommandPanel;
import be.panidel.tools.Tools;

public class PersonButton extends AbstractJToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8401080052111145326L;

	private static final Logger log = Logger.getLogger("PersonButton");

	private Person person;

	protected AbstractCashRegister cashRegister;

	public PersonButton(Person person, AbstractCashRegister cashRegister) {
		super((Tools.isNullOrEmpty(person.getHtmlKeyLabel()) ? person.getName()
				: person.getHtmlKeyLabel()));
		this.person = person;
		this.cashRegister = cashRegister;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("action" + person.getId());
		CashRegister cr = (CashRegister) cashRegister;
		String inputValue = cr.getCashregisterModel().getInputAsString();
		if ("10".equals(person.getId()) && !Tools.isNullOrEmpty(inputValue)
				&& "2222".equals(inputValue.trim())) {
			try {
				CommandPanel.instance().show();
			} catch (DAOException e1) {
				log.error("", e1);
			}
		} else {
			if (CommandPanel.hasInstance()) {
				try {
					CommandPanel.instance().hide();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		}
		cr.getCashRegisterController().selectPerson(person);
		cr.getCashRegisterController().clearInput();
	}
}