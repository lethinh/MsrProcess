package com.capmkts.msrprocess.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.capmkts.bulkupload.client.BulkUploadServiceClient;
import com.capmkts.bulkupload.client.BulkUploadServiceClientJob;
import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.util.FileUtil;

public class CheckDB {
    public static void generateCSVCheck(String originatorID) throws SQLException{
    	FileUtil fileUtil = new FileUtil();
    	//Send to Encompass
//		BulkUploadServiceClientJob bulkUploadClient = new BulkUploadServiceClientJob();
//		bulkUploadClient.schedulerJobTest();
    	while (true) {
    		try {
    			System.out.println("\nStarting Thread!\n");
//    			Thread.sleep(MsrConstants.DB_CHECK_INTERVAL);
    			Thread.sleep(5000);
    			List<String> list;
    			Connection m_Connection = null;
    			Statement m_Statement = null;
    			Statement m_Statement2 = null;
    			Statement m_Statement3 = null;
    			Statement m_Statement4 = null;
    			Statement m_Statement5 = null;
    			ResultSet m_ResultSet = null;
    			ResultSet m_CMCNumResult = null;
    			ResultSet m_PurchasedBitResult = null;
    			ResultSet m_LoanNumsResult = null;
    			try{
    				
    		          // Get DataSource
//    	            Context initContext  = new InitialContext();
//    	            Context envContext  = (Context)initContext.lookup("java:/comp/env");
//    	            DataSource dataSource = (DataSource)envContext.lookup("jdbc/MsrProcessDB");
    	 
    				
    				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		
    			    m_Connection = DriverManager.getConnection(
    			    		"jdbc:sqlserver://10.1.24.4;instanceName=capmkts;databaseName=CoIssue_Test", "coissue", "coi$$ue2");
//    			    		"jdbc:sqlserver://localhost;databaseName=master", "tester", "Passw0rd2");
//    			     url="jdbc:sqlserver://10.1.24.4;instanceName=capmkts;databaseName=CoIssue_Test;" removeAbandoned="true" removeAbandonedTimeout="1800" validationQuery="select 1"/>
//    			    m_Connection = dataSource.getConnection();
    			     
    			    
    			    m_Statement = m_Connection.createStatement();
    			    m_Statement2 = m_Connection.createStatement();
    			    m_Statement3 = m_Connection.createStatement();
    			    m_Statement4 = m_Connection.createStatement();
    			    m_Statement5 = m_Connection.createStatement();
    			    
    			    
    			    Calendar currentDate = Calendar.getInstance();
    			    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    			    String dateNow = formatter.format(currentDate.getTime());
    			    System.out.println(dateNow);
    			    
    			    /** Get CMC Num */
    			    boolean purchasedBitCheck = false;
    			    String queryGetCMCNum = "SELECT DISTINCT CMC_Commit_Number FROM [CoIssue_Test].[dbo].[CMC_Agency_CommitNumber] WHERE CMC_Commit_Status = 'OPEN'";
    			    m_CMCNumResult = m_Statement4.executeQuery(queryGetCMCNum);
    			    while (m_CMCNumResult.next()){
    			    	String cmcCommitNum = m_CMCNumResult.getString("CMC_Commit_Number");
    			    	System.out.println("\nCMC COMMITMENT NUMBER: " + cmcCommitNum);
    			    	
    			    	/** Get Purchased Bit Set */
    			    	String queryGetPurchasedBit = "SELECT PURCHASED FROM [CoIssue_Test].[dbo].[CreditData] WHERE AGENCYCOMMITMENTID IN (SELECT Agency_Commit_Number FROM [CoIssue_Test].[dbo].[CMC_Agency_CommitNumber] WHERE CMC_Commit_Number = '" +cmcCommitNum+ "')";
    			    	m_PurchasedBitResult = m_Statement3.executeQuery(queryGetPurchasedBit);
    			    	while (m_PurchasedBitResult.next()){
    			    		String purchasedBit = m_PurchasedBitResult.getString("PURCHASED");
    			    		if (purchasedBit.contains("1")){
    			    			purchasedBitCheck = true;
    			    		}
    			    		else{
    			    			purchasedBitCheck = false;
    			    			break;
    			    		}
    			    		System.out.println("\nPURCHASED BIT: " +purchasedBit);
    			    	}
    			    	if (purchasedBitCheck){
    			    		
    			    		/** Get Loan Numbers Set */
    			    		String queryGetLoanNum = "SELECT LOANNUMBER FROM [CoIssue_Test].[dbo].[CreditData] WHERE AGENCYCOMMITMENTID IN (SELECT Agency_Commit_Number FROM [CoIssue_Test].[dbo].[CMC_Agency_CommitNumber] WHERE CMC_Commit_Number = '" +cmcCommitNum+ "')";
    			    		m_LoanNumsResult = m_Statement5.executeQuery(queryGetLoanNum);
    			    		while (m_LoanNumsResult.next()){
    			    			String loanNum = m_LoanNumsResult.getString("LOANNUMBER");
    			    			System.out.println("\nLoan Number: " +loanNum);
//    		    			    String queryMain = "SELECT * FROM [CoIssue_Test].[dbo].[Pricing]";
    		    			    String queryMain = "SELECT [ORIGINATORID],[COMMITMENTID],[AGENCYCOMMITMENTID],[LOANNUMBER],[BRANCHID],[LOANOFFICERID],[BORROWERFIRSTNAME],[BORROWERMIDDLEINITIAL],[BORROWERLASTNAME],[BORROWERSSN],[BORROWERCREDITSCORE],[BORROWERCELLPHONENUMBER],[BORROWEREMAILADDRESS],[BORROWER2FIRSTNAME],[BORROWER2MIDDLEINITIAL],[BORROWER2LASTNAME],[BORROWER2SSN],[BORROWER2CREDITSCORE],[BORROWER2CELLPHONENUMBER],[BORROWER2EMAILADDRESS],[BORROWER3FIRSTNAME],[BORROWER3MIDDLEINITIAL],[BORROWER3LASTNAME],[BORROWER3SSN],[BORROWER3CREDITSCORE],[BORROWER3CELLPHONENUMBER],[BORROWER3EMAILADDRESS],[BORROWER4FIRSTNAME],[BORROWER4MIDDLEINITIAL],[BORROWER4LASTNAME],[BORROWER4SSN],[BORROWER4CREDITSCORE],[BORROWER4CELLPHONENUMBER],[BORROWER4EMAILADDRESS],[BORROWER5FIRSTNAME],[BORROWER5MIDDLEINITIAL],[BORROWER5LASTNAME],[BORROWER5SSN],[BORROWER5CREDITSCORE],[BORROWER5CELLPHONENUMBER],[BORROWER5EMAILADDRESS],[ORIGINALMORTGAGEAMOUNT],[ANNUALINTERESTRATE],[LOANPROGRAMID],[PROPERTYTYPE],[LOANPURPOSE],[LTV],[CLTV],[DTI],[OCCUPYCODE],[LOANTERM],[FUNDSSOURCE],[PIPAYMENT],[ESCROWMONTHLYPAYMENT],[TOTALPAYMENT],[CURRENTPRINCIPALBALANCE],[FIRSTDUEDATE],[CURRENTDUEDATE],[MATURITYDATE],[PREPAYPENALTYFLAG],[PREPAYPENALTYDESCRIPTION],[INTERESTONLYFLAG],[INTERESTONLYEXPIRATIONDATE],[INTERESTONLYPERIOD],[ESCROWBALANCE],[ARMPLAN],[ARMINDEX],[ARMMARGIN],[ARMORIGINALIRCHANGEDATE],[ARMORIGINALPICHANGEDATE],[ARMNEXIRCHANGEDATE],[ARMNEXPICHANGEDATE],[ARMIRCHANGEPERIOD],[ARMPICHANGEPERIOD],[FLOODINSMONTHLYPAYMENT],[HAZARDINSMONTHLYPAYMENT],[COUNTYMONTHLYPAYMENT],[CITYMONTHLYPAYMENT],[LIENMONTHLYPAYMENT],[PMIMONTHLYPAYMENT],[CLOSINGDATE],[PROPERTYSTREETADDRESS],[PROPERTYCITY],[PROPERTYSTATE],[PROPERTYZIPPLUS4],[PROPERTYCOUNTY],[MAILINGSTREETADDRESS],[MAILINGCITY],[MAILINGSTATE],[MAILINGZIPPLUS4],[TELEPHONENUMBER],[SECONDTELEPHONENUMBER],[ORIGINALAPPRAISEDVALUE],[ORIGINALAPPRAISEDDATE],[CURRENTAPPRAISEDVALUE],[CURRENTAPPRAISEDDATE],[MERSMINNUMBER],[MERSREGISTRATIONDATE],[MERSREGFLAG],[MERSMOMINDICATOR],[INTERESTCOLLECTEDATCLOSING],[POINTSPAIDBYBORROWER],[BILLMODE],[ESCROWCUSHION],[TAXSERVICECOMPANY],[TAXSERVICECONTRACTNUMBER],[SERVICEFEERATE],[GRACEDAYS],[PURCHASEPRICE],[CENSUSTRACT],[NUMBEROFUNITS],[LEGALDESCRIPTION],[BALLOONTERM],[ROUNDINGFACTOR],[LOOKBACKPERIOD],[MAXCEILINGPERCENTAGE],[MAXFLOORPERCENTAGE],[IRCAPATFIRSTCHANGE],[NEXTCAPPERCHANGE],[LIFEPAYEE],[LIFECERTNUMBER],[LIFEDISBAMT],[LIFEDUEDATE],[DISBPAYEE],[DISBCERTNUMBER],[DISBDISBAMT],[DISBDUEDATE],[FLOODREQUIREDFLAG],[FLOODPROGRAM],[FLOODDETERMINATIONDATE],[FLOODCOMMUNITYNUMBER],[FLOODZONE],[FLOODFIRMDATE],[FLOODCERTNUMBER],[FLOODCMPCO],[FLOODMAPCO],[PMIMIPGUARCERTNO],[PMIMIPPERCENTOFCOVERAGE],[PMIRATEPERCENTAGE],[PMIMIPPAYEE],[PMIMIPDISBURSEMENTDUEDATE],[PMIMIPDISBURSEMENTAMOUNT],[PMIMIPBILLCODE],[PMIMIPANNUALPREMIUM],[PMIMIPEXPIRATIONDATE],[FNMAEXPANDEDAPPROVALCODE],[FNMAFLEX97CODE],[FHACASENUMBER],[FHAADPCODE],[VANUMBER],[HELOCPLAN],[HELOCFLAG],[HELOCDRAWAMOUNT],[HELOCDRAWDATE],[HELOCDUEDATE],[HELOCRATEMARGIN],[HELOCCEILING],[HELOCPROCESSSTOP],[DISTTYPE],[REPLACEMENTRESERVE],[PERIODMONTHS],[FIRSTSUBSIDYDUEDATE],[COOPINDICATOR],[HIGHPRICELOANINDICATOR],[LateChargeCode],[LateChargeFactor],[CMCLOANNUMBER],[VAFUNDING_MIUPFRONT],[BASEBUYPRICE],[ADJUSTMENTDESCRIPTION1],[ADJUSTMENT1],[ADJUSTMENTDESCRIPTION2],[ADJUSTMENT2],[ADJUSTMENTDESCRIPTION3],[ADJUSTMENT3],[ADJUSTMENTDESCRIPTION4],[ADJUSTMENT4],[ADJUSTMENTDESCRIPTION5],[ADJUSTMENT5],[ADJUSTMENTDESCRIPTION6],[ADJUSTMENT6],[ADJUSTMENTDESCRIPTION7],[ADJUSTMENT7],[ADJUSTMENTDESCRIPTION8],[ADJUSTMENT8],[ADJUSTMENTDESCRIPTION9],[ADJUSTMENT9],[NETBUYPRICE] FROM [CoIssue_Test].[dbo].[Pricing] WHERE LOANNUMBER = '" +loanNum+ "'";
    		    			    queryMain = queryMain.replaceAll("null", "");
    		    			    queryMain = queryMain.replaceAll(" 00:00:00.0", "");
    		    			    System.out.println("\nExecuting: " +queryMain);
    		    			    String queryDel;
    		    			    
    		    			    m_ResultSet = m_Statement.executeQuery(queryMain);
    		    			    
    		    			    String nullHandler, mynewstring;
    		    			    String[] listArray = new String[188];
    		    			    String agencyCommitmentNumber = "";
    		    			    while(m_ResultSet.next()){
    		    			    	nullHandler = m_ResultSet.getString("NETBUYPRICE");

    		    			    	if (!(nullHandler == null)){
    		    			    		
    		    			    		list = new LinkedList<String>();
    			    			    	for (int i=0; i<188; i++){
    							        	list.add(m_ResultSet.getString(i+1));
    							        }
    			    			    	
    			    			    	System.out.println("\nList: " +list);
    			    				    listArray = list.toArray(new String[0]);
    			    				    StringBuffer result2 = new StringBuffer();
    			    				    for (int i = 0; i < listArray.length; i++) {
    			    				       if (i == 2){
    			    				    	   agencyCommitmentNumber = listArray[i];
    			    				       }
    			    				       
    			    				       //Check to see if the columns contain 1 and 0 then replace with y and n
    			    				       if (i == 59 || i == 61 || i == 98 || i == 99 || i == 127 || i == 150 || i == 162){
    			    				    	   if (!listArray[i].equalsIgnoreCase("y") || !listArray[i].equalsIgnoreCase("n")){
	    			    				    	   if (listArray[i].equalsIgnoreCase("1")){
	    			    				    		   listArray[i] = "Y";
	    			    				    	   }
	    			    				    	   else if (listArray[i].equalsIgnoreCase("0")){
	    			    				    		   listArray[i] = "N";
	    			    				    	   }
	    			    				    	   else{
	    			    				    		   listArray[i] = "";
	    			    				    	   }
    			    				    	   }
    			    				       }
    			    				       
    			    				       result2.append( listArray[i] );
    			    				       if (!(i == listArray.length-1)){
    			    				    	   result2.append(",");
    			    				       }
    			    				    }
    			    				    System.out.println("\\Agency Commitment Number: " + agencyCommitmentNumber);
    			    				    String tempHeaders = "ORIGINATORID,COMMITMENTID,AGENCYCOMMITMENTID,LOANNUMBER,BRANCHID,LOANOFFICERID,BORROWERFIRSTNAME,BORROWERMIDDLEINITIAL,BORROWERLASTNAME,BORROWERSSN,BORROWERCREDITSCORE,BORROWERCELLPHONENUMBER,BORROWEREMAILADDRESS,BORROWER2FIRSTNAME,BORROWER2MIDDLEINITIAL,BORROWER2LASTNAME,BORROWER2SSN,BORROWER2CREDITSCORE,BORROWER2CELLPHONENUMBER,BORROWER2EMAILADDRESS,BORROWER3FIRSTNAME,BORROWER3MIDDLEINITIAL,BORROWER3LASTNAME,BORROWER3SSN,BORROWER3CREDITSCORE,BORROWER3CELLPHONENUMBER,BORROWER3EMAILADDRESS,BORROWER4FIRSTNAME,BORROWER4MIDDLEINITIAL,BORROWER4LASTNAME,BORROWER4SSN,BORROWER4CREDITSCORE,BORROWER4CELLPHONENUMBER,BORROWER4EMAILADDRESS,BORROWER5FIRSTNAME,BORROWER5MIDDLEINITIAL,BORROWER5LASTNAME,BORROWER5SSN,BORROWER5CREDITSCORE,BORROWER5CELLPHONENUMBER,BORROWER5EMAILADDRESS,ORIGINALMORTGAGEAMOUNT,ANNUALINTERESTRATE,LOANPROGRAMID,PROPERTYTYPE,LOANPURPOSE,LTV,CLTV,DTI,OCCUPYCODE,LOANTERM,FUNDSSOURCE,PIPAYMENT,ESCROWMONTHLYPAYMENT,TOTALPAYMENT,CURRENTPRINCIPALBALANCE,FIRSTDUEDATE,CURRENTDUEDATE,MATURITYDATE,PREPAYPENALTYFLAG,PREPAYPENALTYDESCRIPTION,INTERESTONLYFLAG,INTERESTONLYEXPIRATIONDATE,INTERESTONLYPERIOD,ESCROWBALANCE,ARMPLAN,ARMINDEX,ARMMARGIN,ARMORIGINALIRCHANGEDATE,ARMORIGINALPICHANGEDATE,ARMNEXIRCHANGEDATE,ARMNEXPICHANGEDATE,ARMIRCHANGEPERIOD,ARMPICHANGEPERIOD,FLOODINSMONTHLYPAYMENT,HAZARDINSMONTHLYPAYMENT,COUNTYMONTHLYPAYMENT,CITYMONTHLYPAYMENT,LIENMONTHLYPAYMENT,PMIMONTHLYPAYMENT,CLOSINGDATE,PROPERTYSTREETADDRESS,PROPERTYCITY,PROPERTYSTATE,PROPERTYZIPPLUS4,PROPERTYCOUNTY,MAILINGSTREETADDRESS,MAILINGCITY,MAILINGSTATE,MAILINGZIPPLUS4,TELEPHONENUMBER,SECONDTELEPHONENUMBER,ORIGINALAPPRAISEDVALUE,ORIGINALAPPRAISEDDATE,CURRENTAPPRAISEDVALUE,CURRENTAPPRAISEDDATE,MERSMINNUMBER,MERSREGISTRATIONDATE,MERSREGFLAG,MERSMOMINDICATOR,INTERESTCOLLECTEDATCLOSING,POINTSPAIDBYBORROWER,BILLMODE,ESCROWCUSHION,TAXSERVICECOMPANY,TAXSERVICECONTRACTNUMBER,SERVICEFEERATE,GRACEDAYS,PURCHASEPRICE,CENSUSTRACT,NUMBEROFUNITS,LEGALDESCRIPTION,BALLOONTERM,ROUNDINGFACTOR,LOOKBACKPERIOD,MAXCEILINGPERCENTAGE,MAXFLOORPERCENTAGE,IRCAPATFIRSTCHANGE,NEXTCAPPERCHANGE,LIFEPAYEE,LIFECERTNUMBER,LIFEDISBAMT,LIFEDUEDATE,DISBPAYEE,DISBCERTNUMBER,DISBDISBAMT,DISBDUEDATE,FLOODREQUIREDFLAG,FLOODPROGRAM,FLOODDETERMINATIONDATE,FLOODCOMMUNITYNUMBER,FLOODZONE,FLOODFIRMDATE,FLOODCERTNUMBER,FLOODCMPCO,FLOODMAPCO,PMIMIPGUARCERTNO,PMIMIPPERCENTOFCOVERAGE,PMIRATEPERCENTAGE,PMIMIPPAYEE,PMIMIPDISBURSEMENTDUEDATE,PMIMIPDISBURSEMENTAMOUNT,PMIMIPBILLCODE,PMIMIPANNUALPREMIUM,PMIMIPEXPIRATIONDATE,FNMAEXPANDEDAPPROVALCODE,FNMAFLEX97CODE,FHACASENUMBER,FHAADPCODE,VANUMBER,HELOCPLAN,HELOCFLAG,HELOCDRAWAMOUNT,HELOCDRAWDATE,HELOCDUEDATE,HELOCRATEMARGIN,HELOCCEILING,HELOCPROCESSSTOP,DISTTYPE,REPLACEMENTRESERVE,PERIODMONTHS,FIRSTSUBSIDYDUEDATE,COOPINDICATOR,HIGHPRICELOANINDICATOR,LATECHARGECODE,LATECHARGEFACTOR,CMCLOANNUMBER,VAFUNDINGFEE,BASE BUY PRICE,ADJUSTMENT DESCRIPTION 1,ADJUSTMENT 1,ADJUSTMENT DESCRIPTION 2,ADJUSTMENT 2,ADJUSTMENT DESCRIPTION 3,ADJUSTMENT 3,ADJUSTMENT DESCRIPTION 4,ADJUSTMENT 4,ADJUSTMENT DESCRIPTION 5,ADJUSTMENT 5,ADJUSTMENT DESCRIPTION 6,ADJUSTMENT 6,ADJUSTMENT DESCRIPTION 7,ADJUSTMENT 7,ADJUSTMENT DESCRIPTION 8,ADJUSTMENT 8,ADJUSTMENT DESCRIPTION 9,ADJUSTMENT 9,NET BUY PRICE\n";
    			    				    
    			    				    mynewstring = tempHeaders +result2.toString();
    			    				    String fileName = agencyCommitmentNumber+"_"+loanNum+".csv";
    			    				    System.out.println("\nFileName: " + fileName);
    									String fileLoc = "\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EncompassUpload\\"+fileName;
    			    				    boolean writeSuccess = fileUtil.writeCSV(fileLoc, mynewstring+"\n", false);
    			    				    if (writeSuccess){
    			    				    	System.out.println("Exported to CSV successfully.");
    			    				    }
    			    				    
    			    				    else{
    			    				    	System.out.println("Cannot export to CSV file.");
    			    				    }
    		    			    	}
    		    			    }
    		    			    //Uncomment when testing is complete
//    		    			    queryDel = "DELETE FROM [CoIssue_Test].[dbo].[Pricing] WHERE NETBUYPRICE IS NOT NULL";
//    						    
//    					    	boolean success = m_Statement2.execute(queryDel);
//    					    	if (success){
//    					    		System.out.println("DB row removed successfully.");
//    					    	}
//    					    	else{
//    					    		System.out.println("Error: DB row removed unsuccessfully.");
//    					    	}
    			    		}
    			    	}
    			    }
			    	m_Statement.close();
			    	m_Statement2.close();
			    	m_Statement3.close();
			    	m_Statement4.close();
			    	m_Connection.close();
    			}catch(Exception ie){
    				ie.printStackTrace();
    			} finally {
    		        if (m_ResultSet != null) { 
    		        	m_ResultSet.close();
    		        }

    		        if (m_Statement != null) {
    		        	m_Statement.close();
    		        }

    		        if (m_Connection != null) {
    		            m_Connection.close();
    		        }
    		    }
    		}catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    	}
    }
        
    public static void main(String args[]) throws SQLException {
//    	generateCSVCheck("622");
    	generateCSVCheck("622");
//    	for (int i=0; i<args.length; i++) {
//    		System.out.println("Inputs: " +i+ ": " +args[i]);
//    	}
    }
}