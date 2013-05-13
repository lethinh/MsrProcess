package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[PatronCo]")
public class PatronCompany implements Serializable{

	@Id
	@Column(name = "OriginatorID")
	private Integer originatorID;

	@Column(name = "PatronCoName")
	private String patronCoName;

	@Column(name = "PatronCoAbbr")
	private String patronCoAbbr;
	
	@Column(name = "PatronAddress")
	private String patronAddress;
	
	@Column(name = "CMCCreditTierID")
	private Integer cmcCreditTierID;
	
	@Column(name = "SendCommitResponseEmail")
	private String sendCommitResponseEmail;
	
	@Column(name = "ServFilePickupDirectory")
	private String servFilePickupDirectory;
	
	@Column(name = "PricingGroupID")
	private String pricingGroupID;

	@Column(name = "AdjustmentGroupID")
	private String adjustmentGroupID;

	
	@Column(name = "FundingFeeTierID")
	private String fundingFeeTierID;

	
	@Column(name = "ContactName")
	private String contactName;

	
	@Column(name = "ContactEmail")
	private String contactEmail;

	@Column(name = "ContactPhone")
	private String contactPhone;

	@Column(name = "FundingMethodID")
	private String fundingMethodID;

	@Column(name = "SalesPersonID")
	private Integer salesPersonID;

	@Column(name = "ProductionSource")
	private String productionSource;

	@Column(name = "CapAmount")
	private BigDecimal capAmount;
	
	@Column(name = "LenderNum")
	private String lenderNum;

	public Integer getOriginatorID() {
		return originatorID;
	}

	public void setOriginatorID(Integer originatorID) {
		this.originatorID = originatorID;
	}

	public String getPatronCoName() {
		return patronCoName;
	}

	public void setPatronCoName(String patronCoName) {
		this.patronCoName = patronCoName;
	}

	public String getPatronCoAbbr() {
		return patronCoAbbr;
	}

	public void setPatronCoAbbr(String patronCoAbbr) {
		this.patronCoAbbr = patronCoAbbr;
	}

	public String getPatronAddress() {
		return patronAddress;
	}

	public void setPatronAddress(String patronAddress) {
		this.patronAddress = patronAddress;
	}

	public Integer getCmcCreditTierID() {
		return cmcCreditTierID;
	}

	public void setCmcCreditTierID(Integer cmcCreditTierID) {
		this.cmcCreditTierID = cmcCreditTierID;
	}

	public String getSendCommitResponseEmail() {
		return sendCommitResponseEmail;
	}

	public void setSendCommitResponseEmail(String sendCommitResponseEmail) {
		this.sendCommitResponseEmail = sendCommitResponseEmail;
	}

	public String getServFilePickupDirectory() {
		return servFilePickupDirectory;
	}

	public void setServFilePickupDirectory(String servFilePickupDirectory) {
		this.servFilePickupDirectory = servFilePickupDirectory;
	}

	public String getPricingGroupID() {
		return pricingGroupID;
	}

	public void setPricingGroupID(String pricingGroupID) {
		this.pricingGroupID = pricingGroupID;
	}

	public String getAdjustmentGroupID() {
		return adjustmentGroupID;
	}

	public void setAdjustmentGroupID(String adjustmentGroupID) {
		this.adjustmentGroupID = adjustmentGroupID;
	}

	public String getFundingFeeTierID() {
		return fundingFeeTierID;
	}

	public void setFundingFeeTierID(String fundingFeeTierID) {
		this.fundingFeeTierID = fundingFeeTierID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getFundingMethodID() {
		return fundingMethodID;
	}

	public void setFundingMethodID(String fundingMethodID) {
		this.fundingMethodID = fundingMethodID;
	}

	public Integer getSalesPersonID() {
		return salesPersonID;
	}

	public void setSalesPersonID(Integer salesPersonID) {
		this.salesPersonID = salesPersonID;
	}

	public String getProductionSource() {
		return productionSource;
	}

	public void setProductionSource(String productionSource) {
		this.productionSource = productionSource;
	}

	public BigDecimal getCapAmount() {
		return capAmount;
	}

	public void setCapAmount(BigDecimal capAmount) {
		this.capAmount = capAmount;
	}
	
	public String getLenderNum() {
		return lenderNum;
	}
	
	public void setLenderNum(String lenderNum){
		this.lenderNum = lenderNum;
	}
	
}
