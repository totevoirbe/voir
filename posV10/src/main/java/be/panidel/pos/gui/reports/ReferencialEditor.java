/*
 * MainPage.java
 *
 * Created on __DATE__, __TIME__
 */

package be.panidel.pos.gui.reports;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.management.Payement;
import be.panidel.management.Product;
import be.panidel.management.impl.person.Employee;
import be.panidel.pos.gui.toolsPanel.tables.TableEmployee;
import be.panidel.pos.gui.toolsPanel.tables.TableGroup;
import be.panidel.pos.gui.toolsPanel.tables.TablePayement;
import be.panidel.pos.gui.toolsPanel.tables.TableProduct;
import be.panidel.tools.RefTableModel;
import be.panidel.tools.TableIdentification;
import be.panidel.tools.Tools;

/**
 * 
 * @author __USER__
 */
public class ReferencialEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1983737024486108991L;
	private DAO currentDAO;

	protected static ReferencialEditor contentPane;

	private JScrollPane jScrollPane2 = new JScrollPane();
	private JTable jTable1 = new JTable();
	private JButton jButtonSave = new JButton();
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();

	private JMenu jMenuSelectRef = new JMenu();
	private JMenuItem jMenuProductItem = new JMenuItem();
	private JMenuItem jMenuEmployeeItem = new JMenuItem();
	private JMenuItem jMenuPayItem = new JMenuItem();
	private JMenuItem jMenuGroupeItem = new JMenuItem();

	private JMenuBar menuBar = new JMenuBar();

	private JCheckBox jCheckBox = new JCheckBox();

	private String header[] = null;
	private int colWidth[] = null;

	private static final Logger log = Logger.getLogger(ReferencialEditor.class);

	/** Creates new form MainPage */
	public ReferencialEditor() {
		super();

		initComponents();

	}

	private TableIdentification instantiate(Identification identification,
			boolean extended) {
		TableIdentification tableIdentification = null;
		if (identification instanceof Product) {
			tableIdentification = new TableProduct(identification, extended);
		} else if (identification instanceof Group) {
			tableIdentification = new TableGroup(identification);
		} else if (identification instanceof Employee) {
			tableIdentification = new TableEmployee(identification);
		} else if (identification instanceof Payement) {
			tableIdentification = new TablePayement(identification);
		}
		return tableIdentification;
	}

	private void initComponents() {

		load(FacadeDAO.instance().getProductsDAO());

		jScrollPane2.setViewportView(jTable1);
		jScrollPane2.getVerticalScrollBar()
				.setMinimumSize(new Dimension(50, 0));
		jScrollPane2.getVerticalScrollBar().setPreferredSize(
				new Dimension(50, 0));

		jButtonSave.setText("Enregistrer");
		jButtonSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				save(evt);
				load(currentDAO);
			}
		});

		jButton2.setText("Annuler");
		jButton2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				refresh(evt);
			}
		});

		jButton3.setText("Nouvelle ligne");
		jButton3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					newRow(evt);
				} catch (DAOException e) {
					log.error(e);
				}
			}
		});

		jCheckBox.setText("détail");
		jCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				displayCodeMode(evt);
			}
		});

		jMenuSelectRef.setText("File s\u00e9lection");

		jMenuProductItem.setText("Product");
		jMenuProductItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadProducts(evt);
			}
		});
		jMenuSelectRef.add(jMenuProductItem);

		jMenuEmployeeItem.setText("employee");
		jMenuEmployeeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadEmployees(evt);
			}
		});
		jMenuSelectRef.add(jMenuEmployeeItem);

		jMenuPayItem.setText("payement");
		jMenuPayItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadPayements(evt);
			}
		});
		jMenuSelectRef.add(jMenuPayItem);

		jMenuGroupeItem.setText("groupe");
		jMenuGroupeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadGroups(evt);
			}
		});
		jMenuSelectRef.add(jMenuGroupeItem);

		menuBar.add(jMenuSelectRef);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(jScrollPane2);

		JPanel actionPane = new JPanel(new GridLayout(1, 0));

		actionPane.setPreferredSize(new Dimension(500, 50));
		actionPane.setMaximumSize(new Dimension(500, 50));

		actionPane.add(new ButtonCommand());
		actionPane.add(jButton3);
		actionPane.add(jButtonSave);
		actionPane.add(jButton2);
		actionPane.add(jCheckBox);

		this.add(actionPane);
	}

	private void newRow(MouseEvent evt) throws DAOException {
		TableModel tableModel = addRow(jTable1);
		setModelToTable(tableModel);
	}

	private void save(MouseEvent evt) {
		try {
			saveTableModel(jTable1.getModel());
		} catch (DAOException e) {
			log.error("", e);
		}
	}

	private void refresh(AWTEvent evt) {
		try {
			setModelToTable(initTableModel());
		} catch (DAOException e) {
			log.error("", e);
		}
	}

	private void displayCodeMode(ActionEvent evt) {
		try {
			jButtonSave.setEnabled(!((JCheckBox) evt.getSource()).isSelected());

			setModelToTable(initTableModel());

			for (int i = 0; i < colWidth.length; i++) {
				jTable1.getColumnModel().getColumn(i).setPreferredWidth(
						colWidth[i]);
			}

		} catch (DAOException e) {
			log.error("", e);

		}
	}

	private void setModelToTable(TableModel tm) {
		jTable1.setModel(tm);
		jTable1.setRowSorter(new TableRowSorter<TableModel>(tm));
	}

	private void loadGroups(ActionEvent evt) {
		load(FacadeDAO.instance().getGroupsDAO());
	}

	private void loadPayements(ActionEvent evt) {
		load(FacadeDAO.instance().getPayementModesDAO());
	}

	private void loadEmployees(ActionEvent evt) {
		load(FacadeDAO.instance().getEmployeesDAO());
	}

	private void loadProducts(ActionEvent evt) {
		load(FacadeDAO.instance().getProductsDAO());
	}

	private void load(DAO dao) {
		currentDAO = dao;
		try {
			setModelToTable(initTableModel());
			for (int i = 0; i < colWidth.length; i++) {
				jTable1.getColumnModel().getColumn(i).setPreferredWidth(
						colWidth[i]);
			}
		} catch (DAOException e) {
			log.error("", e);

		}
	}

	private TableModel initTableModel() throws DAOException {

		TableModel tm = null;

		List<Identification> identificationList = currentDAO.getList();

		boolean headerIsSetup = false;

		if (!Tools.isNullOrEmpty(identificationList)) {

			int row = 0;
			int rows = identificationList.size();
			Object[][] docArray = new Object[rows][];

			for (Identification identification : identificationList) {

				TableIdentification tableIdentification = instantiate(
						identification, jCheckBox.isSelected());
				docArray[row++] = tableIdentification.getElementAsArray();

				if (!headerIsSetup) {
					String[][] cols = tableIdentification.getHeader();
					header = new String[cols.length];
					colWidth = new int[cols.length];
					for (int i = 0; i < cols.length; i++) {
						header[i] = cols[i][0];
						colWidth[i] = Integer.parseInt(cols[i][1]);
					}
					headerIsSetup = true;
				}
			}
			tm = new RefTableModel(docArray, header);
		} else {
			tm = new RefTableModel(header, 0);
		}

		return tm;
	}

	private TableModel addRow(JTable table) throws DAOException {

		TableModel dtm = null;
		TableModel tableModel = table.getModel();
		int columnSize = tableModel.getColumnCount();
		int oldRowSize = tableModel.getRowCount();

		Object[][] docArray = new Object[oldRowSize + 1][columnSize];

		// copy existing data
		for (int rowIndex = 0; rowIndex < oldRowSize; rowIndex++) {
			for (int colIndex = 0; colIndex < columnSize; colIndex++) {
				docArray[rowIndex + 1][colIndex] = tableModel.getValueAt(
						rowIndex, colIndex);
			}
		}

		TableIdentification newElement = instantiate(currentDAO.getEmpty(),
				jCheckBox.isSelected());
		newElement.setId(newIndex(docArray));
		docArray[0] = newElement.getElementAsArray();

		dtm = new RefTableModel(docArray, header);

		return dtm;
	}

	private String newIndex(Object[][] docArray) throws DAOException {
		// new id
		int newIndex = 1;
		int arraySize = docArray.length - 1;
		String newIndexAsString = null;
		do {
			newIndexAsString = Integer.toString(newIndex++);
			int rowIndex = 0;
			for (; rowIndex < arraySize; rowIndex++) {
				String value = (String) docArray[rowIndex][0];
				if (value != null && newIndexAsString.equals(value.trim())) {
					break;
				}
			}
			if (rowIndex == arraySize
					&& !currentDAO.isIdExisting(newIndexAsString)) {
				return newIndexAsString;
			}
		} while (true);
	}

	private void saveTableModel(TableModel tableModel) throws DAOException {
		int rowSize = tableModel.getRowCount();
		Element node = currentDAO.deleteAll();
		for (int row = 0; row < rowSize; row++) {
			TableIdentification tableIdentification = instantiate(currentDAO
					.getEmpty(), jCheckBox.isSelected());
			tableIdentification.fulfillElementFromRow(tableModel, row);
			currentDAO.newElement(node, tableIdentification);
		}
		currentDAO.saveTable();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static JFrame doCreateAndShowGUI(ReferencialEditor sp, String title) {
		// Create and set up the window.
		JFrame frame = new JFrame(title);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		contentPane = sp;
		contentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(contentPane);

		frame.setJMenuBar(sp.getMenuBar());

		// Display the window.
		frame.pack();
		frame
				.setExtendedState(frame.getExtendedState()
						| JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		return frame;
	}

	private static JFrame instance;
	private static Object lock = new Object();

	public static void createAndShowGUI(boolean visible) {
		if (!visible) {
			if (instance != null) {
				instance.dispose();
			}
		} else {
			if (instance == null) {
				synchronized (lock) {
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							instance = doCreateAndShowGUI(
									new ReferencialEditor(),
									"Edition des listes référentiels");
						}
					});
				}
			} else {
				instance.setVisible(true);
			}
		}
	}

	public static void main(String[] args) {
		createAndShowGUI(true);
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
