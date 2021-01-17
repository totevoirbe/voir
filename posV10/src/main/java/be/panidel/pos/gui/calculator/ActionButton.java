package be.panidel.pos.gui.calculator;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.tools.Tools;

public abstract class ActionButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6724245846358275432L;
	private AbstractCashRegister cashregister;
	
	public ActionButton(String text, AbstractCashRegister cashregister) {
//		super("<html>"+text+"<br>-</html>");
		super(text);
		setMargin(new Insets(10,0,10,0));
		this.setFont(Tools.getDefaultFont(20));
		this.cashregister = cashregister;
		setActionCommand(text);
		addActionListener(this);
		setOpaque(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		actionCash(evt, cashregister);
	}
	
	protected abstract void actionCash(ActionEvent evt, AbstractCashRegister cashregister);
}