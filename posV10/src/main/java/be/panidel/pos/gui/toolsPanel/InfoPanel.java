package be.panidel.pos.gui.toolsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import be.panidel.management.Item;
import be.panidel.management.Person;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.Calculator;
import be.panidel.pos.gui.calculator.CalculatorPanelButton;
import be.panidel.pos.gui.commandPanel.buttons.ExpandSalesButton;
import be.panidel.pos.gui.commandPanel.buttons.SubmitButton;
import be.panidel.pos.gui.common.Clignote;
import be.panidel.pos.gui.error.Message;
import be.panidel.pos.model.CashRegisterModel;
import be.panidel.pos.model.CashRegisterTable;
import be.panidel.tools.Tools;

public class InfoPanel extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2527004357008614382L;

	private static final Logger log = Logger.getLogger("InfoPanel");

	public final static Object[] ITEMTABLEHEADER = { "Qté", "Description",
			"Prix" };

	private AbstractCashRegister cashRegister;

	private Calculator calculator;
	private JTextField messageField = new JTextField(15);
	private final JTextField inputField = new JTextField(5);
	private final JTextField multiplierField = new JTextField(3);
	private JScrollPane ticketPane;
	private JTable ticketTable;
	private Object[][] table;
	// private List<Item> items;
	private JButton expendButton;
	private JButton submitButton;

	private Clignote submitClignote;
	private Clignote messageFieldClignote;

	public InfoPanel(AbstractCashRegister cashRegister,
			CashRegisterTable itemsTable, CashRegisterTable payTable) {

		this.cashRegister = cashRegister;
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();

		Person person = cashRegisterModel.getPersonnel();

		setBorder(BorderFactory.createTitledBorder((person == null
				|| Tools.isNullOrEmpty(person.getName()) ? "Identifiez vous !"
				: person.getName())));
		LayoutManager lm = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(lm);

		messageFieldClignote = new Clignote(messageField);
		JPanel messageJPanel = new JPanel();
		messageJPanel.setLayout(new BoxLayout(messageJPanel,
				BoxLayout.LINE_AXIS));
		messageJPanel.add(messageField);
		add(messageJPanel);

		ticketTable = new JTable(new Object[250][3], ITEMTABLEHEADER);
		ticketTable.setGridColor(Color.WHITE);

		ticketPane = new JScrollPane(ticketTable);
		add(ticketPane);

		expendButton = new ExpandSalesButton(cashRegister);
		expendButton.setEnabled(false);

		// submit layout
		submitButton = new SubmitButton(
				"<html><br>Cloture vente ET tiroir<br><br></html>",
				cashRegister);
		submitButton.setEnabled(false);
		submitClignote = new Clignote(submitButton);
		JPanel submitJPanel = new JPanel();
		submitJPanel
				.setLayout(new BoxLayout(submitJPanel, BoxLayout.LINE_AXIS));
		submitJPanel.add(expendButton);
		submitJPanel.add(submitButton);
		add(submitJPanel);

		calculator = new Calculator(this.cashRegister);
		add(calculator);
		add(new CalculatorPanelButton(cashRegister));

		JLayeredPane inputPane = new JLayeredPane();
		inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.LINE_AXIS));
		// multiplier field
		inputPane.add(multiplierField);

		JLabel multiplierLabel = new JLabel("X");
		inputPane.add(multiplierLabel);

		// input field
		inputPane.add(inputField);

		add(inputPane);
	}

	public void refreshDisplay() {
		log.debug("START refreshDisplay------------------------------");
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();

		Person person = cashRegisterModel.getPersonnel();
		setBorder(BorderFactory.createTitledBorder((person == null
				|| Tools.isNullOrEmpty(person.getName()) ? "Identifiez vous !"
				: person.getName())));
		refreshInputField();
		boolean canBeClosed = calculator.refresh();

		int selectedRow = -1;
		int tableSize = cashRegisterModel.getTicketTable().size()
				+ cashRegisterModel.getPaidTable().size();

		table = new Object[tableSize + 1][4];

		int rowIndex = tableSize;

		for (Item item : cashRegisterModel.getTicketTable()) {
			table[rowIndex][0] = item.getQuantity().toPlainString();
			table[rowIndex][1] = item.getDescription();
			table[rowIndex][2] = item.getUnitPrice().setScale(2)
					.toPlainString()
					+ "€";
			table[rowIndex][3] = item;
			if (item.equals(cashRegisterModel.getLastItemEdited())) {
				selectedRow = rowIndex;
			}
			rowIndex--;
		}

		table[rowIndex][0] = "---";
		table[rowIndex][1] = "---------------";
		table[rowIndex][2] = "---";
		table[rowIndex][3] = null;
		rowIndex--;

		for (Item item : cashRegisterModel.getPaidTable()) {
			table[rowIndex][0] = item.getQuantity().toPlainString();
			table[rowIndex][1] = item.getDescription();
			table[rowIndex][2] = item.getUnitPrice().setScale(2)
					.toPlainString()
					+ "€";
			table[rowIndex][3] = item;
			if (item.equals(cashRegisterModel.getLastItemEdited())) {
				selectedRow = rowIndex;
			}
			rowIndex--;
		}

		ticketTable.setModel(new DefaultTableModel(table, ITEMTABLEHEADER));

		ticketTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		ticketTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		ticketTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		ticketTable.setPreferredScrollableViewportSize(new Dimension(500, 500));
		ticketTable.setFillsViewportHeight(true);
		ticketTable.setRowHeight(15);

		if (selectedRow >= 0) {
			ticketTable.setRowSelectionInterval(selectedRow, selectedRow);
		}

		refreshMessageField();
		if (cashRegisterModel.getPaidTable().hasOperations()
				|| cashRegisterModel.getTicketTable().hasOperations()) {
			expendButton.setEnabled(true);
		} else {
			expendButton.setEnabled(false);
		}
		if (canBeClosed) {
			submitButton.setEnabled(true);
			submitClignote.start(Color.GREEN);
		} else {
			submitClignote.stop();
			submitButton.setBackground(null);
			submitButton.setEnabled(false);
		}
		log.debug("END refreshDisplay------------------------------");
	}

	public Item[] getSelectedItems() {
		List<Item> returnedItems = null;
		if (ticketTable.getSelectedRowCount() > 0) {
			int[] selectedRows = ticketTable.getSelectedRows();
			returnedItems = new ArrayList<Item>();
			for (int i = 0; i < selectedRows.length; i++) {
				Item selectedItem = (Item) table[selectedRows[i]][3];
				if (selectedItem != null) {
					returnedItems.add(selectedItem);
				}
			}
		}
		return returnedItems.toArray(new Item[returnedItems.size()]);
	}

	public void refreshMessageField() {
		log.debug("START refreshMessageField ----------------------------------------");
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		String message = cashRegisterModel.getMessage();
		log.debug(message);

		messageField.setText(message);

		Integer errorLevel = cashRegisterModel.hasError();
		if (errorLevel == null) {
			messageFieldClignote.stop();
			messageField.setBackground(Color.WHITE);
		} else if (errorLevel == Message.WARNING) {
			messageFieldClignote.start(Color.ORANGE);
		} else if (errorLevel == Message.INFORMATION) {
			messageFieldClignote.stop();
			messageField.setBackground(Color.GREEN);
		} else {
			messageFieldClignote.start(Color.RED);
		}
		log.debug("STOP refreshMessageField ----------------------------------------");
	}

	public void refreshInputField() {
		inputField.setText(cashRegister.getCashregisterModel()
				.getInputAsString());
		multiplierField.setText(cashRegister.getCashregisterModel()
				.getMultiplier().toPlainString());
	}

	public JTable getTicketTable() {
		return ticketTable;
	}
}