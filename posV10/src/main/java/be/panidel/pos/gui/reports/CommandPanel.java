package be.panidel.pos.gui.reports;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.commandPanel.AbstractButton;
import be.panidel.pos.gui.commandPanel.GuiPanel;
import be.panidel.tools.Alerting;
import be.panidel.tools.SalesFilesGroupingTool;

public class CommandPanel extends JPanel implements GuiPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("CommandPanel");

	private static Panel reportPanelByDay;
	private static Panel reportPanelBySupplies;
	private static Panel reportDayDetail;
	private static Panel reportDayDetailForCashBook;
	private static Panel reportCashBook;
	private static Panel reportSplitTVA;
	private static Panel reportPrintReports;

	private AbstractButton refreshButton = refreshButton();

	protected JTable salesTable;
	protected JScrollPane salesPane;

	private JTextArea ta = new JTextArea();
	private PopupFactory factory = PopupFactory.getSharedInstance();
	private final Popup popup = factory.getPopup(this, ta, 200, 200);

	public AbstractButton resSplitTVABtn() {
		return new AbstractButton("<html><center>Ventilation<br>TVA<br><html>") {

			private static final long serialVersionUID = 6500209148976455071L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportSplitTVA.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton resByDayBtn() {
		return new AbstractButton(
				"<html><center>Résultats<br>par<br>jour<html>") {

			private static final long serialVersionUID = 6500209148976455071L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportPanelByDay.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton resBySupplies() {
		return new AbstractButton(
				"<html><center>Résultats<br>par<br>marchandises</html>") {

			private static final long serialVersionUID = 344178102205979571L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportPanelBySupplies.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton resCashBookDayDetail() {
		return new AbstractButton(
				"<html><center>Rapport<br>de caisse<br>détaillé<html>") {

			private static final long serialVersionUID = -8183001045800736586L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportDayDetailForCashBook.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton resCashBook() {
		return new AbstractButton("<html><center>Rapport<br>de<br>caisse<html>") {

			private static final long serialVersionUID = -8183001045800736586L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportCashBook.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton resDayDetail() {
		return new AbstractButton("<html><center>Détail<br>des<br>ventes<html>") {

			private static final long serialVersionUID = -8183001045800736586L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportDayDetail.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	private AbstractButton prtReports() {
		return new AbstractButton(
				"<html><center>Impression<br>des<br>résultats<html>") {

			private static final long serialVersionUID = -8183001045800736586L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				try {
					reportPrintReports.show();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	private AbstractButton refEditor() {
		return new AbstractButton(
				"<html><center>Edition<br>des<br>référentiels</html>") {

			private static final long serialVersionUID = -8183001045800736586L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				ReferencialEditor.createAndShowGUI(true);
			}
		};
	}

	private AbstractButton editKeyboard() {
		return new AbstractButton(
				"<html><center>Edition<br>du<br>clavier</html>") {

			private static final long serialVersionUID = 7067318959405257964L;

			@Override
			public void actionPerformed(ActionEvent e) {
				popup.hide();
				SelectButtonPanel.createAndShowGUI(true);
			}
		};
	}

	private AbstractButton displayParameters() {

		return new AbstractButton(
				"<html><center>Affichage<br>paramètres</html>") {

			private static final long serialVersionUID = 7067318959405257964L;

			@Override
			public void actionPerformed(ActionEvent e) {
				int maxKeyLength = 0;
				StringBuffer sb = new StringBuffer();
				try {
					TreeMap<Object, Object> treeParam = new TreeMap<Object, Object>(
							POSParameters.instance());
					for (Object key : treeParam.keySet()) {
						Object value = treeParam.get(key);
						String valueAsString = value.toString();
						int keyLength = key.toString().trim().length();
						if (keyLength > maxKeyLength) {
							maxKeyLength = keyLength;
						}
						if (value instanceof BigDecimal) {
							BigDecimal valueAsDecimal = (BigDecimal) value;
							valueAsString = valueAsDecimal.toPlainString();
						}
						sb.append(" " + key + "-->" + valueAsString + "\n");
					}
				} catch (ParameterException e1) {
					sb.append("error");
				}
				ta.setText(sb.toString());
				popup.show();
			}
		};
	}

	public AbstractButton close() {
		return new AbstractButton(
				"<html><center> <br>Fermer<br>TOUT<br> </html>") {

			private static final long serialVersionUID = 2855803317842622338L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					popup.hide();
					reportPanelByDay.hide();
					reportPanelBySupplies.hide();
					reportDayDetail.hide();
					reportDayDetailForCashBook.hide();
					reportCashBook.hide();
					reportSplitTVA.hide();
					reportPrintReports.hide();
					ReferencialEditor.createAndShowGUI(false);
					SelectButtonPanel.createAndShowGUI(false);
					CommandPanel.instance().hide();
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	public AbstractButton refreshButton() {
		return new AbstractButton("<html><center> <br>Refresh<br><br> </html>") {

			private static final long serialVersionUID = 2855803317842622338L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					((CommandPanel) CommandPanel.instance().getjPanel())
							.refresh(null);
				} catch (DAOException e1) {
					log.error("", e1);
				}
			}
		};
	}

	private CommandPanel() {
		super();
		initComponents();
	}

	public void initComponents() {

		log.debug("START initComponents");

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.add(resByDayBtn());
		panel.add(resBySupplies());
		panel.add(resCashBook());
		panel.add(resCashBookDayDetail());
		panel.add(resSplitTVABtn());
		panel.add(resDayDetail());
		panel.add(refEditor());
		panel.add(editKeyboard());
		panel.add(displayParameters());
		panel.add(prtReports());
		panel.add(refreshButton);
		panel.add(close());

		add(panel);

		log.debug("STOP initComponents");

	}

	private static Panel instance;

	public static boolean hasInstance() {
		return instance != null;
	}

	public static Panel instance() throws DAOException {
		if (instance == null) {
			log.info("Init panels");
			CommandPanel cp = new CommandPanel();
			instance = new Panel(cp, "Menu", false);
			reportPanelByDay = new Panel(new ReportPanelByDay(instance),
					"Ventes par jour", true);
			reportPanelBySupplies = new Panel(new ReportPanelBySupplies(
					instance), "Matières premières");
			reportDayDetail = new Panel(new ReportDayDetail(instance),
					"Détail des ventes d'un jour");
			reportDayDetailForCashBook = new Panel(
					new ReportDayDetailForCashBook(instance),
					"Rapport de caisse détaillée");
			reportCashBook = new Panel(new ReportCashBook(instance),
					"Rapport de caisse");
			reportSplitTVA = new Panel(new ReportSplitTva(instance),
					"Ventilation TVA");
			reportPrintReports = new Panel(new PrintReports(instance),
					"Impression rapports");
		}
		return instance;
	}

	public static void main(String[] args) {
		try {
			CommandPanel.instance().show();
		} catch (DAOException e) {
			log.error("", e);
		}
		//TODO REMOVE
		Alerting.init();
	}

	public Panel getSummaryPanelByDay() {
		return reportPanelByDay;
	}

	public Panel getSummaryPanelBySupplies() {
		return reportPanelBySupplies;
	}

	public Panel getSummaryDayDetail() {
		return reportDayDetail;
	}

	public Panel getSummaryDayDetailForCashBook() {
		return reportDayDetailForCashBook;
	}

	public Panel getSummaryCashBook() {
		return reportCashBook;
	}

	@Override
	public void refresh(PeriodBean periodBean) throws DAOException {
		try {
			SalesFilesGroupingTool
					.doIt(POSParameters.instance()
							.getResultsStorageCaissesSales(), POSParameters
							.instance().getResultsStorageCaissesGroupbyday(),
							POSParameters.instance()
									.getResultsStorageCaissesArchives(),
							POSParameters.instance()
									.getResultsStorageCaissesRejected());
			refreshButton.setText("<html><center>Mise à jour<br><br>"
					+ POSConstants.SDF_SHORTDATE_SHORTTIME.format(new Date())
					+ "<br> </html>");
		} catch (ParameterException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void print(boolean showPrintDialog) throws PrinterException {
		log.error("Not implemented");
	}
}
