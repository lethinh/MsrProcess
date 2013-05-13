package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CMCAgencyCommitNumberDAO {
	
	public List getCMCCommitNumbers(){
		Session session = HibernateUtil.getSession();
		List list = null;
		String CMCCommitNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT cmcCommitNumber FROM CMCAgencyCommitmentNumber " +
					"WHERE cmcCommitNumber IS NOT NULL");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public List getCMCCommitNumbersOpenStatus(){
		Session session = HibernateUtil.getSession();
		List list = null;
		String CMCCommitNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT cmcCommitNumber FROM CMCAgencyCommitmentNumber " +
					"WHERE cmcCommitNumber IS NOT NULL " +
					"AND cmcCommitStatus = 'OPEN'");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public List getAgencyCommitNumbers(){
		Session session = HibernateUtil.getSession();
		List list = null;
		String CMCCommitNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT agencyCommitNumber FROM CMCAgencyCommitmentNumber " +
					"WHERE agencyCommitNumber IS NOT NULL");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	public String getCMCCommitStatus(String cmcCommitNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
		String CMCCommitStatus = null;
		try{
			Query query = session.createQuery(
					"SELECT cmcCommitStatus FROM CMCAgencyCommitmentNumber " +
					"WHERE cmcCommitNumber = '" +cmcCommitNumber+ "'");
			list = query.list();
			if(list != null){
				CMCCommitStatus = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return CMCCommitStatus;

	}
	
	public String getAgencyCommitNumber(String cmcCommitNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
		String CMCCommitList = null;
		try{
			Query query = session.createQuery(
					"SELECT agencyCommitNumber FROM CMCAgencyCommitmentNumber " +
					"WHERE cmcCommitNumber = '" +cmcCommitNumber+ "'");
			list = query.list();
			if(list != null){
				CMCCommitList = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return CMCCommitList;
	}
	
	public List getAgencyCommitNumberAll(String cmcCommitNumber){
		Session session = HibernateUtil.getSession();
		List list = null;

		try{
			Query query = session.createQuery(
					"SELECT agencyCommitNumber FROM CMCAgencyCommitmentNumber " +
					"WHERE cmcCommitNumber = '" +cmcCommitNumber+ "'");
			list = query.list();

		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	
	public String getCommitNumberType(String number){
		String type = null;
			if (number.length() > 5){
				type = "agencyCommitNumber";
			}
			else if (number.length() < 6){
				type = "cmcCommitNumber";
			}
			else{
				type = "unknown";
			}
		return type;
	}
}


