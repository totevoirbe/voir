package be.panidel.pos.gui.common;

import javax.swing.AbstractButton;

public class ButtonTools {

	static public void drawCommandButton(AbstractButton button, String text) {
		button.setOpaque(true);
		if (text!=null) {
			StringBuffer label = new StringBuffer("<html><center>");
			label.append(text);
			label.append("</html>");
			button.setText(label.toString());
		}
	}
}
