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

public class CommitmentDataDAO {

	public void saveAll(List<CommitmentData> commitmentDataAL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
		//	System.out.println(" CommitmentDataDAO.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < commitmentDataAL.size(); i++) {
					session.save(commitmentDataAL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
	}
	
	// to get total amount of B of I loans
	public BigDecimal getTotalLoanAmountOfBOfI(){
		
		BigDecimal bOfILoanAmount = new BigDecimal("0");
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery("select sum(loanAmount) from CommitmentData where originatorID = "+MsrConstants.BOFI_ORIGINATOR_ID+" and valid = true");
			
			List list = query.list();
			if(list != null && list.size() > 0 && list.get(0) != null){
				bOfILoanAmount = new BigDecimal(list.get(0).toString());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return bOfILoanAmount;
	}

	// to get total amount of B of I loans for CA State
	public BigDecimal getTotalLoanAmountOfBOfICAState(){
		
		BigDecimal bOfILoanAmount = new BigDecimal("0");
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery("select sum(loanAmount) from CommitmentData where originatorID = "+MsrConstants.BOFI_ORIGINATOR_ID+" " +
					"and valid = true and propertyState = 'CA'");
			
			List list = query.list();
			
			if(list != null && list.size() > 0 && list.get(0) != null){
				bOfILoanAmount = new BigDecimal(list.get(0).toString());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return bOfILoanAmount;
	}
	
	public static BigDecimal getTotalLoanAmount(String agencyCommitmentID){
		
		BigDecimal totalLoanAmount = new BigDecimal("0");
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery(
					"select sum(loanAmount) from CommitmentData where agencyCommitmentID = " +agencyCommitmentID);
			
			List list = query.list();
			if(list != null && list.size() > 0 && list.get(0) != null){
				totalLoanAmount = new BigDecimal(list.get(0).toString());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return totalLoanAmount;
	}
	
	public String getAgencyCommitmentID(String sellerLoanNumber){
		
		String agencyCommitmentID = null;
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery(
					"select agencyCommitmentID from CommitmentData where loanNumber = '" +sellerLoanNumber+ "'");
			
			List list = query.list();
			if(!list.isEmpty()){
				agencyCommitmentID = list.get(0).toString();
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return agencyCommitmentID;
	}
}
