package be.panidel.tools;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class RefTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6251664488224251722L;

	public RefTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public RefTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public RefTableModel(Object[][] docArray, List<String> attributeNameList) {

		super(docArray, attributeNameList.toArray());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (0 == column) {
			return false;
		}
		return super.isCellEditable(row, column);
	}
}
