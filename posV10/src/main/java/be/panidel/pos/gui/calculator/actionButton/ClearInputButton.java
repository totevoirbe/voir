package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class ClearInputButton extends ActionButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4201127368586290956L;

	public ClearInputButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}
	
	public void actionCash(ActionEvent evt, AbstractCashRegister cashRegister) {
		cashRegister.getCashRegisterController().clearInput();
	}
}
