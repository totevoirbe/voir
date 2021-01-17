package be.panidel.management.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import be.panidel.common.CoupleMessages;
import be.panidel.common.impl.IdentificationImpl;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.management.Product;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class ProductImpl extends IdentificationImpl implements Product {

	private BigDecimal prix;
	private BigDecimal tvaTakeOnPlace;
	private BigDecimal tvaTakeAway;
	private Group group;
	private List<SubProduct> subProductList;

	public ProductImpl() {
		super();
		this.prix = BigDecimal.ZERO;
		this.tvaTakeOnPlace = BigDecimal.ZERO;
		this.tvaTakeOnPlace = BigDecimal.ZERO;
		setGroup(new GroupImpl());
	}

	public ProductImpl(String id, String code, String name, String description,
			String htmlKeyLabel, BigDecimal prix, BigDecimal tvaTakeOnPlace,
			BigDecimal tvaTakeAway, List<SubProduct> subProductList,
			String groupId) {
		super(id, code, name, description, htmlKeyLabel);
		this.prix = prix;
		this.tvaTakeOnPlace = tvaTakeOnPlace;
		this.tvaTakeAway = tvaTakeAway;
		this.subProductList = subProductList;
		try {
			setGroup(groupId);
		} catch (DAOException e) {
			log.error(groupId);
		}
	}

	@Override
	public String toString() {

		CoupleMessages cm = new CoupleMessages();
		cm.setPrefixString(super.toString());
		cm.put("prix", prix);
		cm.put("tvaTakeAway", tvaTakeAway);
		cm.put("tvaTakeOnPlace", tvaTakeOnPlace);
		cm.put("group", group);
		if (subProductList != null) {
			for (SubProduct subProduct : subProductList) {
				cm.put("sp", subProduct.toString());
			}
		} else {
			cm.put("sp", "null");
		}
		return cm.toString();
	}

	@Override
	public BigDecimal getPrix() {
		return prix;
	}

	@Override
	public List<SubProduct> getSubProductList() {
		return subProductList;
	}

	public String getSubProductAtString(boolean extended) {

		StringBuffer sb = new StringBuffer();

		if (subProductList != null) {
			for (SubProduct subProduct : subProductList) {
				if (sb.length() > 0) {
					sb.append(";");
				}
				sb.append(subProduct.getQty().toPlainString());
				sb.append(":");
				if (!extended) {
					sb.append(subProduct.getRawProductId());
				} else {
					try {
						if (subProduct.getRawProduct() != null) {
							sb.append(subProduct.getRawProduct().getName());
						} else {
							log.error(subProduct.getRawProductId());
						}
					} catch (DAOException e) {
						log.error(subProduct.getRawProductId(), e);
					}
				}
			}
		}

		return sb.toString();
	}

	@Override
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	@Override
	public void setSubProductList(List<SubProduct> subProductList) {
		this.subProductList = subProductList;
	}

	public void setSubProductAtString(String subProductListAsString) {

		if (subProductList == null) {
			subProductList = new ArrayList<SubProduct>();
		}
		if (!Tools.isNullOrEmpty(subProductListAsString)) {
			StringTokenizer stObject = new StringTokenizer(
					subProductListAsString, ";");
			while (stObject.hasMoreElements()) {
				String objet = (String) stObject.nextElement();
				try {
					StringTokenizer stField = new StringTokenizer(objet, ":");
					BigDecimal qty = null;
					String productId = null;
					if (!stField.hasMoreElements()) {
						throw new DAOException(objet);
					}
					String qtyAsString = (String) stField.nextElement();
					try {
						qty = Tools.toBigDecimalNotNull(qtyAsString);
					} catch (ParameterException e) {
						log.info(qtyAsString, e);
					}
					if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
						throw new DAOException(objet + "-"
								+ (qty == null ? "null" : qty.toPlainString()));
					}
					if (!stField.hasMoreElements()) {
						throw new DAOException(objet);
					}
					productId = (String) stField.nextElement();
					SubProduct subproduct = new SubProduct(productId, qty);
					subProductList.add(subproduct);
				} catch (DAOException e) {
					log.error(objet, e);
				}
			}
		} else {
			subProductList = new ArrayList<SubProduct>();
		}
	}

	@Override
	public String getValueLabel() {
		return prix.setScale(2).toPlainString();
	}

	@Override
	public DAO getDAOInstance() {
		return FacadeDAO.instance().getProductsDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.IdentificationImpl#getGroupAsGroup()
	 */
	@Override
	public Group getGroupAsGroup() {
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.IdentificationImpl#getGroup()
	 */
	@Override
	public String getGroup(boolean extended) {
		if (group == null) {
			return new String();
		}
		if (extended) {
			return group.getName();
		} else {
			return group.getId().toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.panidel.common.impl.IdentificationImpl#setGroup(be.panidel.management
	 * .Group)
	 */
	@Override
	public void setGroup(Group group) {
		this.group = group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.IdentificationImpl#setGroup(java.lang.String)
	 */
	@Override
	public void setGroup(String id) throws DAOException {
		this.group = null;
		if (!Tools.isNullOrEmpty(id)) {
			this.group = FacadeDAO.instance().getGroupsDAO().getById(id);
		}
	}

	@Override
	public BigDecimal getTvaTakeOnPlace() {
		return tvaTakeOnPlace;
	}

	@Override
	public void setTvaTakeOnPlace(BigDecimal tvaTakeOnPlace) {
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	@Override
	public BigDecimal getTvaTakeAway() {
		return tvaTakeAway;
	}

	@Override
	public void setTvaTakeAway(BigDecimal tvaTakeAway) {
		this.tvaTakeAway = tvaTakeAway;
	}
}
