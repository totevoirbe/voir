package be.panidel.pos.gui.toolsPanel;

import java.awt.Font;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class SelectDatePanel extends JSpinner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2736124887707246678L;

	public SelectDatePanel() {

		super();
		SpinnerDateModel sdm = new SpinnerDateModel();
		setModel(sdm);
		setFont(new Font(getFont().getName(), getFont().getStyle(), 40));

	}

	public Date getDate() {
		return (Date) getValue();
	}

	public void setDate(Date value) {
		setValue(value);
	}

	public void nextValue() {
		setValue(getNextValue());
	}

	public void previousValue() {
		setValue(getPreviousValue());
	}

}