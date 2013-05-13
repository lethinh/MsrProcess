package com.capmkts.msrprocess.data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[CommitmentData]")
public class CMCPortfolio {
	
	@Id
	@Column(name = "CommitmentID")
	private String CMCCommitmentNumber;

	private Date CMCCommitmentDate;
	private Date CMCExpirationDate;
	private BigDecimal originalBalance;
	private BigDecimal currentBalance;
	private int remainingDays = 0;
	private boolean currentStatus = false;
	
	public String getCMCCommitmentNumber() {
		return CMCCommitmentNumber;
	}
	public void setCMCCommitmentNumber(String CMCCommitmentNumber){
		this.CMCCommitmentNumber = CMCCommitmentNumber;
	}
	public Date getCMCCommitmentDate(){
		return CMCCommitmentDate;
	}
	public void setCMCCommitmentDate(Date CMCCommitmentDate){
		this.CMCCommitmentDate = CMCCommitmentDate;
	}
	public Date getCMCExpirationDate(){
		return CMCExpirationDate;
	}
	public void setCMCExpirationDate(Date CMCExpirationDate){
		this.CMCExpirationDate = CMCExpirationDate;
	}
	public BigDecimal getOriginalBalance(){
		return originalBalance;
	}
	public void setOriginalBalance(BigDecimal originalBalance){
		this.originalBalance = originalBalance;
	}
	public BigDecimal getCurrentBalance(){
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance){
		this.currentBalance = currentBalance;
	}
	public int getRemainingDays(){
		return remainingDays;
	}
	public void setRemainingDays(int remainingDays){
		this.remainingDays = remainingDays;
	}
	public boolean getCurrentStatus(){
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus){
		this.currentStatus = currentStatus;
	}
}
