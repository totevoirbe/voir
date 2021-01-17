package be.panidel.dao;

import org.w3c.dom.Element;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;

public interface ItemDAO {

	public final static String TAG_SALES_ITEM = "item";
	public final static String TAG_SALES_ITEM_QUANTITY = "quantity";
	public final static String TAG_SALES_ITEM_PRODUCT = "product";
	public final static String TAG_SALES_ITEM_UNITPRICE = "unitPrice";
	public final static String TAG_SALES_ITEM_DESCRIPTION = "description";
	public final static String TAG_SALES_ITEM_TVATAKEAWAY = "tvaTakeAway";
	public final static String TAG_SALES_ITEM_TVATAKEONPLACE = "tvaTakeOnPlace";

	public abstract Item extract(Element itemNode) throws DAOException;

	public abstract void append(Item productItem, StringBuffer xml)
			throws DAOException;

}