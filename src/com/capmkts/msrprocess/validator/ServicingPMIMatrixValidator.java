package com.capmkts.msrprocess.validator;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.data.CreditData;

public class ServicingPMIMatrixValidator {
	
	//if isValid = true, insert data into table 'CreditData'; 
	//else insert the ServicingFile's data into a temporary table 'CreditDataManualCheck'
	boolean isValid = false;
	
	public boolean validateCSV_PMIMatrix(CreditData csvFileBean){
		
	
		//> CMC Business clarified they just want to check the fields to see if the values are 'Not Null'. 
		//No validation is needed for the actual values.
		
		if(csvFileBean.getPMIMONTHLYPAYMENT() != null ||
				csvFileBean.getLOANPROGRAMID() != null ||
				csvFileBean.getLTV() != null ) {
			isValid = true;			
		} 
		
		if(isValid){
			int pmiMipTerm = 0; 
			int loType = ServiceValidateUtility.getLoType(csvFileBean);
			
			//1st Check
			if(loType == 1 &&
					csvFileBean.getPMIMIPBILLCODE() == 2) {
				
				//1				
				if(csvFileBean.getPMIMONTHLYPAYMENT() == null){
					isValid = false;
				} 
				
				//2				
				if(csvFileBean.getTOTALPAYMENT() == null){
					isValid = false;
				}
				
				//3
				//This is a certificate # CMC receives. 
				//We do not need to validate what the value is, we just need to verify that a number exists in the field.
				if(csvFileBean.getPMIMIPGUARCERTNO() == null) {
					isValid = false;
				}
				
				//4				
				if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() == null){
					isValid = false;
				}
				
				//5				
				if(csvFileBean.getPMIRATEPERCENTAGE() == null){
					isValid = false;
				}
				
				//6
				if(csvFileBean.getPMIMIPPAYEE() == null){
					isValid = false;
				}		
				
				//7				
				if(csvFileBean.getPMIMIPDISBURSEMENTDUEDATE() == null){
					isValid = false;
				}
				
				//8
				//PMI MIP DISCBURSEMENT AMOUNT --> Annual Amount -- Page 24 PMI Matrix
				//"Annual Amount" is 'PMIMIPANNUALPREMIUM' in the CSV file. However, we do not need to validate the amount, 
				//we just need to verify that an amount exists in the field.
				if(csvFileBean.getPMIMIPANNUALPREMIUM() == null) {
					isValid = false;
				} 
				
				//9
				//INSERT the last 3-digits of the 'FHA CASE NUMBER' to the 'FHASECTIONCODE' in the 'CreditData' table 
				setFhaSectionCode(csvFileBean);	
				
				if(isValid){
					pmiMipTerm = 12;
				}

				//TODO:update table 'CreditData' set 'PMIMIPTERM = 12'
				//CreditDataDB.setPMIPTERM(pmiMipTerm);
				setPmiMipTerm(pmiMipTerm);
			}
			
			//2ND Check
			// IF "LOANPROGRAMID is Conventional" AND "LTV > or = 80%" AND "PMIMIPBILLCODE == 2" 
	        //THEN check the fields for value as noted.
			BigDecimal firstValue = new BigDecimal("80");
			BigDecimal secondValue = new BigDecimal(".80");
			
			if(loType == 6 &&
			//if(csvFileBean.getLOANPROGRAMID().equalsIgnoreCase("Conventional") &&	
					((csvFileBean.getLTV().compareTo(firstValue) == 0) ||
							(csvFileBean.getLTV().compareTo(secondValue) == 0)) &&
					csvFileBean.getPMIMIPBILLCODE() == 2) {
				

				//1				
				if(csvFileBean.getPMIMONTHLYPAYMENT() == null){
					isValid = false;
				} 
				
				//2				
				if(csvFileBean.getTOTALPAYMENT() == null){
					isValid = false;
				}
				
				//3
				//This is a certificate # CMC receives. 
				//We do not need to validate what the value is, we just need to verify that a number exists in the field.
				if(csvFileBean.getPMIMIPGUARCERTNO() == null) {
					isValid = false;
				}
				
				//4				
				if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() == null){
					isValid = false;
				}
				
				//5				
				if(csvFileBean.getPMIRATEPERCENTAGE() == null){
					isValid = false;
				}
				
				
				//6
				//We do not need to validate what the name of the company is, we just need to verify that the field is NOT NULL.
				if(csvFileBean.getPMIMIPPAYEE() == null){
					isValid = false;
				}		
				
				//7			
				if(csvFileBean.getPMIMIPDISBURSEMENTDUEDATE() == null){
					isValid = false;
				}
				
				//8
				//PMI MIP DISCBURSEMENT AMOUNT --> Annual Amount -- Page 24 PMI Matrix
				//"Annual Amount" is 'PMIMIPANNUALPREMIUM' in the CSV file. However, we do not need to validate the amount, 
				//we just need to verify that an amount exists in the field.
				if(csvFileBean.getPMIMIPDISBURSEMENTAMOUNT() == null) {
					isValid = false;
				} 
				
				if(isValid){
					pmiMipTerm = 1;
				}
				
				//TODO:update table 'CreditData' set 'PMIMIPTERM = 1'
				//CreditDataDB.setPMIPTERM(pmiMipTerm);
				setPmiMipTerm(pmiMipTerm);
			}
						
			//4th Check
			//if(csvFileBean.getLOANPROGRAMID().equalsIgnoreCase("Conventional") &&
			if(loType == 6 &&
					((csvFileBean.getLTV().compareTo(firstValue) == 0) ||
							(csvFileBean.getLTV().compareTo(secondValue) == 0)) &&
					csvFileBean.getPMIMIPBILLCODE() == 3) {
				
				//1
				if(csvFileBean.getPMIMONTHLYPAYMENT() != null){
					isValid = false;
				} 
				
				//2
				//This is a certificate # CMC receives. 
				//We do not need to validate what the value is, we just need to verify that a number exists in the field.
				if(csvFileBean.getPMIMIPGUARCERTNO() != null) {
					isValid = false;
				}
				
				//3							
				if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() != null){
					isValid = false;
				}
				
				//4				
				if(csvFileBean.getPMIRATEPERCENTAGE() != null){
					isValid = false;
				}
				
				//5
				if(csvFileBean.getPMIMIPPAYEE() != null){
					isValid = false;
				}		
				
				//6
				//TODO: update table 'CreditData' set 'PMIMIPTERM = NULL or default value'
				//CreditDataDB.setPMIMIPTERM(pmiMipTerm);
				setPmiMipTerm(pmiMipTerm);
				
				//7
				if(csvFileBean.getPMIMIPDISBURSEMENTDUEDATE() == null){
					isValid = false;
				}
				
				//8
				if(csvFileBean.getPMIMIPDISBURSEMENTAMOUNT() != null) {
					isValid = false;
				} 
				
			}
			
			//5th Check
			//if(csvFileBean.getLOANPROGRAMID().equalsIgnoreCase("Conventional") &&
			if(loType == 6 &&
					((csvFileBean.getLTV().compareTo(firstValue) == 0) ||
							(csvFileBean.getLTV().compareTo(secondValue) == 0)) &&
					csvFileBean.getPMIMIPBILLCODE() == 4) {
			
				//1				
				if(csvFileBean.getPMIMONTHLYPAYMENT() == null){
					isValid = false;
				} 
				
				//2				
				if(csvFileBean.getTOTALPAYMENT() == null){
					isValid = false;
				}
				
				//This is a certificate # CMC receives. 
				//We do not need to validate what the value is, we just need to verify that a number exists in the field.
				if(csvFileBean.getPMIMIPGUARCERTNO() == null) {
					isValid = false;
				}
				
				//4				
				if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() == null){
					isValid = false;
				}
				
				//5				
				if(csvFileBean.getPMIRATEPERCENTAGE() == null){
					isValid = false;
				}
				
				//6
				//We do not need to validate what the name of the company is, 
				//we just need to verify that the field is NOT NULL.
				if(csvFileBean.getPMIMIPPAYEE() == null){
					isValid = false;
				}	
				
				//7
				if(csvFileBean.getPMIMIPDISBURSEMENTDUEDATE() == null){
					isValid = false;
				}
				
				//8	
				//We do not need to validate what the name of the company is, we just need to verify that the field is NOT NULL.
				if(csvFileBean.getPMIMIPDISBURSEMENTAMOUNT() == null){
					isValid = false;
				}
				
				if(isValid){
					pmiMipTerm = 12;
				}

				//TODO:update table 'CreditData' set 'PMIMIPTERM = 12'
				//CreditDataDB.setPMIPTERM(pmiMipTerm);
				setPmiMipTerm(pmiMipTerm);
			}
		}
		
		return isValid;
	}
	
	/**
	 * Set PMI Monthly Payment Value. 
	 * @param csvFileBean
	 */
	public void setPmiMonthlyPayment(CreditData csvFileBean){		
		BigDecimal pmiMothlyPayment = null;			
		
		try{
		if(csvFileBean.getPMIMIPANNUALPREMIUM() != null){
			pmiMothlyPayment = csvFileBean.getPMIMIPANNUALPREMIUM().divide(new BigDecimal("12"));
			csvFileBean.setPMIMONTHLYPAYMENT(pmiMothlyPayment);
		} else {
			isValid = false;
		}
		}catch(Exception ex){
			//csvFileBean.setPMIMONTHLYPAYMENT(pmiMothlyPayment);
			isValid = false;
		}
		
	}
	
	/**
	 * Set Total Loan Payment Value. 
	 * @param csvFileBean
	 */
	public void setTotalLoanPayment(CreditData csvFileBean, boolean includeMonthlyPayment){	
		
		//Get Clarification
		//Total Monthly Loan Payment --> includes PMI Monthly Payment Amount -- Page 24 PMI Matrix
		//1. What is Total Monthly Loan Amount? I don't see any field in CSV file
		//2. What does it mean by "includes PMI Monthly Payment Amount"?
		BigDecimal totalMonthlyLoanPayment = null;	
		
		try{
		if(csvFileBean.getPMIMIPANNUALPREMIUM() != null){
			csvFileBean.setTOTALPAYMENT(csvFileBean.getPMIMONTHLYPAYMENT());
		}else {
			isValid = false;
		}
		}catch(Exception ex){
			//csvFileBean.setTOTALPAYMENT(totalMonthlyLoanPayment);
			isValid = false;
		}		
	}
	
	/**
	 * Set PMI MIP Percentage of Coverage Value. 
	 * @param csvFileBean
	 */
	public void setPmiMipPercentageOfCoverage(CreditData csvFileBean){	
		
		//Get Clarification
		//PMI MIP PERCENT OF COVERAGE --> % of coverage found in cert -- Page 24 PMI Matrix
		//What is "% of coverage found in cert"? I don't see any field in CSV file		
		BigDecimal pmiMipPercentageOfCoverage = null;	
		
		try{
		if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() != null){
			csvFileBean.setPMIMIPANNUALPREMIUM(csvFileBean.getPMIMIPANNUALPREMIUM());
		} else {
			isValid = false;
		}
		}catch(Exception ex){
			//csvFileBean.setTOTALPAYMENT(pmiMipPercentageOfCoverage);
			isValid = false;
		}		
	}
	
	/**
	 * Set PMI RATE Percentage Value. 
	 * @param csvFileBean
	 */
	public void setPmiRatePercentage(CreditData csvFileBean){	
		
		//Get Clarification
		//PMI RATE PERCENTAGE --> Rate % found in cert -- Page 24 PMI Matrix
		//What is "Rate % found in cert"? I don't see any field in CSV file		
		BigDecimal pmiRatePercentage = null;	
		
		try{
		if(csvFileBean.getPMIMIPPERCENTOFCOVERAGE() != null){
			csvFileBean.setPMIRATEPERCENTAGE(csvFileBean.getPMIRATEPERCENTAGE());
		} else { 
			isValid = false;
		}
		}catch(Exception ex){
			//csvFileBean.setTOTALPAYMENT(pmiRatePercentage);
			isValid = false;
		}		
	}

	/**
	 * Set PMI DISBURSEMENT DATE Value to maturity date + 1 year
	 * @param csvFileBean
	 */
	public void setPmiDisbursementDate(CreditData csvFileBean, String duration){	
		
		Date disbursementDate = null;
				
		try{
			if(csvFileBean.getMATURITYDATE() != null){
				
				Calendar cal = Calendar.getInstance();
		        cal.setTime(csvFileBean.getMATURITYDATE());
		        if(duration != null && duration.equalsIgnoreCase("year")){
		        	cal.add(Calendar.YEAR, 1);
		        } else if(duration != null && duration.equalsIgnoreCase("month")){
		        	cal.add(Calendar.MONTH, 1);
		        }
		        
		        disbursementDate = cal.getTime();		        
		        csvFileBean.setPMIMIPDISBURSEMENTDUEDATE(disbursementDate);
		        
			} else {
				isValid = false;
			}
		}catch(Exception ex){
			//csvFileBean.setPMIMIPDISBURSEMENTDUEDATE(disbursementDate);
			isValid = false;
		}		
	}
		
	/**
	 * Set FHA CODE Value. 
	 * @param csvFileBean
	 */
	public void setFhaCode(CreditData csvFileBean){	
		
		String last3DigitsOfFHACODE = null;
		
		try{			
			String fhaCode = csvFileBean.getFHAADPCODE();
			
			if(fhaCode != null && fhaCode.length() > 3){
				last3DigitsOfFHACODE = csvFileBean.getFHAADPCODE().substring(fhaCode.length()-3, fhaCode.length());
				csvFileBean.setFHAADPCODE(last3DigitsOfFHACODE);
			}   else {
					isValid = false;
			}
		}catch(Exception ex){
			//csvFileBean.setFHAADPCODE(last3DigitsOfFHACODE);
			isValid = false;
		}		
	}
	
	/**
	 * Set FHA SECTION CODE Value in database. 
	 * @param csvFileBean
	 */
	public void setFhaSectionCode(CreditData csvFileBean){	
		
		String last3DigitsOfFHACODE = null;
		
		try{			
			String fhaCaseNumber = csvFileBean.getFHACASENUMBER();
			
			if(fhaCaseNumber != null && fhaCaseNumber.length() > 3){
				last3DigitsOfFHACODE = fhaCaseNumber.substring(fhaCaseNumber.length()-3, fhaCaseNumber.length());
				//INSERT the last 3-digits of the 'FHA CASE NUMBER' to the 'FHASECTIONCODE' in the 'CreditData' table 
				//TODO: Call Database class to insert last3DigitsOfFHACODE in CreditData table.
				
			} else {
				isValid = false;
			}
		}catch(Exception ex){
			//csvFileBean.setFHAADPCODE(last3DigitsOfFHACODE);
			isValid = false;
		}		
	}
	
	/**
	 * Set PMI DISBURSEMENT DATE Value. 
	 * @param csvFileBean
	 */
	public void setPmiDisbursementDueDatetoClosingDate(CreditData csvFileBean, int year){	
		
		Date disbursementDate = null;
				
		try{
			if(csvFileBean.getMATURITYDATE() != null){
				
				Calendar cal = Calendar.getInstance();
		        cal.setTime(csvFileBean.getCLOSINGDATE());
		        //Move to next month of Closing Date
		        cal.add(Calendar.MONTH, 1);
		        //Set Date to first Day of Month
		        cal.set(Calendar.DAY_OF_MONTH, 1);
		        //Add year if required
		        cal.add(Calendar.YEAR, year);
		        disbursementDate = cal.getTime();		        
		        csvFileBean.setPMIMIPDISBURSEMENTDUEDATE(disbursementDate);
		        
			} else {
				isValid = false;
			}
		}catch(Exception ex){
			//csvFileBean.setPMIMIPDISBURSEMENTDUEDATE(disbursementDate);
			isValid = false;
		}		
	}
	
	/**
	 * Set PMI DISBURSEMENT DATE Value. 
	 * @param csvFileBean
	 */
	public void setPmiMipTerm(int pmiMipTerm){	
				
		try{
			CreditDataDAO creditDataDAO = new CreditDataDAO();
			//creditCheckDAO.updatePmiMipTerm(pmiMipTerm);		        
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}

}
