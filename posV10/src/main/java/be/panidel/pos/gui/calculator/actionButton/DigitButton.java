package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class DigitButton extends ActionButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5034835075660816617L;

	public DigitButton(String text, AbstractCashRegister cashregister) {
		super(text, cashregister);
	}

	@Override
	protected void actionCash(ActionEvent evt, AbstractCashRegister cashregister) {
		cashregister.getCashregisterModel().mergeDigit(getActionCommand());
		cashregister.getInfoPanel().refreshInputField();
	}

}
