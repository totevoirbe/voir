package be.panidel.management;

import be.panidel.common.AdressIdentification;

public interface Company extends AdressIdentification {

	public abstract String getAdress();

	public abstract void setAdress(String adress);

	public abstract String getPostalCode();

	public abstract void setPostalCode(String postalCode);

}