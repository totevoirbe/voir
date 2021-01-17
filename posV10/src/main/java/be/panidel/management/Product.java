package be.panidel.management;

import java.math.BigDecimal;
import java.util.List;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.SubProduct;

public interface Product extends Identification {

	BigDecimal getPrix();

	void setPrix(BigDecimal prix);

	List<SubProduct> getSubProductList();

	void setSubProductList(List<SubProduct> subProductList);

	BigDecimal getTvaTakeAway();

	void setTvaTakeAway(BigDecimal tva);

	BigDecimal getTvaTakeOnPlace();

	void setTvaTakeOnPlace(BigDecimal tva);

	Group getGroupAsGroup();

	String getGroup(boolean extende);

	void setGroup(Group group);

	void setGroup(String id) throws DAOException;
}