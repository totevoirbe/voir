package be.panidel.pos.gui.reports;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.OperationUnit;
import be.panidel.management.Person;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;

public class ReportDayDetailForCheck extends ReportDayDetailForCashBook {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractCashRegister cashregister;

	public ReportDayDetailForCheck(AbstractCashRegister cashregister) {
		super();
		this.cashregister = cashregister;
	}

	@Override
	public void initSpecificComponents() throws DAOException {
		super.initSpecificComponents();
	}

	protected boolean isEligible(OperationUnit operationUnit) {
		Person employee = cashregister.getCashregisterModel().getPersonnel();

		if (employee == null) {
			return false;
		}

		String computerName = operationUnit.getComputerName();
		String employeId = operationUnit.getEmployee().getId();

		return CashRegister.COMPUTERNAME.equals(computerName)
				&& employee.getId().equals(employeId);

	}

	private static Panel instance;

	public static boolean hasInstance() {
		return instance != null;
	}

	public static Panel instance(AbstractCashRegister cashRegister) {

		if (instance == null) {
			instance = new Panel(new ReportDayDetailForCheck(cashRegister),
					"Ventes précédentes", false);
		}

		return instance;
	}

	protected JButton getCloseButton() {
		return new AbstractButton(
				"<html><center> <br>Retour caisse<br><br> </html>") {

			private static final long serialVersionUID = 2855803317842622338L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ReportDayDetailForCheck.instance.hide();
			}
		};
	}
}
