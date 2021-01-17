package be.panidel.management.impl.person;

import be.panidel.management.impl.PersonImpl;

public class Employee extends PersonImpl {

	private String firstName;

	private String lastName;

	public Employee() {
		super();
	}

	public Employee(String id, String code, String name, String description,
			String htmKeyLabel, String adress, String postalCode, String phone,
			String fax, String firstName, String lastName) {
		super(id, code, name, description, htmKeyLabel, adress, postalCode,
				phone, fax);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
