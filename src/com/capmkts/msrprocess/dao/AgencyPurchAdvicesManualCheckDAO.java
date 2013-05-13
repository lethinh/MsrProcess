package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.AgencyPurchAdvices;
import com.capmkts.msrprocess.data.AgencyPurchAdvicesManualCheck;
import com.capmkts.msrprocess.util.HibernateUtil;

public class AgencyPurchAdvicesManualCheckDAO {

	public void save(AgencyPurchAdvicesManualCheck datFile) {

		HibernateUtil.save(datFile);
	}
	
	public void saveAll(List<AgencyPurchAdvicesManualCheck> datFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" AgencyPurchAdvicesManualCheck.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < datFileALL.size(); i++) {
				session.save(datFileALL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
	}
	
}
