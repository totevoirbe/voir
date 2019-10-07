package be.voir.referential.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String name;

	@ManyToMany
	private List<ProductCategoryTag> productCategoryTags = new ArrayList<ProductCategoryTag>();

	@OneToOne
	private CodeTVA vatRateOnPlace;
	@OneToOne
	private CodeTVA vatRateTakeAway;

	private BigDecimal mini;
	private BigDecimal normal;
	private BigDecimal geant;
	private BigDecimal fitmini;
	private BigDecimal fitnormal;
	private BigDecimal fitgeant;

	private String image;
	private String htmlKeyLabel;
	private String ticketLabel;
	private String webDetail;
	private String afficheDetail;
	private Boolean canMerge;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String code, String name, List<ProductCategoryTag> productCategoryTags,
			CodeTVA vatRateOnPlace, CodeTVA vatRateTakeAway, BigDecimal mini, BigDecimal normal, BigDecimal geant,
			BigDecimal fitmini, BigDecimal fitnormal, BigDecimal fitgeant, String image, String htmlKeyLabel,
			String ticketLabel, String webDetail, String afficheDetail, Boolean canMerge) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.productCategoryTags = productCategoryTags;
		this.vatRateOnPlace = vatRateOnPlace;
		this.vatRateTakeAway = vatRateTakeAway;
		this.mini = mini;
		this.normal = normal;
		this.geant = geant;
		this.fitmini = fitmini;
		this.fitnormal = fitnormal;
		this.fitgeant = fitgeant;
		this.image = image;
		this.htmlKeyLabel = htmlKeyLabel;
		this.ticketLabel = ticketLabel;
		this.webDetail = webDetail;
		this.afficheDetail = afficheDetail;
		this.canMerge = canMerge;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", productCategoryTags="
				+ productCategoryTags + ", vatRateOnPlace=" + vatRateOnPlace + ", vatRateTakeAway=" + vatRateTakeAway
				+ ", mini=" + mini + ", normal=" + normal + ", geant=" + geant + ", fitmini=" + fitmini + ", fitnormal="
				+ fitnormal + ", fitgeant=" + fitgeant + ", image=" + image + ", htmlKeyLabel=" + htmlKeyLabel
				+ ", ticketLabel=" + ticketLabel + ", webDetail=" + webDetail + ", afficheDetail=" + afficheDetail
				+ ", canMerge=" + canMerge + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ProductCategoryTag> getProductCategoryTags() {
		return productCategoryTags;
	}

	public void setProductCategoryTags(List<ProductCategoryTag> productCategoryTags) {
		this.productCategoryTags = productCategoryTags;
	}

	public CodeTVA getVatRateOnPlace() {
		return vatRateOnPlace;
	}

	public void setVatRateOnPlace(CodeTVA vatRateOnPlace) {
		this.vatRateOnPlace = vatRateOnPlace;
	}

	public CodeTVA getVatRateTakeAway() {
		return vatRateTakeAway;
	}

	public void setVatRateTakeAway(CodeTVA vatRateTakeAway) {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public String getTicketLabel() {
		return ticketLabel;
	}

	public void setTicketLabel(String ticketLabel) {
		this.ticketLabel = ticketLabel;
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