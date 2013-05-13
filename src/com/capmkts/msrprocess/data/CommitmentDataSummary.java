package com.capmkts.msrprocess.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[CommitmentDataSummary]")
public class CommitmentDataSummary implements Serializable {

	@Id
	@Column(name = "CommitmentID")
	private String commitmentID;
	
	@Column(name = "OriginatorID")
	private String originatorID;
	
	@Column(name = "RequestedLoanCount")
	private String requestedLoanCount;
	
	@Column(name = "RequestedUPB")
	private String requestedUPB;

	@Column(name = "DeliveredLoanCount")
	private String deliveredLoanCount;
	
	@Column(name = "DeliveredUPB")
	private String deliveredUPB;
	
	public String getCommitmentID() {
		return commitmentID;
	}

	public void setCommitmentID(String commitmentID) {
		this.commitmentID = commitmentID;
	}

	public String getOriginatorID() {
		return originatorID;
	}

	public void setOriginatorID(String originatorID) {
		this.originatorID = originatorID;
	}
	
	public String getRequestedLoanCount() {
		return requestedLoanCount;
	}

	public void setRequestedLoanCount(String requestedLoanCount) {
		this.requestedLoanCount = requestedLoanCount;
	}
	
	public String getRequestedUPB() {
		return requestedUPB;
	}

	public void setRequestedUPB(String requestedUPB) {
		this.requestedUPB = requestedUPB;
	}
	
	public String getDeliveredLoanCount() {
		return deliveredLoanCount;
	}

	public void setDeliveredLoanCount(String deliveredLoanCount) {
		this.deliveredLoanCount = deliveredLoanCount;
	}
	
	public String getDeliveredUPB() {
		return deliveredUPB;
	}

	public void setDeliveredUPB(String deliveredUPB) {
		this.deliveredUPB = deliveredUPB;
	}
}
