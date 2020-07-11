package be.panidel.frontLayer.model;

public class PaymentMethodJsonModel implements JsonModelInterface {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String label;
	private String ticketLabel;
	private String htmlKeyLabel;
	private Boolean canMerge;

	@Override
	public String toString() {
		return "id[" + id + "]; label[" + label + "]; htmlKeyLabel[" + htmlKeyLabel + "]; canMerge[" + canMerge + "]";
	}

	public PaymentMethodJsonModel() {
	}

	public PaymentMethodJsonModel(Long id, String label, String ticketLabel, String htmlKeyLabel, Boolean canMerge) {
		super();
		this.id = id;
		this.label = label;
		this.ticketLabel = ticketLabel;
		this.htmlKeyLabel = htmlKeyLabel;
		this.canMerge = canMerge;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTicketLabel() {
		return ticketLabel;
	}

	public void setTicketLabel(String ticketLabel) {
		this.ticketLabel = ticketLabel;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public Boolean getCanMerge() {
		return canMerge;
	}

	public void setCanMerge(Boolean canMerge) {
		this.canMerge = canMerge;
	}

}
