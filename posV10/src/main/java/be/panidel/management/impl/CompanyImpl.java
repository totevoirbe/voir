package be.panidel.management.impl;

import be.panidel.common.impl.AdressIdentificationImpl;
import be.panidel.management.Company;

public class CompanyImpl extends AdressIdentificationImpl implements Company {

	public CompanyImpl() {
		super();
	}

	public CompanyImpl(String id, String code, String name, String description,
			String htmlKeyLabel, String adress, String postalCode,
			String phone, String fax) {
		super(id, code, name, description, htmlKeyLabel, adress, postalCode,
				phone, fax);
	}

}
