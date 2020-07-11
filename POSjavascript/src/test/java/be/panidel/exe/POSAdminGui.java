package be.panidel.exe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.ReportPeriod;
import be.panidel.businessLayer.helper.EnumHelper.PeriodType;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceDbInit;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceUnit;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.helper.SysHelper;
import be.panidel.dataLayer.helper.XlsProductDataReaderHelper;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.pos10.dao.PaymentMethodPos10Dao;
import be.panidel.pos10.dao.ProductPos10Dao;
import be.panidel.pos10.model.ProductPos10;
import be.panidel.pos10.model.ProductsPos10;
import be.panidel.pos10.model.RootPos10;
import be.panidel.pos10.model.RootVatRatesPos10;
import be.panidel.pos10.tool.ProcessAsCashSalesPos10;
import be.panidel.tarif.exception.IllegalTypeException;
import be.panidel.tarif.xlsWriter.XlsAfficheBodyWriter;
import be.panidel.tarif.xlsWriter.XlsCashSaleWriter;
import be.panidel.tarif.xlsWriter.XlsCashSaleWriter.ReportType;

public class POSAdminGui {

	private final static Logger LOG = LoggerFactory.getLogger(POSAdminGui.class);

	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {

				SysHelper.displayMemoryUtilization("POS ADMIN GUI MAIN");
				new POSAdminGui();
			}
		}.start();
	}

	public POSAdminGui() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				JFrame frame = new JFrame("POS Admin UI");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());

				frame.addWindowListener(new CloseWindowsAdapter());

				frame.add(new POSAdminGuiPanel());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class POSAdminGuiPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private JButton updateReferentialsBtn;
		private JButton createDBBtn;
		private JButton listConnectedHostActionBtn;
		private JButton generateAfficheBodyBtn;
		private JButton writePos10CatalogBtn;
		private JButton listProductsBtn;
		private JButton listProduct10sBtn;
		private JButton pos10MigrationBtn;
		private JButton computeSalesBtn;

		private JList<PeriodType> periodicityList;
		private JTextField fromTxtField;
		private JTextField toTxtField;

		private JList<PersistenceUnit> persistenceList;
		private JTextArea textArea;

		private JTextArea grandeZoneTextArea;
		private JScrollPane grandeZoneTextAreaWithScorllPane;

		public POSAdminGuiPanel() {

			LOG.debug("Init UI");

			setLayout(new FlowLayout());

			persistenceList = new JList<>(PersistenceUnit.values());
			persistenceList.addListSelectionListener(new PeristenceUnitListSelectionListner());
			add(persistenceList);

			createDBBtn = new JButton("Create DB");
			createDBBtn.addActionListener(new CreateDBActionListner());
			createDBBtn.setEnabled(false);
			add(createDBBtn);

			updateReferentialsBtn = new JButton("Update referentials");
			updateReferentialsBtn.addActionListener(new UpdateReferentialsActionListner());
			updateReferentialsBtn.setEnabled(false);
			add(updateReferentialsBtn);

			generateAfficheBodyBtn = new JButton("Générer le corp de l'affiche");
			generateAfficheBodyBtn.addActionListener(new GenerateAfficheBodyActionListner());
			generateAfficheBodyBtn.setEnabled(false);
			add(generateAfficheBodyBtn);

			writePos10CatalogBtn = new JButton("Write Pos10 Catalog");
			writePos10CatalogBtn.addActionListener(new WritePos10CatalogActionListner());
			writePos10CatalogBtn.setEnabled(false);
			add(writePos10CatalogBtn);

			listProductsBtn = new JButton("Liste des produits");
			listProductsBtn.addActionListener(new ListProductsActionListner());
			listProductsBtn.setEnabled(false);
			add(listProductsBtn);

			listProduct10sBtn = new JButton("Liste des produits POS 10");
			listProduct10sBtn.addActionListener(new ListProduct10sActionListner());
			listProduct10sBtn.setEnabled(false);
			add(listProduct10sBtn);

			pos10MigrationBtn = new JButton("Migration POS 10");
			pos10MigrationBtn.addActionListener(new Pos10MigrationActionListner());
			pos10MigrationBtn.setEnabled(false);
			add(pos10MigrationBtn);

			fromTxtField = new JTextField("20170712");
			toTxtField = new JTextField("20170712");
			add(new JLabel("From : "));
			add(fromTxtField);
			add(new JLabel("To : "));
			add(toTxtField);

			DefaultListModel<PeriodType> listModel = new DefaultListModel<>();
			listModel.addElement(PeriodType.CASH_SALE_UNIT);
			listModel.addElement(PeriodType.DAY);
			listModel.addElement(PeriodType.MONTH);
			listModel.addElement(PeriodType.YEAR);
			periodicityList = new JList<>(listModel);
			periodicityList.setSelectedIndex(1);
			add(periodicityList);

			computeSalesBtn = new JButton("Compute sales");
			computeSalesBtn.addActionListener(new ComputeSalesActionListner());
			computeSalesBtn.setEnabled(false);
			add(computeSalesBtn);

			listConnectedHostActionBtn = new JButton("Liste des connections à la DB");
			listConnectedHostActionBtn.addActionListener(new ListConnectedHostActionListner());
			listConnectedHostActionBtn.setEnabled(false);
			add(listConnectedHostActionBtn);

			textArea = new JTextArea(10, 80);
			textArea.setLineWrap(true);
			add(textArea);

			grandeZoneTextArea = new JTextArea(25, 80);
			grandeZoneTextAreaWithScorllPane = new JScrollPane(grandeZoneTextArea);
			add(grandeZoneTextAreaWithScorllPane);

			JButton closeBtn = new JButton("Close");
			closeBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			add(closeBtn);

			setSize(1600, 4000);
			setVisible(true);

			LOG.debug("Init UI done");

		}

		Date getFromDay() {
			GregorianCalendar fromDay = ProcessAsCashSalesPos10.fromTextYYYYMMDDToDate(fromTxtField.getText());
			if (fromDay == null) {
				initFromDay();
				fromDay = ProcessAsCashSalesPos10.fromTextYYYYMMDDToDate(fromTxtField.getText());
			}
			return fromDay.getTime();

		}

		Date getToDay() {
			GregorianCalendar toDay = ProcessAsCashSalesPos10.fromTextYYYYMMDDToDate(toTxtField.getText());
			if (toDay == null) {
				initToDay();
				toDay = ProcessAsCashSalesPos10.fromTextYYYYMMDDToDate(toTxtField.getText());
			}
			toDay.add(Calendar.DAY_OF_MONTH, 1);
			return toDay.getTime();

		}

		void initFromDay() {
			fromTxtField.setText("20000101");
		}

		void initToDay() {
			fromTxtField.setText("21001231");
		}

		void setMainMessage(String message) {
			textArea.setText(message);
		}

		void messageAddNewLine(String message) {
			grandeZoneTextArea.setText(grandeZoneTextArea.getText());
			grandeZoneTextArea.setText(grandeZoneTextArea.getText() + "\n" + message);
		}

		void resetPersistenceListSelectedValue() {
			persistenceList.clearSelection();
		}

		PeriodType getPeriodicity() {
			return periodicityList.getSelectedValue();
		}

		public class PeristenceUnitListSelectionListner implements ListSelectionListener {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				LOG.info("[" + persistenceList.getSelectedIndex() + "/" + persistenceList.getSelectedValue() + "]");

				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START Select persist unit");

				PersistenceUnit persistenceUnit = null;

				if (persistenceList.getSelectedIndex() >= 0) {
					persistenceUnit = persistenceList.getSelectedValue();
				}

				if (persistenceUnit == null) {
					messageAddNewLine("No persistence unit selected");
				} else {
					try {
						DataLayerHelper.selectCurrentPersistenceUnit(persistenceUnit);
					} catch (DataLayerException e1) {
						LOG.error("" + persistenceUnit, e1);
						messageAddNewLine("ERROR:" + persistenceUnit + "/" + e1.getMessage());
						resetPersistenceListSelectedValue();
					} finally {

						updateReferentialsBtn.setEnabled(true);
						// listConnectedHostActionBtn.setEnabled(true);
						generateAfficheBodyBtn.setEnabled(true);
						listProductsBtn.setEnabled(true);
						pos10MigrationBtn.setEnabled(true);
						listProduct10sBtn.setEnabled(true);
						writePos10CatalogBtn.setEnabled(true);
						computeSalesBtn.setEnabled(true);
						if (PersistenceDbInit.VALIDATE.equals(persistenceUnit.getPersistenceDbInit())) {
							createDBBtn.setEnabled(false);
						} else {
							createDBBtn.setEnabled(true);
						}

						switch (persistenceUnit.getEnvironmentType()) {
						case DEV:
							textArea.setBackground(Color.GREEN);
							textArea.setForeground(Color.BLACK);
							break;
						case RECETTE:
							listConnectedHostActionBtn.setEnabled(true);
							textArea.setBackground(Color.BLUE);
							textArea.setForeground(Color.WHITE);
							break;
						case PROD:
							listConnectedHostActionBtn.setEnabled(true);
							textArea.setBackground(Color.RED);
							textArea.setForeground(Color.WHITE);
							break;
						}

						textArea.setText(DataLayerHelper.getDescription());

					}
				}

				SysHelper.displayMemoryUtilization("END Select persist unit");

			}
		}

		class UpdateReferentialsActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {

				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START update referential");

				try {

					LOG.info("Update Vat Rates.");
					messageAddNewLine("Update Vat Rates.");
					File vatRatesFile = new File(DAOConfig.RESOURCE_BASE + DAOConfig.POS10_VATRATE);
					RootVatRatesPos10 vatRates = (RootVatRatesPos10) MarshalHelper.unmarchalXml(RootVatRatesPos10.class,
							vatRatesFile);
					DataFacade.instance.updateDbVatRates(vatRates.getVatrates().getVatrate());

					LOG.info("Update Products.");
					messageAddNewLine("Update Products.");
					Collection<ProductModel> products = XlsProductDataReaderHelper.parseXlsxfile();
					DataFacade.instance.updateDbProducts(products);

					LOG.info("Update Payment Methods.");
					messageAddNewLine("Update Payment Methods.");
					Collection<PaymentMethodModel> paymentMethods = PaymentMethodPos10Dao.instance
							.getPaymentMethodList();
					DataFacade.instance.updateDbPaymentMethods(paymentMethods);

				} catch (InvalidFormatException | IOException | IllegalTypeException | DataLayerException
						| JAXBException e) {
					// create to string
					LOG.error("DAOConfig.toString()", e);
				}

				SysHelper.displayMemoryUtilization("END update referential");

			}

		}

		class ListProductsActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {

				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START list products");

				try {

					LOG.info("List products.");
					List<ProductModel> products = DataFacade.instance.getAllProducts();

					String message = "Liste des produits :";
					for (ProductModel product : products) {
						message += "\n";
						message += "" + product;
					}

					messageAddNewLine(message);

				} catch (DataLayerException e) {
					LOG.error("", e);
				}

				SysHelper.displayMemoryUtilization("END list products");

			}

		}

		class ListProduct10sActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {

				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START list products");

				LOG.info("List products POS10.");
				try {

					Map<Integer, ProductPos10> product10s = ProductPos10Dao.instance.getProductCatalog();

					TreeSet<Integer> indexProduct10s = new TreeSet<>(product10s.keySet());

					String message = "Liste des produits :";
					for (Integer key : indexProduct10s) {

						ProductPos10 productPos10 = product10s.get(key);

						message += "\n";
						message += "" + productPos10;

						LOG.info("" + key + " found : " + (productPos10 != null));

					}

					messageAddNewLine(message);
				} catch (InvalidFormatException | IOException | IllegalTypeException e) {
					LOG.error("", e);
					messageAddNewLine("Error : " + e.getMessage());
				}

				SysHelper.displayMemoryUtilization("END list products");

			}

		}

		class Pos10MigrationActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {

				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START POS 10 migrations");

				ProcessAsCashSalesPos10.pos10Migration(getFromDay(), getToDay());

				SysHelper.displayMemoryUtilization("END POS 10 migrations");

			}

		}

		class ComputeSalesActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {

				try {

					SysHelper.maxMemoryRatio = 0;
					SysHelper.displayMemoryUtilization("START compute sales action");

					LOG.info("Cashsales from : " + getFromDay() + " to : " + getToDay());
					messageAddNewLine("Cashsales from : " + getFromDay() + " to : " + getToDay());

					ReportPeriod reportPeriod = new ReportPeriod(getFromDay(), getToDay(), getPeriodicity());

					XlsCashSaleWriter.writeCashsalesReport(reportPeriod, ReportType.WITH_ITEM_DETAILS);

				} catch (DataLayerException | InvalidFormatException | JAXBException | IOException
						| IllegalTypeException e) {
					LOG.error("", e);
				}

				messageAddNewLine("DONE");

				SysHelper.displayMemoryUtilization("END compute sales action");

			}

		}

		class GenerateAfficheBodyActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START GEN affiche body");

				try {

					LOG.info("Write Affiche Catalog.");

					Collection<ProductModel> products = DataFacade.instance.getAllProducts();

					XlsAfficheBodyWriter.writeAfficheModel(products);

					LOG.info("Write Affiche Catalog DONE.");
					messageAddNewLine("Write Affiche Catalog DONE.");

				} catch (InvalidFormatException | IOException | IllegalTypeException | DataLayerException
						| JAXBException e) {
					// TODO create to string
					LOG.error("DAOConfig.toString()", e);
				}

				SysHelper.displayMemoryUtilization("END GEN affiche body");

			}

		}

		class WritePos10CatalogActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START write pos 10 cat");

				try {

					LOG.info("Write pos 10 Catalog.");

					Map<Integer, ProductPos10> productCatalog = ProductPos10Dao.instance.getProductCatalog();

					ProductsPos10 productsPos10 = new ProductsPos10();
					RootPos10 rootPos10 = new RootPos10();
					rootPos10.setProductsPos10(productsPos10);
					productsPos10.setProductPos10s(new ArrayList<>(productCatalog.values()));

					LOG.debug("Write file to catalog file : " + rootPos10);
					MarshalHelper.marchalToXml(rootPos10, DAOConfig.CATALOG_FILE);

					LOG.info("Write pos 10 Catalog DONE : " + DAOConfig.CATALOG_FILE);
					messageAddNewLine("Write pos 10 Catalog DONE.");

				} catch (JAXBException | InvalidFormatException | IOException | IllegalTypeException e) {
					// TODO create to string
					LOG.error("DAOConfig.toString()", e);
				}

				SysHelper.displayMemoryUtilization("END write pos 10 cat");

			}

		}

		class CreateDBActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START create DB");

				LOG.info("START");

				LOG.info("END : " + DataLayerHelper.createDbSchema());

				SysHelper.displayMemoryUtilization("END create DB");

			}

		}

		class ListConnectedHostActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				SysHelper.maxMemoryRatio = 0;
				SysHelper.displayMemoryUtilization("START list connect host");

				LOG.debug("START");

				long count = 0;

				Connection conn = null;
				Statement stmt = null;

				try {

					conn = DataLayerHelper.getConn();

					if (conn == null) {
						messageAddNewLine("Not allowed : " + DataLayerHelper.getDescription());
						LOG.info("Not allowed : " + DataLayerHelper.getDescription());
						return;
					} else {

						stmt = conn.createStatement();

						ResultSet rs = stmt.executeQuery(DAOConfig.MYSQL_PROCESS_LIST_QUERY);
						while (rs.next()) {
							count++;
							String hostName = rs.getString("HOST_NAME");
							String hostCount = rs.getString("HOST_COUNT");
							LOG.debug("Host : " + hostName + "/" + hostCount);
						}
					}

				} catch (SQLException | JAXBException e) {
					LOG.warn("", e);
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							LOG.error("", e);
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							LOG.error("", e);
						}
					}
				}

				SysHelper.displayMemoryUtilization("END list connect host");

				messageAddNewLine("Number of host connected : " + count);
				LOG.info("Number of host connected : " + count);

				LOG.debug("END");

			}

		}

	}

	class CloseWindowsAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
}
