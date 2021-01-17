package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.AbstractJToggleButton;

public class KeyboardButton extends AbstractJToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515910615262847446L;

	private static final Logger log = Logger.getLogger("KeyboardButton");

	private int keyBoardID;
	
	protected AbstractCashRegister cashRegister;

	public KeyboardButton(int keyBoardID, String text, AbstractCashRegister cashRegister) {
		super(text);
		this.keyBoardID = keyBoardID;
		this.cashRegister = cashRegister;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("action");
		cashRegister.getCashRegisterController().setKeyboard(keyBoardID);
	}

	public int getKeyBoardID() {
		return keyBoardID;
	}
}