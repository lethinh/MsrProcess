package com.capmkts.msrprocess.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class HolidayDAO {
	
	public static Boolean isHoliday(Date dateValue){
		Boolean flg = false;
		
		Session session = HibernateUtil.getSession();
		
		try{
			
			Query query = session.createQuery("select count(*) from Holiday where holidayDate = :compareDate");
			query.setParameter("compareDate", dateValue);
			
			List list = query.list();
			int count = Integer.parseInt(list.get(0).toString());
			
			if(count > 0){
				flg = true;
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return flg;

	}

}
