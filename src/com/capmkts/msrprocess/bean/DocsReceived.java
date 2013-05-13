package com.capmkts.msrprocess.bean;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.LinkedHashSet;
import java.util.List;  
import java.util.UUID;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.capmkts.msrprocess.dao.AgencyCommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CMCAgencyCommitNumberDAO;
import com.capmkts.msrprocess.dao.CMCFileDAO;

@ManagedBean
@ViewScoped
public class DocsReceived implements Serializable {    

    public List<Docs> docs;  
  
    public DocsReceived() {  
        docs = new ArrayList<Docs>();  
  
        populateDocs(docs,9);  
    }  
  
    private void populateDocs(List<Docs> list, int size) {  
    	CMCAgencyCommitNumberDAO cmcAgencyCommitNumberDAO = new CMCAgencyCommitNumberDAO();
    	CMCFileDAO cmcFileDAO = new CMCFileDAO();
    	
    	String receivedStatus, cmcCommitmentNumber, agencyCommitmentNumber, agencyCommitmentLetterFileName,
    			commitmentRequestFileName, servicingDataFileName, paFileName;
    	
    	List commitmentRequestStatusList, agencyCommitmentLetterStatusList;
    	List tempCMCCommitNumbers = cmcAgencyCommitNumberDAO.getCMCCommitNumbers();
    	List agencyCommitNumbers = cmcAgencyCommitNumberDAO.getAgencyCommitNumbers();
    	
    	List<String> cmcCommitNumbers = new ArrayList<String>(new LinkedHashSet<String>(tempCMCCommitNumbers));
    	
    	for (int i=0; i<cmcCommitNumbers.size(); i++){
    		cmcCommitmentNumber = String.valueOf(cmcCommitNumbers.get(i));
//    		cmcCommitmentNumber = "239812";
    		agencyCommitmentNumber = String.valueOf(agencyCommitNumbers.get(i));
    		System.out.println("\n\nCommit Number: " +cmcCommitmentNumber);
    		System.out.println("\n\nAgency Number: " +agencyCommitmentNumber);
    		
    		//Handle null commitment numbers
    		if (!cmcCommitmentNumber.equals("null")){
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcCommitmentNumber, "Commitment Letter");
    			System.out.println("\n\nCommit Letter Filename: " +agencyCommitmentLetterFileName);
    			commitmentRequestFileName = cmcFileDAO.getFileName(cmcCommitmentNumber, "Commitment Request");
    			System.out.println("\n\ncommitmentRequestFileName: " +commitmentRequestFileName);
    			servicingDataFileName = cmcFileDAO.getFileName(cmcCommitmentNumber, "Servicing Data");
    			System.out.println("\n\nservicingDataFileName: " +servicingDataFileName);
    			paFileName = cmcFileDAO.getFileName(cmcCommitmentNumber, "Purchase Advice");
    			System.out.println("\n\npaFileName: " +paFileName);
    		}
    		else if (!agencyCommitmentNumber.equals("null")){
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Commitment Letter");
    			commitmentRequestFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Commitment Request");
    			servicingDataFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Servicing Data");
    			paFileName = cmcFileDAO.getFileName(agencyCommitmentNumber, "Purchase Advice");
    		}
    		else{
    			agencyCommitmentLetterFileName = "Not Received";
    			commitmentRequestFileName = "Not Received";
    			servicingDataFileName = "Not Received";
    			paFileName = "Not Received";
    		}
    		
    		commitmentRequestStatusList = cmcFileDAO.getCommitmentRequestList(cmcCommitmentNumber);
    		agencyCommitmentLetterStatusList = cmcFileDAO.getAgencyCommitmentLetterStatus(agencyCommitmentNumber);
    		list.add(new Docs(
				cmcCommitmentNumber, 
				getStatus(agencyCommitmentLetterStatusList, "commitment letter"),
				getStatus(commitmentRequestStatusList, "commitment request"),
				getStatus(commitmentRequestStatusList, "servicing data"),
				getStatus(commitmentRequestStatusList, "purchase advice"),
				agencyCommitmentLetterFileName,
				commitmentRequestFileName,
				servicingDataFileName,
				paFileName
    		));
    	}
    }  
  
    public List<Docs> getDocs() {  
        return docs;  
    }  
    
    public String getStatus(List items, String statusType){
    	String status = "Not Received";
    	if (statusType.equalsIgnoreCase("commitment letter")){
	    	for (int i=0; i<items.size(); i++){
		    	if (String.valueOf(items.get(i)).equals("Commitment Letter")){
					status = "Received";
					break;
				}
				else {
					status = "Not Received";
				}
	    	}
    	}
    	else if (statusType.equalsIgnoreCase("commitment request")){
	    	for (int i=0; i<items.size(); i++){
		    	if (String.valueOf(items.get(i)).equals("Commitment Request")){
					status = "Received";
					break;
				}
				else {
					status = "Not Received";
				}
	    	}
    	}
    	else if (statusType.equalsIgnoreCase("servicing data")){
	    	for (int i=0; i<items.size(); i++){
		    	if (String.valueOf(items.get(i)).equals("Servicing Data")){
					status = "Received";
					break;
				}
				else {
					status = "Not Received";
				}
	    	}
    	}
    	else if (statusType.equalsIgnoreCase("purchase advice")){
	    	for (int i=0; i<items.size(); i++){
		    	if (String.valueOf(items.get(i)).equals("Purchase Advice")){
					status = "Received";
					break;
				}
				else {
					status = "Not Received";
				}
	    	}
    	}
    	else{
    		status = "Unknown";
    	}
	    return status;
    }
} 