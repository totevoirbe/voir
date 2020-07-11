package be.panidel.pos10.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author franzph
 * 
 *         For pos 1.0 co,patibility
 * 
 */

@XmlRootElement(name = "root")
public class RootPos10 {

	ProductsPos10 productsPos10;

	@Override
	public String toString() {
		return (productsPos10 == null ? "null" : productsPos10.toString());
	}

	@XmlElement(name = "products")
	public ProductsPos10 getProductsPos10() {
		return productsPos10;
	}

	public void setProductsPos10(ProductsPos10 productsPos10) {
		this.productsPos10 = productsPos10;
	}

}
