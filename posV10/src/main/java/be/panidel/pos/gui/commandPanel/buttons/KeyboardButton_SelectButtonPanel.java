package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.commandPanel.AbstractJToggleButton;

public class KeyboardButton_SelectButtonPanel extends AbstractJToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515910615262847446L;

	private static final Logger log = Logger.getLogger("KeyboardButton");

	private int keyBoardID;
	private SelectButtonPanel selectButtonPanel;

	public KeyboardButton_SelectButtonPanel(int keyBoardID, String text,
			SelectButtonPanel selectButtonPanel) {
		super(text);
		this.keyBoardID = keyBoardID;
		this.selectButtonPanel = selectButtonPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("action");
		selectButtonPanel.refreshButtons(keyBoardID);
		selectButtonPanel.updateUI();
	}

	public int getKeyBoardID() {
		return keyBoardID;
	}
}