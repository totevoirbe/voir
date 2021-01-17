package be.panidel.tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Product;
import be.panidel.management.impl.SubProduct;

public class InitSubProducts {

	public static void main(String[] args) {
		System.out.println("start");
		try {
			DAO dao = FacadeDAO.instance().getProductsDAO();
			List<Identification> productList = dao.getList();
			for (Identification identification : productList) {
				Product product = (Product) identification;
				List<SubProduct> subProductList = new ArrayList<SubProduct>();
				if (product.getName().startsWith("Mini")) {
					subProductList
							.add(new SubProduct("166", new BigDecimal(1)));
				}
				product.setSubProductList(subProductList);
			}
			Element node = dao.deleteAll();
			for (Identification identification : productList) {

				dao.newElement(node, identification);
			}
			dao.saveTable();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}

}
