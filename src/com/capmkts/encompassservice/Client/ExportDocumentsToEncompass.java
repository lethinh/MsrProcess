package com.capmkts.encompassservice.Client;

import java.io.File;

import com.capmkts.msrprocess.util.FileUtil;

public class ExportDocumentsToEncompass {

	public boolean exportDocumentsToEncompass(String loanNumber, String filePath) {
		
		boolean isUpload = false;
		
		try {

//			String url = "http://localhost:49436/EncompassService.asmx?wsdl";
//			String url = "http://192.168.24.11/EncompassService/EncompassService.asmx";		//CMC
			String url = "https://www.cmcfunding.com/EncompassService/EncompassService.asmx";
			
			EncompassServiceSoapProxy proxy = new EncompassServiceSoapProxy(url);

//			String loanNumber = "{15dcf07f-83b5-44a3-baea-ca895e7041c6}";
//			String filePath = "C:\\Docs\\Encompass360SDKProgrammersGuide.pdf";
//			String filePath = "C:\\E360Test.pdf";
			File file = new File(filePath);

			FileUtil fileUtil = new FileUtil();
			byte[] fileByteArray = fileUtil.getBytes(file);

			isUpload = proxy.exportDocumentsToEncompass(loanNumber, fileByteArray);

			System.out.println("*********** result is ******* \n" + isUpload);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return isUpload;
	}

}
