package be.panidel.frontLayer.model;

import java.math.BigDecimal;

public class ProductJsonModel implements JsonModelInterface {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String label;
	private String ticketLabel;
	private String code;
	private String name;
	private String htmlKeyLabel;
	private String type;
	private String image;
	private BigDecimal vatRateOnPlace;
	private BigDecimal vatRateTakeAway;
	private BigDecimal mini;
	private BigDecimal normal;
	private BigDecimal geant;
	private BigDecimal fitmini;
	private BigDecimal fitnormal;
	private BigDecimal fitgeant;
	private String webDetail;
	private String afficheDetail;
	private Boolean canMerge;

	@Override
	public String toString() {
		return "ProductJsonModel [id=" + id + ", label=" + label + ", ticketLabel=" + ticketLabel + ", code=" + code
				+ ", name=" + name + ", htmlKeyLabel=" + htmlKeyLabel + ", type=" + type + ", image=" + image
				+ ", vatRateOnPlace=" + vatRateOnPlace + ", vatRateTakeAway=" + vatRateTakeAway + ", mini=" + mini
				+ ", normal=" + normal + ", geant=" + geant + ", fitmini=" + fitmini + ", fitnormal=" + fitnormal
				+ ", fitgeant=" + fitgeant + ", webDetail=" + webDetail + ", afficheDetail=" + afficheDetail
				+ ", canMerge=" + canMerge + "]";
	}

	public ProductJsonModel() {
	}

	public ProductJsonModel(Long id, String label, String ticketLabel, String code, String name, String htmlKeyLabel,
			String type, String image, BigDecimal vatRateOnPlace, BigDecimal vatRateTakeAway, BigDecimal mini,
			BigDecimal normal, BigDecimal geant, BigDecimal fitmini, BigDecimal fitnormal, BigDecimal fitgeant,
			String webDetail, String afficheDetail, Boolean canMerge) {
		super();
		this.id = id;
		this.label = label;
		this.ticketLabel = ticketLabel;
		this.code = code;
		this.name = name;
		this.htmlKeyLabel = htmlKeyLabel;
		this.type = type;
		this.image = image;
		this.vatRateOnPlace = vatRateOnPlace;
		this.vatRateTakeAway = vatRateTakeAway;
		this.mini = mini;
		this.normal = normal;
		this.geant = geant;
		this.fitmini = fitmini;
		this.fitnormal = fitnormal;
		this.fitgeant = fitgeant;
		this.webDetail = webDetail;
		this.afficheDetail = afficheDetail;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getVatRateOnPlace() {
		return vatRateOnPlace;
	}

	public void setVatRateOnPlace(BigDecimal vatRateOnPlace) {
		this.vatRateOnPlace = vatRateOnPlace;
	}

	public BigDecimal getVatRateTakeAway() {
		return vatRateTakeAway;
	}

	public void setVatRateTakeAway(BigDecimal vatRateTakeAway) {
		this.vatRateTakeAway = vatRateTakeAway;
	}

	public BigDecimal getMini() {
		return mini;
	}

	public void setMini(BigDecimal mini) {
		this.mini = mini;
	}

	public BigDecimal getNormal() {
		return normal;
	}

	public void setNormal(BigDecimal normal) {
		this.normal = normal;
	}

	public BigDecimal getGeant() {
		return geant;
	}

	public void setGeant(BigDecimal geant) {
		this.geant = geant;
	}

	public BigDecimal getFitmini() {
		return fitmini;
	}

	public void setFitmini(BigDecimal fitmini) {
		this.fitmini = fitmini;
	}

	public BigDecimal getFitnormal() {
		return fitnormal;
	}

	public void setFitnormal(BigDecimal fitnormal) {
		this.fitnormal = fitnormal;
	}

	public BigDecimal getFitgeant() {
		return fitgeant;
	}

	public void setFitgeant(BigDecimal fitgeant) {
		this.fitgeant = fitgeant;
	}

	public String getWebDetail() {
		return webDetail;
	}

	public void setWebDetail(String webDetail) {
		this.webDetail = webDetail;
	}

	public String getAfficheDetail() {
		return afficheDetail;
	}

	public void setAfficheDetail(String afficheDetail) {
		this.afficheDetail = afficheDetail;
	}

	public Boolean getCanMerge() {
		return canMerge;
	}

	public void setCanMerge(Boolean canMerge) {
		this.canMerge = canMerge;
	}

}
