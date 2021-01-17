package be.panidel.pos.gui.reports;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.OperationUnitList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class ReportDayDetail extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("SummaryDayDetail");

	private final static String[][] TABLE_HEADER = { { "   ", "10" },
			{ "Qui", "30" }, { "Caisse", "80" }, { "debut", "60" },
			{ "duree", "30" }, { "QTot", "30" }, { "total", "30" },
			{ "Qté", "30" }, { "Description", "100" }, { "prix", "30" },
			{ "emporté", "30" }, { "surPlace", "30" }, { "total", "60" },
			{ "Payement", "100" }, { "Valeur", "30" } };

	protected int viewColumnMax;
	protected Object[] salesTableHeader;

	public ReportDayDetail(Panel cp) {
		super(cp);
	}

	public ReportDayDetail() {
		super();
	}

	public void initSpecificComponents() throws DAOException {

		log.debug("Init specific components");

		viewColumnMax = TABLE_HEADER.length;

		salesTableHeader = new Object[viewColumnMax];
		int i = 0;
		while (i < TABLE_HEADER.length) {
			salesTableHeader[i] = TABLE_HEADER[i];
			i++;
		}
	}

	@Override
	void afterInitComponents() throws DAOException {
	}

	@Override
	void initRefresh() throws ParameterException {
		endDatePanel.setVisible(false);
		setStartDate(Tools.startOfCurentDay(getStartDate()));
		setEndDate(Tools.endOfDay(getStartDate()));
	}

	@Override
	TableModel fullFillTable(String[] tableHeader) throws DAOException,
			ParameterException {

		int rows = 0;
		OperationUnitList operationUnitList = FacadeDAO
				.instance()
				.getOperationUnitDAO()
				.extractSalesOfPeriod(
						POSParameters.instance()
								.getResultsStorageCaissesGroupbyday(),
						new PeriodBean(getStartDate(), getEndDate()));

		for (OperationUnit operationUnit : operationUnitList.values()) {
			rows += Math.max(operationUnit.getItemList().size(), operationUnit
					.getPayList().size());
			rows++;
		}

		Object[][] table = new Object[rows][salesTableHeader.length];

		log.debug("START ITERATE byDaySales");
		int rowIndex = 0;
		int colIndex = 0;

		for (StringReverseOrder dateAsString : operationUnitList.keySet()) {

			colIndex = 0;

			OperationUnit operationUnit = operationUnitList.get(dateAsString);

			if (operationUnit.isCancelled()) {
				table[rowIndex][colIndex] = "Annulé";
			} else {
				if (operationUnit.getTakeOnPlace() != null
						&& operationUnit.getTakeOnPlace().booleanValue()) {
					table[rowIndex][colIndex] = "Salle";
				}
			}
			colIndex++;
			if (operationUnit.getEmployee() != null) {
				table[rowIndex][colIndex++] = operationUnit.getEmployee()
						.getCode();
			} else {
				table[rowIndex][colIndex++] = "---";
			}
			table[rowIndex][colIndex++] = operationUnit.getComputerName();
			table[rowIndex][colIndex++] = POSConstants.SDF_SHORTDATE_SHORTTIME
					.format(operationUnit.getBeginTime());
			long executionTime = operationUnit.getEndTime().getTime()
					- operationUnit.getBeginTime().getTime();
			table[rowIndex][colIndex++] = executionTime / 1000;
			table[rowIndex][colIndex++] = operationUnit.getItemQuantity()
					.toPlainString();
			table[rowIndex][colIndex++] = operationUnit.getTotalTVAC()
					.toPlainString();

			colIndex = appendItems(operationUnit.getItemList(), rowIndex,
					colIndex, table);

			int greaterList = Math.max(operationUnit.getItemList().size(),
					operationUnit.getPayList().size());

			colIndex = appendPay(operationUnit.getPayList(), rowIndex
					+ greaterList - operationUnit.getPayList().size(),
					colIndex, table);

			rowIndex += greaterList;

			rowIndex++;

		}

		return new DefaultTableModel(table, tableHeader);

	}

	private int appendItems(List<Item> itemList, int rowIndex, int colIndex,
			Object[][] table) {

		int offset = 0;
		for (Item item : itemList) {
			table[rowIndex + offset][colIndex] = item.getQuantity()
					.toPlainString();
			table[rowIndex + offset][colIndex + 1] = item.getDescription();
			table[rowIndex + offset][colIndex + 2] = item.getUnitPrice()
					.toPlainString();
			table[rowIndex + offset][colIndex + 3] = item.getTvaTakeAway()
					.toPlainString();
			table[rowIndex + offset][colIndex + 4] = item.getTvaTakeOnPlace()
					.toPlainString();
			table[rowIndex + offset][colIndex + 5] = item.getTotalTVAC()
					.toPlainString();
			offset++;
		}
		return colIndex + 5;
	}

	private int appendPay(List<Item> itemList, int rowIndex, int colIndex,
			Object[][] table) {

		int offset = 0;
		for (Item item : itemList) {
			table[rowIndex + offset][colIndex] = item.getDescription();
			table[rowIndex + offset][colIndex + 1] = item.getTotalTVAC();
			offset++;
		}
		return colIndex + 2;
	}

	@Override
	String[][] getTableHeader() {
		return TABLE_HEADER;
	}

	@Override
	String getTitle() {
		return "Détail par jour";
	}

}