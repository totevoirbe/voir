package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;

public class SplitUpButton extends AbstractButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6245688767755079481L;

	public SplitUpButton(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cashRegister.getCashRegisterController().openSplitUpPanel();
	}
}