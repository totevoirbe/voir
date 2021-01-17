package be.panidel.pos.gui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import be.panidel.common.POSException.POSException;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.PersonImpl;
import be.panidel.pos.controler.CashRegisterController;
import be.panidel.pos.gui.commandPanel.CommandPanelButton3;
import be.panidel.pos.gui.toolsPanel.InfoPanel;
import be.panidel.pos.model.CashRegisterModel;

public class SplitPanel extends AbstractCashRegister {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5521685831328339119L;

	private final static Object[] TABLE_HEADER = { "Qté", "Description", "Prix" };

	private static final Logger log = Logger.getLogger("SplitPanel");

	private JScrollPane ticketPane;
	private JTable ticketTable;

	private CommandPanelButton3 commandPanelButton3;

	public SplitPanel(AbstractCashRegister cashRegister) {
		super(cashRegister);
	}

	public void initComponents() throws POSException {

		log.debug("START initComponents");

		try {
			cashRegisterController = new CashRegisterController(this);
			cashregisterModel = new CashRegisterModel(new PersonImpl());

			setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

			ticketTable = new JTable(new Object[250][3], TABLE_HEADER);
			ticketTable.setGridColor(Color.WHITE);
			ticketPane = new JScrollPane(ticketTable);

			add(ticketPane);

			// Add command panel to this JPanel.
			commandPanelButton3 = new CommandPanelButton3(this);
			add(commandPanelButton3);

			// Add info panel to this JPanel.
			infoPanel = new InfoPanel(this, cashregisterModel.getTicketTable(),
					cashregisterModel.getPaidTable());
			add(infoPanel);

			log.debug("STOP initComponents");
		} catch (DAOException e) {
			throw new POSException(e);
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static AbstractCashRegister createAndShowGUI(
			AbstractCashRegister cashRegister) {
		// Create and set up the window.
		JFrame frame = new JFrame("Cash Register");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		AbstractCashRegister contentPane = new SplitPanel(cashRegister);
		contentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(contentPane);

		// Display the window.
		frame.pack();

		return contentPane;
	}

}