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

public class ReportDayDetailForCashBook extends ReportDayDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger("SummaryDayDetailForCashBook");

	private final static String[][] TABLE_HEADER = { { "   ", "5" },
			{ "debut", "15" }, { "Qté", "15" }, { "Description", "200" },
			{ "prix", "15" }, { "Payement", "100" }, { "Valeur", "15" } };

	public ReportDayDetailForCashBook(Panel cp) {
		super(cp);
	}

	public ReportDayDetailForCashBook() {
		super();
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
			if (isEligible(operationUnit)) {
				rows += Math.max(operationUnit.getItemList().size(),
						operationUnit.getPayList().size());
				rows++;
			}
		}

		Object[][] table = new Object[rows + 2][salesTableHeader.length];

		log.debug("START ITERATE byDaySales");
		int rowIndex = 0;
		int colIndex = 0;

		table[rowIndex][3] = POSConstants.SDF_FULLDAY.format(getStartDate());
		rowIndex++;
		rowIndex++;

		for (StringReverseOrder dateAsString : operationUnitList.keySet()) {

			colIndex = 0;

			OperationUnit operationUnit = operationUnitList.get(dateAsString);

			if (isEligible(operationUnit)) {
				if (operationUnit.isCancelled()) {
					table[rowIndex][colIndex] = "Annulé";
				} else {
					if (operationUnit.getTakeOnPlace() != null
							&& operationUnit.getTakeOnPlace().booleanValue()) {
						table[rowIndex][colIndex] = "Salle";
					}
				}
				colIndex++;
				table[rowIndex][colIndex++] = POSConstants.SDF_TIME
						.format(operationUnit.getBeginTime());
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
		}

		return new DefaultTableModel(table, tableHeader);
	}

	protected boolean isEligible(OperationUnit operationUnit) {
		return true;
	}

	public int appendItems(List<Item> itemList, int rowIndex, int colIndex,
			Object[][] table) {

		int offset = 0;
		for (Item item : itemList) {
			table[rowIndex + offset][colIndex] = item.getQuantity()
					.toPlainString();
			table[rowIndex + offset][colIndex + 1] = item.getDescription();
			table[rowIndex + offset][colIndex + 2] = item.getUnitPrice()
					.toPlainString();
			offset++;
		}
		return colIndex + 3;
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
}