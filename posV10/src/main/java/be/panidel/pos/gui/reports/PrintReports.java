package be.panidel.pos.gui.reports;

import java.awt.print.PrinterException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class PrintReports extends AbstractReportPanel {

	private static final Logger log = Logger.getLogger("SummaryPrintReports");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String[][] TABLE_HEADER = {};

	public PrintReports(Panel cp) {
		super(cp);
	}

	public PrintReports() {
		super();
	}

	@Override
	public void initSpecificComponents() throws DAOException {
	}

	@Override
	void afterInitComponents() throws DAOException {
		refreshButton.setVisible(false);
	}

	@Override
	void initRefresh() throws ParameterException {
		endDatePanel.setVisible(true);
		setStartDate(Tools.startOfCurentDay(getStartDate()));
		setEndDate(Tools.endOfDay(getEndDate()));
	}

	@Override
	TableModel fullFillTable(String[] tableHeader) throws DAOException {
		Object[][] table = new Object[0][tableHeader.length];
		try {
			print(true);
		} catch (PrinterException e) {
			throw new DAOException(e);
		}
		return new DefaultTableModel(table, tableHeader);
	}

	@Override
	public void print(boolean showPrintDialog) throws PrinterException {

		try {

			GregorianCalendar currentDate = new GregorianCalendar();
			currentDate.setTime(getStartDate());

			Panel summaryCashBook = ((CommandPanel) CommandPanel.instance()
					.getjPanel()).getSummaryCashBook();
			((ReportCashBook) summaryCashBook.getjPanel())
					.forcePeriodType(ReportCashBook.PERIOD_TYPE_DAY);
			Panel summaryDayDetailForCashBook = ((CommandPanel) CommandPanel
					.instance().getjPanel()).getSummaryDayDetailForCashBook();

			Date startDate = null;
			Date endDate = null;

			while (currentDate.getTime().before(getEndDate())) {
				try {
					startDate = Tools.startOfCurentDay(currentDate.getTime());
					endDate = Tools.endOfDay(currentDate.getTime());
					summaryCashBook.show(new PeriodBean(startDate, endDate));
					summaryCashBook.print(false);
					summaryDayDetailForCashBook.show(new PeriodBean(startDate,
							endDate));
					summaryDayDetailForCashBook.print(false);
				} catch (ParameterException e) {
					log.error("[" + currentDate.getTime() + "]", e);
				}
				currentDate.add(Calendar.DAY_OF_YEAR, 1);
			}
			summaryCashBook.hide();
			summaryDayDetailForCashBook.hide();
		} catch (DAOException e) {
			throw new PrinterException(e.getMessage());
		}
	}

	@Override
	String[][] getTableHeader() {
		return TABLE_HEADER;
	}

	@Override
	String getTitle() {
		return "Impression résultats";
	}

}