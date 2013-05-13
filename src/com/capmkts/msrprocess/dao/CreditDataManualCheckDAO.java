package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.CreditDataManualCheck;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CreditDataManualCheckDAO {
	
	public void save(CreditDataManualCheck csvFile) {

		HibernateUtil.save(csvFile);
	}
	
	public void saveAll(List<CreditDataManualCheck> csvFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" CreditDataManualCheckDAO.SAVEALL ");

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
	
	public void updateCreditDataManualCheck(CreditDataManualCheck creditDataManualCheck){
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			//creditDataManualCheck course = (creditDataManualCheck) session.get(CreditDataManualCheck.class, Id);
			
			session.update(creditDataManualCheck);
			
			transaction.commit();
			
		}catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			
			
		}finally{			
			session.close();
		}
		
	}
	
public void deleteCreditDataManualCheck(CreditDataManualCheck creditDataManualCheck){
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			//creditDataManualCheck course = (creditDataManualCheck) session.get(CreditDataManualCheck.class, Id);
			
			session.delete(creditDataManualCheck);
			
			transaction.commit();
			
		}catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
			
			
		}finally{			
			session.close();
		}
		
	}

}
