package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class EqualButton extends ActionButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6152141063208964204L;

	public EqualButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}
	
	public void actionCash(ActionEvent evt, AbstractCashRegister cashRegister) {
		cashRegister.getCashRegisterController().equalOperation();
	}
}
