package com.capmkts.msrprocess.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[AgencyCommitDetails]")
public class AgencyCommitDetails implements Serializable {

	@Id
	@Column(name = "LenderSellerNum")
	private String lenderSellerNum;
	
	@Column(name = "LenderName")
	private String lenderName;
	
	@Column(name = "AgencyCommitmentID")
	private String agencyCommitmentID;
	
	@Column(name = "CommitmentDate")
	private String commitmentDate;

	@Column(name = "ExpirationDate")
	private String expirationDate;
	
	public String getLenderSellerNum() {
		return lenderSellerNum;
	}

	public void setLenderSellerNum(String lenderSellerNum) {
		this.lenderSellerNum = lenderSellerNum;
	}
	
	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public String getAgencyCommitmentID() {
		return agencyCommitmentID;
	}

	public void setAgencyCommitmentID(String agencyCommitmentID) {
		this.agencyCommitmentID = agencyCommitmentID;
	}
	
	public String getCommitmentDate() {
		return commitmentDate;
	}

	public void setCommitmentDate(String commitmentDate) {
		this.commitmentDate = commitmentDate;
	}

	
	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
