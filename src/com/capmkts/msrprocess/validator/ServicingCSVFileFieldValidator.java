package com.capmkts.msrprocess.validator;

import java.util.Date;
import java.util.List;

import com.capmkts.msrprocess.data.CreditData;

/**
 * CSVFileFieldisValidator - It contains methods which are useful to Validate CSV records. 
 * @author Rama Yerramilli
 *
 */
public class ServicingCSVFileFieldValidator {
	
	 
	
	public boolean validateFields(CreditData csvFileBean, DataValidator dataValidator){
		
		boolean isValid = true;
		int loType = ServiceValidateUtility.getLoType(csvFileBean);
		
		isValid = checkEscrow(csvFileBean);
		
		if(isValid){
			//testing: isValid = validateFieldValues(csvFileBean, dataValidator, loType);
			if(loType == 1 && csvFileBean.getPMIMIPBILLCODE() == 2){
				isValid = validateFieldValues(csvFileBean, dataValidator, loType);				
			}
			if(loType == 6 && csvFileBean.getPMIMIPBILLCODE() == 2){
				isValid = validateFieldValues(csvFileBean, dataValidator, loType);				
			}
			if(loType == 6 && csvFileBean.getPMIMIPBILLCODE() == 3){
				isValid = validateFieldValues(csvFileBean, dataValidator, loType);				
			}			
		}
		
		return isValid;
	}
	
	/**
	 * Check Escrow Value.
	 * @param creditData
	 * @return
	 */
	public static boolean checkEscrow(CreditData creditData){
		boolean isValid = true;
		
		if(creditData.getESCROWBALANCE() != null && 
			creditData.getESCROWBALANCE().intValue() > 0 && 
			creditData.getESCROWMONTHLYPAYMENT() != null && 
			creditData.getESCROWMONTHLYPAYMENT().intValue() > 0) {
				
			isValid = false;
		}
		
		return isValid;
	}
	
	public boolean validateFieldValues(CreditData csvFileBean, DataValidator dataValidator, int loType) {
		boolean isValid = false;
		
		if(csvFileBean.getESCROWBALANCE() == null || 
				csvFileBean.getESCROWBALANCE().intValue() > 0){
			
			//1st condition
			if(csvFileBean.getFLOODINSMONTHLYPAYMENT() == null || 
					csvFileBean.getFLOODINSMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nFLOODINSMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}
			//2nd Condition
			if(csvFileBean.getHAZARDINSMONTHLYPAYMENT() == null || 
					csvFileBean.getHAZARDINSMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nHAZARDINSMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}
			//3rd Condition
			if(csvFileBean.getCOUNTYMONTHLYPAYMENT() == null || 
					csvFileBean.getCOUNTYMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nCOUNTYMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}
			//4th Condition
			if(csvFileBean.getCITYMONTHLYPAYMENT() == null || 
					csvFileBean.getCITYMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nCITYMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}
			//5th Condition
			if(csvFileBean.getLIENMONTHLYPAYMENT() == null || 
					csvFileBean.getLIENMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nLIENMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}
			//6th Condition
			if(csvFileBean.getPMIMONTHLYPAYMENT() == null || 
					csvFileBean.getPMIMONTHLYPAYMENT().intValue() <= 0) {
				dataValidator.addMessage(" \nPMIMONTHLYPAYMENT value is NULL or empty ");
				isValid = false;
			}		
		}
		//7th Condition
		if(csvFileBean.getESCROWBALANCE().intValue() == 0){			
			if(csvFileBean.getESCROWCUSHION() == null || csvFileBean.getESCROWCUSHION().intValue() != 0) {
				dataValidator.addMessage(" \nESCROWCUSHION value is > 0 ");
				isValid = false;
			}
		}
		//8th Condition
		if(csvFileBean.getNUMBEROFUNITS() <= 0){
			dataValidator.addMessage(" \nNUMBEROFUNITS value is NULL or empty ");
			isValid = false;
		}
		//9-12 Conditions		
		if(loType != 1 ){
			if(csvFileBean.getFHACASENUMBER() != null){
				dataValidator.addMessage("\nLOTYPE=1 FHACASENUMBER value is NULL or empty ");
				isValid = false;
			}
			if(csvFileBean.getFHAADPCODE() != null){
				dataValidator.addMessage("\nLOTYPE=1 FHAADPCODE value is NULL or empty ");
				isValid = false;
			}			
			
		} else if(loType != 2 ){
			if(csvFileBean.getVANUMBER() != null){
				dataValidator.addMessage("\nLOTYPE=2 VANUMBER value is NULL or empty ");
				isValid = false;
			}
			if(csvFileBean.getVAFUNDINGMIPUPFRONT() != null){
				dataValidator.addMessage("\nLOTYPE=2 VAFUNDINGMIPUPFRONT value is NULL or empty ");
				isValid = false;
			}				
		}
		//13th Condition	
		if(csvFileBean.getFLOODCOMMUNITYNUMBER() == null){
			dataValidator.addMessage(" \nFLOODCOMMUNITYNUMBER value is NULL or empty ");
			isValid = false;
		}
		
		return isValid;
	}
	

}
