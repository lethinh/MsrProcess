package com.capmkts.msrprocess.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.capmkts.encompassservice.Client.ExportDocumentsToEncompass;
import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.dao.PricingGridDAO;
import com.capmkts.msrprocess.data.ServRateSheets;
import com.capmkts.msrprocess.validator.DataValidator;

public class ServiceFactory {

	public static DataValidator processFiles(String requestType, File file) throws Exception{

		DataValidator dataValidator = new DataValidator();
		
		dataValidator.setValid(true);
		
		String fileName = file.getName();
		
		System.out.println(" request type: " +requestType);
		System.out.println(" fileName: "+fileName);
		
//		List<ServRateSheets> temp;
//		PricingGridDAO price = new PricingGridDAO();
//		System.out.println("\nGetting Pricing");
//		temp = price.getPricingGrid(30);
//		System.out.println("\nGot Pricing!!!");
//		for (int i=0; i<temp.size(); i++){
//			System.out.println(temp.get(i).toString());
//		}
		
		if (requestType.equalsIgnoreCase(MsrConstants.AGENCY_COMMITMENT_LETTER)) {
			// Agency COMMITMENT LETTER
			if (fileName.endsWith(".csv") || fileName.endsWith(".xls")) { 
				CommitmentLetterCSVFileService commitmentLetterCSVFileService = new CommitmentLetterCSVFileService();
				commitmentLetterCSVFileService.process(file, dataValidator);}
			else if (fileName.endsWith(".xlsx")){
				dataValidator.addMessage("XLSX files are not currently supported. Please upload an XLS file.");
			}
			else{
				dataValidator.addMessage("Invalid Agency Commitment Letter File Extension!");
			}
		} else if (requestType.equalsIgnoreCase(MsrConstants.COMMIT_REQUEST)){
			// COMMITMENT REQUEST 
			if (fileName.endsWith(".xls")) {
				CommitmentDataXLSFileService commitmentDataXLSFileService = new CommitmentDataXLSFileService();
				commitmentDataXLSFileService.process(file,dataValidator);
			}
			else if (fileName.endsWith(".xlsx")){
				dataValidator.addMessage("XLSX files are not currently supported. Please upload an XLS file.");
			}
			else{
				dataValidator.addMessage("Invalid Agency Commitment Reuqest File(s)!");
			}
		// SERVICING DATA
		} else if (requestType.equalsIgnoreCase(MsrConstants.SERVICING_DATA)) { 
			// .csv
				if (fileName.endsWith(".csv")) { 
					ServicingDataCSVFileService servicingDataCSVFileService = new ServicingDataCSVFileService();
					servicingDataCSVFileService.process(file, dataValidator);
				}else if (fileName.endsWith(".dat")) { 
					ServicingDataDATFileService servicingDataDATFileService = new ServicingDataDATFileService();
					servicingDataDATFileService.process(file, dataValidator);
				}
				else{
					dataValidator.addMessage("Invalid file type. Your request cannot be completed!");
				}
				
				if (dataValidator.getMessageList().size() > 7){
					int errors = dataValidator.getMessageList().size();
					dataValidator.setMessageList(null);
					dataValidator.addMessage(errors + " errors occurred. Please check log file for details");
				}
		} else if (requestType.equalsIgnoreCase(MsrConstants.LOAN_DOCUMENT_FILE)) { // LOAN DOCS PDF
			if (fileName.endsWith(".pdf")) { 
				String sellerLoanNumber = "";
				String tempLoanNumber = "";
				int agencyCommitmentID;
//				if (fileName.contains("_")){
//					tempLoanNumber = (fileName.split("_")[1]);
//					sellerLoanNumber = tempLoanNumber.split("\\.")[0];
//				}
//				else if (fileName.contains("-")){
//					tempLoanNumber = (fileName.split("-")[1]);
//					sellerLoanNumber = tempLoanNumber.split("\\.")[0];
//				}
//				else {
//					sellerLoanNumber = fileName.split("\\.")[0];
//				}
				
				sellerLoanNumber = fileName.split("\\.")[0];
		
				System.out.println("\n\nSELLER LOAN NUMBER: " +sellerLoanNumber);
				
				CommitmentDataDAO commitmentDataDAO = new CommitmentDataDAO();
				System.out.println("\n\nAgency Commitment ID: " + commitmentDataDAO.getAgencyCommitmentID(sellerLoanNumber)+"\n\n");
				String tempAgencyCommitmentID = commitmentDataDAO.getAgencyCommitmentID(sellerLoanNumber);
				
				CreditDataDAO creditDataDAO = new CreditDataDAO();
				String GUID = creditDataDAO.getGUID(sellerLoanNumber);
				
				if (tempAgencyCommitmentID != null){
					agencyCommitmentID = Integer.parseInt(tempAgencyCommitmentID);
				
	//				System.out.println("\n\nAgency Commitment ID: " + agencyCommitmentID+ "\n\n");
					CMCFileDAO cmcFileDAO = new CMCFileDAO();
					cmcFileDAO.saveFile(file, MsrConstants.LOAN_DOCUMENT_FILE, true, "", null, agencyCommitmentID);
					
					String fileLoc = MsrConstants.UPLOADED_LOAN_DOCS+file.getName();
					
					FileOutputStream fout = new FileOutputStream(fileLoc);
					OutputStream outputStream = new FileOutputStream (fileLoc); 
	
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					InputStream is = new FileInputStream(file);
					byte[] temp = new byte[1024];
					int read;
	
					while((read = is.read(temp)) >= 0){
					   buffer.write(temp, 0, read);
					}
					buffer.writeTo(outputStream);
					outputStream.close();
					is.close();
					fout.close();
					buffer.close();
					
					//SEND TO ENCOMPASS
					ExportDocumentsToEncompass exportDocumentsToEncompass = new ExportDocumentsToEncompass();
					boolean sentSuccess = false;
					try{
						sentSuccess = exportDocumentsToEncompass.exportDocumentsToEncompass(GUID, fileLoc);
					}catch (Exception e) {
						System.out.println(e);
					}
					
					//REMOVE FILE IF SENT SUCCESSFULLY
					if (sentSuccess){
						File tempFile = new File(fileLoc);
						tempFile.delete();
					}
					
					dataValidator.addMessage("Your file(s) has been uploaded successfully.");
				}
				else{
					dataValidator.addMessage("Upload failed. No reference for loan docs found.");
				}
			} 
			else{
				dataValidator.addMessage("CMC only accepts Loan Documents in PDF format.");
			}
		} 
		
		// Code to save files to Database -- File Type, File, Messages, UploadedDate
		
		return dataValidator;
		
	}

}
