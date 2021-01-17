package be.panidel.pos.gui.commandPanel.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.common.ButtonTools;

public class OnPlaceButton extends JToggleButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AbstractCashRegister cashRegister;

	public OnPlaceButton() {
		super();
		setMargin(new Insets(0, 0, 0, 0));
	}

	public OnPlaceButton(String text) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setMinimumSize(new Dimension(60, 60));
		ButtonTools.drawCommandButton(this, text);
		addActionListener(this);
		setBackground(Color.WHITE);
		setText("EMPORTE");
	}

	public OnPlaceButton(String text, AbstractCashRegister cashRegister) {
		this(text);
		this.cashRegister = cashRegister;
		setBackground(Color.WHITE);
		setText("EMPORTE");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isSelected = isSelected();
		if (!isSelected) {
			setText("EMPORTE");
			setForeground(Color.BLACK);
			setBackground(Color.WHITE);
			cashRegister.getInfoPanel().getTicketTable()
					.setBackground(Color.WHITE);
		} else {
			setText("SUR PLACE");
			setForeground(Color.CYAN);
			setBackground(Color.CYAN);
			cashRegister.getInfoPanel().getTicketTable()
					.setBackground(Color.CYAN);
		}
		cashRegister.getCashRegisterController().setTakeOnPlace(isSelected);
	}
}