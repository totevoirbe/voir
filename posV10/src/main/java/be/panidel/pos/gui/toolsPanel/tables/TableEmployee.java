package be.panidel.pos.gui.toolsPanel.tables;

import javax.swing.table.TableModel;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.person.Employee;
import be.panidel.tools.TableIdentification;

public class TableEmployee extends Employee implements TableIdentification {

	public static final String[][] HEADER = new String[][] { { "id", "30" },
			{ "code", "30" }, { "name", "30" }, { "description", "30" },
			{ "Libellé touche", "30" }, { "adresse", "30" }, { "cp", "30" },
			{ "Tél", "30" }, { "Fax", "30" }, { "Nom", "30" },
			{ "Prénom", "30" } };

	final int COL_ID = 0;
	final int COL_CODE = 1;
	final int COL_NAME = 2;
	final int COL_DESCRIPTION = 3;
	final int COL_HTMLKEYLABEL = 4;
	final int COL_ADRESS = 5;
	final int COL_POSTALCODE = 6;
	final int COL_TEL = 7;
	final int COL_FAX = 8;
	final int COL_LASTNAME = 9;
	final int COL_FIRSTNAME = 10;

	public String[][] getHeader() {
		return HEADER;
	}

	public TableEmployee(Identification identification) {
		super();
		Employee employee = (Employee) identification;
		setId(employee.getId());
		setCode(employee.getCode());
		setName(employee.getName());
		setDescription(employee.getDescription());
		setHtmlKeyLabel(employee.getHtmlKeyLabel());
		setAdress(employee.getAdress());
		setPostalCode(employee.getPostalCode());
		setPhone(employee.getPhone());
		setFax(employee.getFax());
		setLastName(employee.getLastName());
		setFirstName(employee.getFirstName());
	}

	public Object[] getElementAsArray() {

		Object[] elementAsArray = new Object[size()];

		elementAsArray[COL_ID] = this.getId();
		elementAsArray[COL_CODE] = this.getCode();
		elementAsArray[COL_NAME] = this.getName();
		elementAsArray[COL_DESCRIPTION] = this.getDescription();
		elementAsArray[COL_HTMLKEYLABEL] = this.getHtmlKeyLabel();
		elementAsArray[COL_ADRESS] = this.getAdress();
		elementAsArray[COL_POSTALCODE] = this.getPostalCode();
		elementAsArray[COL_TEL] = this.getPhone();
		elementAsArray[COL_FAX] = this.getFax();
		elementAsArray[COL_LASTNAME] = this.getLastName();
		elementAsArray[COL_FIRSTNAME] = this.getFirstName();

		return elementAsArray;
	}

	public void fulfillElementFromRow(TableModel tableModel, int row)
			throws DAOException {

		this.setId((String) tableModel.getValueAt(row, COL_ID));
		this.setCode((String) tableModel.getValueAt(row, COL_CODE));
		this.setName((String) tableModel.getValueAt(row, COL_NAME));
		this.setDescription((String) tableModel
				.getValueAt(row, COL_DESCRIPTION));
		this.setHtmlKeyLabel((String) tableModel.getValueAt(row,
				COL_HTMLKEYLABEL));
		this.setAdress((String) tableModel.getValueAt(row, COL_ADRESS));
		this.setPostalCode((String) tableModel.getValueAt(row, COL_POSTALCODE));
		this.setPhone((String) tableModel.getValueAt(row, COL_TEL));
		this.setFax((String) tableModel.getValueAt(row, COL_FAX));
		this.setLastName((String) tableModel.getValueAt(row, COL_LASTNAME));
		this.setFirstName((String) tableModel.getValueAt(row, COL_FIRSTNAME));

	}

	public int size() {
		return HEADER.length;
	}
}