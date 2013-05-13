package com.capmkts.msrprocess.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class DataMapper {
	public static void main(String[] args) throws IOException {

		File filee = new File(
//				"C:\\Users\\tle\\Dropbox\\Capmkts\\TestingData\\ANM\\ServicingTest.csv");
//				"C:\\Users\\tle\\Dropbox\\Capmkts\\TestingData\\FNMA Commit Letter Test Data\\Testing\\Testing\\672\\672.csv");
				"K:\\ServicingAcquisition\\KES\\Testing\\672\\672.csv");
		 String test = mapfile(filee);
		 
		 System.out.println(test);
	}

	public static String mapfile(File file1) throws IOException {

		DataMapper csvmapper = new DataMapper();
		
		// Read file to string
		String fileContent1 = FileUtils.readFileToString(file1);

		// Split string into records
		String[] recordArray = fileContent1.split("\\n");

		// Read header
		String[] headerArray = recordArray[0].split("\\,");
		
		
//		//Temporarily remove these three headers
//		for (int n=0; n<headerArray.length; n++){
//			if (headerArray[n].equalsIgnoreCase("LATECHARGECODE"))
//				headerArray[n] = null;
//			else if (headerArray[n].equalsIgnoreCase("LATECHARGEFACTOR"))
//				headerArray[n] = null;
//			else if (headerArray[n].equalsIgnoreCase("CMCLOANNUMBER"))
//				headerArray[n] = null;
//		}
//		List<String> list = new ArrayList<String>();
//	    for(String s : headerArray) {
//	       if(s != null && s.length() > 0) {
//	          list.add(s);
//	       }
//	    }
//	    headerArray = list.toArray(new String[list.size()]);

		headerArray[headerArray.length - 1] = headerArray[headerArray.length - 1].trim();

		String[] itemsArray = { "ORIGINATORID", "COMMITMENTID",
				"AGENCYCOMMITMENTID", "LOANNUMBER", "BRANCHID",
				"LOANOFFICERID", "BORROWERFIRSTNAME", "BORROWERMIDDLEINITIAL",
				"BORROWERLASTNAME", "BORROWERSSN", "BORROWERCREDITSCORE",
				"BORROWERCELLPHONENUMBER", "BORROWEREMAILADDRESS",
				"BORROWER2FIRSTNAME", "BORROWER2MIDDLEINITIAL",
				"BORROWER2LASTNAME", "BORROWER2SSN", "BORROWER2CREDITSCORE",
				"BORROWER2CELLPHONENUMBER", "BORROWER2EMAILADDRESS",
				"BORROWER3FIRSTNAME", "BORROWER3MIDDLEINITIAL",
				"BORROWER3LASTNAME", "BORROWER3SSN", "BORROWER3CREDITSCORE",
				"BORROWER3CELLPHONENUMBER", "BORROWER3EMAILADDRESS",
				"BORROWER4FIRSTNAME", "BORROWER4MIDDLEINITIAL",
				"BORROWER4LASTNAME", "BORROWER4SSN", "BORROWER4CREDITSCORE",
				"BORROWER4CELLPHONENUMBER", "BORROWER4EMAILADDRESS",
				"BORROWER5FIRSTNAME", "BORROWER5MIDDLEINITIAL",
				"BORROWER5LASTNAME", "BORROWER5SSN", "BORROWER5CREDITSCORE",
				"BORROWER5CELLPHONENUMBER", "BORROWER5EMAILADDRESS",
				"ORIGINALMORTGAGEAMOUNT", "ANNUALINTERESTRATE",
				"LOANPROGRAMID", "PROPERTYTYPE", "LOANPURPOSE", "LTV", "CLTV",
				"DTI", "OCCUPYCODE", "LOANTERM", "FUNDSSOURCE", "PIPAYMENT",
				"ESCROWMONTHLYPAYMENT", "TOTALPAYMENT",
				"CURRENTPRINCIPALBALANCE", "FIRSTDUEDATE", "CURRENTDUEDATE",
				"MATURITYDATE", "PREPAYPENALTYFLAG",
				"PREPAYPENALTYDESCRIPTION", "INTERESTONLYFLAG",
				"INTERESTONLYEXPIRATIONDATE", "INTERESTONLYPERIOD",
				"ESCROWBALANCE", "ARMPLAN", "ARMINDEX", "ARMMARGIN",
				"ARMORIGINALIRCHANGEDATE", "ARMORIGINALPICHANGEDATE",
				"ARMNEXIRCHANGEDATE", "ARMNEXPICHANGEDATE",
				"ARMIRCHANGEPERIOD", "ARMPICHANGEPERIOD",
				"FLOODINSMONTHLYPAYMENT", "HAZARDINSMONTHLYPAYMENT",
				"COUNTYMONTHLYPAYMENT", "CITYMONTHLYPAYMENT",
				"LIENMONTHLYPAYMENT", "PMIMONTHLYPAYMENT", "CLOSINGDATE",
				"PROPERTYSTREETADDRESS", "PROPERTYCITY", "PROPERTYSTATE",
				"PROPERTYZIPPLUS4", "PROPERTYCOUNTY", "MAILINGSTREETADDRESS",
				"MAILINGCITY", "MAILINGSTATE", "MAILINGZIPPLUS4",
				"TELEPHONENUMBER", "SECONDTELEPHONENUMBER",
				"ORIGINALAPPRAISEDVALUE", "ORIGINALAPPRAISEDDATE",
				"CURRENTAPPRAISEDVALUE", "CURRENTAPPRAISEDDATE",
				"MERSMINNUMBER", "MERSREGISTRATIONDATE", "MERSREGFLAG",
				"MERSMOMINDICATOR", "INTERESTCOLLECTEDATCLOSING",
				"POINTSPAIDBYBORROWER", "BILLMODE", "ESCROWCUSHION",
				"TAXSERVICECOMPANY", "TAXSERVICECONTRACTNUMBER",
				"SERVICEFEERATE", "GRACEDAYS", "PURCHASEPRICE", "CENSUSTRACT",
				"NUMBEROFUNITS", "LEGALDESCRIPTION", "BALLOONTERM",
				"ROUNDINGFACTOR", "LOOKBACKPERIOD", "MAXCEILINGPERCENTAGE",
				"MAXFLOORPERCENTAGE", "IRCAPATFIRSTCHANGE", "NEXTCAPPERCHANGE",
				"LIFEPAYEE", "LIFECERTNUMBER", "LIFEDISBAMT", "LIFEDUEDATE",
				"DISBPAYEE", "DISBCERTNUMBER", "DISBDISBAMT", "DISBDUEDATE",
				"FLOODREQUIREDFLAG", "FLOODPROGRAM", "FLOODDETERMINATIONDATE",
				"FLOODCOMMUNITYNUMBER", "FLOODZONE", "FLOODFIRMDATE",
				"FLOODCERTNUMBER", "FLOODCMPCO", "FLOODMAPCO",
				"PMIMIPGUARCERTNO", "PMIMIPPERCENTOFCOVERAGE",
				"PMIRATEPERCENTAGE", "PMIMIPPAYEE",
				"PMIMIPDISBURSEMENTDUEDATE", "PMIMIPDISBURSEMENTAMOUNT",
				"PMIMIPBILLCODE", "PMIMIPANNUALPREMIUM",
				"PMIMIPEXPIRATIONDATE", "FNMAEXPANDEDAPPROVALCODE",
				"FNMAFLEX97CODE", "FHACASENUMBER", "FHAADPCODE", "VANUMBER",
				"HELOCPLAN", "HELOCFLAG", "HELOCDRAWAMOUNT", "HELOCDRAWDATE",
				"HELOCDUEDATE", "HELOCRATEMARGIN", "HELOCCEILING",
				"HELOCPROCESSSTOP", "DISTTYPE", "REPLACEMENTRESERVE",
				"PERIODMONTHS", "FIRSTSUBSIDYDUEDATE", "COOPINDICATOR",
				"HIGHPRICELOANINDICATOR", "LATECHARGECODE", "LATECHARGEFACTOR",
				"CMCLOANNUMBER", "VAFUNDINGFEE", "BASEBUYPRICE",
				"ADJUSTMENT DESCRIPTION 1", "ADJUSTMENT 1",
				"ADJUSTMENT DESCRIPTION 2", "ADJUSTMENT 2",
				"ADJUSTMENT DESCRIPTION 3", "ADJUSTMENT 3",
				"ADJUSTMENT DESCRIPTION 4", "ADJUSTMENT 4",
				"ADJUSTMENT DESCRIPTION 5", "ADJUSTMENT 5",
				"ADJUSTMENT DESCRIPTION 6", "ADJUSTMENT 6",
				"ADJUSTMENT DESCRIPTION 7", "ADJUSTMENT 7",
				"ADJUSTMENT DESCRIPTION 8", "ADJUSTMENT 8",
				"ADJUSTMENT DESCRIPTION 9", "ADJUSTMENT 9", "NETBUYPRICE" 
		};
		String mainHeader = "";
		for (int k=0; k<itemsArray.length; k++){
			mainHeader += itemsArray[k];
			if (itemsArray[k].equals("NETBUYPRICE")){
				break;
			}
			else {
				mainHeader += ",";
			}
		}
		System.out.println("TEST: " + mainHeader);
		
		int l = itemsArray.length;
		System.out.println("lenght is" + l);
		LinkedHashMap myhashmap = new LinkedHashMap();

		for (int i = 0; i < itemsArray.length; i++) {
			myhashmap.put(itemsArray[i], "");
		}

		String buff="";
		String my="";
		String my2="";
		String buff1="";
		String[] temp = new String[myhashmap.size()];
		String[] my1 = new String[300];
		StringBuilder strBldr = new StringBuilder(300);
		
		try {
			CsvReader products = new CsvReader(file1.getAbsolutePath());

			products.readHeaders();
			while (products.readRecord()) {
				for (int i = 0; i < headerArray.length; i++) {
					String column = products.get(headerArray[i]);
					for (int j = 0; j < itemsArray.length; j++) {
						if (headerArray[i].equalsIgnoreCase(itemsArray[j])) {
							System.out.println(headerArray[i] + " : "+ itemsArray[j]);
							myhashmap.put(headerArray[i], column);
						}
					}
				}
				Set s = myhashmap.entrySet();
				for (Iterator i = s.iterator(); i.hasNext();) {
					Map.Entry	me = (Map.Entry) i.next();
					
						my = (String) me.getValue();
						if (my.contains(",")){
							my = "\"" +my+ "\"";
						}
						if (my.equalsIgnoreCase("n")){
							my = "FALSE";
						}
						if (my.equalsIgnoreCase("y")){
							my = "TRUE";
						}
						  System.out.println(my);
				         buff = buff +my+",";
				} 
				buff += "\n";
			}
			products.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(buff);
		
		return mainHeader += "\n" + buff.substring(0, buff.length()-1);
	}

}