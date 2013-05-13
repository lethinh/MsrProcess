package com.capmkts.msrprocess.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.data.Pricing;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.util.HibernateUtil;

public class PricingDAO {
	
	public void save(Pricing csvFile) {

		HibernateUtil.save(csvFile);
	}
	
	public void saveAll(List<Pricing> csvFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" PricingDAO.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < csvFileALL.size(); i++) {
				session.save(csvFileALL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
	}
	
	public BigDecimal getSumCurrentPrincipalBalance(int commitmentId){
		
		BigDecimal sumCurrentPrincipalBalance = new BigDecimal("0");
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery("select SUM(CURRENTPRINCIPALBALANCE) as SUMCURRENTPRINCIPALBALANCE from CreditData where commitmentid = " + commitmentId + 
					"GROUP BY COMMITMENTID");
			
			List<Object> list = query.list();
			int count = Integer.parseInt(list.get(0).toString());
			
			if(count > 0){
				sumCurrentPrincipalBalance =  new BigDecimal(list.get(0).toString());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return sumCurrentPrincipalBalance;
	}

	public boolean insertPurchased(String loanNumber, String prinPurchased){
		boolean success = false;
		Session session = HibernateUtil.getSession();
		
		String insertPurchased = "UPDATE Pricing " +
							"SET PURCHASED = '" +1+ "' " +
							"WHERE LOANNUMBER = '" +loanNumber+ "' " +
							"AND ORIGINALMORTGAGEAMOUNT = '" +prinPurchased+ "'";
		System.out.println("\n\nSQL: " +insertPurchased+ "\n\n");
		int rowsAffected = session.createQuery( insertPurchased ).executeUpdate();
		if (rowsAffected > 0){
			success = true;
		}
		session.close();
		return success;
	}
}
