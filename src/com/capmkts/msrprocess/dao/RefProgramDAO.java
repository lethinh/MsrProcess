package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.RefProgram;

import com.capmkts.msrprocess.util.HibernateUtil;

public class RefProgramDAO {
	
	public void save(RefProgram refFile) {

		HibernateUtil.save(refFile);
	}
	
	public void saveAll(List<RefProgram> refFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" RefProgramDAO.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < refFileALL.size(); i++) {
				session.save(refFileALL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
	}
	
	public int getLoTypeByLoanProgramId(int loanProgramId) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		int lc_LOType = 0;
		
		try {
			System.out.println(" RefProgramDAO.getLoTypeByLoanProgramId ");

			Query query = session.createQuery("select LC_LOType from RefProgram where loanprogramid = "+loanProgramId);
			
			List<Object> list = query.list();
			int count = Integer.parseInt(list.get(0).toString());
			
			if(count > 0){
				Integer loType = (Integer)list.get(0);
				lc_LOType = loType.intValue();
			}

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
		
		return lc_LOType;
	}

}
