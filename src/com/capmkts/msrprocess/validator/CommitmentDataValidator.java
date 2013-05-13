package com.capmkts.msrprocess.validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CommimentDataInElgStateDAO;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.CommitmentLetterDAO;
import com.capmkts.msrprocess.dao.PatronCompanyDAO;
import com.capmkts.msrprocess.data.CommitmentData;
import com.capmkts.msrprocess.data.PatronCompany;

public class CommitmentDataValidator {

	private String inEligibleStates = "";

	public CommitmentDataValidator() {
		getInEligibleStates();
		
	}

	public void validate(List<CommitmentData> commitmentDataList,DataValidator dataValidator, CommitmentDataDAO commitmentDAO) {

		BigDecimal currentBOfICALoanAmount = new BigDecimal("0");

		for (int i = 0; i < commitmentDataList.size(); i++) {

			CommitmentData commitmentData = commitmentDataList.get(i);
			StringBuffer message = new StringBuffer();

			// B Of I - CA - LoanAmount
			if (commitmentData.getPropertyState().equalsIgnoreCase("CA")
					&& commitmentData.getOriginatorID() == MsrConstants.BOFI_ORIGINATOR_ID) {
				currentBOfICALoanAmount.add(commitmentData.getLoanAmount());
			}

			boolean isValidAgencyCommitNumber = true;
			boolean isValidState = true;

			// check for valid commitment number
			CommitmentLetterDAO commitmentLetterDAO = new CommitmentLetterDAO();

			if (!commitmentLetterDAO.isCommitmentLetterExists(commitmentData.getAgencyCommitmentID())) {
				isValidAgencyCommitNumber = false;
			}

			// STATE Validation -- Req 1
			if (!isValidState(commitmentData)) {
				isValidState = false;
			}

			if (!isValidAgencyCommitNumber || !isValidState) {

				dataValidator.addMessage("### Loan "+ commitmentData.getLoanNumber() + " is Invalid --"+ message.toString());

				StringBuffer invalidMessage = new StringBuffer();

				if (!isValidAgencyCommitNumber) {
					invalidMessage.append(" InValid Agency Commit Num :");
					invalidMessage.append(commitmentData.getAgencyCommitmentID());

				}
				if(!isValidState){
					if(!isValidAgencyCommitNumber){
						invalidMessage.append(", "); 
					}
					invalidMessage.append(" InEligible State :");
					invalidMessage.append(commitmentData.getPropertyState());
				}	
				
				setInValidMessage(commitmentData, invalidMessage.toString());
				dataValidator.addMessage(invalidMessage.toString());
			}
		}

		// Validation 2
		// State Specific Patron Cap % - START
			// Total Loan Amount of B Of I
			BigDecimal loanAmountOfBOfITotal = commitmentDAO.getTotalLoanAmountOfBOfI();
	
			// Total Loan Amount of B Of I (CA state)
			BigDecimal existingLAOfBOfICAStateTotal = commitmentDAO.getTotalLoanAmountOfBOfICAState();
	
			// adding current loan amount of B Of I - CA State
			// adding existing and current
			existingLAOfBOfICAStateTotal.add(currentBOfICALoanAmount);
	
			// 30 % of total B of I portfolio
			BigDecimal bOfICACapAmount = loanAmountOfBOfITotal.multiply(new BigDecimal(30 / 100)); 
	
//			if (existingLAOfBOfICAStateTotal.doubleValue() > bOfICACapAmount.doubleValue()) {
//				inValidCurrentBofICALoans(commitmentDataList, dataValidator);
//	
//			}
		// State Specific Patron Cap % -- END

		// Validation 3 - TODO
		// Loan Cap per patron by month -- START
			//checkPatronCompanySpecificCap(commitmentDataList,dataValidator);
		// Loan Cap per patron by month -- END

		if (dataValidator.getMessageList().size() > 0) {
			dataValidator.setValid(false);
		}

	}

	private boolean isValidState(CommitmentData commitmentData) {

		boolean flg = true;

		if (inEligibleStates.indexOf(commitmentData.getPropertyState()) != -1) {
			flg = false;
		}

		return flg;
	}

	private void getInEligibleStates() {

		CommimentDataInElgStateDAO inElgStateDAO = new CommimentDataInElgStateDAO();
		this.inEligibleStates = inElgStateDAO.getInEligibleStates();

	}
	


	private void setInValidMessage(CommitmentData commitmentData, String message) {
		commitmentData.setValid(false);
		commitmentData.setInvalidMessage(message);
	}


	private void inValidCurrentBofICALoans(
			List<CommitmentData> commitmentDataList, DataValidator dataValidator) {

		boolean caLoansFlgRejected = false;

		for (int i = 0; i < commitmentDataList.size(); i++) {

			CommitmentData commitmentData = commitmentDataList.get(i);
			StringBuffer message = new StringBuffer();

			// B Of I - CA - LoanAmount
			if (commitmentData.getPropertyState().equalsIgnoreCase("CA")
					&& commitmentData.getOriginatorID() == MsrConstants.BOFI_ORIGINATOR_ID) {

				commitmentData.setValid(false);
				caLoansFlgRejected = true;
				message.append(" B Of I California exceeded Max 30% of entire portfolio");
				commitmentData.setInvalidMessage(commitmentData.getInvalidMessage()+" , "+message.toString());

			}
		}

		if (caLoansFlgRejected) {
			dataValidator.addMessage("B Of I California exceeded Max 30% of entire portfolio ");
		}

	}
}
