package be.panidel.pos.gui.commandPanel;

import java.awt.Color;
import javax.swing.JButton;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.commandPanel.buttons.CloseSplitUpPanel;
import be.panidel.pos.gui.commandPanel.buttons.HiddenButton;

public class CommandPanelButton3 extends AbstractCommandPanelButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7602407057630891777L;

	public CommandPanelButton3(AbstractCashRegister cashRegister) {

		super("3");

		JButton jButton = null;
		add(new HiddenButton());
		add(new HiddenButton());
		jButton = addPayButton(cashRegister, "60");
		jButton.setBackground(Color.GREEN);
		add(new HiddenButton());
		jButton = addPayButton(cashRegister, "40");
		jButton = addPayButton(cashRegister, "50");
		add(new HiddenButton());
		add(new CloseSplitUpPanel("Quit", cashRegister));
		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
		add(new HiddenButton());
	}
}