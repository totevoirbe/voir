package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class BackInputButton extends ActionButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3990759936680466209L;

	public BackInputButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}
	
	public void actionCash(ActionEvent evt, AbstractCashRegister cashRegister) {
		cashRegister.getCashRegisterController().backInputOperation();
	}
}
