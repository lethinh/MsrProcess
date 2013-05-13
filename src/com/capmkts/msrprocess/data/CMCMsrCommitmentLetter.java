package com.capmkts.msrprocess.data;

import java.math.BigDecimal;

public class CMCMsrCommitmentLetter {

	private int commitmentNumber;
	private PatronCompany patronCompany;
	private AgencyCommitmentLetter agencyCommitmentLetter;
	private int loanCount = 0;
	private BigDecimal upb;
	
	public PatronCompany getPatronCompany() {
		return patronCompany;
	}
	public void setPatronCompany(PatronCompany patronCompany) {
		this.patronCompany = patronCompany;
	}
	public AgencyCommitmentLetter getAgencyCommitmentLetter() {
		return agencyCommitmentLetter;
	}
	public void setAgencyCommitmentLetter(
			AgencyCommitmentLetter agencyCommitmentLetter) {
		this.agencyCommitmentLetter = agencyCommitmentLetter;
	}
	public int getLoanCount() {
		return loanCount;
	}
	public void setLoanCount(int loanCount) {
		this.loanCount = loanCount;
	}
	public int getCommitmentNumber() {
		return commitmentNumber;
	}
	public void setCommitmentNumber(int commitmentNumber) {
		this.commitmentNumber = commitmentNumber;
	}
	public BigDecimal getUpb() {
		return upb;
	}
	public void setUpb(BigDecimal upb) {
		this.upb = upb;
	}

}
