package be.panidel.pos.gui.reports;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.commandPanel.GuiPanel;
import be.panidel.pos.gui.toolsPanel.SelectDatePanel;
import be.panidel.tools.Tools;

public abstract class AbstractReportPanel extends JPanel implements GuiPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("SummaryPanel");

	protected SelectDatePanel startDatePanel;
	protected SelectDatePanel endDatePanel;
	protected JButton refreshButton;

	protected JTable salesTable;
	private JScrollPane salesPane;
	private CommandPanel cp;

	protected AbstractReportPanel contentPane;
	protected JPanel actionPane;

	protected AbstractReportPanel(Panel cp) {
		super();
		if (cp != null) {
			this.cp = (CommandPanel) cp.getjPanel();
		}
		initComponents();
	}

	protected AbstractReportPanel() {
		this(null);
	}

	public void refresh(PeriodBean periodBean) throws DAOException {

		refreshButton.setEnabled(false);

		String operationIdentifier = "["
				+ POSConstants.SDF_FULLDAY.format(getStartDate()) + "]/["
				+ POSConstants.SDF_FULLDAY.format(getEndDate()) + "]";
		log.debug("Compute sales : " + operationIdentifier);

		try {
			if (periodBean != null && periodBean.isBorned()) {
				startDatePanel.setValue(Tools.startOfCurentDay(periodBean
						.getStartDate()));
				endDatePanel.setValue(Tools.endOfDay(periodBean.getEndDate()));
			}
		} catch (ParameterException e) {
			log.error("", e);
		}

		try {

			String[] headerLabels = new String[getTableHeader().length];
			for (int i = 0; i < getTableHeader().length; i++) {
				headerLabels[i] = getTableHeader()[i][0];
			}
			log.debug("Fulfill table");
			initRefresh();
			TableModel tm = fullFillTable(headerLabels);
			salesTable.setModel(tm);

			// adjust column size
			for (int i = 0; i < getTableHeader().length; i++) {
				salesTable
						.getColumnModel()
						.getColumn(i)
						.setPreferredWidth(
								Integer.parseInt(getTableHeader()[i][1]));
			}
			salesTable.setGridColor(Color.WHITE);
		} catch (DAOException e) {
			log.error(operationIdentifier, e);
		} catch (ParameterException e) {
			log.error(operationIdentifier, e);
		}
		refreshButton.setEnabled(true);
	}

	abstract void initSpecificComponents() throws DAOException;

	abstract void afterInitComponents() throws DAOException;

	abstract String getTitle();

	public Date getStartDate() {
		return (Date) startDatePanel.getValue();
	}

	public Date getEndDate() {
		return (Date) endDatePanel.getValue();
	}

	public void setStartDate(Date value) {
		startDatePanel.setValue(value);
	}

	public void setEndDate(Date value) {
		endDatePanel.setValue(value);
	}

	public void initComponents() {

		log.debug("InitComponents");

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel dateSelectionPane = new JPanel(new GridLayout(1, 3));
		actionPane = new JPanel(new GridLayout(1, 0));

		try {

			initSpecificComponents();

			// Layout the text fields in a panel.

			startDatePanel = new SelectDatePanel();
			// startDatePanel.setPreferredSize(new Dimension(200, 30));

			for (int i = 0; i < startDatePanel.getComponentCount(); i++) {
				setSize(startDatePanel.getComponent(i));
			}

			endDatePanel = new SelectDatePanel();

			refreshButton = new ButtonRefresh(this);

			// dateSelectionPane.add(new Label("Du"));
			dateSelectionPane.add(startDatePanel);
			// dateSelectionPane.add(new Label("Au"));
			dateSelectionPane.add(endDatePanel);
			dateSelectionPane.add(refreshButton);

			// dateSelectionPane.setPreferredSize(new Dimension(700, 50));

			add(dateSelectionPane);

			String[] headerLabels = new String[getTableHeader().length];
			for (int i = 0; i < getTableHeader().length; i++) {
				headerLabels[i] = getTableHeader()[i][0];
			}
			salesTable = new JTable(new Object[1][getTableHeader().length],
					headerLabels);

			salesPane = new JScrollPane(salesTable);
			salesPane.getVerticalScrollBar().setPreferredSize(
					new Dimension(50, 0));
			salesTable.addMouseListener(new TableMouseListener());

			add(salesPane);

			if (cp != null) {
				actionPane.add(cp.resCashBook());
				actionPane.add(cp.resCashBookDayDetail());
				actionPane.add(cp.resByDayBtn());
				actionPane.add(cp.resDayDetail());
				actionPane.add(cp.resBySupplies());
				actionPane.add(new ButtonCommand());
			}
			actionPane.add(new ButtonPrint(this));

			if (cp != null) {
				actionPane.add(cp.close());
			} else {
				actionPane.add(getCloseButton());
			}

			// actionPane.setPreferredSize(new Dimension(700, 150));

			add(actionPane);
			afterInitComponents();

		} catch (HeadlessException e) {
			log.error("", e);
		} catch (DAOException e) {
			log.error("", e);
		}
	}

	private void setSize(Component component) {

		if (component instanceof JButton) {
			JButton button = (JButton) component;
			component.setBackground(Color.PINK);
			button.setMinimumSize(new Dimension(0, 100));
			button.setPreferredSize(new Dimension(0, 100));
			System.out.println("JButton");
		} else if (component instanceof JComboBox) {
			System.out.println("JComboBox");
		} else if (component instanceof JTextField) {
			// JTextField jTextField = (JTextField) component;
			// jTextField.setName("sdfsdf");
			// component.setBackground(Color.yellow);
			// component.setPreferredSize(new Dimension(20, 20));
			System.out.println("JTextField");
		} else if (component instanceof JFormattedTextField) {
			// component.setBackground(Color.red);
			// component.setPreferredSize(new Dimension(40, 20));
			System.out.println("JFormattedTextField");
		} else if (component instanceof JTable) {
			// component.setBackground(Color.darkGray);
			System.out.println("JTable");
		} else if (component instanceof JScrollPane) {
			// component.setBackground(Color.CYAN);
			// component.setPreferredSize(new Dimension(40, 20));
			System.out.println("JScrollPane");
		} else if (component instanceof JPanel) {
			// JPanel panel = (JPanel) component;
			System.out.println("==>JPanel");
			// for (int i = 0; i < panel.getComponentCount(); i++) {
			// setSize(panel.getComponent(i));
			// }
		} else {
			System.out.println("unknown");

		}

	}

	protected JButton getCloseButton() {
		log.error("Illegal use of close button");
		return null;
	}

	protected Map<String, String> createIndexByDescription(
			Map<String, Item> items) {

		Map<String, String> itemsOrdering = new TreeMap<String, String>();

		for (String itemId : items.keySet()) {
			Item item = items.get(itemId);
			String indexValue = item.getItem().getDescription() + itemId;
			itemsOrdering.put(indexValue, itemId);
		}

		return itemsOrdering;

	}

	public void print(boolean showPrintDialog) throws PrinterException {
		salesTable.doLayout();
		salesTable.print(PrintMode.FIT_WIDTH, null, null, showPrintDialog,
				null, true);
	}

	abstract void initRefresh() throws ParameterException;

	abstract TableModel fullFillTable(String[] tableHeader)
			throws DAOException, ParameterException;

	abstract String[][] getTableHeader();

}

class ButtonRefresh extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger("ButtonRefresh");
	private static final long serialVersionUID = 1L;
	private AbstractReportPanel sp;

	public ButtonRefresh(AbstractReportPanel sp) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setText("Refresh");
		this.sp = sp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Action performed");
		try {
			sp.refresh(null);
		} catch (DAOException e1) {
			log.error("", e1);
		}
	}
}

class ButtonCommand extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger("ButtonCommand");

	public ButtonCommand() {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setText("Menu");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			CommandPanel.instance().show();
		} catch (DAOException e1) {
			log.error("", e1);
		}
	}
}

class ButtonPrint extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger("ButtonPrintSalesByDay");
	private static final long serialVersionUID = 1L;
	private AbstractReportPanel sp;

	public ButtonPrint(AbstractReportPanel sp) {
		super();
		setMargin(new Insets(0, 0, 0, 0));
		setText("Imprimer");
		this.sp = sp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Action performed");

		try {
			sp.print(true);
		} catch (PrinterException exp) {
			log.error("", exp);
		}
	}
}

class TableMouseListener implements MouseListener {

	private static final Logger log = Logger.getLogger("TableMouseListener");

	@Override
	public void mouseReleased(MouseEvent e) {
		log.debug("Mouse released");
		String dateAsDate = null;
		try {
			JTable jtable = (JTable) e.getSource();
			int selectedRow = jtable.getSelectedRow();
			int selectedCol = jtable.getSelectedColumn();
			if (log.isDebugEnabled()) {
				log.debug("Selected rol : " + selectedRow + "Selected col : "
						+ selectedCol);
			}
			if (selectedCol == 0 && selectedRow > 0) {
				dateAsDate = (String) jtable.getModel().getValueAt(selectedRow,
						0);
				if (log.isDebugEnabled()) {
					log.debug("Date as string" + dateAsDate);
				}
				if (!Tools.isNullOrEmpty(dateAsDate)) {
					Date date = POSConstants.SDF_FULLDAY.parse(dateAsDate);
					if (log.isDebugEnabled()) {
						log.debug("Date "
								+ POSConstants.SDF_FULLDAY.format(date));
					}
					try {
						((CommandPanel) CommandPanel.instance().getjPanel())
								.getSummaryDayDetail().show(
										new PeriodBean(date, date));
					} catch (DAOException e1) {
						log.error("", e1);
					}
				}
			}
		} catch (ParseException e1) {
			log.error(dateAsDate);
		}
		log.debug("End mouse released");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// nothing
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// nothing
	}
}
