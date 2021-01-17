package be.panidel.pos.gui.toolsPanel.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import org.apache.log4j.Logger;

public class ButtonPickColorPanel extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2616982098821097526L;

	/**
	 * 
	 */

	private static final Logger log = Logger.getLogger("ButtonPickColorPanel");

	public ButtonPickColorPanel() {
		super();
		setMargin(new Insets(0,0,0,0));
		setMinimumSize(new Dimension(60, 60));
		setText("<html><center>Couleur du groupe</html>");
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		log.debug("Pick color");
		JColorChooser jColorChooser = new JColorChooser();
		Color selectColor = JColorChooser.showDialog(jColorChooser, "Coleur du groupe", getBackground());
		setBackground(selectColor);
	}
}