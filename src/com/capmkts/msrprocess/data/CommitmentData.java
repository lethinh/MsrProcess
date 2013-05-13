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
@Table(name = "[CoIssue_Test].[dbo].[CommitmentData_Staging]")

public class CommitmentData implements Serializable {

	@Id
	@GeneratedValue
	private int tableID;

	@Column(name = "OriginatorID")
	private Integer originatorID;

	@Column(name = "SellerLoanNumber")
	private String loanNumber;

	@Column(name = "AgencyCommitmentID")
	private Integer agencyCommitmentID;

	@Column(name = "Product")
	private String product;

	@Column(name = "LoanAmount")
	private BigDecimal loanAmount;

	@Column(name = "NoteRate")
	private Double noteRate;

	@Column(name = "PropertyState")
	private String propertyState;

	@Column(name = "LTV")
	private Double ltv;

	@Column(name = "CLTV")
	private Double cltv;

	@Column(name = "BorrowerFICO")
	private Double borrowerFICO;

	@Column(name = "OccupancyDesc")
	private String occupancyDesc;

	@Column(name = "PropertyTypeDesc")
	private String propertyTypeDesc;

	@Column(name = "LoanPurposeDesc")
	private String loanPurposeDesc;

	@Column(name = "DocTypeDesc")
	private String docTypeDesc;

	@Column(name = "WaiveEscrowsFlag")
	private Boolean waiveEscrowFlag;

	@Column(name = "DTI")
	private Double dti;

	@Column(name = "ActualCloseDate")
	private Date actualCloseDate;

	@Column(name = "ServPurchPrice")
	private Double cmcSRP;
	
	@Column(name = "Valid")
	private Boolean valid; 

	@Column(name = "InvalidMessage")
	private String invalidMessage; 

	@Column(name = "CreatedDate")
	private Date createdDate; 
	
	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public Integer getOriginatorID() {
		return originatorID;
	}

	public void setOriginatorID(Integer originatorID) {
		this.originatorID = originatorID;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public Integer getAgencyCommitmentID() {
		return agencyCommitmentID;
	}

	public void setAgencyCommitmentID(Integer agencyCommitmentID) {
		this.agencyCommitmentID = agencyCommitmentID;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getNoteRate() {
		return noteRate;
	}

	public void setNoteRate(Double noteRate) {
		this.noteRate = noteRate;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public Double getLtv() {
		return ltv;
	}

	public void setLtv(Double ltv) {
		this.ltv = ltv;
	}

	public Double getCltv() {
		return cltv;
	}

	public void setCltv(Double cltv) {
		this.cltv = cltv;
	}

	public Double getBorrowerFICO() {
		return borrowerFICO;
	}

	public void setBorrowerFICO(Double borrowerFICO) {
		this.borrowerFICO = borrowerFICO;
	}

	public String getOccupancyDesc() {
		return occupancyDesc;
	}

	public void setOccupancyDesc(String occupancyDesc) {
		this.occupancyDesc = occupancyDesc;
	}

	public String getPropertyTypeDesc() {
		return propertyTypeDesc;
	}

	public void setPropertyTypeDesc(String propertyTypeDesc) {
		this.propertyTypeDesc = propertyTypeDesc;
	}

	public String getLoanPurposeDesc() {
		return loanPurposeDesc;
	}

	public void setLoanPurposeDesc(String loanPurposeDesc) {
		this.loanPurposeDesc = loanPurposeDesc;
	}

	public String getDocTypeDesc() {
		return docTypeDesc;
	}

	public void setDocTypeDesc(String docTypeDesc) {
		this.docTypeDesc = docTypeDesc;
	}

	public Boolean getWaiveEscrowFlag() {
		return waiveEscrowFlag;
	}

	public void setWaiveEscrowFlag(Boolean waiveEscrowFlag) {
		this.waiveEscrowFlag = waiveEscrowFlag;
	}

	public Double getDti() {
		return dti;
	}

	public void setDti(Double dti) {
		this.dti = dti;
	}

	public Date getActualCloseDate() {
		return actualCloseDate;
	}

	public void setActualCloseDate(Date actualCloseDate) {
		this.actualCloseDate = actualCloseDate;
	}

	public Double getCmcSRP() {
		return cmcSRP;
	}

	public void setCmcSRP(Double cmcSRP) {
		this.cmcSRP = cmcSRP;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
