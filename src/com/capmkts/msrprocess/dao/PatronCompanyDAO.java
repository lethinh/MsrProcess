package com.capmkts.msrprocess.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.data.PatronCompany;
import com.capmkts.msrprocess.util.HibernateUtil;

public class PatronCompanyDAO {
	
	public Map<String, PatronCompany> getPatronCapAmount(){
		
		Map<String, PatronCompany> patronCapAmount = new HashMap<String, PatronCompany>();
		
		Session session = HibernateUtil.getSession();
		
		try{

			List patronCompanyAL = session.createQuery("from PatronCompany where capAmount is not null").list();

//			System.out.println(" patronCompanyAL Size >> "+patronCompanyAL.size());
//			System.out.println(" patronCompanyAL List >> "+patronCompanyAL);
			
			for (Iterator iterator = patronCompanyAL.iterator(); iterator.hasNext();) {
				PatronCompany patronCompany = (PatronCompany) iterator.next();
				patronCapAmount.put(patronCompany.getPatronCoName(), patronCompany);	
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
	
		return patronCapAmount;
	}

	public PatronCompany getPatronCompany(Integer patronCompanyID){
		
		PatronCompany patronCompany = null;
		
		Session session = HibernateUtil.getSession();
		
		try{

			List patronCompanyAL = session.createQuery("from PatronCompany where originatorID = "+patronCompanyID).list();

			if(patronCompanyAL != null){
				patronCompany = (PatronCompany)patronCompanyAL.get(0);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
	
		return patronCompany;
	}
	
public String getPatronCompanyByLenderNum(Integer lenderNum){
		
		String patronName = "";
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery("select patronCoName from PatronCompany where lenderNum = '" +lenderNum+ "'");
			
			List list = query.list();
			patronName = list.get(0).toString();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
	
		return patronName;
	}

	public PatronCompany getPatronCompany(String patronCompanyName){
		
		PatronCompany patronCompany = null;
		
		Session session = HibernateUtil.getSession();
		
		try{

			List patronCompanyAL = session.createQuery("from PatronCompany where patronCoName = '"+patronCompanyName+"'").list();

			if(patronCompanyAL != null){
				patronCompany = (PatronCompany)patronCompanyAL.get(0);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
	
		return patronCompany;
	}

}
