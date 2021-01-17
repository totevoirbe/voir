package be.panidel.tools;

import javax.swing.table.TableModel;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;

public interface TableIdentification extends Identification {

	public String[][] getHeader();

	Object[] getElementAsArray() throws DAOException;

	void fulfillElementFromRow(TableModel tableModel, int row)
			throws DAOException;

	public int size();
}