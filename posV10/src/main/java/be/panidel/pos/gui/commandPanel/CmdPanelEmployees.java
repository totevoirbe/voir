package be.panidel.pos.gui.commandPanel;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import org.apache.log4j.Logger;

import be.panidel.common.PeriodBean;
import be.panidel.dao.EmployeesDAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Person;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.buttons.CancelButton;
import be.panidel.pos.gui.commandPanel.buttons.HiddenButton;
import be.panidel.pos.gui.commandPanel.buttons.PersonButton;
import be.panidel.pos.gui.reports.ReportDayDetailForCheck;

public class CmdPanelEmployees extends AbstractCommandPanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -540383548393077965L;

	private static final Logger log = Logger.getLogger("CmdPanelEmployees");

	private ButtonGroup personButtonGroup;

	public CmdPanelEmployees(AbstractCashRegister cashRegister) {

		super(" ");

		EmployeesDAO employeesDAO = FacadeDAO.instance().getEmployeesDAO();

		try {
			personButtonGroup = new ButtonGroup();
			Person person = null;
			JToggleButton jToggleButton = null;
			String[] employees = employeesDAO.listOfId();
			for (int i = 0; i < employees.length; i++) {
				person = employeesDAO.getById(employees[i]);
				jToggleButton = new PersonButton(person, cashRegister);
				personButtonGroup.add(jToggleButton);
				add(jToggleButton);
			}

			// add("new SplitUpButton(<html>Multiples<br>Payements</html>",cashRegister);
			for (int i = 0; i < 5 - employees.length; i++) {
				add(new HiddenButton());
			}
			add(new CheckSalesButton(cashRegister,
					"<html><center> <br>Ventes précédents<br><br> </html>"));

			add(new close(cashRegister, "<html>Fermer<br>caisse</html>"));

			JButton jButton = addPayButton(cashRegister, "10");
			jButton.setBackground(null);
			jButton = addPayButton(cashRegister, "20");
			jButton = addPayButton(cashRegister, "30");
			jButton = addPayButton(cashRegister, "70");
			add(new CancelButton("<html>Annule<br>vente</html>", cashRegister));
		} catch (DAOException e) {
			log.error("Erreur lecture table employés", e);

		}
	}

}

class CheckSalesButton extends AbstractButton {

	private static final long serialVersionUID = 344178102205979571L;
	private static final Logger log = Logger.getLogger("checkSalesButton");
	private AbstractCashRegister cashRegister;

	public CheckSalesButton(AbstractCashRegister cashRegister, String text) {
		super(text);
		this.cashRegister = cashRegister;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Date toDay = new Date();
			ReportDayDetailForCheck.instance(cashRegister).show(
					new PeriodBean(toDay, toDay));
		} catch (DAOException e1) {
			log.error("", e1);
		}
	}
}

class close extends AbstractButton {

	private static final long serialVersionUID = 1L;
	private AbstractCashRegister cashRegister;

	public close(AbstractCashRegister cashRegister, String text) {
		super(text);
		this.cashRegister = cashRegister;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cashRegister.getCashRegisterController().close();
	}

}
