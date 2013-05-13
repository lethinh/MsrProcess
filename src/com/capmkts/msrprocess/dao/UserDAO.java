package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class UserDAO {
	
	public static Boolean isValidUser(String userName, String password){
		Boolean flg = false;
		
		Session session = HibernateUtil.getSession();
		
		try{
			
			Query query = session.createQuery("select count(*) from User where userName = :userName and password = :password");
			query.setParameter("userName", userName);
			query.setParameter("password", password);
					
			List<?> list = query.list();
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
