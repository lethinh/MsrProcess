package com.capmkts.msrprocess.dao;

import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CMCMsrCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;

public class CMCMsrCommitmentLetterDAO {

	public void populateCMCMsrCommitmentLetterDetails(CMCMsrCommitmentLetter cmcMsrCommitmentLetter){
		
		// Commitment Details
		CommitmentLetterDAO commitmentLetterDAO = new CommitmentLetterDAO();
		AgencyCommitmentLetter agencyCommitmentLetter = cmcMsrCommitmentLetter.getAgencyCommitmentLetter();
		agencyCommitmentLetter = commitmentLetterDAO.getAgencyCommitmentLetter(agencyCommitmentLetter.getAgencyCommitmentID());
		cmcMsrCommitmentLetter.setAgencyCommitmentLetter(agencyCommitmentLetter);

		// UPB
		cmcMsrCommitmentLetter.setUpb(agencyCommitmentLetter.getOrigCommitmentAmt());
		
		// Loan Count
		int loanCount = commitmentLetterDAO.getLoanCount(agencyCommitmentLetter.getAgencyCommitmentID());
		cmcMsrCommitmentLetter.setLoanCount(loanCount);
		
		// Seller information
		PatronCompanyDAO patronCompanyDAO = new PatronCompanyDAO();
		
		System.out.println(" agencyCommitmentLetter.getLenderName() "+agencyCommitmentLetter.getLenderName());
		
		PatronCompany patronCompany = patronCompanyDAO.getPatronCompany(agencyCommitmentLetter.getLenderName());
		cmcMsrCommitmentLetter.setPatronCompany(patronCompany);
		
	}
}
