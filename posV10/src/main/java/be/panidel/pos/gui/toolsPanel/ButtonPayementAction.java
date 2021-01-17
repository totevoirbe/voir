package be.panidel.pos.gui.toolsPanel;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.AbstractCashRegister;

public class ButtonPayementAction  extends AbstractButtonAction {

	private static final Logger log = Logger.getLogger("ButtonPayementAction");

	AbstractCashRegister cashregister;

	public ButtonPayementAction(AbstractCashRegister cashregister) {
		super();
		this.cashregister = cashregister;
	}

	@Override
	public void theAction(ActionEvent evt) {
		if (cashregister == null) {
			log.error("cashRegister is null");
			return;
		}
		if (cashregister.getCashRegisterController().isSalesOK(cashregister)) {
			cashregister.getCashRegisterController()
					.appendPaymentToPaidTable(content);
		}
		cashregister.getInfoPanel().refreshMessageField();
	}
}