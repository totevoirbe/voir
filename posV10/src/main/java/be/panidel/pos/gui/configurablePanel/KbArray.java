package be.panidel.pos.gui.configurablePanel;

import javax.swing.JButton;

public class KbArray {

	private JButton arrayKeyboard[][];

	public KbArray() {
		arrayKeyboard = new JButton[ConfigurablePanelButton.HKEYBOARDSIZE][ConfigurablePanelButton.VKEYBOARDSIZE];
	}

	public JButton get(int hIndex, int vIndex) {
		return arrayKeyboard[hIndex][vIndex];
	}

	public void set(int hIndex, int vIndex, JButton button) {
		arrayKeyboard[hIndex][vIndex] = button;
	}
}