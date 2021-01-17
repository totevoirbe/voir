package be.panidel.pos.gui.reports;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.impl.PeriodResult;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.ReportHelper;
import be.panidel.tools.Tools;

public class ReportCashBook extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("SummaryCashBook");

	public static final int PERIOD_TYPE_DAY = 1;
	public static final int PERIOD_TYPE_MONTH = 2;
	public static final int PERIOD_TYPE_YEAR = 3;

	private final static String[][] TABLE_HEADER = { { "Qté", "30" },
			{ "Libellé", "150" }, { "Total", "30" }, { "Qté", "30" },
			{ "Libellé", "150" }, { "Total", "30" } };

	private int viewColumnMax;
	private Object[] salesTableHeader;
	private JRadioButton byDayRadioButton;
	private JRadioButton byMonthRadioButton;
	private JRadioButton byYearRadioButton;
	private JCheckBox listRejectedProducts;

	public ReportCashBook(Panel cp) {
		super(cp);
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

		ButtonGroup periodButtonGroup = new ButtonGroup();
		byDayRadioButton = new JRadioButton("jour");
		byMonthRadioButton = new JRadioButton("mois");
		byYearRadioButton = new JRadioButton("an");
		listRejectedProducts = new JCheckBox("Détail jetés");

		periodButtonGroup.add(byDayRadioButton);
		periodButtonGroup.add(byMonthRadioButton);
		periodButtonGroup.add(byYearRadioButton);

		JPanel periodPane = new JPanel(new GridLayout(0, 1));

		periodPane.add(byDayRadioButton);
		periodPane.add(byMonthRadioButton);
		periodPane.add(byYearRadioButton);
		periodPane.add(listRejectedProducts);

		byDayRadioButton.setSelected(true);

		actionPane.add(periodPane);
	}

	@Override
	void afterInitComponents() throws DAOException {
	}

	@Override
	void initRefresh() throws ParameterException {
		endDatePanel.setEnabled(false);
		if (byDayRadioButton.isSelected()) {
			setStartDate(Tools.startOfCurentDay(getStartDate()));
			setEndDate(Tools.endOfDay(getStartDate()));
		} else if (byMonthRadioButton.isSelected()) {
			setStartDate(Tools.startOfMonth(getStartDate()));
			setEndDate(Tools.endOfMonth(getStartDate()));
		} else if (byYearRadioButton.isSelected()) {
			setStartDate(Tools.startOfYear(getStartDate()));
			setEndDate(Tools.endOfYear(getStartDate()));
		}
	}

	@Override
	TableModel fullFillTable(String[] tableHeader) throws DAOException,
			ParameterException {
		Object[][] table = null;

		try {

			PeriodResult periodResult = new PeriodResult(new PeriodBean(
					getStartDate(), getEndDate()), POSParameters.instance()
					.getResultsStorageCaissesGroupbyday(),
					listRejectedProducts.isSelected());

			int rows = tableSizeReservation(0, periodResult);

			table = new Object[rows][salesTableHeader.length];

			log.debug("START ITERATE byDaySales");
			int rowIndex = 0;

			rowIndex = writeTable(table, rowIndex, periodResult);
		} catch (ParseException e) {
			throw new DAOException("Erreur grave", e);
		}

		return new DefaultTableModel(table, tableHeader);
	}

	private int tableSizeReservation(int rows, PeriodResult dr) {
		rows += 4;
		rows += dr.getHtva().size();
		rows += dr.getProductSales().size() / 2 + 1;
		rows += dr.getRejectedProductList().size() / 2 + 1;
		rows += dr.getPaymentsByType().size() / 2 + 1;
		return rows;
	}

	private int writeTable(Object[][] table, int rowIndex,
			PeriodResult dayResult) {
		if (byDayRadioButton.isSelected()) {
			table[rowIndex][1] = POSConstants.SDF_FULLDAY.format(dayResult
					.getPeriod().getStartDate());
		} else {
			table[rowIndex][1] = "Du "
					+ POSConstants.SDF_FULLDAY.format(dayResult.getPeriod()
							.getStartDate())
					+ " au "
					+ POSConstants.SDF_FULLDAY.format(dayResult.getPeriod()
							.getEndDate());
		}

		BigDecimal caTVAC = dayResult.getCaTVAC();
		table[rowIndex][2] = ReportHelper.formatTotalTotalTVAC(caTVAC);
		rowIndex++;

		Map<BigDecimal, BigDecimal> htvaList = dayResult.getHtva();

		for (BigDecimal tvaId : htvaList.keySet()) {
			BigDecimal tvac = htvaList.get(tvaId);
			BigDecimal htvac = Tools.getWitoutTax(tvac, tvaId);
			BigDecimal tvaValue = tvac.subtract(htvac);
			table[rowIndex][1] = tvaId.toPlainString() + "%";
			table[rowIndex][2] = ReportHelper.formatTotalUnitPrice(tvac);
			table[rowIndex][3] = ReportHelper.formatTotalUnitPrice(tvaValue);
			table[rowIndex][4] = ReportHelper.formatTotalUnitPrice(htvac);
			rowIndex++;
		}
		rowIndex++;
		rowIndex = appendItems(dayResult.getProductSales(), rowIndex, table);
		rowIndex++;
		rowIndex = appendPay(dayResult, rowIndex, table);
		if (listRejectedProducts.isSelected()) {
			rowIndex++;
			rowIndex = appendItems(dayResult.getRejectedProductList(),
					rowIndex, table);
		}
		return rowIndex;
	}

	public int appendItems(Map<String, Item> items, int rowIndex,
			Object[][] table) {
		boolean isFirstColumn = true;
		Map<String, String> orderedMap = createIndexByDescription(items);
		for (String key : orderedMap.keySet()) {
			String link = orderedMap.get(key);
			Item item = items.get(link);
			int offset = (isFirstColumn ? 0 : 3);
			table[rowIndex][offset] = ReportHelper.formatItemQte(item
					.getQuantity());
			table[rowIndex][offset + 1] = item.getItem().getDescription();
			table[rowIndex][offset + 2] = ReportHelper.formatItemUnitPrice(item
					.getUnitPrice());
			isFirstColumn = !isFirstColumn;
			if (isFirstColumn) {
				rowIndex++;
			}
		}
		if (!isFirstColumn) {
			rowIndex++;
		}
		return rowIndex;
	}

	public int appendPay(PeriodResult dr, int rowIndex, Object[][] table) {
		Map<String, BigDecimal> payList = dr.getPaymentsByType();
		boolean isFirstColumn = true;
		for (String item : payList.keySet()) {
			int offset = (isFirstColumn ? 0 : 3);
			try {
				table[rowIndex][offset + 1] = FacadeDAO.instance()
						.getPayementModesDAO().getById(item).getDescription();
			} catch (DAOException e) {
				table[rowIndex][offset + 1] = "Err(" + item + ")";
			}
			table[rowIndex][offset + 2] = ReportHelper
					.formatItemUnitPrice(payList.get(item));
			isFirstColumn = !isFirstColumn;
			if (isFirstColumn) {
				rowIndex++;
			}
		}
		if (!isFirstColumn) {
			rowIndex++;
		}
		return rowIndex;
	}

	@Override
	String[][] getTableHeader() {
		return TABLE_HEADER;
	}

	@Override
	String getTitle() {
		return "Rapport de caisse";
	}

	public void forcePeriodType(int periodType) {
		if (PERIOD_TYPE_DAY == periodType) {
			byDayRadioButton.setSelected(true);
		} else if (PERIOD_TYPE_MONTH == periodType) {
			byMonthRadioButton.setSelected(true);
		} else if (PERIOD_TYPE_YEAR == periodType) {
			byYearRadioButton.setSelected(true);
		}
	}
}