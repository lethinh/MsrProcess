package com.capmkts.msrprocess.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.capmkts.msrprocess.dao.AgencyCommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
//import com.capmkts.msrprocess.dao.AgencyCommitDetailsDAO;
import com.capmkts.msrprocess.dao.CMCAgencyCommitNumberDAO;
import com.capmkts.msrprocess.util.DateUtil;

@ManagedBean
@ViewScoped
public class PortfolioCommitted implements Serializable {    
    public List<Portfolio> portfolio;  
  
    public PortfolioCommitted() throws Exception {  
    	portfolio = new ArrayList<Portfolio>();  
    	
//    	System.out.println(cmcPortfolio.getCMCCommitmentNumber() + " : " + cmcPortfolio.getCurrentBalance() + "\n\n");
    	populatePortfolio(portfolio,5);  
    }  
  
    private void populatePortfolio(List<Portfolio> list, int size) throws Exception {  
    	NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
    	
    	CMCFileDAO cmcFileDAO = new CMCFileDAO();
    	CMCAgencyCommitNumberDAO cmcAgencyCommitNumberDAO = new CMCAgencyCommitNumberDAO();
    	AgencyCommitmentLetterDAO agencyCommitmentLetterDAO = new AgencyCommitmentLetterDAO();
    	CommitmentDataDAO commitmentDataDAO = new CommitmentDataDAO();
    	
    	DocsReceived docsReceived = new DocsReceived();
    	List commitmentRequestStatusList;
    	List tempCMCCommitNumbers = cmcAgencyCommitNumberDAO.getCMCCommitNumbersOpenStatus();
    	List agencyCommitNumbers = cmcAgencyCommitNumberDAO.getAgencyCommitNumbers();
    	
    	List<String> cmcCommitNumbers = new ArrayList<String>(new LinkedHashSet<String>(tempCMCCommitNumbers));
    	
    	List origCommitmentAmtAll;
    	List agencyCommitmentIDAll;
    	String agencyCommitmentID;
    	String cmcNumber, agencyCommitmentNumber, commitStatus, commitDate, expirationDate;
    	String origCommitmentAmt, balance, servicingDataReceivedStatus, purchaseAdviceReceivedStatus;
    	String CMCCommitmentLetterFileName, agencyCommitmentLetterFileName, commitmentRequestFileName, servicingDataFileName, paFileName;
    	
    	BigDecimal bal = new BigDecimal(0.00);
    	BigDecimal bal2 = new BigDecimal(0.00);
    	
    	for (int i=0; i<cmcCommitNumbers.size(); i++){
    		bal = new BigDecimal(0.00);
        	bal2 = new BigDecimal(0.00);
        	
    		cmcNumber = String.valueOf(cmcCommitNumbers.get(i));
    		agencyCommitmentNumber = String.valueOf(agencyCommitNumbers.get(i));
    		agencyCommitmentID = cmcAgencyCommitNumberDAO.getAgencyCommitNumber(cmcNumber);
    		agencyCommitmentIDAll = cmcAgencyCommitNumberDAO.getAgencyCommitNumberAll(cmcNumber);
    		commitStatus = cmcAgencyCommitNumberDAO.getCMCCommitStatus(cmcNumber);
    		commitDate = agencyCommitmentLetterDAO.getCommitmentDate(agencyCommitmentID);
    		expirationDate = agencyCommitmentLetterDAO.getExpirationDate(agencyCommitmentID);
    		origCommitmentAmtAll = agencyCommitmentLetterDAO.getOrigCommitmentAmtAll(agencyCommitmentID);
    		
//    		for (int k=0; k<origCommitmentAmtAll.size(); k++){
//    			bal2 = bal2.add(new BigDecimal(origCommitmentAmtAll.get(k).toString()));
//    		}
//    		origCommitmentAmt = bal2.toString();
    		for (int j=0; j<agencyCommitmentIDAll.size(); j++){
    			bal2 = bal2.add(new BigDecimal(agencyCommitmentLetterDAO.getOrigCommitmentAmtAll(agencyCommitmentIDAll.get(j).toString()).get(0).toString()));
    			bal = bal.add(commitmentDataDAO.getTotalLoanAmount(agencyCommitmentIDAll.get(j).toString()));
    			System.out.println("Balance: " +bal.longValue());
    		}
    		balance = String.valueOf(bal);
    		origCommitmentAmt = bal2.toString();
    		//Handle null commitment numbers
    		if (!cmcNumber.equals("null")){
    			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "CMC Commitment Letter");
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Letter");
    			commitmentRequestFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Request");
    			servicingDataFileName = cmcFileDAO.getFileName(cmcNumber, "Servicing Data");
    			paFileName = cmcFileDAO.getFileName(cmcNumber, "Purchase Advice");
    		}
    		else if (!agencyCommitmentNumber.equals("null")){
    			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "CMC Commitment Letter");
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Commitment Letter");
    			commitmentRequestFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Commitment Request");
    			servicingDataFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Servicing Data");
    			paFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Purchase Advice");
    		}
    		else{
    			CMCCommitmentLetterFileName = "Not Available";
    			agencyCommitmentLetterFileName = "Not Received";
    			commitmentRequestFileName = "Not Received";
    			servicingDataFileName = "Not Received";
    			paFileName = "Not Received";
    		}
    		
    		int extendCount = agencyCommitmentLetterDAO.getExtendCount(agencyCommitmentID);
    		
            DateFormat formatter = null;
            Date commitDateObj = null;
            Date expirationDateObj = null;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
//            	System.out.println("\nBEFORE COMMIT DATE: " + commitDate);
            	if (commitDate != null){
            		commitDateObj = (Date) formatter.parse(commitDate);
            	}
				System.out.println("\nAfter Commit Date: " + commitDateObj);
				if (expirationDate != null){
					expirationDateObj = (Date) formatter.parse(expirationDate);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
            
//            Date dateToday = new Date();
            
            //Testing Purposes
            Calendar cal = Calendar.getInstance();
//            cal.set(2012, 9, 9);
            cal.add(Calendar.DATE, -1);
            Date d = cal.getTime();
            
            String checkExpiration = checkExpiration(commitDateObj, expirationDateObj,d);
            System.out.println("\nToday's Date: " + d);
    		System.out.println("\n\nDays In Between: " +checkExpiration+ "\n\n");
    		if (checkExpiration.equals("0")){
    			checkExpiration = "<1";
    		}
    		//Handle null values
    		System.out.println("\nAGENCY COMMITMENT ID: " +agencyCommitmentID+ "\n\n");
    		System.out.println("\nORIG COMMIT AMT: " + origCommitmentAmt);
    		if (origCommitmentAmt.equals("null"))
    			origCommitmentAmt = "0.00";
    		if (balance.equals("null"))
    			balance = "0.00";
    		
    		//Check to see if servicing files received
    		commitmentRequestStatusList = cmcFileDAO.getCommitmentRequestList(cmcNumber);
    		servicingDataReceivedStatus = docsReceived.getStatus(commitmentRequestStatusList, "servicing data");
    		purchaseAdviceReceivedStatus = docsReceived.getStatus(commitmentRequestStatusList, "purchase advice");
    		if (servicingDataReceivedStatus.equals("Received") && purchaseAdviceReceivedStatus.equals("Received")){
    			//Check requested amount vs agency committed amount to make sure they match
    			if (!origCommitmentAmt.equals(balance)){
    				balance = String.valueOf(Double.valueOf(origCommitmentAmt) - Double.valueOf(balance));
    			}
    			else{
    				balance = "0.00";
    			}
    		}
    		
    		list.add(new Portfolio(
    				cmcNumber, 
    				agencyCommitmentNumber,
    				commitDate, 
    				expirationDate, 
    				defaultFormat.format(Double.parseDouble(origCommitmentAmt)),
    				defaultFormat.format(Double.parseDouble(balance)),
    				checkExpiration,
    				commitStatus, 
    				extendCount,
    				CMCCommitmentLetterFileName,
    				agencyCommitmentLetterFileName,
    				commitmentRequestFileName,
    				servicingDataFileName,
    				paFileName)
    		);
    	}
    }   
    
    public List<Portfolio> getPortfolio() {  
        return portfolio;  
    } 
    
    public String checkExpiration(Date commitDateObj, Date expirationDateObj, Date todayDate) throws Exception{
    	String expiration = "Expired";
    	long daysBetween;
    	
    	if (expirationDateObj != null){
	    	if (expirationDateObj.before(todayDate)){
	    		expiration = "Expired";
	    	}
	    	else{
	    		daysBetween = DateUtil.getWorkingDaysBetweenTwoDates(expirationDateObj, todayDate);
	    		expiration = String.valueOf(daysBetween);
	    	}
    	}
    	
    	return expiration;
    }
} 