package be.panidel.pos.gui.configurablePanel;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.management.Group;
import be.panidel.management.Product;
import be.panidel.pos.gui.common.ButtonAction;
import be.panidel.tools.Tools;

public class ConfigurableButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2447159288976519129L;
	private static final Logger log = Logger.getLogger("ConfigurableButton");

	protected ButtonAction buttonAction;
	protected Identification content;

	public ConfigurableButton() {
		super();
		addActionListener(this);
		setMargin(new Insets(0, 0, 0, 0));
	}

	public ConfigurableButton(Identification content, ButtonAction buttonAction) {

		this();

		this.content = content;
		this.buttonAction = buttonAction;

		if (content == null) {
			log.error("Undefined product : " + content + " Button : "
					+ buttonAction);
			setText("N/A");
			return;
		}
		setTextOnButton(
				(Tools.isNullOrEmpty(content.getHtmlKeyLabel()) ? content
						.getName() : content.getHtmlKeyLabel()), content
						.getValueLabel(), 78);
		if (content instanceof Product) {
			Product product = (Product) content;
			Group group = product.getGroupAsGroup();
			if (group != null && group.getTouchColor() != null) {
				setBackground(group.getTouchColorAsColor());
			} else {
				log.debug(content.getName());
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (buttonAction != null) {
			buttonAction.theAction(evt);
		} else {
			log.error("Button action is null :" + content);
		}
	}

	protected void setTextOnButton(String label, String lastLine,
			int minimumWidth) {

		int maxLineLength = 8;
		StringBuffer textOnButton = new StringBuffer("<html><center>");
		String[] labels = label.split(";");

		for (int i = 0; i < labels.length && i < 2; i++) {
			if (i > 0) {
				textOnButton.append("<br>");
			}
			textOnButton.append(labels[i], 0,
					(labels[i].length() > maxLineLength ? maxLineLength
							: labels[i].length()));
		}
		if (labels.length == 1) {
			textOnButton.append("<br>");
		}
		if (lastLine != null) {
			textOnButton.append("<br>");
			textOnButton.append(lastLine, 0,
					(lastLine.length() > maxLineLength ? maxLineLength
							: lastLine.length()));
		}
		textOnButton.append("</html>");
		setText(textOnButton.toString());
		setFont(Tools.getDefaultFont(15));
		setMinimumSize(new Dimension(minimumWidth, 0));
		setMargin(new Insets(0, 0, 0, 0));
	}

	public Identification getContent() {
		return content;
	}

}