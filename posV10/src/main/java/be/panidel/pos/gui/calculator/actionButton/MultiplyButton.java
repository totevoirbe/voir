package be.panidel.pos.gui.calculator.actionButton;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.ActionButton;

public class MultiplyButton extends ActionButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1261487593119520406L;

	public MultiplyButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}
	
	public void actionCash(ActionEvent evt, AbstractCashRegister cashregister) {
		cashregister.getCashRegisterController().setMultiplyer();
	}


}
