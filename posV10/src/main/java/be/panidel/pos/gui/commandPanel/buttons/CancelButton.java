package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;

public class CancelButton extends AbstractButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3411881882720294926L;

	public CancelButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cashRegister.getCashRegisterController().cancelSale();
	}
}