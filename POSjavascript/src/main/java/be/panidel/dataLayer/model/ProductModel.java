package be.panidel.dataLayer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.panidel.dataLayer.helper.ModelHelper;

@Entity
@Table(name = "product")
@NamedQueries({ @NamedQuery(name = DataModelInterface.POS_PRODUCT_ALL, query = "SELECT e FROM ProductModel e"),
		@NamedQuery(name = DataModelInterface.POS_PRODUCT_DELETE_ALL, query = "DELETE FROM ProductModel"),
		@NamedQuery(name = DataModelInterface.POS_PRODUCT_BYCODE, query = "SELECT e FROM ProductModel e where e.code = :code"),
		@NamedQuery(name = DataModelInterface.POS_PRODUCT_BYID, query = "SELECT e FROM ProductModel e where e.id = :id"),
		@NamedQuery(name = DataModelInterface.POS_PRODUCT_COUNT, query = "SELECT count(e) FROM ProductModel e") })

public class ProductModel implements DataModelInterface<ProductModel> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	private Long id;

	private String label;
	private String ticketLabel;
	private String code;
	private String name;
	private String htmlKeyLabel;
	private String type;
	private String image;

	@ManyToOne
	private VatRateModel vatRateOnPlace;

	@ManyToOne
	private VatRateModel vatRateTakeAway;
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
	public boolean equals(Object productAsObject) {

		if (productAsObject instanceof ProductModel) {

			ProductModel productEval = (ProductModel) productAsObject;

			if (!ModelHelper.checkEquals(this.id, productEval.id)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.label, productEval.label)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.ticketLabel, productEval.ticketLabel)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.code, productEval.code)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.name, productEval.name)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.htmlKeyLabel, productEval.htmlKeyLabel)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.type, productEval.type)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.image, productEval.image)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.vatRateOnPlace, productEval.vatRateOnPlace)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.vatRateTakeAway, productEval.vatRateTakeAway)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.mini, productEval.mini)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.normal, productEval.normal)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.geant, productEval.geant)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.fitmini, productEval.fitmini)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.fitnormal, productEval.fitnormal)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.fitgeant, productEval.fitgeant)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.webDetail, productEval.webDetail)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.afficheDetail, productEval.afficheDetail)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.canMerge, productEval.canMerge)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {
		return "id[" + id + "]; code[" + code + "]; name[" + name + "]; htmlKeyLabel[" + htmlKeyLabel + "]; type["
				+ type + "]; image[" + image + "]; vatRateOnPlace[" + vatRateOnPlace + "]; vatRateTakeAway["
				+ vatRateTakeAway + "]; mini[" + mini + "]; normal[" + normal + "]; geant[" + geant + "]; fitmini["
				+ fitmini + "]; fitnormal[" + fitnormal + "]; fitgeant[" + fitgeant + "]; webDetail[" + webDetail
				+ "]; afficheDetail[" + afficheDetail + "]" + "]; canMerge[" + canMerge + "]";
	}

	public ProductModel() {
	}

	public ProductModel(Long id, String label, String ticketLabel, String code, String name, String htmlKeyLabel,
			String type, String image, VatRateModel vatRateOnPlace, VatRateModel vatRateTakeAway, BigDecimal mini,
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

	public VatRateModel getVatRateOnPlace() {
		return vatRateOnPlace;
	}

	public void setVatRateOnPlace(VatRateModel vatRateOnPlace) {
		this.vatRateOnPlace = vatRateOnPlace;
	}

	public VatRateModel getVatRateTakeAway() {
		return vatRateTakeAway;
	}

	public void setVatRateTakeAway(VatRateModel vatRateTakeAway) {
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
