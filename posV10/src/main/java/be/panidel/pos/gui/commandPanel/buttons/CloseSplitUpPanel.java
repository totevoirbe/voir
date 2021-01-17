package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractButton;

public class CloseSplitUpPanel extends AbstractButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4974868825599305729L;

	public CloseSplitUpPanel(String text, AbstractCashRegister cashRegister) {
		super(text, cashRegister);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cashRegister.setVisible(false);
	}
}