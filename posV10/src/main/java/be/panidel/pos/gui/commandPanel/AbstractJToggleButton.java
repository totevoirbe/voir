package be.panidel.pos.gui.commandPanel;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import be.panidel.pos.gui.common.ButtonTools;

public abstract class AbstractJToggleButton extends JToggleButton implements ActionListener {
	
	/**
	 * 
	 */
	static final long serialVersionUID = 7205053118743781132L;

	public AbstractJToggleButton(String text) {
		super();
		setMargin(new Insets(0,0,0,0));
		setBackground(Color.YELLOW);
		ButtonTools.drawCommandButton(this, text);
		addActionListener(this);
	}
}