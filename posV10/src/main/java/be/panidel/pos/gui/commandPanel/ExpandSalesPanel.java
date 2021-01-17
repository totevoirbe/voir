package be.panidel.pos.gui.commandPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.calculator.Calculator;
import be.panidel.pos.gui.commandPanel.AbstractButton;
import be.panidel.pos.gui.reports.Panel;
import be.panidel.pos.model.CashRegisterModel;

public class ExpandSalesPanel extends JPanel implements GuiPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("ExpandSalesPanel");

	private static Panel thisAsPanel;

	protected JTable salesTable;
	protected JTable payTable;
	protected JTable suppliesTable;
	private AbstractCashRegister parentCashRegister;
	protected JPanel tablePane = new JPanel(new GridLayout(1, 0));
	protected JPanel actionPane = new JPanel(new GridLayout(1, 0));
	private Calculator calculator;

	public static AbstractButton close() {
		return new AbstractButton(
				"<html><center> <br>Retour caisse<br><br> </html>") {

			private static final long serialVersionUID = 2855803317842622338L;

			@Override
			public void actionPerformed(ActionEvent e) {
				thisAsPanel.hide();
			}
		};
	}

	public ExpandSalesPanel(AbstractCashRegister parentCashRegister) {
		super();
		this.parentCashRegister = parentCashRegister;
		initComponents();
	}

	public void initComponents() {

		log.debug("START initComponents");

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		salesTable = setTable();
		payTable = setTable();
		suppliesTable = setTable();

		add(tablePane);

		calculator = new Calculator(parentCashRegister);
		actionPane.add(calculator);
		actionPane.add(close());

		add(actionPane);

		log.debug("STOP initComponents");

	}

	private JTable setTable() {

		JTable jTable = new JTable();
		jTable.setGridColor(Color.WHITE);
		JScrollPane jscrollPane = new JScrollPane(jTable);
		jscrollPane.getVerticalScrollBar().setMinimumSize(new Dimension(50, 0));
		jscrollPane.getVerticalScrollBar().setPreferredSize(
				new Dimension(50, 0));
		tablePane.add(jscrollPane);

		return jTable;
	}

	public void refresh(PeriodBean periodBean) throws DAOException {

		log.debug("START refresh");

		CashRegisterModel cashRegisterModel = parentCashRegister
				.getCashregisterModel();

		calculator.refresh();

		getTableModel(salesTable, cashRegisterModel.getTicketTable(), 0);

		getTableModel(payTable, cashRegisterModel.getPaidTable(), 0);

		Map<String, Item> supplies = new HashMap<String, Item>();
		// TODO CHECK WHAT IS IT ?
		// ComputeHelper.getSupplies(supplies,
		// cashRegisterModel.getTicketTable());
		getTableModelSupplies(suppliesTable, supplies.values(), 0);

		log.debug("STOP refresh");

	}

	private void getTableModel(JTable jTable, Collection<Item> itemList,
			int nextRow) {

		Object[][] table = new Object[itemList.size() + 2][3];

		BigDecimal totalItem = BigDecimal.ZERO;

		for (Item item : itemList) {
			totalItem = totalItem.add(item.getQuantity());
			table[nextRow][0] = item.getQuantity().toPlainString();
			table[nextRow][1] = item.getItem().getDescription();
			table[nextRow][2] = item.getUnitPrice().setScale(2).toPlainString()
					+ "€";
			nextRow++;
		}
		table[nextRow + 1][0] = totalItem.toPlainString();
		table[nextRow + 1][1] = "=> Quantité totale";

		TableModel tm = new DefaultTableModel(table, new Object[] { "Qté",
				"Description", "Prix" });
		jTable.setModel(tm);
		jTable.setRowSorter(new TableRowSorter<TableModel>(tm));
		jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(30);
	}

	private void getTableModelSupplies(JTable jTable,
			Collection<Item> itemList, int nextRow) {

		Object[][] table = new Object[itemList.size() + 2][3];

		BigDecimal totalItem = BigDecimal.ZERO;

		for (Item item : itemList) {
			totalItem = totalItem.add(item.getQuantity());
			table[nextRow][0] = item.getQuantity().toPlainString();
			table[nextRow][1] = item.getItem().getDescription();
			nextRow++;
		}
		table[nextRow + 1][0] = totalItem.toPlainString();
		table[nextRow + 1][1] = "=> Quantité totale";

		TableModel tm = new DefaultTableModel(table, new Object[] { "Qté",
				"Description", "Prix" });
		jTable.setModel(tm);
		jTable.setRowSorter(new TableRowSorter<TableModel>(tm));
		jTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(30);
	}

	public Panel getThisAsPanel() {
		return thisAsPanel;
	}

	public void setThisAsPanel(Panel thisAsPanel) {
		ExpandSalesPanel.thisAsPanel = thisAsPanel;
	}

	@Override
	public void print(boolean showPrintDialog) throws PrinterException {
		log.error("Not implemented");
	}
}