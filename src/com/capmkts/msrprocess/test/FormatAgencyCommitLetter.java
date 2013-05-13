package com.capmkts.msrprocess.test;

import java.io.File;

import com.capmkts.msrprocess.data.AgencyCommitmentLetter;

public class FormatAgencyCommitLetter {
	private static String formatFile(File file) throws Exception {
		   AgencyCommitmentLetter agencyCommitmentLetter = new AgencyCommitmentLetter();
//		   agencyCommitmentLetter = readCSVFile2(file);
		   String formattedFile = "\"Lender #:\",\"" +"Test";						//Obj1
		   formattedFile += "\",\"Lender Name:\",\"" + "Test2" +"\"\n";				//Obj2
		   formattedFile += "\"Fannie Mae's Commitment ID:\",\"" + "Test3";
		   formattedFile += "\",\"Lender's Commitment ID:\",\"" + "Test4"+ "\"\n";
		   formattedFile += "\"Source:\",\"" +"Test5";
		   formattedFile += "\",\"Commitment Date:\",\"" +"Test6"+ "\"\n";
		   formattedFile += "\"Contact:\",\"" + "Test7";
		   formattedFile += "\",\"Expiration Date:\",\"" +"Test8"+ "\"\n";
		   formattedFile += "\"Delivery:\"\n";
		   formattedFile += "\"Original Commitment Amount:\",\"" + "Test9";
		   formattedFile += "\",\"Low Tolerance Amount:\",\"" +"Test10"+ "\"\n";
		   formattedFile += "\"Current Commitment Amount:\",\"" + "Test9";
		   formattedFile += "\",\"High Tolerance Amount:\",\"" +"Test10"+ "\"\n";    
		   formattedFile += "\"Purchased Amount:\",\"" + "Test9";
		   formattedFile += "\",\"Maximum Over-delivery Amount:\",\"" +"Test10"+ "\"\n";  
		   formattedFile += "\"Pending Purchase:\",\"" + "Test9";
		   formattedFile += "\",\"HBL Cap%:\",\"" +"Test10"+ "\"\n";
		   formattedFile += "\"Balance:\",\"" + "Test9";
		   formattedFile += "\",\"Maximum Allowable HBL Delivery:\",\"" +"Test10"+ "\"\n";
		   formattedFile += "\"Pass-Through Rate:\",\",\"Price\",\"Net Yield\"";
		   formattedFile += "\",\"HBL Purchased Amount:\",\"" +"Test10"+ "\"\n";		   
		   formattedFile += "\""+"Test"+"\",\"" + "Test" +"\",\""+ "Test" +"\"\n";
		   formattedFile += "\""+"Test"+"\",\"" + "Test" +"\",\""+ "Test" +"\"\n";
		   formattedFile += "\""+"Test"+"\",\"" + "Test" +"\",\""+ "Test" +"\"\n";
		   formattedFile += "\""+"Test"+"\",\"" + "Test" +"\",\""+ "Test" +"\"\n";
		   formattedFile += "\""+"Test"+"\",\"" + "Test" +"\",\""+ "Test" +"\"\n";
		   formattedFile += "\"Commitment Details:\"\n";
		   formattedFile += "\"Master Agreement ID:\",\"" + "Test9" +"\"\n";
		   formattedFile += "\"Commitment Period:\",\"" + "Test9";
		   formattedFile += "\",\"Base Servicing Fee:\",\"" +"Test10"+ "\",\"BPS\"\n";
		   formattedFile += "\"Pricing Option:\",\"" + "Test9";
		   formattedFile += "\",\"LPMI:\",\"" +"Test10"+ "\"\n";
		   formattedFile += "\"Minimum Pass-through Rate:\",\"" + "Test9";
		   formattedFile += "\",\"Total Servicing Fee:\",\"" +"Test10"+ "\",\"BPS\"\n";
		   formattedFile += "\"Required Net Yield:\",\"" + "Test9" +"\"\n";
		   formattedFile += "\"LLPA Applicable:\",\"" + "Test9" +"\"\n"; 
		   formattedFile += "\"Product Description:\"\n";
		   formattedFile += "\"Product Name:\",\"" + "Test9" +"\"\n";
		   formattedFile += "\"Product Favorite Name:\",\"" + "Test9" +"\"\n";
		   
		   formattedFile += "\"Amortization Type:\",\"" + "Test9" +"\",";
		   formattedFile += "\"Amortization Term:\",\"" + "Test9" +"\"\n";
		   formattedFile += "\"Loan Type:\",\"" + "Test9" +"\",";
		   formattedFile += "\"Loan Term:\",\"" + "Test9" +"\"\n";
		   formattedFile += "\"Lien Type:\",\"" + "Test9" +"\",";
		   formattedFile += "\"Remittance Type:\",\"" + "Test9" +"\"\n";
		   
		   System.out.println(formattedFile);
		return formattedFile;
	}
	public static void main(String args[]) throws Exception{

		File file = new File(
				"C:\\Users\\tle\\Dropbox\\Capmkts\\TestingData\\ANM\\333740.csv");
		
		String x = formatFile(file);
		System.out.println(x);
	}
}
