package be.panidel.management.impl;

import be.panidel.common.impl.AdressIdentificationImpl;
import be.panidel.management.Person;

public class PersonImpl extends AdressIdentificationImpl implements Person {

	public PersonImpl() {
		super();
	}

	public PersonImpl(String id, String code, String name, String description,
			String htmlKeyLabel, String adress, String postalCode,
			String phone, String fax) {
		super(id, code, name, description, htmlKeyLabel, adress, postalCode,
				phone, fax);
	}

}
