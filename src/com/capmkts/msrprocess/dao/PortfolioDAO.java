package com.capmkts.msrprocess.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class PortfolioDAO {
	
	public static List getPortfolio(String lenderName){
		
		Session session = HibernateUtil.getSession();
		List list = null;
		try{
			
			Query query = session.createQuery(
					"SELECT CommitmentID, SUM(LoanAmount)as TotalLoanAmout " +
					"FROM [CoIssue_Test].[dbo].[CommitmentData] " +
					"WHERE OriginatorID = (" +
					"SELECT OriginatorID from CoIssue_Test.dbo.PatronCo " +
					"WHERE PatronCoName = :lenderName) " +
					"GROUP BY OriginatorID, CommitmentID " +
					"ORDER BY CommitmentID; ");
//					"select count(*) from Holiday where holidayDate = :compareDate");
			query.setParameter("lenderName", lenderName);
			
			list = query.list();
			
//			System.out.println(list);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return list;

	}

}
