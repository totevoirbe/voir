package be.panidel.pos.gui.toolsPanel;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;

public class ButtonProductAction extends AbstractButtonAction {

	private static final Logger log = Logger.getLogger("ButtonProductAction");

	AbstractCashRegister cashregister;

	public ButtonProductAction(AbstractCashRegister cashregister) {
		super();
		if (cashregister == null) {
			log.error("cashRegister is null");
			return;
		}
		this.cashregister = cashregister;
	}

	@Override
	public void theAction(ActionEvent evt) {
		if (cashregister.getCashRegisterController().isSalesOK(cashregister)) {
			cashregister.getCashRegisterController()
					.appendProductToTicketTable(content);
		}
		cashregister.getCashRegisterController().setKeyboard(
				CashRegister.KBDEFAULT);
		cashregister.getInfoPanel().refreshMessageField();
	}
}
