package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[AgencyCommitmentLetter_Staging]")
public class AgencyCommitmentLetter implements Serializable {

	@Id
	@GeneratedValue
	private int tableID;

	@Column(name = "LenderNum")
	private Integer lenderNum;

	@Column(name = "LenderName")
	private String lenderName;

	@Column(name = "AgencyCommitmentID")
	private Integer agencyCommitmentID;

	@Column(name = "LenderCommitmentID")
	private Integer lenderCommitmentID;

	@Column(name = "Source")
	private String source;

	@Column(name = "CommitmentDate")
	private Date commitmentDate;

	@Column(name = "Contact")
	private String contact;

	@Column(name = "ExpirationDate")
	private Date expirationDate;

	@Column(name = "Delivery")
	private String delivery;

	@Column(name = "OrigCommitmentAmt")
	private BigDecimal origCommitmentAmt;

	@Column(name = "LowToleranceAmt")
	private BigDecimal lowToleranceAmt;

	@Column(name = "CurrentCommitmentAmt")
	private BigDecimal currentCommitmentAmt;

	@Column(name = "HighToleranceAmt")
	private BigDecimal highToleranceAmt;

	@Column(name = "PurchasedAmt")
	private BigDecimal purchasedAmt;

	@Column(name = "MaxOverDeliveryAmt")
	private BigDecimal maxOverDeliveryAmt;

	@Column(name = "PendingPurchase")
	private BigDecimal pendingPurchase;

	@Column(name = "HBLCap")
	private String HBLCap;

	@Column(name = "Balance")
	private BigDecimal balance;

	@Column(name = "MaxAllowableHBLDelivery")
	private String maxAllowableHBLDelivery;

	@Column(name = "HBLPurchasedAmt")
	private BigDecimal HBLPurchasedAmt;

	@Column(name = "PassThroughRateHigh")
	private Double passThroughRateHigh;

	@Column(name = "PriceHigh")
	private Double priceHigh;

	@Column(name = "NetYieldHigh")
	private Double netYieldHigh;

	@Column(name = "PassThroughRateLow")
	private Double passThroughRateLow;

	@Column(name = "PriceLow")
	private Double priceLow;

	@Column(name = "NetYieldLow")
	private Double netYieldLow;

	@Column(name = "CommitmentDetails")
	private String commitmentDetails;

	@Column(name = "MasterAgreementID")
	private String masterAgreementID;

	@Column(name = "CommitmentPeriod")
	private String commitmentPeriod;

	@Column(name = "BaseServicingFee")
	private Double baseServicingFee;

	@Column(name = "PricingOption")
	private String pricingOption;

	@Column(name = "LPMI")
	private Double LPMI;

	@Column(name = "MinPassThroughRate")
	private Double minPassThroughRate;

	@Column(name = "TotalServicingFee")
	private Double totalServicingFee;

	@Column(name = "RequiredNetYield")
	private Double requiredNetYield;

	@Column(name = "LLPAApplicable")
	private Boolean LLPAApplicable;

	@Column(name = "ProductDescription")
	private String productDescription;

	@Column(name = "ProductName")
	private String productName;

	@Column(name = "ProductFavoriteName")
	private String productFavoriteName;

	@Column(name = "AmortizationType")
	private String amortizationType;

	@Column(name = "AmortizationTerm")
	private String amortizationTerm;

	@Column(name = "LoanType")
	private String loanType;

	@Column(name = "LoanTerm")
	private String loanTerm;

	@Column(name = "LienType")
	private String lienType;

	@Column(name = "RemittanceType")
	private String remittanceType;
	
	@Column(name = "TargetFundDate")
	private Date targetFundDate;
	
	@Column(name = "DataDeliveryDate")
	private Date dataDeliveryDate;

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public Integer getLenderNum() {
		return lenderNum;
	}

	public void setLenderNum(Integer lenderNum) {
		this.lenderNum = lenderNum;
	}

	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public Integer getAgencyCommitmentID() {
		return agencyCommitmentID;
	}

	public void setAgencyCommitmentID(Integer agencyCommitmentID) {
		this.agencyCommitmentID = agencyCommitmentID;
	}

	public Integer getLenderCommitmentID() {
		return lenderCommitmentID;
	}

	public void setLenderCommitmentID(Integer lenderCommitmentID) {
		this.lenderCommitmentID = lenderCommitmentID;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCommitmentDate() {
		return commitmentDate;
	}

	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public BigDecimal getOrigCommitmentAmt() {
		return origCommitmentAmt;
	}

	public void setOrigCommitmentAmt(BigDecimal origCommitmentAmt) {
		this.origCommitmentAmt = origCommitmentAmt;
	}

	public BigDecimal getLowToleranceAmt() {
		return lowToleranceAmt;
	}

	public void setLowToleranceAmt(BigDecimal lowToleranceAmt) {
		this.lowToleranceAmt = lowToleranceAmt;
	}

	public BigDecimal getCurrentCommitmentAmt() {
		return currentCommitmentAmt;
	}

	public void setCurrentCommitmentAmt(BigDecimal currentCommitmentAmt) {
		this.currentCommitmentAmt = currentCommitmentAmt;
	}

	public BigDecimal getHighToleranceAmt() {
		return highToleranceAmt;
	}

	public void setHighToleranceAmt(BigDecimal highToleranceAmt) {
		this.highToleranceAmt = highToleranceAmt;
	}

	public BigDecimal getPurchasedAmt() {
		return purchasedAmt;
	}

	public void setPurchasedAmt(BigDecimal purchasedAmt) {
		this.purchasedAmt = purchasedAmt;
	}

	public BigDecimal getMaxOverDeliveryAmt() {
		return maxOverDeliveryAmt;
	}

	public void setMaxOverDeliveryAmt(BigDecimal maxOverDeliveryAmt) {
		this.maxOverDeliveryAmt = maxOverDeliveryAmt;
	}

	public BigDecimal getPendingPurchase() {
		return pendingPurchase;
	}

	public void setPendingPurchase(BigDecimal pendingPurchase) {
		this.pendingPurchase = pendingPurchase;
	}

	public String getHBLCap() {
		return HBLCap;
	}

	public void setHBLCap(String HBLCap) {
		this.HBLCap = HBLCap;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getMaxAllowableHBLDelivery() {
		return maxAllowableHBLDelivery;
	}

	public void setMaxAllowableHBLDelivery(String maxAllowableHBLDelivery) {
		this.maxAllowableHBLDelivery = maxAllowableHBLDelivery;
	}

	public BigDecimal getHBLPurchasedAmt() {
		return HBLPurchasedAmt;
	}

	public void setHBLPurchasedAmt(BigDecimal HBLPurchasedAmt) {
		this.HBLPurchasedAmt = HBLPurchasedAmt;
	}

	public Double getPassThroughRateHigh() {
		return passThroughRateHigh;
	}

	public void setPassThroughRateHigh(Double passThroughRateHigh) {
		this.passThroughRateHigh = passThroughRateHigh;
	}

	public Double getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(Double priceHigh) {
		this.priceHigh = priceHigh;
	}

	public Double getNetYieldHigh() {
		return netYieldHigh;
	}

	public void setNetYieldHigh(Double netYieldHigh) {
		this.netYieldHigh = netYieldHigh;
	}

	public Double getPassThroughRateLow() {
		return passThroughRateLow;
	}

	public void setPassThroughRateLow(Double passThroughRateLow) {
		this.passThroughRateLow = passThroughRateLow;
	}

	public Double getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(Double priceLow) {
		this.priceLow = priceLow;
	}

	public Double getNetYieldLow() {
		return netYieldLow;
	}

	public void setNetYieldLow(Double netYieldLow) {
		this.netYieldLow = netYieldLow;
	}

	public String getCommitmentDetails() {
		return commitmentDetails;
	}

	public void setCommitmentDetails(String commitmentDetails) {
		this.commitmentDetails = commitmentDetails;
	}

	public String getMasterAgreementID() {
		return masterAgreementID;
	}

	public void setMasterAgreementID(String masterAgreementID) {
		this.masterAgreementID = masterAgreementID;
	}

	public String getCommitmentPeriod() {
		return commitmentPeriod;
	}

	public void setCommitmentPeriod(String commitmentPeriod) {
		this.commitmentPeriod = commitmentPeriod;
	}

	public Double getBaseServicingFee() {
		return baseServicingFee;
	}

	public void setBaseServicingFee(Double baseServicingFee) {
		this.baseServicingFee = baseServicingFee;
	}

	public String getPricingOption() {
		return pricingOption;
	}

	public void setPricingOption(String pricingOption) {
		this.pricingOption = pricingOption;
	}

	public Double getLPMI() {
		return LPMI;
	}

	public void setLPMI(Double LPMI) {
		this.LPMI = LPMI;
	}

	public Double getMinPassThroughRate() {
		return minPassThroughRate;
	}

	public void setMinPassThroughRate(Double minPassThroughRate) {
		this.minPassThroughRate = minPassThroughRate;
	}

	public Double getTotalServicingFee() {
		return totalServicingFee;
	}

	public void setTotalServicingFee(Double totalServicingFee) {
		this.totalServicingFee = totalServicingFee;
	}

	public Double getRequiredNetYield() {
		return requiredNetYield;
	}

	public void setRequiredNetYield(Double requiredNetYield) {
		this.requiredNetYield = requiredNetYield;
	}

	public Boolean getLLPAApplicable() {
		return LLPAApplicable;
	}

	public void setLLPAApplicable(Boolean LLPAApplicable) {
		this.LLPAApplicable = LLPAApplicable;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductFavoriteName() {
		return productFavoriteName;
	}

	public void setProductFavoriteName(String productFavoriteName) {
		this.productFavoriteName = productFavoriteName;
	}

	public String getAmortizationType() {
		return amortizationType;
	}

	public void setAmortizationType(String amortizationType) {
		this.amortizationType = amortizationType;
	}

	public String getAmortizationTerm() {
		return amortizationTerm;
	}

	public void setAmortizationTerm(String amortizationTerm) {
		this.amortizationTerm = amortizationTerm;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLienType() {
		return lienType;
	}

	public void setLienType(String lienType) {
		this.lienType = lienType;
	}

	public String getRemittanceType() {
		return remittanceType;
	}

	public void setRemittanceType(String remittanceType) {
		this.remittanceType = remittanceType;
	}

	public Date getTargetFundDate() {
		return targetFundDate;
	}

	public void setTargetFundDate(Date targetFundDate) {
		this.targetFundDate = targetFundDate;
	}

	public Date getDataDeliveryDate() {
		return dataDeliveryDate;
	}

	public void setDataDeliveryDate(Date dataDeliveryDate) {
		this.dataDeliveryDate = dataDeliveryDate;
	}
	
	
}
