package com.capmkts.msrprocess.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.AgencyPurchAdvicesDAO;
import com.capmkts.msrprocess.dao.AgencyPurchAdvicesManualCheckDAO;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.dao.CreditDataManualCheckDAO;
import com.capmkts.msrprocess.dao.PricingDAO;
import com.capmkts.msrprocess.data.AgencyPurchAdvicesManualCheck;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.CreditDataManualCheck;
import com.capmkts.msrprocess.data.AgencyPurchAdvices;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.validator.ServicingCSVFileFieldValidator;
import com.capmkts.msrprocess.validator.ServicingDATFileFieldValidator;
import com.capmkts.msrprocess.validator.ServiceValidateUtility;
import com.capmkts.msrprocess.validator.DataValidator;

/**
 * ServicingDataDATFileService - This class process DAT File and validate fields
 * in each record.
 * 
 * @author Rama Yerramilli
 * 
 */
public class ServicingDataDATFileService implements FileService {

	public void process(File file, DataValidator dataValidator)
			throws Exception {

		try {
			String fileContent = FileUtils.readFileToString(file);
			System.out.println("fileContent: " + fileContent);

			String[] recordArray = fileContent.split("\\n");

			/*
			 * First 64 values are column headers. Process the request if record
			 * count > 1.
			 */
			String[] headerArray = recordArray[0].split("\\|");
			if (headerArray.length < 10){
				headerArray = recordArray[0].split("\\t");
			}
			boolean isFileCorrect = false;

			if (recordArray.length > 1) {
				HashMap<Integer, DataValidator> recordValidationMap = new HashMap<Integer, DataValidator>();

				List<AgencyPurchAdvices> agencyPurchAdvicesFileBeanList = new ArrayList<AgencyPurchAdvices>();
				
				for (int i = 1; i < recordArray.length; i++) {
					System.out.println("Record: " + i + " **** "
							+ recordArray[i].trim());

					// Split each value in a record which are separated by pipe.
					String[] valueArray = recordArray[i].split("\\|");
					if (valueArray.length < 20){
						valueArray = recordArray[i].split("\\t");
					}
					
					if (headerArray != null && valueArray != null) {
						if (headerArray.length != valueArray.length) {
							dataValidator.addMessage("\nRecord " +i+ ": Data fields do not match header. Please check the file!");
						} else {
							isFileCorrect = true;
						}

					} else {
						dataValidator.addMessage("\nRecord " +i+ ": Missing header or missing data. Please check the file!");
					}

					if (isFileCorrect) {
						AgencyPurchAdvices datFileBean = new AgencyPurchAdvices();
						boolean recordValid = false;

						for (int j = 0; j < valueArray.length; j++) {
							// System.out.println("Value: " +
							// valueArray[j].trim());

							// For few column names -- names are separated by
							// space.
							// In bean, we replaced space with _.
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Cash Out Refinance")) {
								headerArray[j] = "Cash_Out_Refinance";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Standard Subordinate Financing")) {
								headerArray[j] = "Standard_Subordinate_Financing";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Investment Property")) {
								headerArray[j] = "Investment_Property";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Credit Score LTV Adj Flow")) {
								headerArray[j] = "Credit_Score_LTV_Adj_Flow";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Adverse Market Delivery Charge")) {
								headerArray[j] = "Adverse_Market_Delivery_Charge";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Condo Properties")) {
								headerArray[j] = "Condo_Properties";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Property Inspection Waiver")) {
								headerArray[j] = "Property_Inspection_Waiver";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Pricing Cap Offset")) {
								headerArray[j] = "Pricing_Cap_Offset";
							}
							if (headerArray[j] != null
									&& headerArray[j].trim().equals(
											"Affinity Reverse Credit")) {
								headerArray[j] = "Affinity_Reverse_Credit";
							}

							// Prepare Bean
							ServiceValidateUtility.setDatFileBeanValue(datFileBean, headerArray[j].trim(),valueArray[j].trim());
							
							agencyPurchAdvicesFileBeanList.add(datFileBean);
						}

						// Perform Validations
//						ServicingDATFileFieldValidator datFileFieldValidator = new ServicingDATFileFieldValidator();
//						recordValid = datFileFieldValidator.validateFields(datFileBean, dataValidator, i);
						
						recordValid = true;
						/*
						 * System.out.println(" For record : " + i +
						 * " Field Validation completed: result is: " +
						 * recordValid);
						 */

						// DB Operations
						if (recordValid) {
							// Field value check are
							// success. Store the bean to database.
							// insert bean into CreditData table.
							AgencyPurchAdvicesDAO agencyPurchAdvicesDAO = new AgencyPurchAdvicesDAO();
							agencyPurchAdvicesDAO.save(datFileBean);
							System.out.println("Success**** " + recordValid);

						} else {

							AgencyPurchAdvicesManualCheck tempDATFile = new AgencyPurchAdvicesManualCheck();
							ServiceValidateUtility.copyBean(tempDATFile,
									datFileBean);

							System.out.println(ReflectionToStringBuilder
									.toString(tempDATFile,
											ToStringStyle.MULTI_LINE_STYLE));

							AgencyPurchAdvicesManualCheckDAO agencyPurchAdvicesManualCheckDAO = new AgencyPurchAdvicesManualCheckDAO();
							agencyPurchAdvicesManualCheckDAO.save(tempDATFile);
							
							String fileName = "PurchaseAdvice_"+tempDATFile.getSELLER_NUMBER()+"_"+tempDATFile.getLENDER_LOAN_NO()+  ".csv";
//							String fileLoc = "C:\\"+fileName;	
							String fileLoc = MsrConstants.SOFT_ERRORS+fileName;
							String errorFileName = "PurchaseAdvice_"+tempDATFile.getSELLER_NUMBER()+"_"+tempDATFile.getLENDER_LOAN_NO()+"_ErrorLog.csv";
//							String errorFileLoc = "C:\\"+errorFileName;
							String errorFileLoc = MsrConstants.SOFT_ERRORS+errorFileName;
							
							FileUtil fileUtil = new FileUtil();
							
							fileUtil.writeCSV(errorFileLoc, dataValidator.getMessageList().toString()+"\n", false);
							System.out.println("Failed**** " + recordValid);

						}

						// Add ValidationMessage List Object to Map
						recordValidationMap.put(new Integer(i), dataValidator);

						if (i == 27) {
							System.out.println(ReflectionToStringBuilder
									.toString(datFileBean,
											ToStringStyle.MULTI_LINE_STYLE));
						}
					}
				}
				
				/** Update purchased value */
				CreditDataDAO creditDataDAO = new CreditDataDAO();
				PricingDAO pricingDAO = new PricingDAO();
				for (int n=0; n<agencyPurchAdvicesFileBeanList.size(); n++){
					System.out.println("\n\nUPDATING CREDIT DATA\n\n");
					String loanNum = agencyPurchAdvicesFileBeanList.get(n).getLENDER_LOAN_NO();
					System.out.println("\n\nLoanNum: " + loanNum);
					String prinPurchased = String.valueOf(agencyPurchAdvicesFileBeanList.get(n).getPRIN_PURCHASED());
					System.out.println("\n\nPrin Purchased: " + prinPurchased);
					creditDataDAO.insertPurchased(loanNum, prinPurchased);
					pricingDAO.insertPurchased(loanNum, prinPurchased);
				}
			} else {
				String validationMessage = "Purchase Advice (DAT) File Has No Data! ";
				dataValidator.addMessage(validationMessage);
				System.out.println(validationMessage);
			}

			System.out.println(" SERVICING DAT - dataValidator.getMessageList() "
							+ dataValidator.getMessageList());

			if (dataValidator.getMessageList().size() > 0) {
				dataValidator.setValid(false);
			}

			// Insert File to "CMCFile" table irrespective of validation result
			// for auditing purposes.
			AgencyPurchAdvicesDAO agencyPurchAdvicesDAO = new AgencyPurchAdvicesDAO();
			String dataValidationList = "";
			if (dataValidator.getMessageList() != null) {
				dataValidationList = dataValidator.getMessageList().toString();
			}
			agencyPurchAdvicesDAO.saveFile(file, MsrConstants.SERVICING_DATA,
					dataValidator.isValid(), dataValidationList, null, 0);

			if (dataValidator.getMessageList().isEmpty()){
				dataValidator.addMessage("Agency Purchase Advise has been accepted.");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

}
