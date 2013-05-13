package com.capmkts.msrprocess.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.data.CommitmentData;
import com.capmkts.msrprocess.util.HibernateUtil;

public class AgencyCommitmentLetterDAO {

	public String getCommitmentDate(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		List list = null;
		String commitmentDate = null;
		try{
			Query query = session.createQuery(
					"SELECT commitmentDate FROM AgencyCommitmentLetter " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
			if(list != null){
				commitmentDate = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return commitmentDate;
	}
	
	public int getExtendCount(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		int extendCount = 0;
		List list = null;
		int ExtendCount = 0;
		try{
			Query query = session.createSQLQuery(
					"SELECT ExtendCount FROM AgencyCommitmentLetter_Staging " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
			if(list.get(0) != null){
				if (Integer.parseInt(list.get(0).toString()) == 1){
					ExtendCount = 1;
				}
			}
			else{
				ExtendCount = 0;
			}
//			System.out.println("*******************"+ExtendCount + "***********");
					
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return extendCount;
	}
	
	public String getExpirationDate(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		List list = null;
		String commitmentDate = null;
		try{
			Query query = session.createQuery(
					"SELECT expirationDate FROM AgencyCommitmentLetter " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
			if(list != null){
				commitmentDate = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return commitmentDate;

	}
	
	public String getOrigCommitmentAmt(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		List list = null;
		String commitmentDate = null;
		try{
			Query query = session.createQuery(
					"SELECT origCommitmentAmt FROM AgencyCommitmentLetter " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
			if(list != null){
				commitmentDate = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return commitmentDate;

	}
	
	public List getOrigCommitmentAmtAll(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		List list = null;
		String commitmentDate = null;
		try{
			Query query = session.createQuery(
					"SELECT origCommitmentAmt FROM AgencyCommitmentLetter " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
//			if(list != null){
//				commitmentDate = list.get(0).toString();
//			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}
	
	public String getBalance(String agencyCommitmentID){
		Session session = HibernateUtil.getSession();
		List list = null;
		String commitmentDate = null;
		try{
			Query query = session.createQuery(
					"SELECT balance FROM AgencyCommitmentLetter " +
					"WHERE AgencyCommitmentID = '" +agencyCommitmentID+ "'");
			list = query.list();
			if(list != null){
				commitmentDate = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return commitmentDate;

	}
}
