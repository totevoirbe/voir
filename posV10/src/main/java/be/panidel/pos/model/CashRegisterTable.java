package be.panidel.pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import be.panidel.dao.ItemDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;

public abstract class CashRegisterTable extends ArrayList<Item> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("CashRegisterTable");

	public boolean hasOperations() {

		if (size() > 0 && get(0) != null) {
			return true;
		}
		return false;

	}

	public boolean hasOther(String id) {

		for (int j = 0; j < size(); j++) {
			if (!get(j).getItem().getId().equals(id)) {
				return true;
			}
		}

		return false;
	}

	public BigDecimal getQuantity(String id) {

		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < size(); i++) {
			Item item = get(i);
			if (get(i).getItem().getId().equals(id)) {
				total = total.add(item.getQuantity());
			}
		}

		return total;
	}

	public BigDecimal getTableTotal() {

		return getTableTotal(null);

	}

	public BigDecimal getTableTotal(String id) {

		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < size(); i++) {
			Item item = get(i);
			if (id == null || get(i).getItem().getId().equals(id)) {
				total = total.add(item.getQuantity().multiply(
						item.getUnitPrice()));
			}
			if (log.isDebugEnabled()) {
				log
						.debug("GetTableTotal [" + total + "] item : [" + item
								+ "]");
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("GetTableTotal [" + total + "]");
		}

		return total;
	}

	public Item append(Item item) {

		log.debug("START addItem -----------------------------------------");

		Item rowItem = null;
		int rowIndex = 0;

		while (rowIndex < size() && rowItem == null) {
			Item compareRowItem = get(rowIndex);
			if (compareRowItem.isSameUnitpriceAndDescription(item)) {
				rowItem = compareRowItem;
			} else {
				rowIndex++;
			}
		}

		if (rowItem == null) {

			add(item);

			rowItem = item;

		} else {

			log.debug("[rowIndex:" + rowIndex + "][rowItem:" + rowItem + "]");

			rowItem.addQuantity(item.getQuantity());

		}

		return rowItem;
	}

	public void appendToXML(StringBuffer xml) {

		ItemDAO dao = null;
		for (int rowIndex = 0; rowIndex < size(); rowIndex++) {
			Item item = get(rowIndex);
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
}