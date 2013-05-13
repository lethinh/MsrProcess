package com.capmkts.msrprocess.validator;

import java.math.BigDecimal;
import java.util.List;

import com.capmkts.msrprocess.dao.CommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.AgencyPurchAdvices;

/**
 * ServicingDATFileFieldValidator - It contains methods which are useful to validate DAT records. 
 * @author Rama Yerramilli
 *
 */
public class ServicingDATFileFieldValidator {
	
	public boolean validateFields(AgencyPurchAdvices datFileBean, DataValidator dataValidator, int record){
		
		boolean isValid = true;
		
		//Step1
		boolean isDatFileRecordValid = true;
		//Step2
		boolean isCommitmentLetterPresent = true;
		//Step3
		boolean isCreditDataPresent = true;
		
		//Step1: Make sure required fields have valid values.
		if(datFileBean.getPURCHASE_DATE() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  PURCHASE_DATE is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getSELLER_NUMBER() == 0){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  SELLER_NUMBER is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getFANNIE_MAE_LN() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  FANNIE_MAE_LN is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getLENDER_LOAN_NO() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  LENDER_LOAN_NO is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getMTG_ID_NO() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  MTG_ID_NO is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getCONTRACT_NO() == 0){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  CONTRACT_NO is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getPASS_THRU_RATE() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  PASS_THRU_RATE is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getINTEREST_RATE() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  INTEREST_RATE is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getFIRST_DUE_DATE() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  FIRST_DUE_DATE is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getORIGINAL_TERM() == 0){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  ORIGINAL_TERM is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getSTATE() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  STATE is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getLTV() == null){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  LTV is null or empty" );
			isDatFileRecordValid = false;
		}
		if(datFileBean.getFICO() == 0){
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  FICO is null or empty" );
			isDatFileRecordValid = false;
		}
		
		//Step2, Step3: 
		// If Step1 passes, Get a record from CommitmentLetter table based on Seller Number
		if(isDatFileRecordValid) {
			//Step2
			CommitmentLetterDAO commitmentLetterDAO = new CommitmentLetterDAO();
			AgencyCommitmentLetter agencyCommitmentLetter = commitmentLetterDAO.getAgencyCommitmentLetterBySellerNumber(datFileBean.getSELLER_NUMBER());
			
			if(agencyCommitmentLetter == null){
				dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  AgencyCommitmentLetter record not Found for Seller Number: " +  datFileBean.getSELLER_NUMBER());
				isCommitmentLetterPresent = false;
			}
			
			//Step3
			CreditDataDAO creditDataDAO = new CreditDataDAO();
			CreditData creditData = creditDataDAO.getCreditDataByLoanNumber(datFileBean.getLENDER_LOAN_NO());
			
			if(creditData == null){
				dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  CreditData record not Found for Loan Number: " +  datFileBean.getLENDER_LOAN_NO());
				isCreditDataPresent = false;
			}
			
			if(isCommitmentLetterPresent && isCreditDataPresent){
				boolean isAgencyCommitmentLetterRecordValid = true;
				
				if(agencyCommitmentLetter.getExpirationDate() == null || 
						agencyCommitmentLetter.getAgencyCommitmentID() == null || 
						agencyCommitmentLetter.getPassThroughRateHigh() == null ||
						agencyCommitmentLetter.getPassThroughRateLow() == null) {
					isAgencyCommitmentLetterRecordValid = false;
				}
				
				if(isAgencyCommitmentLetterRecordValid){
					//Now Comparison of fields/
					if(! datFileBean.getPURCHASE_DATE().before(agencyCommitmentLetter.getExpirationDate())){
					 	dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice PURCHASE_DATE is not before AgencyCommitmentLetter ExpirationDate.");
						isValid = false;
					}
					if(datFileBean.getSELLER_NUMBER() != agencyCommitmentLetter.getLenderNum()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice SELLER_NUMBER is not equal to AgencyCommitmentLetter Lender Number.");
						isValid = false;
					}
					if(datFileBean.getCONTRACT_NO() != agencyCommitmentLetter.getAgencyCommitmentID().intValue()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice CONTRACT_NO is not equal to AgencyCommitmentLetter AgencyCommitmentId.");
						isValid = false;
					}
					if(datFileBean.getPASS_THRU_RATE().compareTo(new BigDecimal(agencyCommitmentLetter.getPassThroughRateHigh())) == -1 && 
							datFileBean.getPASS_THRU_RATE().compareTo(new BigDecimal(agencyCommitmentLetter.getPassThroughRateLow())) == 1 ){
							dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice PASS_THRU_RATE is not less than AgencyCommitmentLetter MaxPTRate or greater than MinPTRate.");
						isValid = false;
					}
					
					/* Not Required as per Thinh.
					 * if(datFileBean.getFANNIE_MAE_LN() != (creditData.get()){
						isValid = false;
					}*/
				} else {
					isValid = false;
				}
				
				boolean isCreditDataRecordValid = true;
				
				if(creditData.getLOANNUMBER()== null || creditData.getLOANNUMBER() == "" ||						
						creditData.getMERSMINNUMBER() == null || creditData.getMERSMINNUMBER() == "" || 
						creditData.getANNUALINTERESTRATE() == null ||
						creditData.getFIRSTDUEDATE() == null ||
						creditData.getLOANTERM() == 0 ||
						creditData.getPROPERTYSTATE() == null || creditData.getPROPERTYSTATE() == "" ||
						creditData.getCURRENTPRINCIPALBALANCE() == null ||
						creditData.getLTV() == null || 
						creditData.getBORROWERCREDITSCORE() == 0) {
					isCreditDataRecordValid = false;
				}
					
				if(isCreditDataRecordValid) {
				
					if(datFileBean.getLENDER_LOAN_NO() != creditData.getLOANNUMBER()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice Lender Loan number is not equal to CredtData Loan Number.");
						isValid = false;
					}
					if(datFileBean.getMTG_ID_NO() != creditData.getMERSMINNUMBER()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice MTG_ID_NO is not equal to CredtData MERSIMINNUMBER.");
						isValid = false;
					}
					if(datFileBean.getCONTRACT_NO() != creditData.getAGENCYCOMMITMENTID()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice CONTRACT_NO is not equal to CredtData AGENCYCOMMITMENTID.");
						isValid = false;
					}
					if(datFileBean.getINTEREST_RATE() != creditData.getANNUALINTERESTRATE()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice INTEREST_RATE is not equal to CredtData ANNUALINTERESTRATE.");
						isValid = false;
					}
					if(datFileBean.getFIRST_DUE_DATE().compareTo(creditData.getFIRSTDUEDATE()) != 0){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice FIRST_DUE_DATE is not equal to CredtData FIRSTDUEDATE.");
						isValid = false;
					}
					if(datFileBean.getORIGINAL_TERM() != creditData.getLOANTERM()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice ORIGINAL_TERM is not equal to CredtData LOANTERM.");
						isValid = false;
					}
					if(datFileBean.getSTATE() != creditData.getPROPERTYSTATE()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice STATE is not equal to CredtData PROPERTYSTATE.");
						isValid = false;
					}
					if(datFileBean.getPRIN_PURCHASED() != creditData.getCURRENTPRINCIPALBALANCE()){
					dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice PRIN_PURCHASED is not equal to CredtData CURRENTPRINCIPALBALANCE.");
						isValid = false;
					}
					if(datFileBean.getLTV() != creditData.getLTV()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice LTV is not equal to CredtData LTV.");
						isValid = false;
					}
					if(datFileBean.getFICO() != creditData.getBORROWERCREDITSCORE()){
						dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  Purchase Advice FICO is not equal to CredtData BORROWERCREDITSCORE.");
						isValid = false;
					}
				}else {
					dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + " CreditData Record is not Valid. DAT File Validation Failed! Insert record into AgencyPurchAdvicesManualCheck table");
					isValid = false;
				}
			}else{
				dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + "  DAT File Validation Failed! Insert record into AgencyPurchAdvicesManualCheck table");
				isValid = false;
			}
		}else {
			dataValidator.addMessage("\nPurchase Advice File: Record Number: " + record + " DAT File Record is missing some values. DAT File Validation Failed! Insert record into AgencyPurchAdvicesManualCheck table");
			isValid = false;
		}
		
		return isValid;
	}

}
