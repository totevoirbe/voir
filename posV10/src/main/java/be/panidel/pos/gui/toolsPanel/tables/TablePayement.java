package be.panidel.pos.gui.toolsPanel.tables;

import javax.swing.table.TableModel;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Payement;
import be.panidel.management.impl.PayementModeImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.TableIdentification;
import be.panidel.tools.Tools;

public class TablePayement extends PayementModeImpl implements
		TableIdentification {

	public static final String[][] HEADER = new String[][] { { "id", "30" },
			{ "code", "30" }, { "name", "30" }, { "description", "30" },
			{ "Libellé touche", "30" }, { "valeur obligatoire", "30" },
			{ "Nombre max", "30" }, { "total max", "30" }, { "unique", "30" } };

	final int COL_ID = 0;
	final int COL_CODE = 1;
	final int COL_NAME = 2;
	final int COL_DESCRIPTION = 3;
	final int COL_HTMLKEYLABEL = 4;
	final int COL_NEEDSOMEVALUE = 5;
	final int COL_MAXQUANTITY = 6;
	final int COL_MAXTOTALAMOUNT = 7;
	final int COL_BEALONE = 8;

	public String[][] getHeader() {
		return HEADER;
	}

	public TablePayement(Identification identification) {
		super();
		Payement payement = (Payement) identification;
		setId(payement.getId());
		setCode(payement.getCode());
		setName(payement.getName());
		setDescription(payement.getDescription());
		setHtmlKeyLabel(payement.getHtmlKeyLabel());
		setNeedSomeValue(payement.isNeedSomeValue());
		setMaxQuantity(payement.getMaxQuantity());
		setMaxTotalAmount(payement.getMaxTotalAmount());
		setBeAlone(payement.isBeAlone());
	}

	public Object[] getElementAsArray() throws DAOException {

		Object[] elementAsArray = new Object[size()];

		try {
			elementAsArray[COL_ID] = getId();
			elementAsArray[COL_CODE] = getCode();
			elementAsArray[COL_NAME] = getName();
			elementAsArray[COL_DESCRIPTION] = getDescription();
			elementAsArray[COL_HTMLKEYLABEL] = getHtmlKeyLabel();
			elementAsArray[COL_NEEDSOMEVALUE] = Boolean.toString(this
					.isNeedSomeValue());
			elementAsArray[COL_MAXQUANTITY] = Tools.toString(getMaxQuantity());
			elementAsArray[COL_MAXTOTALAMOUNT] = Tools
					.toString(getMaxTotalAmount());
			elementAsArray[COL_BEALONE] = Tools.toString(isBeAlone());
		} catch (ParameterException e) {
			throw new DAOException(e);
		}

		return elementAsArray;
	}

	public void fulfillElementFromRow(TableModel tableModel, int row)
			throws DAOException {

		try {
			setId((String) tableModel.getValueAt(row, COL_ID));
			setCode((String) tableModel.getValueAt(row, COL_CODE));
			setName((String) tableModel.getValueAt(row, COL_NAME));
			setDescription((String) tableModel.getValueAt(row, COL_DESCRIPTION));
			setHtmlKeyLabel((String) tableModel.getValueAt(row,
					COL_HTMLKEYLABEL));
			setNeedSomeValue(Tools.toBoolean((String) tableModel.getValueAt(
					row, COL_NEEDSOMEVALUE)));
			setMaxQuantity(Tools.toBigDecimal((String) tableModel.getValueAt(
					row, COL_MAXQUANTITY)));
			setMaxTotalAmount(Tools.toBigDecimal((String) tableModel
					.getValueAt(row, COL_MAXTOTALAMOUNT)));
			setBeAlone(Tools.toBoolean((String) tableModel.getValueAt(row,
					COL_BEALONE)));
		} catch (ParameterException e) {
			throw new DAOException(e);
		}

	}

	public int size() {
		return HEADER.length;
	}
}