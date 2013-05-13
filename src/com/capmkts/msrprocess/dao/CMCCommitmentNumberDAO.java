package com.capmkts.msrprocess.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CMCAgencyCommitmentNumber;
import com.capmkts.msrprocess.data.CMCCommitNumber;
import com.capmkts.msrprocess.data.CMCMsrCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;
import com.capmkts.msrprocess.generator.CMCMSRCommitmentLetterPDF;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CMCCommitmentNumberDAO {
	
	public Integer generateCMCCommitmentNumber(List<Integer> agencyCommitmentNumbersAL){
		
		Integer cmcCommitmentNumber = 0;
		
		//System.out.println(" CMCCommitmentNumberDAO.generateCMCCommitmentNumber : "+agencyCommitmentNumbersAL);
		
		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {

			txn = session.beginTransaction();
			
			// Generate CMC Commit Number
			CMCCommitNumber cmcCommitNumber = new CMCCommitNumber();
			session.save(cmcCommitNumber);
			//session.flush();
			
			cmcCommitmentNumber = cmcCommitNumber.getTableID();
			System.out.println(" CMCCommitmentNumberDAO.cmcCommitNumber : "+cmcCommitNumber.getTableID());
			// associate CMC Commit Number to Agency CommitNumber
			
			BigDecimal balance = new BigDecimal("0.00");
			int count = 0;
			
			for (int i = 0; i < agencyCommitmentNumbersAL.size(); i++) {
				
				System.out.println("\nInside for loop "+agencyCommitmentNumbersAL.get(i)+ "\n");
				
				CMCAgencyCommitmentNumber cmcAgencyCommitmentNumber = new CMCAgencyCommitmentNumber();
				cmcAgencyCommitmentNumber.setAgencyCommitNumber(agencyCommitmentNumbersAL.get(i));
				cmcAgencyCommitmentNumber.setCmcCommitNumber(cmcCommitNumber.getTableID());
				cmcAgencyCommitmentNumber.setCmcCommitStatus(MsrConstants.CMC_COMMITMENT_OPEN);
				
			//	if(!(isCMCCommitNumberGenerateForAgencyCommitNumber(agencyCommitmentNumbersAL.get(i)))){
					session.save(cmcAgencyCommitmentNumber);
					
					// START - Generate CMCMsrCommitmentLetter PDF
					CMCMsrCommitmentLetter cmcMsrCommitmentLetter = new CMCMsrCommitmentLetter();

					cmcMsrCommitmentLetter.setCommitmentNumber(cmcCommitNumber.getTableID());

					AgencyCommitmentLetter agencyCommitmentLetter = new AgencyCommitmentLetter();
					agencyCommitmentLetter.setAgencyCommitmentID(cmcAgencyCommitmentNumber.getAgencyCommitNumber());
					cmcMsrCommitmentLetter.setAgencyCommitmentLetter(agencyCommitmentLetter);	
					
					CMCMsrCommitmentLetterDAO cmcMsrCommitmentLetterDAO = new CMCMsrCommitmentLetterDAO();
					cmcMsrCommitmentLetterDAO.populateCMCMsrCommitmentLetterDetails(cmcMsrCommitmentLetter);
					
					System.out.println("\n Before Generate CMCMsrCommitmentLetter");
					// call generate PDF method.
					
						balance = balance.add(cmcMsrCommitmentLetter.getUpb());
						count+= cmcMsrCommitmentLetter.getLoanCount();
						
						if (i == agencyCommitmentNumbersAL.size()-1){
							CMCMSRCommitmentLetterPDF cmcmsrCommitmentLetterPDF = new CMCMSRCommitmentLetterPDF();
							cmcmsrCommitmentLetterPDF.preparePDF(cmcMsrCommitmentLetter, balance, count);
						}
					// END - Generate CMCMsrCommitmentLetter
						System.out.println("\n\nUPB: " +balance+ "\n\n");
						System.out.println("\n\nLoan Count: " +count+ "\n\n");
					System.out.println("\n After Generate CMCMsrCommitmentLetter");
					

			//	}
				//session.flush();
			}
			
			
			
			txn.commit();
			session.flush();
			
		} catch (Exception ex) {
			txn.rollback();
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		return cmcCommitmentNumber;
	}
	
	
	public Boolean isCMCCommitNumberGenerateForAgencyCommitNumber(Integer agencyCommitmentNumber){
		
		Boolean flg = false;
		
		Session session = HibernateUtil.getSession();
		int count = 0;
		try{
			Query query = session.createQuery("select count(*) from CMCAgencyCommitmentNumber where agencyCommitNumber = "+agencyCommitmentNumber);
			
			List list = query.list();
			if (!list.isEmpty()){
				count = Integer.parseInt(list.get(0).toString());
			}
			
			if(count > 0){
				flg = true;
			}
			//session.close();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return flg;
	}

	public void closeCMCCommitment(Integer cmcCommitmentNumber){
		
		Session session = HibernateUtil.getSession();
		
		try{
			Query query = session.createQuery("update CMCAgencyCommitmentNumber set status = :commitStatus " +
					"where cmcCommitNumber = "+cmcCommitmentNumber);
			
			query.setParameter("commitStatus", MsrConstants.CMC_COMMITMENT_CLOSE);
			
			int  rowCount = query.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
	}
}
