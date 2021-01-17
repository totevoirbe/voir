package be.panidel.pos.gui.reports;

import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.PeriodResult;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class ReportSplitTva extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8093691847074631739L;

	private final static String[][] TABLE_HEADER = { { "Date", "100" },
			{ "Total TVAC", "30" } };
	private String[][] tableHeader = null;

	private static final Logger log = Logger.getLogger("ReportSplitTva");

	public ReportSplitTva(Panel cp) {
		super(cp);
	}

	@Override
	public void initSpecificComponents() throws DAOException {
	}

	@Override
	void afterInitComponents() throws DAOException {
	}

	@Override
	void initRefresh() throws ParameterException {
		setStartDate(Tools.startOfMonth(getStartDate()));
		setEndDate(Tools.endOfMonth(getStartDate()));
	}

	public TableModel fullFillTable(String[] tableHeader) throws DAOException,
			ParameterException {

		Map<StringReverseOrder, PeriodResult> periodResultList = FacadeDAO
				.instance()
				.getOperationUnitDAO()
				.getDayResultList(
						new PeriodBean(getStartDate(), getEndDate()),
						POSParameters.instance()
								.getResultsStorageCaissesGroupbyday());

		log.debug("START ITERATE byDaySales");

		String[] tvaList = POSParameters.instance().getTVAList().split(",");
		BigDecimal[][] tvaValues = new BigDecimal[tvaList.length + 1][2];
		tvaValues[0][0] = BigDecimal.ZERO;
		tvaValues[0][1] = BigDecimal.ZERO;
		for (int i = 1; i < tvaValues.length; i++) {
			tvaValues[i][0] = new BigDecimal(tvaList[i - 1]);
			tvaValues[i][1] = BigDecimal.ZERO;
		}

		Object[][] table = new Object[periodResultList.size() + 2][tvaValues.length + 1];
		int rowIndex = 0;

		for (StringReverseOrder dateAsString : periodResultList.keySet()) {
			PeriodResult dayResult = periodResultList.get(dateAsString);
			append(dayResult, rowIndex,
					POSConstants.SDF_FULLDAY.format(dayResult.getPeriod()
							.getStartDate()), table, tvaList);
			tvaValues[0][1] = tvaValues[0][1].add(dayResult.getCaTVAC());
			for (int i = 1; i < tvaValues.length; i++) {
				BigDecimal dayValue = dayResult.getHtva().get(tvaValues[i][0]);
				if (dayValue != null) {
					tvaValues[i][1] = tvaValues[i][1].add(dayValue);
				}
			}
			rowIndex++;
		}

		int col = 0;
		table[rowIndex][col++] = "Total";
		for (int i = 0; i < tvaValues.length; i++) {
			BigDecimal value = tvaValues[i][1];
			if (value != null) {
				table[rowIndex][col++] = value.toPlainString();
			}
		}

		TableModel tm = new DefaultTableModel(table, tableHeader);

		return tm;

	}

	public void append(PeriodResult dr, int rowIndex, String label,
			Object[][] table, String[] tvaList) throws DAOException {

		int col = 0;

		table[rowIndex][col++] = label;
		table[rowIndex][col++] = dr.getCaTVAC().toPlainString();

		for (int i = 0; i < tvaList.length; i++) {
			BigDecimal value = dr.getHtva().get(new BigDecimal(tvaList[i]));
			if (value != null) {
				table[rowIndex][col] = value.toPlainString();
			}
			col++;
		}

		return;
	}

	public String[][] getTableHeader() {

		if (tableHeader == null) {

			try {
				int baseTableHeaderSize = TABLE_HEADER.length;

				String[] tvaList = POSParameters.instance().getTVAList()
						.split(",");
				tableHeader = new String[tvaList.length + baseTableHeaderSize][2];

				for (int i = 0; i < TABLE_HEADER.length; i++) {
					tableHeader[i] = TABLE_HEADER[i];
				}

				for (int i = 0; i < tvaList.length; i++) {
					String[] columnHeader = new String[2];
					columnHeader[0] = tvaList[i];
					columnHeader[1] = "40";
					tableHeader[i + baseTableHeaderSize] = columnHeader;
				}
			} catch (ParameterException e) {
				log.error("", e);
			}

		}

		return tableHeader;
	}

	@Override
	String getTitle() {
		return "Ventilation TVA";
	}

	@Override
	public void print(boolean showPrintDialog) throws PrinterException {
		salesTable.doLayout();
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(OrientationRequested.PORTRAIT);
		salesTable.print(PrintMode.FIT_WIDTH, null, null, showPrintDialog,
				pras, true);
	}
}