package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.bean.Portfolio;
import com.capmkts.msrprocess.util.HibernateUtil;

public class ExtensionRequestDAO {
	public void extensionRequest(Portfolio portfolio, int extensionDaysCount, int extendCount) {

		Session session = HibernateUtil.getSession();

		try {
			String sqlQuery = "UPDATE a SET a.ExpirationDate = DATEADD(d, "
					+ extensionDaysCount
					+ ",  '"
					+ portfolio.getExpirationDate()
					+ "') , a.ExtendCount = "+ extendCount +"  FROM [CoIssue_Test].[dbo].[AgencyCommitmentLetter_Staging] AS a INNER JOIN [CoIssue_Test].[dbo].[CMC_Agency_CommitNumber] AS b  ON a.AgencyCommitmentId = b.[Agency_Commit_Number] WHERE b.[CMC_Commit_Number] = '"
					+ portfolio.getCMCCommitmentNumber() + "'";

			System.out.println("sqlQuery " + sqlQuery);

			Query query = session.createSQLQuery(sqlQuery);

			query.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
	}
}
