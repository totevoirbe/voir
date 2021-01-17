package be.panidel.pos.gui.commandPanel;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.buttons.HiddenButton;
import be.panidel.pos.gui.commandPanel.buttons.KeyboardButton;
import be.panidel.pos.gui.commandPanel.buttons.OnPlaceButton;

public class CmdPanelKeyboard extends AbstractCommandPanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OnPlaceButton onPlaceButton;

	public CmdPanelKeyboard(AbstractCashRegister cashRegister) {

		super(" ");

		keyboardButtonGroup = new ButtonGroup();
		JToggleButton jToggleButton1 = new KeyboardButton(3, "GEANT",
				cashRegister);
		keyboardButtonGroup.add(jToggleButton1);
		add(jToggleButton1);
		JToggleButton jToggleButton2 = new KeyboardButton(1, "NORMAL",
				cashRegister);
		keyboardButtonGroup.add(jToggleButton2);
		add(jToggleButton2);
		JToggleButton jToggleButton3 = new KeyboardButton(2,
				"<html>Panini<br>MINI</html>", cashRegister);
		keyboardButtonGroup.add(jToggleButton3);
		add(jToggleButton3);
		JToggleButton jToggleButton4 = new KeyboardButton(6,
				"<html>Fitness<br>GEANT</html>", cashRegister);
		keyboardButtonGroup.add(jToggleButton4);
		add(jToggleButton4);
		JToggleButton jToggleButton5 = new KeyboardButton(4,
				"<html>Fitness<br>NORMAL</html>", cashRegister);
		keyboardButtonGroup.add(jToggleButton5);
		add(jToggleButton5);
		JToggleButton jToggleButton6 = new KeyboardButton(5,
				"<html>Salades<br>Fit.MINI</html>", cashRegister);
		keyboardButtonGroup.add(jToggleButton6);
		add(jToggleButton6);

		JButton jButton = null;
		add(new HiddenButton());
		jButton = addPayButton(cashRegister, "60");
		jButton.setBackground(Color.GREEN);
		add(new HiddenButton());
		onPlaceButton = new OnPlaceButton("EMPORTE", cashRegister);
		// onPlaceButton.setBackground(Color.CYAN);
		add(onPlaceButton);
		jButton = addPayButton(cashRegister, "40");
		jButton = addPayButton(cashRegister, "50");
	}

	public OnPlaceButton getOnPlaceButton() {
		return onPlaceButton;
	}
}