package com.capmkts.msrprocess.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CMCCommitmentNumberDAO;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.dao.CreditDataManualCheckDAO;
import com.capmkts.msrprocess.dao.PricingDAO;
//import com.capmkts.commit.bean.CSVFileBean;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.CreditDataManualCheck;
import com.capmkts.msrprocess.data.Pricing;
import com.capmkts.msrprocess.util.DataMapper;
import com.capmkts.msrprocess.util.EmailUtil;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.validator.ServicingCSVFileFieldValidator;
import com.capmkts.msrprocess.validator.ServicingPMIMatrixValidator;
import com.capmkts.msrprocess.validator.ServiceValidateUtility;
import com.capmkts.msrprocess.validator.DataValidator;

/**
 * ServicingDataCSVFileService - This class process CSV File and validate fields
 * in each record.
 * 
 * @author Rama Yerramilli
 * 
 */
public class ServicingDataCSVFileService implements FileService {
	
	// These two fields are required to insert into CMCFile table for each file.
	// Assumption is that first record (all records) in the upload file has these values.
	private String LOANNUMBER = null;
	private int AGENCYCOMMITMENTID = 0;

	public void process(File file, DataValidator dataValidator)
			throws Exception {
		try {
			boolean failedValidation = false;
			CreditDataManualCheck tempCDMC = new CreditDataManualCheck();
			
//			String fileContent = FileUtils.readFileToString(file);
			String fileContent = DataMapper.mapfile(file);
			System.out.println("fileContent: " + fileContent);

			CreditDataDAO creditDataDAO = new CreditDataDAO();

			String[] recordArray = fileContent.split("\\n");
			boolean isFileCorrect = false;

			/*
			 * First 188 values are column headers. Process the request if
			 * record count > 1.
			 */
			String[] headerArray = recordArray[0].split("\\,");
			
			int loanNumIdx;
			for (int n=0; n<headerArray.length; n++){
				if (headerArray[n].equalsIgnoreCase("loannumber")){
					loanNumIdx = n;
					break;
				}
			}

			String[] mandateFieldArray = { "ORIGINATORID", "COMMITMENTID",
					"AGENCYCOMMITMENTID", "LOANNUMBER", "BORROWERFIRSTNAME",
					"BORROWERLASTNAME", "BORROWERSSN",
					"BORROWERCREDITSCORE","BORROWERCELLPHONENUMBER",
					"ORIGINALMORTGAGEAMOUNT",
					"ANNUALINTERESTRATE", "LOANPROGRAMID", "PROPERTYTYPE",
					"LOANPURPOSE", "LTV", "CLTV", "DTI", "OCCUPYCODE",
					"LOANTERM", "PIPAYMENT", "ESCROWMONTHLYPAYMENT",
					"TOTALPAYMENT", "CURRENTPRINCIPALBALANCE", "FIRSTDUEDATE",
					"CURRENTDUEDATE", "MATURITYDATE", "PREPAYPENALTYFLAG",
					"INTERESTONLYFLAG", "ESCROWBALANCE",
					"PROPERTYSTREETADDRESS", "PROPERTYCITY", "PROPERTYSTATE",
					"PROPERTYZIPPLUS4", "ORIGINALAPPRAISEDVALUE",
					"ORIGINALAPPRAISEDDATE", "CURRENTAPPRAISEDVALUE",
					"CURRENTAPPRAISEDDATE", "MERSREGFLAG", "MERSMOMINDICATOR",
					"INTERESTCOLLECTEDATCLOSING", "POINTSPAIDBYBORROWER",
					"BILLMODE", "SERVICEFEERATE", "GRACEDAYS", "PURCHASEPRICE",
					"FLOODREQUIREDFLAG", "FLOODPROGRAM",
					"FLOODDETERMINATIONDATE", "FLOODCERTNUMBER", "FLOODCMPCO",
					"FLOODMAPCO", "HIGHPRICELOANINDICATOR",
					"LateChargeCode", "LateChargeFactor", "MERSMINNUMBER",
					"MERSREGISTRATIONDATE" };
					
					//"BORROWERMIDDLEINITIAL", 
					//"BORROWEREMAILADDRESS", 
					//"COUNTYCODE"
					//"CO-OPINDICATOR" ?

			boolean isTempTable = false;

			if (recordArray.length > 1) {

				List<CreditData> csvFileBeanList = new ArrayList<CreditData>();

				// 1. Check for mandate field values.
				for (int i = 1; i < recordArray.length; i++) {
					System.out.println("Record: " + i + " **** "
							+ recordArray[i].trim());

					// Split each value in a record which are separated by comma.
					String[] valueArray = recordArray[i].split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					
					//Temp assignment
					System.out.println("\nHeaderArray Length: " + headerArray.length);
					System.out.println("\nValueArray length: " + valueArray.length+ "\n\n");
					String[] tempArr = new String[headerArray.length];
					if (headerArray.length > valueArray.length){
						for (int k=0; k<valueArray.length; k++){
							tempArr[k] = valueArray[k];
						}
					}
					else{
						for (int k=0; k<headerArray.length; k++){
							tempArr[k] = valueArray[k];
						}
					}
					valueArray = tempArr;
					
					
					System.out.println("\n\nHeader Array Count: " +headerArray.length+ "\n\n");
					System.out.println("\n\nValue Array Count:" +valueArray.length+ "\n\n");
					if (headerArray != null && valueArray != null) {
						if (headerArray.length != valueArray.length) {
							dataValidator
									.addMessage("\nHeader element count does not match actual data. Please check the file!");
						} else {
							isFileCorrect = true;
						}

					} else {
						dataValidator
								.addMessage("\nHeader element count does not match  actual data. Please check the file!");
					}

					if (isFileCorrect) {

						CreditData csvFileBean = new CreditData();

						for (int j = 0; j < valueArray.length; j++) {
							// System.out.println("Value: " +
							// valueArray[j].trim());
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"CO-OPINDICATOR")) {
								headerArray[j] = "COOPINDICATOR";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"VA FUNDING/MI UPFRONT")) {
								headerArray[j] = "VAFUNDINGMIPUPFRONT";
							}

							ServiceValidateUtility.setCsvFileBeanValue(
									csvFileBean, headerArray[j], valueArray[j]);
							
							this.LOANNUMBER = csvFileBean.LOANNUMBER;
							this.AGENCYCOMMITMENTID = csvFileBean.AGENCYCOMMITMENTID;
							

							if (ServiceValidateUtility.mandateFieldExists(
									mandateFieldArray, headerArray[j].trim())) {

								if (valueArray[j] == null
										|| valueArray[j].trim() == ""
										|| valueArray[j].trim().length() == 0) {

									String validationMessage = "\nRecord: "
											+ i
											+ " HeaderName is: "
											+ headerArray[j]
											+ " This field is Mandate. Value is either Null or Empty";
									dataValidator.addMessage(validationMessage);
									System.out.println(validationMessage);
								}
							}
						}

						// If Validation messages present in dataValidator
						// class, mandate check is failed.
						if (dataValidator.getMessageList().size() > 0) {
							csvFileBean.setMandateCompleted(false);
						}
						else{
							csvFileBean.setMandateCompleted(true);
						}

						System.out.println("****** Mandate Check Status: "
								+ csvFileBean.getMandateCompleted());

						csvFileBeanList.add(csvFileBean);
					}
				}
					// Perform Field data validation after Mandate fields check
					// is successful.
					for (int j = 0; j < csvFileBeanList.size(); j++) {
						
						System.out.println(csvFileBeanList.get(j).getAGENCYCOMMITMENTID());
						// System.out.println("Value: " + //
						// valueArray[j].trim());
						boolean isFieldValid = false;

						CreditData csvFileRecord = csvFileBeanList.get(j);

//						boolean isTolarenceMet = ServiceValidateUtility
//								.performCurrentPrincipalBalanceTolerance(csvFileRecord);
						boolean isTolarenceMet = false;
						
						if (!isTolarenceMet) {
							if (csvFileRecord.getMandateCompleted()) {

								ServicingCSVFileFieldValidator csvFileFieldValidator = new ServicingCSVFileFieldValidator();
								isFieldValid = csvFileFieldValidator
										.validateFields(csvFileRecord,
												dataValidator);

								System.out.println(" \nFor record : "
												+ j
												+ " Field Validation completed: result is: "
												+ isFieldValid);

								isFieldValid = true;  //UNCOMMENT TO TEST
								
								// Mandate field check and field value check are
								// success.
								// Now perform PMI Matrix Validation.
								if (isFieldValid) {

									ServicingPMIMatrixValidator pmiMatrixValidator = new ServicingPMIMatrixValidator();
									boolean isPmiValid = pmiMatrixValidator
											.validateCSV_PMIMatrix(csvFileRecord);

									if (isPmiValid) {
										// insert bean into CreditData table.
										isTempTable = false;
									} else {
										dataValidator.addMessage("\nFor record "
												+(j+=1)+ ": PMI Matrix Validation failed!");
										isTempTable = true;
									}

								} else {
									isTempTable = true;
									dataValidator.addMessage("\nRecord "
											+(j+=1)+ ": Field Validation Failed: ESCROWBALANCE > 0 and ESCROW MONTHLY PAYMENT > 0");
								}
							} else {
								// Mandate Check failed.
								isTempTable = true;
								dataValidator.addMessage("\nFor record "
										+(j+=1)+ ": Mandate Fields Check failed!");
							}
						} else {
							// isTolarenceMet Check failed.
							isTempTable = true;
							dataValidator.addMessage("\nFor record "
									+(j+=1)+ ": Tolerance Check failed!");

							// Update Commitment Data table with status
							// "Completed".
							CMCCommitmentNumberDAO cmcCommitmentNumberDAO = new CMCCommitmentNumberDAO();
							cmcCommitmentNumberDAO
									.closeCMCCommitment(csvFileRecord
											.getCOMMITMENTID());
						}

						// DB Operations
						// Insert File to "CMCFile" table irrespective of
						// validation result for auditing purposes.

						// if isValid = true, insert data into table
						// 'CreditData';
						// else insert the ServicingFile's data into a temporary
						// table 'CreditDataManualCheck'
						// Store the bean to database.
						if (!isTempTable) {
							// Populate County Name if it missed in the record
							// based on CountyCode. Else set value to NULL.
							ServiceValidateUtility
									.setPropertyCounty(csvFileRecord);
							// insert bean into CreditData table.
							creditDataDAO.save(csvFileRecord);
							System.out.println("Success**** ");
//							System.out.println("\nINSERTING CREDIT DATA\n");
							

							// Insert record into Pricing table.
							Pricing pricingCSVFile = new Pricing();
							ServiceValidateUtility.copyBean(pricingCSVFile,
									csvFileRecord);

							PricingDAO pricingDAO = new PricingDAO();
							pricingDAO.save(pricingCSVFile);

							CreditDataManualCheck tempCSVFile = new CreditDataManualCheck();
							ServiceValidateUtility.copyBean(tempCSVFile,csvFileRecord);
							CreditDataManualCheckDAO creditDataManualCheckDAO = new CreditDataManualCheckDAO();
							creditDataManualCheckDAO.deleteCreditDataManualCheck(tempCSVFile);
							
//							String errorFileName = "ServicingFile_"+tempCSVFile.getORIGINATORID()+ "_" +tempCSVFile.getAGENCYCOMMITMENTID()+"_ErrorLog.csv";
//							String errorFileLoc = "C:\\"+errorFileName;
//							String errorFileLoc = MsrConstants.SOFT_ERRORS+errorFileName;
							
//							System.out.println("\n\nerrorFileLoc: " + errorFileLoc);
							
//							File tempFile = new File(errorFileLoc);
//							if (tempFile.exists()){
//								tempFile.delete();
//							}

						} else {
							// else insert the ServicingFile's data into a
							// temporary table 'CreditDataManualCheck'
							// Store the bean to database.
							CreditDataManualCheck tempCSVFile = new CreditDataManualCheck();
							tempCDMC = tempCSVFile;
							ServiceValidateUtility.copyBean(tempCSVFile,
									csvFileRecord);

							System.out.println(ReflectionToStringBuilder
									.toString(tempCSVFile,
											ToStringStyle.MULTI_LINE_STYLE));
							
							CreditDataManualCheckDAO creditDataManualCheckDAO = new CreditDataManualCheckDAO();
							creditDataManualCheckDAO.deleteCreditDataManualCheck(tempCSVFile);
							creditDataManualCheckDAO.save(tempCSVFile);
							
							failedValidation = true;
							
							
						}
					
				}
					if (failedValidation){
						//Write file to network folder for manual check
						System.out.println("\nWriting Credit Data Manual Check to network drive\n");
						String fileName = "ServicingFile_" +tempCDMC.getORIGINATORID()+ ".csv";
//						String fileLoc = "C:\\"+fileName;
						String fileLoc = MsrConstants.SOFT_ERRORS+fileName;
						String errorFileName = "ServicingFile_" +tempCDMC.getORIGINATORID() +"_ErrorLog.csv";
//						String errorFileLoc = "C:\\"+errorFileName;
						String errorFileLoc = MsrConstants.SOFT_ERRORS+errorFileName;

						/** Check if there are errors in dataValidator */
						if (dataValidator.getMessageList().size() > 0){
							FileOutputStream fout = new FileOutputStream(fileLoc);
							OutputStream outputStream = new FileOutputStream (fileLoc); 
							
//							ByteArrayInputStream baos = reteriveByteArrayInputStream(file);
							
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							InputStream is = new FileInputStream(file);
							byte[] temp = new byte[1024];
							int read;

							while((read = is.read(temp)) >= 0){
							   buffer.write(temp, 0, read);
							}
							buffer.writeTo(outputStream);

							/** Write error file to identify errors */
							FileUtil fileUtil = new FileUtil();
							
							//Get error log
//							CMCFileDAO cmcFileDAO = new CMCFileDAO();
//							String errorLog = cmcFileDAO.getErrorLog();
							
							fileUtil.writeCSV(errorFileLoc, dataValidator.getMessageList().toString()+"\n", false);
							System.out.println("Done");
							is.close();
						}
						else{
							File tempFile = new File(errorFileLoc);
							if (tempFile.exists()){
								tempFile.delete();
							}
						}
						System.out.println("Failed**** " + isTempTable);
					}
			} else {
				String validationMessage = "\nCSV File Has No Data! ";
				dataValidator.addMessage(validationMessage);
				System.out.println(validationMessage);
			}

			if (dataValidator.getMessageList() != null
					&& dataValidator.getMessageList().size() > 0) {
				dataValidator.setValid(false);
			}

			String dataValidationList = "";
			if (dataValidator.getMessageList() != null) {
				dataValidationList = dataValidator.getMessageList().toString();
				EmailUtil emailUtil = new EmailUtil();
				emailUtil.sendErrorEmail("Co-Issue Notification", "A servicing file has been uploaded but failed validation.");
			}

			creditDataDAO.saveFile(file, MsrConstants.SERVICING_DATA,
					dataValidator.isValid(), dataValidationList, this.LOANNUMBER, this.AGENCYCOMMITMENTID);

			if (dataValidator.getMessageList().isEmpty()){
				dataValidator.addMessage("Servicing Data has been accepted.");
				EmailUtil emailUtil = new EmailUtil();
				emailUtil.sendEWSEmail("Co-Issue Notification", "A new servicing file has been successfully uploaded.");
			}
			if (dataValidator.getMessageList().size() > 5){
				dataValidator.setMessageList(null);
				dataValidator.addMessage("Errors found in file. Please refer to CMC log file.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("error messages are: "
					+ dataValidator.getMessageList().toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};
	
	public static ByteArrayInputStream reteriveByteArrayInputStream(File file) throws Exception{

	    return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

	}

}
