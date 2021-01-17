package be.panidel.pos.gui.commandPanel;

import java.awt.print.PrinterException;

import be.panidel.common.PeriodBean;
import be.panidel.dao.exeption.DAOException;

public interface GuiPanel {

	void refresh(PeriodBean periodBean) throws DAOException;

	void print(boolean showPrintDialog) throws PrinterException;

}
