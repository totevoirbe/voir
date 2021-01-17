package be.panidel.pos.gui;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import be.panidel.pos.gui.commandPanel.CommandSelectButtonPanel;
import be.panidel.pos.gui.configurablePanel.impl.ConfigurablePanelButtonForSelectImpl;
import be.panidel.pos.gui.toolsPanel.ButtonDetailPanel;

public class SelectButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("SummaryPanel");

	private ConfigurablePanelButtonForSelectImpl configurablePanelButton;
	private CommandSelectButtonPanel keyboardCommandPanelButton;
	private ButtonDetailPanel buttonDetailPanel;
	private AbstractCashRegister parentCashRegister;
	private JFrame frame;

	private int selectedKeyBoardID = 1;

	public ButtonDetailPanel getButtonDetailPanel() {
		return buttonDetailPanel;
	}

	public SelectButtonPanel() {
		super();
		initComponents();
		// super.setVisible(true);
		setVisible(true);
	}

	public void initComponents() {

		log.debug("START initComponents");

		LayoutManager lm = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(lm);

		buttonDetailPanel = new ButtonDetailPanel(this);

		// Add button panel to this JPanel.
		configurablePanelButton = new ConfigurablePanelButtonForSelectImpl(
				CashRegister.KBALL, this);
		configurablePanelButton.refreshButtons(selectedKeyBoardID, false);

		// Add command panel to this JPanel.
		keyboardCommandPanelButton = new CommandSelectButtonPanel(this);

		add(configurablePanelButton);
		add(keyboardCommandPanelButton);
		add(buttonDetailPanel);

		log.debug("STOP initComponents");
	}

	public void refreshButtons() {
		refreshButtons(selectedKeyBoardID);
	}

	public void refreshButtons(int keyBoardID) {
		selectedKeyBoardID = keyBoardID;
		configurablePanelButton.refreshButtons(keyBoardID, true);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static SelectButtonPanel doCreateAndShowGUI() {

		// Create and set up the window.
		JFrame frame = new JFrame("Summary2");
		// frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Create and set up the content pane.
		SelectButtonPanel contentPane = new SelectButtonPanel();
		contentPane.setOpaque(true); // content panes must be opaque
		contentPane.setFrame(frame);
		frame.setContentPane(contentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		return contentPane;
	}

	private static SelectButtonPanel instance;
	private static Object lock = new Object();

	public static SelectButtonPanel createAndShowGUI(boolean visible) {
		if (!visible) {
			if (instance != null) {
				instance.getFrame().dispose();
			}
		} else {
			if (instance == null) {
				synchronized (lock) {
					instance = doCreateAndShowGUI();
				}
			}
			instance.getFrame().setVisible(true);
		}
		return instance;
	}

	public int getSelectedKeyBoardID() {
		return selectedKeyBoardID;
	}

	public void setSelectedKeyBoardID(int selectedKeyBoardID) {
		this.selectedKeyBoardID = selectedKeyBoardID;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public ConfigurablePanelButtonForSelectImpl getConfigurablePanelButton() {
		return configurablePanelButton;
	}

	public AbstractCashRegister getParentCashRegister() {
		return parentCashRegister;
	}

	public void setParentCashRegister(AbstractCashRegister parentCashRegister) {
		this.parentCashRegister = parentCashRegister;
	}
}