package be.panidel.pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import be.panidel.dao.ItemDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.pos.gui.error.Message;

public abstract class CopyOfCashRegisterTable extends ArrayList<Item> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("CashRegisterTable");

	public List<Item> rowItems = new ArrayList<Item>();

	public int size() {
		return rowItems.size();
	}

	public boolean hasOperations() {

		if (rowItems != null && rowItems.size() > 0 && rowItems.get(0) != null) {
			return true;
		}
		return false;

	}

	public BigDecimal getTableTotal() {

		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < rowItems.size(); i++) {
			Item item = rowItems.get(i);
			total = total.add(item.getQuantity().multiply(item.getUnitPrice()));
			log.debug("GetTableTotal [" + total + "] item : [" + item + "]");
		}

		log.debug("GetTableTotal [" + total + "]");

		return total;
	}

	public void clearTable() {

		rowItems = new ArrayList<Item>();

	}

	public Item getItem(int row) {
		return rowItems.get(row);
	}

	public void addItem(Item item, List<Message> messages) {

		log.debug("START addItem -----------------------------------------");

		Item rowItem = null;
		int rowIndex = 0;

		while (rowIndex < rowItems.size() && rowItem == null) {
			Item compareRowItem = rowItems.get(rowIndex);
			if (compareRowItem.isSameUnitpriceAndDescription(item)) {
				rowItem = compareRowItem;
			} else {
				rowIndex++;
			}
		}

		if (rowItem == null) {

			int newRowIndex = rowItems.size() - 1;

			log.debug("New item : [newRowIndex:" + newRowIndex + "][item:"
					+ item + "]");
			rowItems.add(item);

		} else {

			log.debug("[rowIndex:" + rowIndex + "][rowItem:" + rowItem + "]");

			rowItem.addQuantity(item.getQuantity());

		}
		log.debug("END addItem -----------------------------------------");
	}

	public void appendToXML(StringBuffer xml) {

		ItemDAO dao = null;
		for (int rowIndex = 0; rowIndex < rowItems.size(); rowIndex++) {
			Item item = rowItems.get(rowIndex);
			if (item != null) {

				if (dao == null) {
					dao = item.getDAO();
				}

				try {
					dao.append(item, xml);
				} catch (DAOException e) {
					log.error(item.toString());
				}
			}
		}
	}

	public List<Item> getRowItems() {
		return rowItems;
	}
}