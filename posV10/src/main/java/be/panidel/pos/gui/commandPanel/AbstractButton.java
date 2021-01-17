package be.panidel.pos.gui.commandPanel;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.common.ButtonTools;

public abstract class AbstractButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AbstractCashRegister cashRegister;

	public AbstractButton() {
		super();
		setMargin(new Insets(0, 0, 0, 0));
	}

	public AbstractButton(String text) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setMinimumSize(new Dimension(60, 60));
		ButtonTools.drawCommandButton(this, text);
		addActionListener(this);
	}

	public AbstractButton(String text, AbstractCashRegister cashRegister) {
		this(text);
		this.cashRegister = cashRegister;
	}
}