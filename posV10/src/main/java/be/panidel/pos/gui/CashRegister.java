package be.panidel.pos.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import org.apache.log4j.Logger;

import javax.swing.JFrame;

import be.panidel.common.POSParameters;
import be.panidel.common.POSException.POSException;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.controler.CashRegisterController;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.commandPanel.CmdPanelEmployees;
import be.panidel.pos.gui.commandPanel.CmdPanelKeyboard;
import be.panidel.pos.gui.configurablePanel.impl.ConfigurablePanelButtonImpl;
import be.panidel.pos.gui.toolsPanel.InfoPanel;
import be.panidel.pos.model.CashRegisterModel;
import be.panidel.tools.Alerting;
import be.panidel.tools.DifferedPostByEmail;

public class CashRegister extends AbstractCashRegister {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4554577792375571804L;

	private static final Logger log = Logger.getLogger("CashRegister");

	private CmdPanelEmployees cmdPanelEmployees;

	public static final String COMPUTERNAME = System.getenv("COMPUTERNAME");

	private CashRegister() {
		super(null);
		log.info("Init POS");
		try {
			log.info(POSParameters.instance().toString());
		} catch (ParameterException e) {
			log.error("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.pos.gui.AbstractCashRegister#initComponents()
	 */
	public void initComponents() throws POSException {

		log.debug("initComponents");

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

		try {
			cashRegisterController = new CashRegisterController(this);
			cashregisterModel = new CashRegisterModel(null);

			// Add button panel to this JPanel.
			configurablePanelButton = new ConfigurablePanelButtonImpl(this,
					KBALL);
			getCashRegisterController().setKeyboard(KBDEFAULT);

			// Add command panel to this JPanel.
			keyboardCommandPanelButton = new CmdPanelKeyboard(this);

			// Add info panel to this JPanel.
			infoPanel = new InfoPanel(this, cashregisterModel.getTicketTable(),
					cashregisterModel.getPaidTable());

			// Add command panel to this JPanel.
			cmdPanelEmployees = new CmdPanelEmployees(this);

			setLayout(new GridBagLayout());
			setFont(new Font("SansSerif", Font.PLAIN, 14));

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			add(configurablePanelButton, c);

			c.fill = GridBagConstraints.VERTICAL;
			c.ipadx = 9;
			add(keyboardCommandPanelButton, c);

			c.fill = GridBagConstraints.VERTICAL;
			c.ipadx = 131;
			add(infoPanel, c);

			c.fill = GridBagConstraints.VERTICAL;
			c.ipadx = 9;
			add(cmdPanelEmployees, c);
		} catch (DAOException e) {
			throw new POSException(e);
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.

		JFrame frame = new JFrame("Cash Register");

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);

		// Create and set up the content pane.
		// CashRegister cashRegister = new CashRegister();

		cashRegister.setOpaque(true); // content panes must be opaque

		frame.setContentPane(cashRegister);
		frame.setResizable(true);

		frame.addWindowListener(cashRegister.getWindowsClose(cashRegister));

		// Display the window.
		frame.pack();
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		frame.setVisible(true);
	}

	private static AbstractCashRegister cashRegister = new CashRegister();

	public static void main(String[] args) {

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		try {
			if (POSParameters.instance().getSyncSendEnabled()) {
				DifferedPostByEmail.initAndRunOnlyOnce(cashRegister);
			}
		} catch (ParameterException e) {
			log.error("", e);
		} catch (POSException e) {
			log.error("", e);
		}

		//TODO REMOVE
		Alerting.init();

	}

	public CmdPanelEmployees getCmdPanelEmployees() {
		return cmdPanelEmployees;
	}
}