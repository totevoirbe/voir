package be.panidel.common.impl;

import be.panidel.common.AdressIdentification;
import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.management.Group;

public class AdressIdentificationImpl extends IdentificationImpl implements
		AdressIdentification, Identification {

	private String adress;

	private String postalCode;

	private String phone;

	private String fax;

	public AdressIdentificationImpl() {
		super();
		adress = new String();
		postalCode = new String();
		phone = new String();
		fax = new String();
	}

	public AdressIdentificationImpl(String id, String code, String name,
			String description, String htmlKeyLabel, String adress, String postalCode, String phone,
			String fax) {
		super(id, code, name, description, htmlKeyLabel);
		this.adress = adress;
		this.postalCode = postalCode;
		this.phone = phone;
		this.fax = fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#getAdress()
	 */
	public String getAdress() {
		return adress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#setAdress(java.lang.String)
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#getFax()
	 */
	public String getFax() {
		return fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#setFax(java.lang.String)
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#getPhone()
	 */
	public String getPhone() {
		return phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#setPhone(java.lang.String)
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#getPostalCode()
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.common.impl.AdressIdentification#setPostalCode(java.lang.String)
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String getValueLabel() {
		return null;
	}

	@Override
	public DAO getDAOInstance() {
		log.error("AdressIdentificationImpl - Not yet implemented : getDAOInstance()");
		return null;
	}
	

	public Group getGroupAsGroup() {
		return null;
	}

	public String getGroup() {
		return null;
	}

	public void setGroup(Group group) {}

	public void setGroup(String id){}

}
