package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class CommitmentDataSummaryDAO {
	
	public static List getCommitmentID(String originatorID){
		Session session = HibernateUtil.getSession();
		List list = null;
		try{
			Query query = session.createQuery("SELECT commitmentID "+
												"FROM CommitmentDataSummary " +
												"WHERE OriginatorID = '" +originatorID+ "'");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}
	
	public static List getRequestedUPB(String originatorID){
		Session session = HibernateUtil.getSession();
		List list = null;
		try{
			Query query = session.createQuery("SELECT requestedUPB "+
												"FROM CommitmentDataSummary " +
												"WHERE OriginatorID = '" +originatorID+ "'");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}
	
	public static List getDeliveredUPB(String originatorID){
		Session session = HibernateUtil.getSession();
		List list = null;
		try{
			Query query = session.createQuery("SELECT deliveredUPB "+
												"FROM CommitmentDataSummary " +
												"WHERE OriginatorID = '" +originatorID+ "'");
			list = query.list();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}

}
