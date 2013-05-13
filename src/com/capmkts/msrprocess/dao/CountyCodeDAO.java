package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class CountyCodeDAO {
	
	public static String getCountyCode(String countyName){
		String countyCode = null;
		Session session = HibernateUtil.getSession();
		try{
			Query query = session.createQuery("SELECT countyCode from CountyCode where CountyName = '" +countyName+ "'");
			List list = query.list();
			if(list != null){
				countyCode = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return countyCode;

	}

}
