package be.panidel.pos.gui.commandPanel;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.commandPanel.buttons.HiddenButton;
import be.panidel.pos.gui.commandPanel.buttons.KeyboardButton_SelectButtonPanel;

public class CommandSelectButtonPanel extends AbstractCommandPanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7400213460084186637L;

	public CommandSelectButtonPanel(SelectButtonPanel selectButtonPanel) {
		
		super("2");

		keyboardButtonGroup = new ButtonGroup();
		JToggleButton jToggleButton1 = new KeyboardButton_SelectButtonPanel(3, "GEANT", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton1);
		add(jToggleButton1);
		JToggleButton jToggleButton2 = new KeyboardButton_SelectButtonPanel(1, "NORMAL", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton2);
		add(jToggleButton2);
		JToggleButton jToggleButton3 = new KeyboardButton_SelectButtonPanel(2, "MINI", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton3);
		add(jToggleButton3);
		JToggleButton jToggleButton4 = new KeyboardButton_SelectButtonPanel(6,
				"<html>Fitness<br>GEANT</html>", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton4);
		add(jToggleButton4);
		JToggleButton jToggleButton5 = new KeyboardButton_SelectButtonPanel(4,
				"<html>Fitness<br>NORMAL</html>", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton5);
		add(jToggleButton5);
		JToggleButton jToggleButton6 = new KeyboardButton_SelectButtonPanel(5,
				"<html>Fitness<br>MINI</html>", selectButtonPanel);
		keyboardButtonGroup.add(jToggleButton6);
		add(jToggleButton6);

		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
	}
}