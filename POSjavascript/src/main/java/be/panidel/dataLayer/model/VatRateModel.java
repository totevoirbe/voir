package be.panidel.dataLayer.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "vat")
@NamedQueries({
		@NamedQuery(name = DataModelInterface.POS_VAT_ALL, query = "SELECT e FROM VatRateModel e WHERE CURRENT_DATE BETWEEN :fromDate AND :toDate"),
		@NamedQuery(name = DataModelInterface.POS_VAT_BYCODE, query = "SELECT e FROM VatRateModel e where e.code = :code") })

public class VatRateModel implements DataModelInterface<VatRateModel> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vat_id")
	private Long id;
	private String code;
	private Date fromDate;
	private Date toDate;
	private BigDecimal rate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VatRateModel other = (VatRateModel) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VatModel [id=" + id + ", code=" + code + ", fromDate=" + fromDate + ", toDate=" + toDate + ", rate="
				+ rate + "]";
	}

	public VatRateModel() {
	}

	public VatRateModel(Long id, String code, Date fromDate, Date toDate, BigDecimal rate) {
		super();
		this.id = id;
		this.code = code;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}