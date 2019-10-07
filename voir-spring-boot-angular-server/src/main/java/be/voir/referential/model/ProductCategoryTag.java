package be.voir.referential.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductCategoryTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	private String label;

	public ProductCategoryTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategoryTag(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}

	@Override
	public String toString() {
		return "ProductCategoryTag [code=" + code + ", label=" + label + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
