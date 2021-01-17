package be.panidel.common;

public interface AdressIdentification extends Identification {

	public abstract String getAdress();

	public abstract void setAdress(String adress);

	public abstract String getFax();

	public abstract void setFax(String fax);

	public abstract String getPhone();

	public abstract void setPhone(String phone);

	public abstract String getPostalCode();

	public abstract void setPostalCode(String postalCode);

}