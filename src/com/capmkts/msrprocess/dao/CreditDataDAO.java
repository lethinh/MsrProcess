package com.capmkts.msrprocess.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.CMCFile;
import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CreditDataDAO {

	public void save(CreditData csvFile) {

		HibernateUtil.save(csvFile);
	}

	/**
	 * Insert new record to CMCFiles table. If already record present with
	 * LoanNumber and AgencyCommitmentId --> delete the existing record and
	 * insert new one.
	 * 
	 * @param csvFile
	 * @param uploadType
	 * @param valid
	 * @param inValidMessage
	 * @param loanNumber
	 * @param agencyCommitmentId
	 */
	public void saveFile(File csvFile, String uploadType, boolean valid,
			String inValidMessage, String loanNumber, int agencyCommitmentId) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;

		try {

			CMCFile cmcFileExists = new CMCFile();

		
			if (loanNumber != null) {
				cmcFileExists.setLoanNumber(loanNumber);
			}

			cmcFileExists.setAgencyCommitmentNumber(new Integer(agencyCommitmentId));

			// Make sure that there is no record exists with same loan number
			// and
			// agencycommitment id.
			// if record present, delete it and insert new one.

			boolean isExists = checkCMCFileByLoanNumberAgencyCommitmentId(
					loanNumber, agencyCommitmentId);
			txn = session.beginTransaction();

			if (isExists) {
				String hqlDelete = "delete CMCFile c where c.loanNumber= :loanNumber";

				int deletedEntities = session.createQuery( hqlDelete )
				        .setString( "loanNumber", loanNumber)
				        .executeUpdate();
			}
			
			CMCFile cmcFile = new CMCFile();

			
			if (loanNumber != null) {
				cmcFile.setLoanNumber(loanNumber);
			}

			cmcFile.setAgencyCommitmentNumber(new Integer(agencyCommitmentId));


			cmcFile.setFileData(FileUtil.getBytes(csvFile));
			cmcFile.setFileName(csvFile.getName());
			cmcFile.setFileType(uploadType);
			cmcFile.setValid(valid);

			if (!cmcFile.getValid()) {
				cmcFile.setInvalidMessage(inValidMessage);
			}
			cmcFile.setCreatedDate(new Date());

			session.save(cmcFile);

			txn.commit();
			session.flush();
		} catch (Exception ex) {
			txn.rollback();

		} finally {
			session.close();
		}
	}

	public void saveAll(List<CreditData> csvFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" CreditDataDAO.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < csvFileALL.size(); i++) {
				session.save(csvFileALL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();

		} finally {
			session.close();
		}
	}

	public CreditData getCreditDataByLoanNumber(String loanNumber) {

		CreditData creditData = null;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select * from CreditData where loannumber = "
							+ loanNumber);

			List<Object> list = query.list();
			int count = Integer.parseInt(list.get(0).toString());

			if (count > 0) {
				creditData = (CreditData) list.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return creditData;
	}

	public boolean checkCMCFileByLoanNumberAgencyCommitmentId(
			String loanNumber, int agencyCommitmentId) {

		boolean isExists = false;

		Session session = HibernateUtil.getSession();

		try {

			String sqlQuery = "select fileName from CMCFile where LoanNumber ='"
					+ loanNumber
					+ "' and AgencyCommitmentNumber="
					+ agencyCommitmentId;

			System.out.println("sqlQuery " + sqlQuery);

			Query query = session.createQuery(sqlQuery);

			List<Object> list = query.list();

			if (list != null) {

				if (list.size() > 0) {
					isExists = true;
				}
			}

			System.out
					.println("***checkCMCFileByLoanNumberAgencyCommitmentId***"
							+ isExists);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return isExists;
	}

	public BigDecimal getSumCurrentPrincipalBalance(int commitmentId) {

		BigDecimal sumCurrentPrincipalBalance = new BigDecimal("0");

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select SUM(CURRENTPRINCIPALBALANCE) as SUMCURRENTPRINCIPALBALANCE from CreditData where commitmentid = "
							+ commitmentId + "GROUP BY COMMITMENTID");

			List<Object> list = query.list();
			
			int count = 0;

			if (list != null && list.size() > 0) {
				count = Integer.parseInt(list.get(0).toString());
			}
			if (count > 0) {
				sumCurrentPrincipalBalance = new BigDecimal(list.get(0)
						.toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return sumCurrentPrincipalBalance;
	}
	
	public boolean insertEncompassGUID(int originatorID, String loanNumber, String GUID){
		boolean success = false;
		Session session = HibernateUtil.getSession();
		
		String insertGUID = "UPDATE [CoIssue_Test].[dbo].[CreditData] " +
							"SET GUID = ('" +GUID+ "')" +
							"WHERE ORIGINATORID = '" +originatorID+ "'" +
							"AND LOANNUMBER = '" +loanNumber+ "'";

		int rowsAffected = session.createQuery( insertGUID ).executeUpdate();
		if (rowsAffected > 0){
			success = true;
		}
		
		return success;
	}
	
	public String getGUID(String loanNumber) {

		String GUID = null;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select GUID from CreditData where loannumber = '"
							+loanNumber+ "'");

			List<Object> list = query.list();

			if (!list.isEmpty()) {
				GUID = (String)list.get(0).toString();
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return GUID;
	}
	
	public boolean insertPurchased(String loanNumber, String prinPurchased){
		boolean success = false;
		Session session = HibernateUtil.getSession();
		
		String insertPurchased = "UPDATE CreditData " +
							"SET PURCHASED = '" +1+ "' " +
							"WHERE LOANNUMBER = '" +loanNumber+ "' " +
							"AND CURRENTPRINCIPALBALANCE = '" +prinPurchased+ "'";
		System.out.println("\n\nSQL: " +insertPurchased+ "\n\n");
		int rowsAffected = session.createQuery( insertPurchased ).executeUpdate();
		if (rowsAffected > 0){
			success = true;
		}
		session.close();
		return success;
	}
	
	public String getPurchased(String loanNumber) {

		String purchased = null;
		Session session = HibernateUtil.getSession();
		try {
			Query query = session
					.createQuery("select PURCHASED from CreditData where loannumber = '"
							+loanNumber+ "'");
			List<Object> list = query.list();

			if (!list.isEmpty()) {
				purchased = (String)list.get(0).toString();
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return purchased;
	}

}
