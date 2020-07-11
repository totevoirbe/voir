package be.panidel.pos10.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author franzph
 * 
 *         For pos 1.0 co,patibility
 * 
 */

@XmlRootElement(name = "products")
public class ProductsPos10 {

	private final static Logger LOG = LoggerFactory.getLogger(ProductsPos10.class);

	private List<ProductPos10> productPos10s;

	@Override
	public String toString() {

		String desc = "";

		if (productPos10s != null) {
			for (ProductPos10 productPos10 : productPos10s) {
				desc += ";productPos10[" + productPos10 + "]";
			}
		} else {
			LOG.debug("productPos10s is null");
		}

		return desc;

	}

	@XmlElement(name = "product")
	public List<ProductPos10> getProductPos10s() {
		return productPos10s;
	}

	public void setProductPos10s(List<ProductPos10> productPos10s) {
		this.productPos10s = productPos10s;
	}

}
