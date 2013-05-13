package com.capmkts.msrprocess.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.util.DateUtil;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CommitmentLetterDAO {

	public void save(AgencyCommitmentLetter agencyCommitmentLetter) {

		HibernateUtil.save(agencyCommitmentLetter);
	}

	// Check for existing of a commitment number for agency commitment number
	public Boolean isCommitmentLetterExists(Integer agencyCommitmentID) {

		Boolean commitmentLetterExists = false;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select count(*) from AgencyCommitmentLetter where agencyCommitmentID = "
							+ agencyCommitmentID);

			List<Object> list = query.list();
			int count = Integer.parseInt(list.get(0).toString());

			if (count > 0) {
				commitmentLetterExists = true;
			}

		} catch (Exception ex) {

		} finally {
			session.close();
		}

		return commitmentLetterExists;
	}

	// to get total original commitment amount of a Patron for a given month
	public BigDecimal getPatronCompanyTotalAmount(Date targetFundDate,
			int lenderNum) {

		BigDecimal patronCompanyTotalLoanAmount = new BigDecimal("0");

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select sum(origCommitmentAmt) from AgencyCommitmentLetter where lenderNum = :lenderNum and "
							+ "(targetFundDate >= :startDate and targetFundDate <= :endDate)");

			query.setParameter("lenderNum", lenderNum);
			query.setParameter("startDate",
					DateUtil.getStartDateOfMonth(targetFundDate));
			query.setParameter("endDate",
					DateUtil.getEndDateOfMonth(targetFundDate));

			List<Object> list = query.list();

			if (list != null && list.size() > 0 && list.get(0) != null) {
				patronCompanyTotalLoanAmount = (BigDecimal) list.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		System.out.println(" \n\npatronCompanyTotalLoanAmount "
				+ patronCompanyTotalLoanAmount.doubleValue());

		return patronCompanyTotalLoanAmount;
	}

	// to get total of CMC commitment amount
	public BigDecimal getCMCCommitmentAmount(int cmcCommitmentNumner) {

		BigDecimal cmcTotalCommitmentAmount = new BigDecimal("0");

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select sum(AgencyCommitmentLetter.origCommitmentAmt) from AgencyCommitmentLetter,CMCAgencyCommitmentNumber "
							+ "where  CMCAgencyCommitmentNumber.agencyCommitNumber = AgencyCommitmentLetter.agencyCommitmentID and "
							+ "CMCAgencyCommitmentNumber.cmcCommitNumber = "
							+ cmcCommitmentNumner);

			List<Object> list = query.list();

			if (list != null && list.size() > 0 && list.get(0) != null) {
				cmcTotalCommitmentAmount = (BigDecimal) list.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		System.out.println(" cmcTotalCommitmentAmount "
				+ cmcTotalCommitmentAmount.doubleValue());

		return cmcTotalCommitmentAmount;
	}

	public AgencyCommitmentLetter getAgencyCommitmentLetterBySellerNumber(
			int sellerNumber) {

		AgencyCommitmentLetter agencyCommitmentLetter = null;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select * from AgencyCommitmentLetter where sellernumber = "
							+ sellerNumber);

			List<Object> list = query.list();
			int count = Integer.parseInt(list.get(0).toString());

			if (count > 0) {
				agencyCommitmentLetter = (AgencyCommitmentLetter) list.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return agencyCommitmentLetter;
	}
	
	
	public AgencyCommitmentLetter getAgencyCommitmentLetter(int agnecyCommimentNumber) {

		AgencyCommitmentLetter agencyCommitmentLetter = null;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery(" from AgencyCommitmentLetter where agencyCommitmentID = "+agnecyCommimentNumber);

			List<Object> list = query.list();
			//int count = Integer.parseInt(list.get(0).toString());

			if (list != null) {
				agencyCommitmentLetter = (AgencyCommitmentLetter) list.get(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return agencyCommitmentLetter;
	}

	public int getLoanCount(int agencyCommitmentNumber) {

		int loanCount = 0;

		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select count(*) from CommitmentData where agencyCommitmentID = "+
					agencyCommitmentNumber+ " and valid = 1");

			List<Object> list = query.list();
			loanCount = Integer.parseInt(list.get(0).toString());


		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return loanCount;
	}

}
