package com.capmkts.msrprocess.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.Pricing;
import com.capmkts.msrprocess.data.ServRateSheets;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.util.HibernateUtil;

public class PricingGridDAO {

	public ServRateSheets[] getPricingGrid(int years){
		ServRateSheets[] servRateSheetsArray = new ServRateSheets[12];
		String programId = null;
		if (years == 30)
			programId = "1";		//30 Year Fixed
		else if(years == 20)
			programId = "2";		//20 Year Fixed
		else if (years == 15)
			programId = "3";		//15 Year Fixed
		else
			programId = null;

		Session session = HibernateUtil.getSession();
		List list1 = null;
		List list2 = null;
	
		try{
			Query query = session.createQuery(
					"SELECT noteRate " +
							"FROM ServRateSheets " +
							"WHERE activeTS = (SELECT MAX(activeTS) " +
								"FROM ServRateSheets) " +
							"AND ProgramID = " +programId+ " order by noteRate ");
			Query query2 = session.createQuery(
					"SELECT servPrice " +
							"FROM ServRateSheets " +
							"WHERE activeTS = (SELECT MAX(activeTS) " +
								"FROM ServRateSheets) " +
							"AND ProgramID = " +programId +" order by noteRate ");
			list1 = query.list();
			list2 = query2.list();
			if(!list1.isEmpty()){
//				for (int i=0; i<12; i++){
//					System.out.println("\nList 1: "+i+ ": "+list1.get(i).toString());
//					System.out.println("\nList 2: "+i+ ": "+list2.get(i).toString());
//				}
				if( programId.equals("1")){
					System.out.println("\nList 1: "+list1);
					System.out.println("\nList 2: "+list2);
				}
				BigDecimal noteRateBD, servPriceBD;
				for(int i=0; i<12; i++){
					ServRateSheets servRateSheets = new ServRateSheets();
					noteRateBD = new BigDecimal(list1.get(i).toString());
					servPriceBD = new BigDecimal(list2.get(i).toString());
					servRateSheets.setNoteRate(noteRateBD.setScale(3, noteRateBD.ROUND_HALF_DOWN));
					servRateSheets.setServPrice(servPriceBD.setScale(3, noteRateBD.ROUND_HALF_DOWN));
					servRateSheetsArray[i] = servRateSheets;
				}
//				servRateSheetList.add(servRateSheets);
//				servRateSheetList = servRateSheetList;
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return servRateSheetsArray;
	}
}
