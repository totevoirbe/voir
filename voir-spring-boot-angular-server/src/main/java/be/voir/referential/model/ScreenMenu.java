package be.voir.referential.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ScreenMenu {

	@Id
	@GeneratedValue
	private Long id;
	private String page;

	@OneToOne
	private Product product;

	private String label;

	public ScreenMenu() {
		super();
	}

	public ScreenMenu(Long id, String page, Product product, String label) {
		super();
		this.id = id;
		this.page = page;
		this.product = product;
		this.label = label;
	}

	@Override
	public String toString() {
		return "ScreenMenu [id=" + id + ", page=" + page + ", product=" + (product != null ? product.getCode() : "")
				+ ", label=" + label + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}