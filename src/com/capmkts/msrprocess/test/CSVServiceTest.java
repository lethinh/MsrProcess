package com.capmkts.msrprocess.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.capmkts.msrprocess.service.ServicingDataCSVFileService;
import com.capmkts.msrprocess.service.ServicingDataDATFileService;
import com.capmkts.msrprocess.validator.DataValidator;
import com.capmkts.msrprocess.validator.ServiceValidateUtility;

/**
 * CSVServiceTest - This is test client for ServicingDataCSVFileService. 
 * @author Rama Yerramilli
 *
 */
public class CSVServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("C:/Caps/Docs/ServicingFile.csv");
		
		ServicingDataCSVFileService csvFileService = new ServicingDataCSVFileService();
		try {
			DataValidator dataValidator = new DataValidator();
			csvFileService.process(file, dataValidator);
			
			if(dataValidator.getMessageList() != null)
			{
				for(String s : dataValidator.getMessageList()){
					System.out.println("DataValidator Message: " + s);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*BigDecimal value = ServiceValidateUtility.getRoundedValue(new BigDecimal("57.17"));		
		System.out.println(" Value is: " + value);*/
		
		/*Date output = ServiceValidateUtility.getFormattedDate(new Date("10/26/2012"));
		System.out.println(" Value is: " + output);
		
		String outputString = ServiceValidateUtility.getFormattedDateString(new Date("10/26/2012"));
		System.out.println(" Value is: " + outputString);*/

	}

}
