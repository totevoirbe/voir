package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class ChangeSignButton extends ActionButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4943448969658093322L;

	public ChangeSignButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}
	
	public void actionCash(ActionEvent evt, AbstractCashRegister cashRegister) {
		cashRegister.getCashRegisterController().invertInputValue();
	}
}
