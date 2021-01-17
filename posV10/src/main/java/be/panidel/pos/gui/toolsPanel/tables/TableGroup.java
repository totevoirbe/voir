package be.panidel.pos.gui.toolsPanel.tables;

import javax.swing.table.TableModel;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.management.impl.GroupImpl;
import be.panidel.tools.TableIdentification;

public class TableGroup extends GroupImpl implements TableIdentification {

	public static final String[][] HEADER = new String[][] { { "id", "30" },
			{ "code", "30" }, { "name", "30" }, { "description", "30" },
			{ "Libellé touche", "30" }, { "Color", "30" } };

	final int COL_ID = 0;
	final int COL_CODE = 1;
	final int COL_NAME = 2;
	final int COL_DESCRIPTION = 3;
	final int COL_HTMLKEYLABEL = 4;
	final int COL_TOUCHCOLOR = 5;

	public String[][] getHeader() {
		return HEADER;
	}

	public TableGroup(Identification identification) {
		super();

		Group group = (Group) identification;

		setId(group.getId());
		setCode(group.getCode());
		setName(group.getName());
		setDescription(group.getDescription());
		setHtmlKeyLabel(group.getHtmlKeyLabel());
		setTouchColor(group.getTouchColorAsColor());
	}

	public Object[] getElementAsArray() {

		Object[] elementAsArray = new Object[size()];

		elementAsArray[COL_ID] = this.getId();
		elementAsArray[COL_CODE] = this.getCode();
		elementAsArray[COL_NAME] = this.getName();
		elementAsArray[COL_DESCRIPTION] = this.getDescription();
		elementAsArray[COL_HTMLKEYLABEL] = this.getHtmlKeyLabel();
		elementAsArray[COL_TOUCHCOLOR] = this.getTouchColor();

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
		this.setTouchColor((String) tableModel.getValueAt(row, COL_TOUCHCOLOR));

	}

	public int size() {
		return HEADER.length;
	}
}