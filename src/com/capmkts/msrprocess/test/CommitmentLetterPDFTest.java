package com.capmkts.msrprocess.test;

import java.math.BigDecimal;
import java.sql.Date;

import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CMCMsrCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;
import com.capmkts.msrprocess.generator.CMCMSRCommitmentLetterPDF;

public class CommitmentLetterPDFTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			PatronCompany patronCompany = new PatronCompany();
			patronCompany.setContactEmail("contactemail@test.com");
			patronCompany.setContactName("contactname");
			patronCompany.setContactPhone("contactphone11111111");
			patronCompany.setPatronCoName("companyname");
			
			CMCMsrCommitmentLetter  cmcMsrCommitmentLetter = new CMCMsrCommitmentLetter();
			cmcMsrCommitmentLetter.setCommitmentNumber(100);
			cmcMsrCommitmentLetter.setLoanCount(200);
			cmcMsrCommitmentLetter.setPatronCompany(patronCompany);
			
			AgencyCommitmentLetter agencyCommitmentLetter = new AgencyCommitmentLetter();
			agencyCommitmentLetter.setAgencyCommitmentID(new Integer(1111));
			agencyCommitmentLetter.setCommitmentDate(new Date(1, 1, 1999));
			agencyCommitmentLetter.setExpirationDate(new Date(1, 1, 2000));
			agencyCommitmentLetter.setDataDeliveryDate(new Date(1, 1, 2001));
			agencyCommitmentLetter.setTargetFundDate(new Date(1, 1, 2002));
			agencyCommitmentLetter.setRemittanceType("remittanceType");
			
			cmcMsrCommitmentLetter.setUpb(new BigDecimal("100"));
			
			cmcMsrCommitmentLetter.setAgencyCommitmentLetter(agencyCommitmentLetter);
			
			CMCMSRCommitmentLetterPDF pdf = new CMCMSRCommitmentLetterPDF();
//			pdf.preparePDF(cmcMsrCommitmentLetter);
			
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
	}

}
