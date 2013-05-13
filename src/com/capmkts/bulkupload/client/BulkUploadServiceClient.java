package com.capmkts.bulkupload.client;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

public class BulkUploadServiceClient {
	public boolean uploadCSVToBulkUploadService(String filePath) {

		boolean isUpload = false;
		try {
			FileInputStream fisTargetFile = new FileInputStream(new File(filePath));

			String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");

			SSIServiceSoapProxy proxy = new SSIServiceSoapProxy();

			String[] resultArray = proxy.processImport(targetFileStr,Channel.Delegated, "Agency");

			if (resultArray != null && resultArray.length > 0) {
				System.out.println("*********** result is ******* \n" + resultArray[0]);
				isUpload = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isUpload;

	}

}
