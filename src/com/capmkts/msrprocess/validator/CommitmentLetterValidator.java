package com.capmkts.msrprocess.validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.CommitmentLetterDAO;
import com.capmkts.msrprocess.dao.PatronCompanyDAO;
import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;

public class CommitmentLetterValidator {

	private Map<String, PatronCompany> patronCapAmount = null;

	public CommitmentLetterValidator(){
		getPatronCapAmount();
	}
	
	private void getPatronCapAmount(){
		PatronCompanyDAO patronCompanyDAO = new PatronCompanyDAO();
		this.patronCapAmount = patronCompanyDAO.getPatronCapAmount();
	}
	
	public Boolean isPatronCapAmountReached(AgencyCommitmentLetter agencyCommitmentLetter,DataValidator dataValidator) {
	
		Boolean flg = false;
		
		CommitmentLetterDAO commitmentLetterDAO = new CommitmentLetterDAO();
		
		BigDecimal totalOriginalCommitmentAmount = commitmentLetterDAO.getPatronCompanyTotalAmount(agencyCommitmentLetter.getTargetFundDate(), agencyCommitmentLetter.getLenderNum());
		
		if(agencyCommitmentLetter.getOrigCommitmentAmt() != null){
			totalOriginalCommitmentAmount = agencyCommitmentLetter.getOrigCommitmentAmt();
			System.out.println("\n\n2nd Orig Commit Amt: " +totalOriginalCommitmentAmount+ "\n\n");
		}
		
		System.out.println("\nTESTING LENDER NAME: " +agencyCommitmentLetter.getLenderName());
		
		PatronCompany patronCompany = patronCapAmount.get(agencyCommitmentLetter.getLenderName());
		System.out.println("LENDER NAME: " +agencyCommitmentLetter.getLenderName());
		System.out.println("\n\nOrig Commit Amt Dbl:  " +totalOriginalCommitmentAmount.doubleValue()+ "\n\n");
		System.out.println("\nTESTING: " + patronCompany.getCapAmount());
		System.out.println("\n\nCap Amt Dbl:  " +patronCompany.getCapAmount().doubleValue() +"\n\n");
		if(totalOriginalCommitmentAmount.doubleValue() > patronCompany.getCapAmount().doubleValue()){
			System.out.println("\nYES!!!!!\n");
			flg = true;
			dataValidator.addMessage("Patron per month cap amount exceeded ");
		}
		
		
		System.out.println(" isPatronCapAmountReached "+flg);
//		
//		System.out.println(" patronCompany.getCapAmount().doubleValue() "+patronCompany.getCapAmount().doubleValue());
//		System.out.println(" totalOriginalCommitmentAmount.doubleValue() "+totalOriginalCommitmentAmount.doubleValue());
		
		
		return flg;
	}
}
