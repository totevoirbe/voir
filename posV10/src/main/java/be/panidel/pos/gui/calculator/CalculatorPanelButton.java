package be.panidel.pos.gui.calculator;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.actionButton.BackInputButton;
import be.panidel.pos.gui.calculator.actionButton.ChangeSignButton;
import be.panidel.pos.gui.calculator.actionButton.ClearInputButton;
import be.panidel.pos.gui.calculator.actionButton.DigitButton;
import be.panidel.pos.gui.calculator.actionButton.EqualButton;
import be.panidel.pos.gui.calculator.actionButton.MultiplyButton;
import be.panidel.tools.Tools;

public class CalculatorPanelButton extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -460083522184069318L;
	private AbstractCashRegister cashregister;

	public CalculatorPanelButton(AbstractCashRegister cashregister) {
		super();
		
		this.cashregister = cashregister;
		
		setBorder(BorderFactory.createTitledBorder("Calculator"));
		setLayout(new GridLayout(5,4));
		add(new BackInputButton("<", cashregister));
		add(createDigitButton("0"));
		add(createDigitButton("0"));
		add(new EqualButton("=", cashregister));
		add(createDigitButton("7"));
		add(createDigitButton("8"));
		add(createDigitButton("9"));
		add(createDigitButton("0"));
		add(createDigitButton("4"));
		add(createDigitButton("5"));
		add(createDigitButton("6"));
		add(new ChangeSignButton("-", cashregister));
		add(createDigitButton("1"));
		add(createDigitButton("2"));
		add(createDigitButton("3"));
		add(new MultiplyButton("X", cashregister));
		add(createDigitButton("0"));
		add(createDigitButton("."));
		add(createDigitButton("0"));
		add(new ClearInputButton("{}", cashregister));
	}
	
	private JButton createDigitButton(String label) {
		DigitButton button = new DigitButton(label, cashregister);
		button.setFont(Tools.getDefaultFont(20));
		return button;
	}
}