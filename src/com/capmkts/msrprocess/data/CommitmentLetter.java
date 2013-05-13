package com.capmkts.msrprocess.data;

public class CommitmentLetter {
	
	private String titleHeader = "Capital Markets Cooperative - MSR Commitment Letter";
	private Buyer buyer;
	private CommitmentDetails commitmentDetails;
	private Patron patron;
	
	
	public String getTitleHeader() {
		return titleHeader;
	}
	public void setTitleHeader(String titleHeader) {
		this.titleHeader = titleHeader;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public CommitmentDetails getCommitmentDetails() {
		return commitmentDetails;
	}
	public void setCommitmentDetails(CommitmentDetails commitmentDetails) {
		this.commitmentDetails = commitmentDetails;
	}
	public Patron getPatron() {
		return patron;
	}
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
}
