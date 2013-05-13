package com.capmkts.msrprocess.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import com.capmkts.bulkupload.client.BulkUploadServiceClient;
import com.capmkts.bulkupload.client.BulkUploadServiceClientJob;
import com.capmkts.bulkupload.client.Channel;
import com.capmkts.bulkupload.client.SSIServiceSoapProxy;
import com.capmkts.bulkupload.client.SSIServiceSoapStub;
import com.capmkts.encompassservice.Client.ExportDocumentsToEncompass;

public class BulkUploadServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
//			String filePath = "C:\\workspace\\testimport11.csv";
			String filePath = "\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EncompassUpload\\333740.csv";
			
			BulkUploadServiceClientJob bulkUploadClient = new BulkUploadServiceClientJob();
			
			boolean isUpload = bulkUploadClient.uploadCSVToBulkUploadService(filePath);
//			bulkUploadClient.schedulerJobTest();
			
			System.out.println("*********** result is ******* " );

		} catch (Exception ex) {
			ex.printStackTrace();
		}


	}

}
