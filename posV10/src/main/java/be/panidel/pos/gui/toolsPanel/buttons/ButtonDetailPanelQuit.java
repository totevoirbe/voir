package be.panidel.pos.gui.toolsPanel.buttons;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.SelectButtonPanel;

public class ButtonDetailPanelQuit extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("Submit");
	
	private SelectButtonPanel selectButtonPanel;
	
	public ButtonDetailPanelQuit(SelectButtonPanel selectButtonPanel) {
		super();
		setMargin(new Insets(0,0,0,0));
		setMinimumSize(new Dimension(60, 60));
		setText("<html><center>Quitter<br>Les modifications non enregistrées<br>seront annulées</html>");
		this.selectButtonPanel = selectButtonPanel;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		log.debug("Hide Window");
		selectButtonPanel.getFrame().setVisible(false);
	}
}

