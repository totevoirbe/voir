package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;

public class SubmitButton extends AbstractButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1914493119927597207L;

	public SubmitButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		cashRegister.getCashRegisterController().endSale();
	}
}