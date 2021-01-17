package be.panidel.pos.gui.reports;

import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.gui.commandPanel.GuiPanel;

public class Panel implements Runnable {

	private static Object lockNewReport = new Object();

	private JFrame jFrame;
	private GuiPanel guiPanel;
	private String title;
	private boolean extended;

	public Panel(GuiPanel guiPanel, String title) {
		this(guiPanel, title, false);
	}

	public Panel(GuiPanel guiPanel, String title, boolean extended) {
		this.guiPanel = guiPanel;
		this.title = title;
		this.extended = extended;
	}

	public void show(PeriodBean periodBean) throws DAOException {
		if (jFrame == null) {
			createReport();
		}
		guiPanel.refresh(null);
		if (jFrame != null) {
			jFrame.setVisible(true);
		}
	}

	public void show() throws DAOException {
		show(null);
	}

	public void print(boolean showPrintDialog) throws PrinterException {
		guiPanel.print(showPrintDialog);
	}

	public void hide() {
		if (jFrame != null) {
			jFrame.setVisible(false);
			jFrame.dispose();
		}
	}

	public void createReport() {
		if (jFrame == null) {
			synchronized (lockNewReport) {
				javax.swing.SwingUtilities.invokeLater(this);
			}
		}
	}

	public void run() {

		// Create and set up the window.
		jFrame = new JFrame(title);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel = (JPanel) guiPanel;
		jPanel.setOpaque(true); // content panes must be opaque
		jFrame.setContentPane(jPanel);

		// Display the window.
		jFrame.pack();

		if (extended) {
			jFrame.setExtendedState(jFrame.getExtendedState()
					| JFrame.MAXIMIZED_BOTH);
		}
		jFrame.setVisible(true);
	}

	public GuiPanel getjPanel() {
		return guiPanel;
	}
}
