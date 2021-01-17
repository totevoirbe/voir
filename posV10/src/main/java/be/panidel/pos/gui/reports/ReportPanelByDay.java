package be.panidel.pos.gui.reports;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JPanel;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

import be.panidel.common.HourAndMinute;
import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.PeriodValue;
import be.panidel.management.impl.PeriodResult;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class ReportPanelByDay extends AbstractReportPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private final static String[][] TABLE_HEADER = { { "Date", "100" },
			{ "Ouvre", "30" }, { "Ferme", "30" }, { "Recette", "30" },
			{ "< 7", "30" }, { "7/8", "30" }, { "8/9", "30" },
			{ "9/10", "30" }, { "10/11", "30" }, { "11/12", "30" },
			{ "12/13", "30" }, { "13/14", "30" }, { "14/15", "30" },
			{ "15/16", "30" }, { "16/17", "30" }, { "17/18", "30" },
			{ "18/19", "30" }, { "19 >", "30" } };
	private String[][] tableHeader = null;

	private static final Logger log = Logger.getLogger("SummaryPanelByDay");

	private int viewColumnMax;
	private List<Identification> payModes;
	private SelectEmployeeGroup selectEmployeeGroup;

	public ReportPanelByDay(Panel cp) {
		super(cp);
	}

	public void initSpecificComponents() throws DAOException {

		log.debug("init specific components");

		selectEmployeeGroup = new SelectEmployeeGroup();
		actionPane.add(selectEmployeeGroup);
	}

	@Override
	void afterInitComponents() throws DAOException {
	}

	@Override
	void initRefresh() throws ParameterException {
		setStartDate(Tools.startOfCurentDay(getStartDate()));
		setEndDate(Tools.endOfDay(getEndDate()));
	}

	public TableModel fullFillTable(String[] tableHeader) throws DAOException,
			ParameterException {

		List<Identification> seg = selectEmployeeGroup.getSelectedEmployees();

		Map<StringReverseOrder, PeriodResult> periodResultList = FacadeDAO
				.instance()
				.getOperationUnitDAO()
				.getDayResultList(
						new PeriodBean(getStartDate(), getEndDate()),
						POSParameters.instance()
								.getResultsStorageCaissesGroupbyday());

		Object[][] table = new Object[(seg.size() + 1)
				* periodResultList.size() + 1 + seg.size()][tableHeader.length];

		BigDecimal[][] totalTable = new BigDecimal[seg.size() + 1][tableHeader.length];

		for (int j = 0; j < seg.size() + 1; j++) {
			for (int i = 1; i < tableHeader.length; i++) {
				totalTable[j][i] = BigDecimal.ZERO;
			}
		}

		log.debug("START ITERATE byDaySales");

		int rowIndex = (seg.size() + 1) * periodResultList.size() + seg.size();

		for (StringReverseOrder dateAsString : periodResultList.keySet()) {
			PeriodResult dayResult = periodResultList.get(dateAsString);
			for (int i = 0; i < seg.size(); i++) {
				append(dayResult, rowIndex, " - " + seg.get(i).getCode(),
						table, totalTable[i + 1], seg.get(i));
				rowIndex--;
			}
			append(dayResult, rowIndex,
					POSConstants.SDF_FULLDAY.format(dayResult.getPeriod()
							.getStartDate()), table, totalTable[0], null);
			rowIndex--;
		}

		for (int j = 0; j < seg.size(); j++) {
			table[j + 1][0] = " - " + seg.get(j).getCode();
			for (int i = 3; i < tableHeader.length; i++) {
				table[j + 1][i] = Long.toString(totalTable[j + 1][i]
						.longValue());
			}
		}
		table[0][0] = "Total";
		for (int i = 3; i < tableHeader.length; i++) {
			table[0][i] = Long.toString(totalTable[0][i].longValue());
		}

		TableModel tm = new DefaultTableModel(table, tableHeader);

		return tm;

	}

	private void sum(int column, BigDecimal value, BigDecimal[] totalTable) {
		if (value != null) {
			totalTable[column] = totalTable[column].add(value);
		}
	}

	public void append(PeriodResult dr, int rowIndex, String label,
			Object[][] table, BigDecimal[] totalTable, Identification employee)
			throws DAOException, ParameterException {

		int col = 0;

		table[rowIndex][col++] = label;

		HourAndMinute hourAndMinute = dr.getFirstOperationInPeriod(employee);
		table[rowIndex][col++] = (hourAndMinute == null ? "-" : hourAndMinute
				.toString());
		hourAndMinute = dr.getLastOperationInPeriod(employee);
		table[rowIndex][col++] = (hourAndMinute == null ? "-" : hourAndMinute
				.toString());
		if (employee == null) {
			table[rowIndex][col] = Long.toString(dr.getCaTVAC().longValue());
			sum(col, dr.getCaTVAC(), totalTable);
		} else {
			BigDecimal employeeCaTvac = dr.getCaTVAC(employee);
			table[rowIndex][col] = Long.toString(employeeCaTvac.longValue());
			sum(col, employeeCaTvac, totalTable);
		}
		col++;

		Map<String, PeriodValue> salesByPeriod;
		try {
			if (employee == null) {
				salesByPeriod = dr.getValuesByPeriod();
			} else {
				salesByPeriod = dr.getValuesByPeriod(employee);
			}
		} catch (ParameterException e) {
			throw new DAOException("Erreur grave", e);
		}

		BigDecimal periodValue = BigDecimal.ZERO;

		for (int periodHour = 0; periodHour <= 6; periodHour++) {
			String periodHourAsString = Integer.toString(periodHour);
			PeriodValue addeValue = salesByPeriod.get(periodHourAsString);
			if (addeValue != null) {
				periodValue = periodValue.add(addeValue.getSalesByPeriod());
			}
		}

		table[rowIndex][col] = Long.toString(periodValue.longValue());
		sum(col, periodValue, totalTable);
		col++;

		for (int periodHour = 7; periodHour < 19; periodHour++) {
			String periodHourAsString = Integer.toString(periodHour);

			PeriodValue pv = salesByPeriod.get(periodHourAsString);
			if (pv != null) {
				periodValue = pv.getSalesByPeriod();
				if (periodValue != null) {
					table[rowIndex][col] = Long.toString(periodValue
							.longValue());
					sum(col, periodValue, totalTable);
					col++;
				}
			} else {
				table[rowIndex][col++] = BigDecimal.ZERO;
			}
		}

		periodValue = BigDecimal.ZERO;
		for (int periodHour = 20; periodHour < 24; periodHour++) {
			String periodHourAsString = Integer.toString(periodHour);
			PeriodValue addeValue = salesByPeriod.get(periodHourAsString);
			if (addeValue != null) {
				periodValue = periodValue.add(addeValue.getSalesByPeriod());
			}
		}

		table[rowIndex][col] = Long.toString(periodValue.longValue());
		sum(col, periodValue, totalTable);
		col++;

		BigDecimal payValue = null;
		Map<String, BigDecimal> payByType = dr.getPaymentsByType(employee);
		for (Identification payement : getPayModes()) {
			payValue = payByType.get(payement.getId());
			if (col < getTableHeader().length) {
				log.debug("Payement " + payement.getCode() + " : [" + payValue
						+ "]");
				if (payValue == null) {
					table[rowIndex][col++] = 0;
				} else {
					table[rowIndex][col] = Long.toString(payValue.longValue());
					sum(col, payValue, totalTable);
					col++;
				}
			} else {
				log.error("Table size=[" + getTableHeader().length + "]/col=["
						+ col + "]/" + payement.getCode() + "=[" + payValue
						+ "]");
			}
		}

		if (employee == null) {
			table[rowIndex][col] = dr.getCanceled();
			sum(col, new BigDecimal(dr.getCanceled()), totalTable);
		} else {
			int employeeCancelled = dr.getCanceled(employee);
			table[rowIndex][col] = employeeCancelled;
			sum(col, new BigDecimal(employeeCancelled), totalTable);
		}
		col++;

		return;
	}

	public List<Identification> getPayModes() throws DAOException {

		if (payModes == null) {
			payModes = FacadeDAO.instance().getPayementModesDAO().getList();
		}

		return payModes;
	}

	public String[][] getTableHeader() {

		if (tableHeader == null) {

			try {
				int baseTableHeaderSize = TABLE_HEADER.length;
				int payModesSize = getPayModes().size();
				viewColumnMax = baseTableHeaderSize + payModesSize + 1;

				tableHeader = new String[viewColumnMax][2];

				int i = 0;
				while (i < TABLE_HEADER.length) {
					tableHeader[i] = TABLE_HEADER[i];
					i++;
				}

				for (Identification identification : getPayModes()) {
					String[] columnHeader = new String[2];
					columnHeader[0] = identification.getCode();
					columnHeader[1] = "40";
					tableHeader[i] = columnHeader;
					i++;
				}
				tableHeader[i] = new String[] { "-", "20" };
			} catch (DAOException e) {
				log.error("Error pay method access", e);
			}
		}

		return tableHeader;
	}

	@Override
	String getTitle() {
		return "Rapport par jour";
	}

	@Override
	public void print(boolean showPrintDialog) throws PrinterException {
		salesTable.doLayout();
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(OrientationRequested.LANDSCAPE);
		salesTable.print(PrintMode.FIT_WIDTH, null, null, showPrintDialog,
				pras, true);
	}
}

class SelectEmployeeGroup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectEmployee> employeesCheckBoxList = new ArrayList<SelectEmployee>();

	public SelectEmployeeGroup() throws DAOException {
		super();
		setLayout(new GridLayout(0, 1));
		List<Identification> employeeList = FacadeDAO.instance()
				.getEmployeesDAO().getList();
		for (Identification employee : employeeList) {
			SelectEmployee se = new SelectEmployee(employee);
			employeesCheckBoxList.add(se);
			add(se);
		}
	}

	public List<Identification> getSelectedEmployees() {
		List<Identification> employeeList = new ArrayList<Identification>();
		for (SelectEmployee checkBoxEmployee : employeesCheckBoxList) {
			if (checkBoxEmployee.getState()) {
				employeeList.add(checkBoxEmployee.getEmployee());
			}
		}
		return employeeList;
	}
}

class SelectEmployee extends Checkbox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Identification employee;

	public SelectEmployee(Identification employee) throws HeadlessException {
		super(employee.getName());
		this.employee = employee;
	}

	public Identification getEmployee() {
		return employee;
	}
}