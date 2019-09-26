package be.voir.dataLayer;

public class ProductCategoryTag {

	private String code;
	private String label;

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

	public String getLabel() {
		return label;
	}

}
