package com.capmkts.msrprocess.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.util.DateUtil;

public class FNMACommitLetterTest {
	public static void process() throws Exception{
		
		AgencyCommitmentLetter agencyCommitmentLetter = new AgencyCommitmentLetter();
		
//		File file = new File("c:\\testFNMACommitLetter.csv");
		File file = new File("C:\\Users\\tle\\Dropbox\\Capmkts\\TestingData\\FNMA Commit Letter Test Data\\Testing\\Testing\\659\\392661.csv");
		String fileContent = FileUtils.readFileToString(file);
		String[] contentArray = fileContent.split("\\n");
		
		
		for (int i = 0; i < contentArray.length; i++) {
			String[] valueArray = contentArray[i].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			for (int j = 0; j < valueArray.length; j++) {
				System.out.println(valueArray[j].replaceAll("\"", ""));
				
				//Lender #
				if (valueArray[j].contains("Lender #")){
					agencyCommitmentLetter.setLenderNum(Integer.parseInt(valueArray[j+1].replaceAll("-", "").replaceAll("\"", "")));
//					System.out.println(agencyCommitmentLetter.getLenderNum());
				}
				
				//Lender Name
				else if (valueArray[j].contains("Lender Name")){
					agencyCommitmentLetter.setLenderName(valueArray[j+1].replaceAll("\"", ""));
//					System.out.println(agencyCommitmentLetter.getLenderName());
				}
				
				//Fannie Mae's Commitment ID
				else if (valueArray[j].contains("Fannie Mae's Commitment ID")){
					agencyCommitmentLetter.setAgencyCommitmentID(Integer.parseInt(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println(agencyCommitmentLetter.getAgencyCommitmentID());
				}
				
				//Lender's Commitment ID
				else if (valueArray[j].contains("Lender's Commitment ID")){
					if (isInteger(valueArray[j+1])){
						agencyCommitmentLetter.setLenderCommitmentID(Integer.parseInt(valueArray[j+1].replaceAll("\"", "")));
					}
//					System.out.println(agencyCommitmentLetter.getLenderCommitmentID());
				}
				
				//Source
				else if (valueArray[j].contains("Source")){
					agencyCommitmentLetter.setSource(valueArray[j+1].replaceAll("\"", ""));
//					System.out.println(agencyCommitmentLetter.getSource());
				}
				
				//Commitment Date
				else if (valueArray[j].contains("Commitment Date")){
					agencyCommitmentLetter.setCommitmentDate(getDateValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println(agencyCommitmentLetter.getCommitmentDate());
				}
				
				//Contact
				else if (valueArray[j].contains("Contact")){
					agencyCommitmentLetter.setContact(valueArray[j+1].replaceAll("\"", ""));
//					System.out.println(agencyCommitmentLetter.getContact());
				}
				
				//Expiration Date
				else if (valueArray[j].contains("Expiration Date")){
					agencyCommitmentLetter.setExpirationDate(getDateValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println(agencyCommitmentLetter.getExpirationDate());
				}
				
				//Original Commitment Amount
				else if (valueArray[j].contains("Original Commitment Amount")){
					agencyCommitmentLetter.setOrigCommitmentAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println(agencyCommitmentLetter.getOrigCommitmentAmt());
				}
				
				//Low Tolerance Amount
				else if (valueArray[j].contains("Low Tolerance")){
					agencyCommitmentLetter.setLowToleranceAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n" +agencyCommitmentLetter.getLowToleranceAmt());
				}
				
				//Current Commitment Amount
				else if (valueArray[j].contains("Current Commitment Amount")){
					agencyCommitmentLetter.setCurrentCommitmentAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getCurrentCommitmentAmt());
				}
				
				//High Tolerance Amount
				else if (valueArray[j].contains("High Tolerance Amount")){
					agencyCommitmentLetter.setHighToleranceAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getHighToleranceAmt());
				}
				
				//Purchased Amount
				else if (valueArray[j].contains("Purchased Amount")){
					if (!valueArray[j].contains("HBL")){
						agencyCommitmentLetter.setPurchasedAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//						System.out.println("\n\n>" +agencyCommitmentLetter.getPurchasedAmt());
					}
					
					//HBL Purchased Amount
					else{
//						System.out.println("\n\n>" +valueArray[j+1].replaceAll("\\$", ""));
						agencyCommitmentLetter.setHBLPurchasedAmt(getBigDecimalValue(valueArray[j+1].replaceAll("\"", "").replaceAll("\\$", "")));
//						System.out.println("\n\n>" +agencyCommitmentLetter.getHBLPurchasedAmt());
					}
				}
				
				//Maximum over-delivery amount
				else if (valueArray[j].contains("Maximum Over")){
					agencyCommitmentLetter.setMaxOverDeliveryAmt(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getMaxOverDeliveryAmt());
				}
				
				//Pending Purchase
				else if (valueArray[j].contains("Pending Purchase")){
					agencyCommitmentLetter.setPendingPurchase(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPendingPurchase());
				}
				
				//HBL Cap%
				else if (valueArray[j].contains("HBL Cap")){
					agencyCommitmentLetter.setHBLCap(getStringValue(valueArray[j+1].replaceAll("\"","")));
//					System.out.println(agencyCommitmentLetter.getHBLCap());
				}
				
				//Balance
				else if (valueArray[j].contains("Balance")){
					agencyCommitmentLetter.setBalance(getBigDecimalValue(valueArray[j+1].replaceAll("[^\\d.]", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getBalance());
				}
				
				//Maximum Allowable HBL Delivery
				else if (valueArray[j].contains("Maximum Allowable HBL")){
					agencyCommitmentLetter.setMaxAllowableHBLDelivery(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getMaxAllowableHBLDelivery());
				}
				
				//Pass-Through Rate - Price - Netyield
				else if (valueArray[j].contains("Pass-Through Rate")){
					i += 1;
					String[] tempValueArrayHigh = contentArray[i].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					for (int k=0; k<tempValueArrayHigh.length; k++){
//						System.out.println(tempValueArrayHigh[k].replaceAll("\"", ""));
						switch(k){
						case 0:
							agencyCommitmentLetter.setPassThroughRateHigh(getDoubleValue(tempValueArrayHigh[k].replaceAll("\"", "")));
							break;
						case 1:
							agencyCommitmentLetter.setPriceHigh(getDoubleValue(tempValueArrayHigh[k].replaceAll("\"", "")));
							break;
						case 2:
							agencyCommitmentLetter.setNetYieldHigh(getDoubleValue(tempValueArrayHigh[k].replaceAll("\"", "")));
							break;
						}
					}
					
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPassThroughRateHigh());
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPriceHigh());
//					System.out.println("\n\n>" +agencyCommitmentLetter.getNetYieldHigh());
					
					i += 4;
					String[] tempValueArray = contentArray[i].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					for (int k=0; k<tempValueArray.length; k++){
//						System.out.println(tempValueArray[k].replaceAll("\"", ""));
						switch(k){
						case 0:
							agencyCommitmentLetter.setPassThroughRateLow(getDoubleValue(tempValueArray[k].replaceAll("\"", "")));
							break;
						case 1:
							agencyCommitmentLetter.setPriceLow(getDoubleValue(tempValueArray[k].replaceAll("\"", "")));
							break;
						case 2:
							agencyCommitmentLetter.setNetYieldLow(getDoubleValue(tempValueArray[k].replaceAll("\"", "")));
							break;
						}
					}
					
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPassThroughRateLow());
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPriceLow());
//					System.out.println("\n\n>" +agencyCommitmentLetter.getNetYieldLow());
				}
				
				//Master Agreement ID
				else if (valueArray[j].contains("Master Agreement ID")){
					agencyCommitmentLetter.setMasterAgreementID(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getMasterAgreementID());
				}
				
				//Commitment Period
				else if (valueArray[j].contains("Commitment Period")){
					agencyCommitmentLetter.setCommitmentPeriod(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getCommitmentPeriod());
				}
				
				//Base Servicing Fee
				else if (valueArray[j].contains("Base Servicing Fee")){
					agencyCommitmentLetter.setBaseServicingFee(getDoubleValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getBaseServicingFee());
				}
				
				//Pricing Option
				else if (valueArray[j].contains("Pricing Option")){
					agencyCommitmentLetter.setPricingOption(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getPricingOption());
				}
				
				//LPMI
				else if (valueArray[j].contains("LPMI")){
					agencyCommitmentLetter.setLPMI(getDoubleValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getLPMI());
				}
				
				//Minimum Pass-Through Rate
				else if (valueArray[j].contains("Minimum Pass")){
					agencyCommitmentLetter.setMinPassThroughRate(getDoubleValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getMinPassThroughRate());
				}
				
				//Total Servicing Fee
				else if (valueArray[j].contains("Total Servicing Fee")){
					agencyCommitmentLetter.setTotalServicingFee(getDoubleValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getTotalServicingFee());
				}
				
				//Required Net Yield
				else if (valueArray[j].contains("Required Net Yield")){
					agencyCommitmentLetter.setRequiredNetYield(getDoubleValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getRequiredNetYield());
				}
				
				//LLPA Application
				else if (valueArray[j].contains("LLPA Applicable")){
					agencyCommitmentLetter.setLLPAApplicable(getBooleanValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getLLPAApplicable());
				}
				
				//Product Name
				else if (valueArray[j].contains("Product Name")){
					agencyCommitmentLetter.setProductName(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getProductName());
				}
				
				//Product Favorite Name
				else if (valueArray[j].contains("Product Favorite Name")){
					agencyCommitmentLetter.setProductFavoriteName(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getProductFavoriteName());
				}
				
				//Amortization Type
				else if (valueArray[j].contains("Amortization Type")){
					agencyCommitmentLetter.setAmortizationType(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getAmortizationType());
				}
				
				//Amortization Term
				else if (valueArray[j].contains("Amortization Term")){
					agencyCommitmentLetter.setAmortizationTerm(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getAmortizationTerm());
				}
				
				//Loan Type
				else if (valueArray[j].contains("Loan Type")){
					agencyCommitmentLetter.setLoanType(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getLoanType());
				}
				
				//Loan Term
				else if (valueArray[j].contains("Loan Term")){
					agencyCommitmentLetter.setLoanTerm(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getLoanTerm());
				}
				
				//Lien Type
				else if (valueArray[j].contains("Lien Type")){
					agencyCommitmentLetter.setLienType(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getLienType());
				}
				
				//Remittance Type
				else if (valueArray[j].contains("Remittance Type")){
					agencyCommitmentLetter.setRemittanceType(getStringValue(valueArray[j+1].replaceAll("\"", "")));
//					System.out.println("\n\n>" +agencyCommitmentLetter.getRemittanceType());
				}
				
				//Target Fund Date
				
//				if (agencyCommitmentLetter.getExpirationDate() != null){
//					System.out.println("\n\nExpiration Date: " + agencyCommitmentLetter.getExpirationDate());
//					if (agencyCommitmentLetter.getTargetFundDate() == null){
//						System.out.println("\nTarget Fund Date: " +agencyCommitmentLetter.getTargetFundDate());
//						agencyCommitmentLetter.setTargetFundDate(getTargetFundDate(agencyCommitmentLetter.getExpirationDate()));
//						System.out.println("\n\nTarget Fund Date: " +agencyCommitmentLetter.getTargetFundDate()+ "\n\n");
//					}
//				}
			}
		}
		
	}
	
	//TargetFundDt = CMC Commitment Expiration Date + 7 Days excluding holidays
		// CMC Commitment Expiration Date == Agency Commitment Expiration Date
		private static Date getTargetFundDate(Date commitmentExprDate){
			Date tempDate = null;
			if (!(commitmentExprDate == null)){
				tempDate = DateUtil.addDays(commitmentExprDate, 8); // 7 calendar days
			}
			return tempDate;
		}

		 //DataDeliveryDate = CMC Commitment  Date + 3 Days excluding holidays
		private Date getDataDeliveryDate(Date commitmentDate){
			Date tempDate = null;
			if (!(commitmentDate == null)){
				tempDate = DateUtil.addDays(commitmentDate, 4); // 3 calendar days
			}
			return tempDate;
		}
	
	private static Boolean getBooleanValue(String value) {
    	Boolean tempVal = null;
    	if (!(value == null)){
	        if (value.equalsIgnoreCase("yes")) {
	            value = "true";
	        } else {
	            value = "false";
	        }
	        tempVal = Boolean.parseBoolean(value);
    	}
        return tempVal;
    }
	
	private static Double getDoubleValue(String value) {

//		System.out.println(value.isEmpty());
    	Double tempVal = null;
		if (value.equalsIgnoreCase("none")){
			value = "";
		}
		if (!(value.isEmpty())){
			try {
				tempVal = Double.valueOf(value);
				System.out.println(tempVal);
			} catch (NumberFormatException nfe) {
//				System.out.println("Value: " +value);
//				tempVal = null;
			}
		}
        return tempVal;
    }
	
	private static String getStringValue(String value) {
		if (value == null){
			value = "";
		}
		else if(value.equalsIgnoreCase("none")){
			value = "";
		}
		else if (value.equalsIgnoreCase("null")) {
            value = "";
        }
		else if (value.equalsIgnoreCase("n/a")) {
            value = "";
        }
		else if (value.equalsIgnoreCase("na")){
        	value = "";
        }
		else if (value == ""){
			value = "";
		}
        return value;
    }
	
	private static BigDecimal getBigDecimalValue(String value) {
    	BigDecimal tempBD = null;
    	if (!(value == null)){
	        if (value.equalsIgnoreCase("n/a") || value.equalsIgnoreCase("na")){
	        	tempBD =  new BigDecimal("0.00");
	        }
	        else{
	        	tempBD = new BigDecimal(value);
	        }
    	}
    	return tempBD;
    }
	
	private static Date getDateValue(String value) throws Exception {
    	Date tempDate = null;
    	if (!(value == null)){
	        String newFormat = "MM/dd/yyyy";
	
	        SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
	
	        tempDate = sdf.parse(value);
    	}
        return tempDate;
    }
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public static void main(String[] args) throws Exception {
		process();

	}

}
