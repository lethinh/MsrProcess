package com.capmkts.bulkupload.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.apache.commons.io.IOUtils;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.dao.PricingDAO;

public class BulkUploadServiceClientJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		System.out.println("CSV BulkUpload Process Started!" + new Date());
		try {
			List<String> fileNames = getCSVFilenames();
			for (String fileName : fileNames) {
				System.out.println("FILENAME: " +fileName);
				boolean isUpload = uploadCSVToBulkUploadService(fileName);
				// if csv file is uploaded successfully, rename the file to
				// avoid re-upload.
				if (isUpload) {
					File renameFile = new File(fileName);
					String fileNameOnly = renameFile.getName()
							+ "BULKUPLOADCOMPLETE";

					String absolutePath = renameFile.getAbsolutePath();
					String filePath = absolutePath.substring(0,
							absolutePath.lastIndexOf(File.separator));

					boolean isFileNameChanged = renameFile.renameTo(new File(MsrConstants.CSV_DIRECTORY + "\\" + fileNameOnly));
					
					System.out.println("CSV BulkUpload Process Ended!" + isFileNameChanged);
				}
			}
			System.out.println("CSV BulkUpload Process Ended!" + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void schedulerJobTest() {
		System.out.println("CSV BulkUpload Process Started!" + new Date());
		try {
			List<String> fileNames = getCSVFilenames();
			for (String fileName : fileNames) {
				System.out.println("FILENAME: " + fileName);
				boolean isUpload = uploadCSVToBulkUploadService(fileName);
				// if csv file is uploaded successfully, rename the file to
				// avoid re-upload.
				if (isUpload) {
					File renameFile = new File(fileName);
					String fileNameOnly = renameFile.getName()
							+ "BULKUPLOADCOMPLETE";

					String absolutePath = renameFile.getAbsolutePath();
					String filePath = absolutePath.substring(0,
							absolutePath.lastIndexOf(File.separator));

					boolean isFileNameChanged = renameFile.renameTo(new File(MsrConstants.CSV_DIRECTORY + "\\" + fileNameOnly));
					
					System.out.println("CSV BulkUpload Process Ended!" + isFileNameChanged);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static List<String> getCSVFilenames() {

		File dir = new File(MsrConstants.CSV_DIRECTORY);
		//String[] fileNames = new String[0];
		List<String> fileNames = new ArrayList<String>();

		File[] files = dir.listFiles();
		

		for (File file : files) {
			if (!file.isDirectory()) {
				if (!file.getPath().contains("BULKUPLOADCOMPLETE")) {

					if (file.getAbsolutePath().endsWith("CSV")
							|| file.getAbsolutePath().endsWith("csv")) {
						fileNames.add(file.getPath());

						System.out.println("*********** fileNames is ******* "
								+ file.getPath());
						
						break;
					}
				}
			}
		}

		return fileNames;
	}

	public boolean uploadCSVToBulkUploadService(String filePath) {
		int originatorID;
		String loanNumber;
		String GUID;
		boolean isUpload = false;
		FileInputStream fisTargetFile = null;
		
		try {

			fisTargetFile = new FileInputStream(new File(
					filePath));

			String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
			
			ArrayList<String[]> origIDAndLoanNum = getOrigIDAndLoanNumber(targetFileStr);	
			
			SSIServiceSoapProxy proxy = new SSIServiceSoapProxy();

			System.out.println("Processing File. Please wait....");
			String[] resultArray = proxy.processImport(targetFileStr,
					Channel.Delegated, "Agency");
			
			System.out.println("\nOriginatorID : LoanNumber : GUID");
			if (resultArray != null && resultArray.length > 0) {
				for (int i=0; i<resultArray.length; i++){
					String[] tempStr = (String[])origIDAndLoanNum.get(i);
					originatorID = Integer.parseInt(tempStr[0]);
					loanNumber = tempStr[1];
					GUID = resultArray[i];
					System.out.println(i +"-->"+ originatorID +" : "+ loanNumber +" : "+ GUID);
					isUpload = true;
					// Update CreditData Table Field with Encompass Loan GUID value
					CreditDataDAO creditDataDAO = new CreditDataDAO();
					creditDataDAO.insertEncompassGUID(originatorID, loanNumber, GUID);
				}

				// int result =
				// pricingDAO.updateEncompassLoanGUID(resultArray[0]);
				// if(result > 0)
				// {
				//	isUpload = true;
				// }
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(fisTargetFile != null){
				try {
					fisTargetFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return isUpload;
	}
	
	public ArrayList<String[]> getOrigIDAndLoanNumber(String fileContent){
		String[] valueArray;
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		String[] contentArray = fileContent.split("\\n");
		for (int i = 1; i < contentArray.length; i++) {
			String[] temp = new String[2];
			valueArray = contentArray[i].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			temp[0] = valueArray[0];	//Get OriginatorID
			temp[1] = valueArray[3];	//Get Loan Number
			result.add(temp);
		}
		
		return result;
	}

}
