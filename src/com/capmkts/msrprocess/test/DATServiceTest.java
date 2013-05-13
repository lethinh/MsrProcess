package com.capmkts.msrprocess.test;

import java.io.File;

import com.capmkts.msrprocess.service.ServicingDataCSVFileService;
import com.capmkts.msrprocess.service.ServicingDataDATFileService;
import com.capmkts.msrprocess.validator.DataValidator;

/**
 * DATServiceTest - This is test client for ServicingDataDATFileService. 
 * @author Rama Yerramilli
 *
 */
public class DATServiceTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("C:/Caps/Docs/Sample FNMA PA.dat");
		
		ServicingDataDATFileService datFileService = new ServicingDataDATFileService();
		try {
			DataValidator dataValidator = new DataValidator();
			datFileService.process(file, dataValidator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
