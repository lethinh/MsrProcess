package com.capmkts.msrprocess.test;

import java.io.File;
import java.math.BigDecimal;

import java.rmi.RemoteException;
import java.sql.Date;

import java.net.URL;

import javax.xml.rpc.Service;

import com.capmkts.encompassservice.Client.EncompassService;
import com.capmkts.encompassservice.Client.EncompassServiceSoap;
import com.capmkts.encompassservice.Client.EncompassServiceSoapProxy;
import com.capmkts.encompassservice.Client.EncompassServiceSoapStub;
import com.capmkts.encompassservice.Client.ExportDocumentsToEncompass;
import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CMCMsrCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;
import com.capmkts.msrprocess.generator.CMCMSRCommitmentLetterPDF;
import com.capmkts.msrprocess.util.FileUtil;

public class EncompassServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String loanNumber = "{4b126426-9812-4aeb-8fd7-714c06da8723}";
			String filePath = "C:\\CommitmentLetter.pdf";

			ExportDocumentsToEncompass exportDocumentsToEncompass = new ExportDocumentsToEncompass();
			boolean result = exportDocumentsToEncompass.exportDocumentsToEncompass(loanNumber, filePath);
			
			System.out.println("*********** result is ******* " + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
