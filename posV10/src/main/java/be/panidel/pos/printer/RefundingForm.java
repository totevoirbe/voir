package be.panidel.pos.printer;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * RefundingForm generated by hbm2java
 */
public class RefundingForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2575448529312518084L;
	
	private Integer id;
	private int version;
	private String bankAccount;
	private double refundingAmount;
	private String formIntroducerId;
	private String refundingRecipientId;
	private String realEstateAddress;
	private Calendar saleContractDate;
	private int saleContractDirectoryNumber;
	private double saleContractPrice;
	private Calendar loanDeedDate;
	private int loanDeedDirectoryNumber;
	private String loanDeedNotaryHolderName;
	private String financialCompanyName;
	private String financialCompanyAddress;
	private double financialCompanyFinancingAmount;

	public RefundingForm() {
		super();
		this.id = new Integer(1);
		this.version = 2;
		this.bankAccount = "bankAccount";
		this.refundingAmount = new Double(1.5);
		this.formIntroducerId = "formIntroducerId";
		this.refundingRecipientId = "refundingRecipientId";
		this.realEstateAddress = "realEstateAddress";
		this.saleContractDate = new GregorianCalendar();
		this.saleContractDirectoryNumber = 3;
		this.saleContractPrice = new Double(4);
		this.loanDeedDate = new GregorianCalendar();
		this.loanDeedDirectoryNumber = 5;
		this.loanDeedNotaryHolderName = "loanDeedNotaryHolderName";
		this.financialCompanyName = "financialCompanyName";
		this.financialCompanyAddress = "financialCompanyAddress";
		this.financialCompanyFinancingAmount = new Double(6);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public double getRefundingAmount() {
		return refundingAmount;
	}
	public void setRefundingAmount(double refundingAmount) {
		this.refundingAmount = refundingAmount;
	}
	public String getFormIntroducerId() {
		return formIntroducerId;
	}
	public void setFormIntroducerId(String formIntroducerId) {
		this.formIntroducerId = formIntroducerId;
	}
	public String getRefundingRecipientId() {
		return refundingRecipientId;
	}
	public void setRefundingRecipientId(String refundingRecipientId) {
		this.refundingRecipientId = refundingRecipientId;
	}
	public String getRealEstateAddress() {
		return realEstateAddress;
	}
	public void setRealEstateAddress(String realEstateAddress) {
		this.realEstateAddress = realEstateAddress;
	}
	public Calendar getSaleContractDate() {
		return saleContractDate;
	}
	public void setSaleContractDate(Calendar saleContractDate) {
		this.saleContractDate = saleContractDate;
	}
	public int getSaleContractDirectoryNumber() {
		return saleContractDirectoryNumber;
	}
	public void setSaleContractDirectoryNumber(int saleContractDirectoryNumber) {
		this.saleContractDirectoryNumber = saleContractDirectoryNumber;
	}
	public double getSaleContractPrice() {
		return saleContractPrice;
	}
	public void setSaleContractPrice(double saleContractPrice) {
		this.saleContractPrice = saleContractPrice;
	}
	public Calendar getLoanDeedDate() {
		return loanDeedDate;
	}
	public void setLoanDeedDate(Calendar loanDeedDate) {
		this.loanDeedDate = loanDeedDate;
	}
	public int getLoanDeedDirectoryNumber() {
		return loanDeedDirectoryNumber;
	}
	public void setLoanDeedDirectoryNumber(int loanDeedDirectoryNumber) {
		this.loanDeedDirectoryNumber = loanDeedDirectoryNumber;
	}
	public String getLoanDeedNotaryHolderName() {
		return loanDeedNotaryHolderName;
	}
	public void setLoanDeedNotaryHolderName(String loanDeedNotaryHolderName) {
		this.loanDeedNotaryHolderName = loanDeedNotaryHolderName;
	}
	public String getFinancialCompanyName() {
		return financialCompanyName;
	}
	public void setFinancialCompanyName(String financialCompanyName) {
		this.financialCompanyName = financialCompanyName;
	}
	public String getFinancialCompanyAddress() {
		return financialCompanyAddress;
	}
	public void setFinancialCompanyAddress(String financialCompanyAddress) {
		this.financialCompanyAddress = financialCompanyAddress;
	}
	public double getFinancialCompanyFinancingAmount() {
		return financialCompanyFinancingAmount;
	}
	public void setFinancialCompanyFinancingAmount(
			double financialCompanyFinancingAmount) {
		this.financialCompanyFinancingAmount = financialCompanyFinancingAmount;
	}
}