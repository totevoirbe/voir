package be.panidel.pos.gui.toolsPanel.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Product;
import be.panidel.management.impl.ProductImpl;
import be.panidel.management.impl.SubProduct;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.TableIdentification;
import be.panidel.tools.Tools;

public class TableProduct extends ProductImpl implements TableIdentification {

	String[][] HEADER = new String[][] { { "id", "30" }, { "code", "150" },
			{ "name", "150" }, { "description", "200" },
			{ "Libellé touche", "100" }, { "achat", "30" }, { "prix", "30" },
			{ "emporté", "30" }, { "surPlace", "30" }, { "group", "75" },
			{ "Matières", "250" } };

	final int COL_ID = 0;
	final int COL_CODE = 1;
	final int COL_NAME = 2;
	final int COL_DESCRIPTION = 3;
	final int COL_HTMLKEYLABEL = 4;
	final int COL_PRIXACHAT = 5;
	final int COL_PRIX = 6;
	final int COL_TVATAKEAWAY = 7;
	final int COL_TVATAKEONPLACE = 8;
	final int COL_GROUP = 9;
	final int COL_SUBPRODUCT = 10;

	private boolean extended;

	public String[][] getHeader() {
		return HEADER;
	}

	public TableProduct(Identification identification, boolean extended) {
		super();

		Product product = (Product) identification;
		setId(product.getId());
		setCode(product.getCode());
		setName(product.getName());
		setDescription(product.getDescription());
		setHtmlKeyLabel(product.getHtmlKeyLabel());
		setPrix(product.getPrix());
		setTvaTakeAway(product.getTvaTakeAway());
		setTvaTakeOnPlace(product.getTvaTakeOnPlace());
		setGroup(product.getGroupAsGroup());
		if (product.getSubProductList() != null) {
			List<SubProduct> subProducts = new ArrayList<SubProduct>();
			subProducts.addAll(product.getSubProductList());
			setSubProductList(subProducts);
		}
		this.extended = extended;
	}

	public Object[] getElementAsArray() throws DAOException {

		try {
			Object[] elementAsArray = new Object[size()];

			elementAsArray[COL_ID] = getId();
			elementAsArray[COL_CODE] = getCode();
			elementAsArray[COL_NAME] = getName();
			elementAsArray[COL_DESCRIPTION] = getDescription();
			elementAsArray[COL_HTMLKEYLABEL] = getHtmlKeyLabel();
			elementAsArray[COL_PRIX] = Tools.toString(getPrix());
			elementAsArray[COL_TVATAKEAWAY] = Tools.toString(getTvaTakeAway());
			elementAsArray[COL_TVATAKEONPLACE] = Tools
					.toString(getTvaTakeOnPlace());
			elementAsArray[COL_GROUP] = getGroup(extended);
			elementAsArray[COL_SUBPRODUCT] = this
					.getSubProductAtString(extended);

			return elementAsArray;
		} catch (ParameterException e) {
			throw new DAOException(e);
		}
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
			setPrix(Tools.toBigDecimal((String) tableModel.getValueAt(row,
					COL_PRIX)));
			setTvaTakeAway((Tools.toBigDecimal((String) tableModel.getValueAt(
					row, COL_TVATAKEAWAY))));
			setTvaTakeOnPlace((Tools.toBigDecimal((String) tableModel
					.getValueAt(row, COL_TVATAKEONPLACE))));
			if (!extended) {
				setGroup((String) tableModel.getValueAt(row, COL_GROUP));
				String subProductAtString = (String) tableModel.getValueAt(row,
						COL_SUBPRODUCT);
				setSubProductAtString(subProductAtString.replaceAll(",", "."));
			}
		} catch (ParameterException e) {
			throw new DAOException(e);
		}
	}

	public int size() {
		return HEADER.length;
	}
}