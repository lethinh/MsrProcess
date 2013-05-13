package com.capmkts.msrprocess.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.data.CommitmentDataInElgState;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CommimentDataInElgStateDAO {

	public String getInEligibleStates() {

		StringBuffer inElgStates = new StringBuffer();

		Session session = HibernateUtil.getSession();

		try {

			List commitmentDataInElgStateAL = session.createQuery(
					"from CommitmentDataInElgState").list();

			if (commitmentDataInElgStateAL != null) {

				for (Iterator iterator = commitmentDataInElgStateAL.iterator(); iterator.hasNext();) {
				
					CommitmentDataInElgState commitmentDataInElgState = (CommitmentDataInElgState) iterator.next();

					inElgStates.append(commitmentDataInElgState.getState()+ "|");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();	
		} finally {
			session.close();
		}
		//System.out.println(" states " + inElgStates);
		return inElgStates.toString();
	}

}
