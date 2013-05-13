package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capmkts.msrprocess.validator.ServiceValidateUtility;

/**
 * Pricing - CSV file Fields. 
 * @author Rama Yerramilli
 *
 */

@Entity
@Table(name = "[CoIssue_Test].[dbo].[Pricing]")
public class Pricing implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer tableID;
	
	@Column(name = "ORIGINATORID")
	public String ORIGINATORID = null;
	
	@Column(name = "COMMITMENTID")
	public int COMMITMENTID = 0;
	
	@Column(name = "AGENCYCOMMITMENTID")
	public int AGENCYCOMMITMENTID = 0;
	
	@Column(name = "LOANNUMBER")
	public String LOANNUMBER = null;
	
	@Column(name = "BRANCHID")
	public String BRANCHID = null;
	
	@Column(name = "LOANOFFICERID")
	public String LOANOFFICERID = null;
	
	@Column(name = "BORROWERFIRSTNAME")
	public String BORROWERFIRSTNAME = null;
	
	@Column(name = "BORROWERMIDDLEINITIAL")
	public String BORROWERMIDDLEINITIAL = null;
	
	@Column(name = "BORROWERLASTNAME")
	public String BORROWERLASTNAME = null;
	
	@Column(name = "BORROWERSSN")
	public String BORROWERSSN = null;
	
	@Column(name = "BORROWERCREDITSCORE")
	public int BORROWERCREDITSCORE = 0;
	
	@Column(name = "BORROWERCELLPHONENUMBER")
	public String BORROWERCELLPHONENUMBER = null;
	
	@Column(name = "BORROWEREMAILADDRESS")
	public String BORROWEREMAILADDRESS = null;
	
	@Column(name = "BORROWER2FIRSTNAME")
	public String BORROWER2FIRSTNAME = null;
	
	@Column(name = "BORROWER2MIDDLEINITIAL")
	public String BORROWER2MIDDLEINITIAL = null;
	
	@Column(name = "BORROWER2LASTNAME")
	public String BORROWER2LASTNAME = null;
	
	@Column(name = "BORROWER2SSN")
	public String BORROWER2SSN = null;
	
	@Column(name = "BORROWER2CREDITSCORE")
	public int BORROWER2CREDITSCORE = 0;
	
	@Column(name = "BORROWER2CELLPHONENUMBER")
	public String BORROWER2CELLPHONENUMBER = null;
	
	@Column(name = "BORROWER2EMAILADDRESS")
	public String BORROWER2EMAILADDRESS = null;
	
	@Column(name = "BORROWER3FIRSTNAME")
	public String BORROWER3FIRSTNAME = null;
	
	@Column(name = "BORROWER3MIDDLEINITIAL")
	public String BORROWER3MIDDLEINITIAL = null;
	
	@Column(name = "BORROWER3LASTNAME")
	public String BORROWER3LASTNAME = null;
	
	@Column(name = "BORROWER3SSN")
	public String BORROWER3SSN = null;
	
	@Column(name = "BORROWER3CREDITSCORE")
	public int BORROWER3CREDITSCORE = 0;
	
	@Column(name = "BORROWER3CELLPHONENUMBER")
	public String BORROWER3CELLPHONENUMBER = null;
	
	@Column(name = "BORROWER3EMAILADDRESS")
	public String BORROWER3EMAILADDRESS = null;
	
	@Column(name = "BORROWER4FIRSTNAME")
	public String BORROWER4FIRSTNAME = null;
	
	@Column(name = "BORROWER4MIDDLEINITIAL")
	public String BORROWER4MIDDLEINITIAL = null;
	
	@Column(name = "BORROWER4LASTNAME")
	public String BORROWER4LASTNAME = null;
	
	@Column(name = "BORROWER4SSN")
	public String BORROWER4SSN = null;
	
	@Column(name = "BORROWER4CREDITSCORE")
	public int BORROWER4CREDITSCORE = 0;
	
	@Column(name = "BORROWER4CELLPHONENUMBER")
	public String BORROWER4CELLPHONENUMBER = null;
	
	@Column(name = "BORROWER4EMAILADDRESS")
	public String BORROWER4EMAILADDRESS = null;
	
	@Column(name = "BORROWER5FIRSTNAME")
	public String BORROWER5FIRSTNAME = null;
	
	@Column(name = "BORROWER5MIDDLEINITIAL")
	public String BORROWER5MIDDLEINITIAL = null;
	
	@Column(name = "BORROWER5LASTNAME")
	public String BORROWER5LASTNAME = null;
	
	@Column(name = "BORROWER5SSN")
	public String BORROWER5SSN = null;
	
	@Column(name = "BORROWER5CREDITSCORE")
	public int BORROWER5CREDITSCORE = 0;
	
	@Column(name = "BORROWER5CELLPHONENUMBER")
	public String BORROWER5CELLPHONENUMBER = null;
	
	@Column(name = "BORROWER5EMAILADDRESS")
	public String BORROWER5EMAILADDRESS = null;
	
	@Column(name = "ORIGINALMORTGAGEAMOUNT")
	public BigDecimal ORIGINALMORTGAGEAMOUNT = null;
	
	@Column(name = "ANNUALINTERESTRATE")
	public BigDecimal ANNUALINTERESTRATE = null;
	
	@Column(name = "LOANPROGRAMID")
	public String LOANPROGRAMID = null;
	
	@Column(name = "PROPERTYTYPE")
	public String PROPERTYTYPE = null;
	
	@Column(name = "LOANPURPOSE")
	public String LOANPURPOSE = null;
	
	@Column(name = "LTV")
	public BigDecimal LTV = null;
	
	@Column(name = "CLTV")
	public BigDecimal CLTV = null;
	
	@Column(name = "DTI")
	public BigDecimal DTI = null;
	
	@Column(name = "OCCUPYCODE")
	public String OCCUPYCODE = null;
	
	@Column(name = "LOANTERM")
	public int LOANTERM = 0;
	
	@Column(name = "FUNDSSOURCE")
	public String FUNDSSOURCE = null;
	
	@Column(name = "PIPAYMENT")
	public String PIPAYMENT = null;
	
	@Column(name = "ESCROWMONTHLYPAYMENT")
	public BigDecimal ESCROWMONTHLYPAYMENT = null;
	
	@Column(name = "TOTALPAYMENT")
	public BigDecimal TOTALPAYMENT = null;
	
	@Column(name = "CURRENTPRINCIPALBALANCE")
	public BigDecimal CURRENTPRINCIPALBALANCE = null;
	
	@Column(name = "FIRSTDUEDATE")
	public Date FIRSTDUEDATE = null;
	
	@Column(name = "CURRENTDUEDATE")
	public Date CURRENTDUEDATE = null;
	
	@Column(name = "MATURITYDATE")
	public Date MATURITYDATE = null;
	
	@Column(name = "PREPAYPENALTYFLAG")
	public String PREPAYPENALTYFLAG = null;
	
	@Column(name = "PREPAYPENALTYDESCRIPTION")
	public String PREPAYPENALTYDESCRIPTION = null;
	
	@Column(name = "INTERESTONLYFLAG")
	public String INTERESTONLYFLAG = null;
	
	@Column(name = "INTERESTONLYEXPIRATIONDATE")
	public Date INTERESTONLYEXPIRATIONDATE = null;
	
	@Column(name = "INTERESTONLYPERIOD")
	public String INTERESTONLYPERIOD = null;
	
	@Column(name = "ESCROWBALANCE")
	public BigDecimal ESCROWBALANCE = null;
	
	@Column(name = "ARMPLAN")
	public String ARMPLAN = null;
	
	@Column(name = "ARMINDEX")
	public String ARMINDEX = null;
	
	@Column(name = "ARMMARGIN")
	public String ARMMARGIN = null;
	
	@Column(name = "ARMORIGINALIRCHANGEDATE")
	public Date ARMORIGINALIRCHANGEDATE = null;
	
	@Column(name = "ARMORIGINALPICHANGEDATE")
	public Date ARMORIGINALPICHANGEDATE = null;
	
	@Column(name = "ARMNEXIRCHANGEDATE")
	public Date ARMNEXIRCHANGEDATE = null;
	
	@Column(name = "ARMNEXPICHANGEDATE")
	public Date ARMNEXPICHANGEDATE = null;
	
	@Column(name = "ARMIRCHANGEPERIOD")
	public String ARMIRCHANGEPERIOD = null;
	
	@Column(name = "ARMPICHANGEPERIOD")
	public String ARMPICHANGEPERIOD = null;
	
	@Column(name = "FLOODINSMONTHLYPAYMENT")
	public BigDecimal FLOODINSMONTHLYPAYMENT = null;
	
	@Column(name = "HAZARDINSMONTHLYPAYMENT")
	public BigDecimal HAZARDINSMONTHLYPAYMENT = null;
	
	@Column(name = "COUNTYMONTHLYPAYMENT")
	public BigDecimal COUNTYMONTHLYPAYMENT = null;
	
	@Column(name = "CITYMONTHLYPAYMENT")
	public BigDecimal CITYMONTHLYPAYMENT = null;
	
	@Column(name = "LIENMONTHLYPAYMENT")
	public BigDecimal LIENMONTHLYPAYMENT = null;
	
	@Column(name = "PMIMONTHLYPAYMENT")
	public BigDecimal PMIMONTHLYPAYMENT = null;
	
	@Column(name = "CLOSINGDATE")
	public Date CLOSINGDATE = null;
	
	@Column(name = "PROPERTYSTREETADDRESS")
	public String PROPERTYSTREETADDRESS = null;
	
	@Column(name = "PROPERTYCITY")
	public String PROPERTYCITY = null;
	
	@Column(name = "PROPERTYSTATE")
	public String PROPERTYSTATE = null;
	
	@Column(name = "PROPERTYZIPPLUS4")
	public String PROPERTYZIPPLUS4 = null;
	
	@Column(name = "COUNTYCODE")
	public String COUNTYCODE = null;
	
	@Column(name = "MAILINGSTREETADDRESS")
	public String MAILINGSTREETADDRESS = null;
	
	@Column(name = "MAILINGCITY")
	public String MAILINGCITY = null;
	
	@Column(name = "MAILINGSTATE")
	public String MAILINGSTATE = null;
	
	@Column(name = "MAILINGZIPPLUS4")
	public String MAILINGZIPPLUS4 = null;
	
	@Column(name = "TELEPHONENUMBER")
	public String TELEPHONENUMBER = null;
	
	@Column(name = "SECONDTELEPHONENUMBER")
	public String SECONDTELEPHONENUMBER = null;
	
	@Column(name = "ORIGINALAPPRAISEDVALUE")
	public BigDecimal ORIGINALAPPRAISEDVALUE = null;
	
	@Column(name = "ORIGINALAPPRAISEDDATE")
	public Date ORIGINALAPPRAISEDDATE = null;
	
	@Column(name = "CURRENTAPPRAISEDVALUE")
	public BigDecimal CURRENTAPPRAISEDVALUE = null;
	
	@Column(name = "CURRENTAPPRAISEDDATE")
	public Date CURRENTAPPRAISEDDATE = null;
	
	@Column(name = "MERSMINNUMBER")
	public String MERSMINNUMBER = null;
	
	@Column(name = "MERSREGISTRATIONDATE")
	public Date MERSREGISTRATIONDATE = null;
	
	@Column(name = "MERSREGFLAG")
	public String MERSREGFLAG = null;
	
	@Column(name = "MERSMOMINDICATOR")
	public String MERSMOMINDICATOR = null;
	
	@Column(name = "INTERESTCOLLECTEDATCLOSING")
	public BigDecimal INTERESTCOLLECTEDATCLOSING = null;
	
	@Column(name = "POINTSPAIDBYBORROWER")
	public BigDecimal POINTSPAIDBYBORROWER = null;
	
	@Column(name = "BILLMODE")
	public String BILLMODE = null;
	
	@Column(name = "ESCROWCUSHION")
	public BigDecimal ESCROWCUSHION = null;
	
	@Column(name = "TAXSERVICECOMPANY")
	public String TAXSERVICECOMPANY = null;
	
	@Column(name = "TAXSERVICECONTRACTNUMBER")
	public String TAXSERVICECONTRACTNUMBER = null;
	
	@Column(name = "SERVICEFEERATE")
	public BigDecimal SERVICEFEERATE = null;
	
	@Column(name = "GRACEDAYS")
	public int GRACEDAYS = 0;
	
	@Column(name = "PURCHASEPRICE")
	public BigDecimal PURCHASEPRICE = null;
	
	@Column(name = "CENSUSTRACT")
	public String CENSUSTRACT = null;
	
	@Column(name = "NUMBEROFUNITS")
	public int NUMBEROFUNITS = 0;
	
	@Column(name = "LEGALDESCRIPTION")
	public String LEGALDESCRIPTION = null;
	
	@Column(name = "BALLOONTERM")
	public String BALLOONTERM = null;
	
	@Column(name = "ROUNDINGFACTOR")
	public String ROUNDINGFACTOR = null;
	
	@Column(name = "LOOKBACKPERIOD")
	public String LOOKBACKPERIOD = null;
	
	@Column(name = "MAXCEILINGPERCENTAGE")
	public String MAXCEILINGPERCENTAGE = null;
	
	@Column(name = "MAXFLOORPERCENTAGE")
	public String MAXFLOORPERCENTAGE = null;
	
	@Column(name = "IRCAPATFIRSTCHANGE")
	public String IRCAPATFIRSTCHANGE = null;
	
	@Column(name = "NEXTCAPPERCHANGE")
	public String NEXTCAPPERCHANGE = null;
	
	@Column(name = "LIFEPAYEE")
	public String LIFEPAYEE = null;
	
	@Column(name = "LIFECERTNUMBER")
	public String LIFECERTNUMBER = null;
	
	@Column(name = "LIFEDISBAMT")
	public BigDecimal LIFEDISBAMT = null;
	
	@Column(name = "LIFEDUEDATE")
	public Date LIFEDUEDATE = null;
	
	@Column(name = "DISBPAYEE")
	public String DISBPAYEE = null;
	
	@Column(name = "DISBCERTNUMBER")
	public String DISBCERTNUMBER = null;
	
	@Column(name = "DISBDISBAMT")
	public BigDecimal DISBDISBAMT = null;
	
	@Column(name = "DISBDUEDATE")
	public Date DISBDUEDATE = null;
	
	@Column(name = "FLOODREQUIREDFLAG")
	public String FLOODREQUIREDFLAG = null;
	
	@Column(name = "FLOODPROGRAM")
	public String FLOODPROGRAM = null;
	
	@Column(name = "FLOODDETERMINATIONDATE")
	public Date FLOODDETERMINATIONDATE = null;
	
	@Column(name = "FLOODCOMMUNITYNUMBER")
	public String FLOODCOMMUNITYNUMBER = null;
	
	@Column(name = "FLOODZONE")
	public String FLOODZONE = null;
	
	@Column(name = "FLOODFIRMDATE")
	public Date FLOODFIRMDATE = null;
	
	@Column(name = "FLOODCERTNUMBER")
	public String FLOODCERTNUMBER = null;
	
	@Column(name = "FLOODCMPCO")
	public String FLOODCMPCO = null;
	
	@Column(name = "FLOODMAPCO")
	public String FLOODMAPCO = null;
	
	@Column(name = "PMIMIPGUARCERTNO")
	public String PMIMIPGUARCERTNO = null;
	
	@Column(name = "PMIMIPPERCENTOFCOVERAGE")
	public BigDecimal PMIMIPPERCENTOFCOVERAGE = null;
	
	@Column(name = "PMIRATEPERCENTAGE")
	public BigDecimal PMIRATEPERCENTAGE = null;
	
	@Column(name = "PMIMIPPAYEE")
	public String PMIMIPPAYEE = null;
	
	@Column(name = "PMIMIPDISBURSEMENTDUEDATE")
	public Date PMIMIPDISBURSEMENTDUEDATE = null;
	
	@Column(name = "PMIMIPDISBURSEMENTAMOUNT")
	public BigDecimal PMIMIPDISBURSEMENTAMOUNT = null;
	
	@Column(name = "PMIMIPBILLCODE")
	public int PMIMIPBILLCODE = 0;
	
	@Column(name = "PMIMIPANNUALPREMIUM")
	public BigDecimal PMIMIPANNUALPREMIUM = null;
	
	@Column(name = "PMIMIPEXPIRATIONDATE")
	public Date PMIMIPEXPIRATIONDATE = null;
	
	@Column(name = "FNMAEXPANDEDAPPROVALCODE")
	public String FNMAEXPANDEDAPPROVALCODE = null;
	
	@Column(name = "FNMAFLEX97CODE")
	public String FNMAFLEX97CODE = null;
	
	@Column(name = "FHACASENUMBER")
	public String FHACASENUMBER = null;
	
	@Column(name = "FHAADPCODE")
	public String FHAADPCODE = null;
	
	@Column(name = "VANUMBER")
	public String VANUMBER = null;
	
	@Column(name = "HELOCFLAG")
	public String HELOCFLAG = null;
	
//	@Column(name = "HELOCPLAN")
//	public String HELOCPLAN = null;
	
	@Column(name = "HELOCDRAWAMOUNT")
	public BigDecimal HELOCDRAWAMOUNT = null;
	
	@Column(name = "HELOCDRAWDATE")
	public Date HELOCDRAWDATE = null;
	
	@Column(name = "HELOCDUEDATE")
	public Date HELOCDUEDATE = null;
	
	@Column(name = "HELOCRATEMARGIN")
	public String HELOCRATEMARGIN = null;
	
	@Column(name = "HELOCCEILING")
	public String HELOCCEILING = null;
	
	@Column(name = "HELOCPROCESSSTOP")
	public String HELOCPROCESSSTOP = null;
	
	@Column(name = "DISTTYPE")
	public String DISTTYPE = null;
	
	@Column(name = "REPLACEMENTRESERVE")
	public String REPLACEMENTRESERVE = null;
	
	@Column(name = "PERIODMONTHS")
	public String PERIODMONTHS = null;
	
	@Column(name = "FIRSTSUBSIDYDUEDATE")
	public Date FIRSTSUBSIDYDUEDATE = null;
	
	@Column(name = "COOPINDICATOR")
	public String COOPINDICATOR = null;
	
	@Column(name = "HIGHPRICELOANINDICATOR")
	public String HIGHPRICELOANINDICATOR = null;
	
	@Column(name = "LateChargeCode")
	public String LateChargeCode = null;
	
	@Column(name = "LateChargeFactor")
	public BigDecimal LateChargeFactor = null;
	
	@Column(name = "VAFUNDING_MIUPFRONT")
	public BigDecimal VAFUNDINGMIUPFRONT = null;
	
	@Column(name = "PURCHASED")
	public boolean PURCHASED = false;
	
	/*[BASEBUYPRICE] [float] NULL,
	[ADJUSTMENTDESCRIPTION1] [nvarchar](255) NULL, 
	[ADJUSTMENT1] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION2] [nvarchar](255) NULL,
	[ADJUSTMENT2] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION3] [nvarchar](255) NULL,
	[ADJUSTMENT3] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION4] [nvarchar](255) NULL,
	[ADJUSTMENT4] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION5] [nvarchar](255) NULL,
	[ADJUSTMENT5] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION6] [nvarchar](255) NULL,
	[ADJUSTMENT6] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION7] [nvarchar](255) NULL,
	[ADJUSTMENT7] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION8] [nvarchar](255) NULL,
	[ADJUSTMENT8] [nvarchar](255) NULL,
	[ADJUSTMENTDESCRIPTION9] [nvarchar](255) NULL,
	[ADJUSTMENT9] [nvarchar](255) NULL,
	[NETBUYPRICE] [float] NULL,	*/
	
	@Column(name = "BASEBUYPRICE")
	public BigDecimal BASEBUYPRICE = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION1")
	public String ADJUSTMENTDESCRIPTION1 = null;
	
	@Column(name = "ADJUSTMENT1")
	public BigDecimal ADJUSTMENT1 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION2")
	public String ADJUSTMENTDESCRIPTION2 = null;
	
	@Column(name = "ADJUSTMENT2")
	public BigDecimal ADJUSTMENT2 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION3")
	public String ADJUSTMENTDESCRIPTION3 = null;
	
	@Column(name = "ADJUSTMENT3")
	public BigDecimal ADJUSTMENT3 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION4")
	public String ADJUSTMENTDESCRIPTION4 = null;
	
	@Column(name = "ADJUSTMENT4")
	public BigDecimal ADJUSTMENT4 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION5")
	public String ADJUSTMENTDESCRIPTION5 = null;
	
	@Column(name = "ADJUSTMENT5")
	public BigDecimal ADJUSTMENT5 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION6")
	public String ADJUSTMENTDESCRIPTION6 = null;
	
	@Column(name = "ADJUSTMENT6")
	public BigDecimal ADJUSTMENT6 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION7")
	public String ADJUSTMENTDESCRIPTION7 = null;
	
	@Column(name = "ADJUSTMENT7")
	public BigDecimal ADJUSTMENT7 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION8")
	public String ADJUSTMENTDESCRIPTION8 = null;
	
	@Column(name = "ADJUSTMENT8")
	public BigDecimal ADJUSTMENT8 = null;
	
	@Column(name = "ADJUSTMENTDESCRIPTION9")
	public String ADJUSTMENTDESCRIPTION9 = null;
	
	@Column(name = "ADJUSTMENT9")
	public BigDecimal ADJUSTMENT9 = null;
	
	@Column(name = "NETBUYPRICE")
	public BigDecimal NETBUYPRICE = null;
		
	public BigDecimal getBASEBUYPRICE() {
		return BASEBUYPRICE;
	}

	public void setBASEBUYPRICE(BigDecimal bASEBUYPRICE) {
		BASEBUYPRICE = bASEBUYPRICE;
	}

	public String getADJUSTMENTDESCRIPTION1() {
		return ADJUSTMENTDESCRIPTION1;
	}

	public void setADJUSTMENTDESCRIPTION1(String aDJUSTMENTDESCRIPTION1) {
		ADJUSTMENTDESCRIPTION1 = aDJUSTMENTDESCRIPTION1;
	}

	public BigDecimal getADJUSTMENT1() {
		return ADJUSTMENT1;
	}

	public void setADJUSTMENT1(BigDecimal aDJUSTMENT1) {
		ADJUSTMENT1 = aDJUSTMENT1;
	}

	public String getADJUSTMENTDESCRIPTION2() {
		return ADJUSTMENTDESCRIPTION2;
	}

	public void setADJUSTMENTDESCRIPTION2(String aDJUSTMENTDESCRIPTION2) {
		ADJUSTMENTDESCRIPTION2 = aDJUSTMENTDESCRIPTION2;
	}

	public BigDecimal getADJUSTMENT2() {
		return ADJUSTMENT2;
	}

	public void setADJUSTMENT2(BigDecimal aDJUSTMENT2) {
		ADJUSTMENT2 = aDJUSTMENT2;
	}

	public String getADJUSTMENTDESCRIPTION3() {
		return ADJUSTMENTDESCRIPTION3;
	}

	public void setADJUSTMENTDESCRIPTION3(String aDJUSTMENTDESCRIPTION3) {
		ADJUSTMENTDESCRIPTION3 = aDJUSTMENTDESCRIPTION3;
	}

	public BigDecimal getADJUSTMENT3() {
		return ADJUSTMENT3;
	}

	public void setADJUSTMENT3(BigDecimal aDJUSTMENT3) {
		ADJUSTMENT3 = aDJUSTMENT3;
	}

	public String getADJUSTMENTDESCRIPTION4() {
		return ADJUSTMENTDESCRIPTION4;
	}

	public void setADJUSTMENTDESCRIPTION4(String aDJUSTMENTDESCRIPTION4) {
		ADJUSTMENTDESCRIPTION4 = aDJUSTMENTDESCRIPTION4;
	}

	public BigDecimal getADJUSTMENT4() {
		return ADJUSTMENT4;
	}

	public void setADJUSTMENT4(BigDecimal aDJUSTMENT4) {
		ADJUSTMENT4 = aDJUSTMENT4;
	}

	public String getADJUSTMENTDESCRIPTION5() {
		return ADJUSTMENTDESCRIPTION5;
	}

	public void setADJUSTMENTDESCRIPTION5(String aDJUSTMENTDESCRIPTION5) {
		ADJUSTMENTDESCRIPTION5 = aDJUSTMENTDESCRIPTION5;
	}

	public BigDecimal getADJUSTMENT5() {
		return ADJUSTMENT5;
	}

	public void setADJUSTMENT5(BigDecimal aDJUSTMENT5) {
		ADJUSTMENT5 = aDJUSTMENT5;
	}

	public String getADJUSTMENTDESCRIPTION6() {
		return ADJUSTMENTDESCRIPTION6;
	}

	public void setADJUSTMENTDESCRIPTION6(String aDJUSTMENTDESCRIPTION6) {
		ADJUSTMENTDESCRIPTION6 = aDJUSTMENTDESCRIPTION6;
	}

	public BigDecimal getADJUSTMENT6() {
		return ADJUSTMENT6;
	}

	public void setADJUSTMENT6(BigDecimal aDJUSTMENT6) {
		ADJUSTMENT6 = aDJUSTMENT6;
	}

	public String getADJUSTMENTDESCRIPTION7() {
		return ADJUSTMENTDESCRIPTION7;
	}

	public void setADJUSTMENTDESCRIPTION7(String aDJUSTMENTDESCRIPTION7) {
		ADJUSTMENTDESCRIPTION7 = aDJUSTMENTDESCRIPTION7;
	}

	public BigDecimal getADJUSTMENT7() {
		return ADJUSTMENT7;
	}

	public void setADJUSTMENT7(BigDecimal aDJUSTMENT7) {
		ADJUSTMENT7 = aDJUSTMENT7;
	}

	public String getADJUSTMENTDESCRIPTION8() {
		return ADJUSTMENTDESCRIPTION8;
	}

	public void setADJUSTMENTDESCRIPTION8(String aDJUSTMENTDESCRIPTION8) {
		ADJUSTMENTDESCRIPTION8 = aDJUSTMENTDESCRIPTION8;
	}

	public BigDecimal getADJUSTMENT8() {
		return ADJUSTMENT8;
	}

	public void setADJUSTMENT8(BigDecimal aDJUSTMENT8) {
		ADJUSTMENT8 = aDJUSTMENT8;
	}

	public String getADJUSTMENTDESCRIPTION9() {
		return ADJUSTMENTDESCRIPTION9;
	}

	public void setADJUSTMENTDESCRIPTION9(String aDJUSTMENTDESCRIPTION9) {
		ADJUSTMENTDESCRIPTION9 = aDJUSTMENTDESCRIPTION9;
	}

	public BigDecimal getADJUSTMENT9() {
		return ADJUSTMENT9;
	}

	public void setADJUSTMENT9(BigDecimal aDJUSTMENT9) {
		ADJUSTMENT9 = aDJUSTMENT9;
	}

	public BigDecimal getNETBUYPRICE() {
		return NETBUYPRICE;
	}

	public void setNETBUYPRICE(BigDecimal nETBUYPRICE) {
		NETBUYPRICE = nETBUYPRICE;
	}

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer tableID) {
		this.tableID = tableID;
	}
	
	public String getORIGINATORID() {
		return ORIGINATORID;
	}
	public void setORIGINATORID(String oRIGINATORID) {
		ORIGINATORID = oRIGINATORID;
	}
	public int getCOMMITMENTID() {
		return COMMITMENTID;
	}
	public void setCOMMITMENTID(int cOMMITMENTID) {
		COMMITMENTID = cOMMITMENTID;
	}
	public int getAGENCYCOMMITMENTID() {
		return AGENCYCOMMITMENTID;
	}
	public void setAGENCYCOMMITMENTID(int aGENCYCOMMITMENTID) {
		AGENCYCOMMITMENTID = aGENCYCOMMITMENTID;
	}
	public String getLOANNUMBER() {
		return LOANNUMBER;
	}
	public void setLOANNUMBER(String lOANNUMBER) {
		LOANNUMBER = lOANNUMBER;
	}
	public String getBRANCHID() {
		return BRANCHID;
	}
	public void setBRANCHID(String bRANCHID) {
		BRANCHID = bRANCHID;
	}
	public String getLOANOFFICERID() {
		return LOANOFFICERID;
	}
	public void setLOANOFFICERID(String lOANOFFICERID) {
		LOANOFFICERID = lOANOFFICERID;
	}
	public String getBORROWERFIRSTNAME() {
		return BORROWERFIRSTNAME;
	}
	public void setBORROWERFIRSTNAME(String bORROWERFIRSTNAME) {
		BORROWERFIRSTNAME = bORROWERFIRSTNAME;
	}
	public String getBORROWERMIDDLEINITIAL() {
		return BORROWERMIDDLEINITIAL;
	}
	public void setBORROWERMIDDLEINITIAL(String bORROWERMIDDLEINITIAL) {
		this.BORROWERMIDDLEINITIAL = bORROWERMIDDLEINITIAL;
	}
	public String getBORROWERLASTNAME() {
		return BORROWERLASTNAME;
	}
	public void setBORROWERLASTNAME(String bORROWERLASTNAME) {
		BORROWERLASTNAME = bORROWERLASTNAME;
	}
	public String getBORROWERSSN() {
		return BORROWERSSN;
	}
	public void setBORROWERSSN(String bORROWERSSN) {
		BORROWERSSN = bORROWERSSN;
	}
	public int getBORROWERCREDITSCORE() {
		return BORROWERCREDITSCORE;
	}
	public void setBORROWERCREDITSCORE(int bORROWERCREDITSCORE) {
		BORROWERCREDITSCORE = bORROWERCREDITSCORE;
	}
	public String getBORROWERCELLPHONENUMBER() {
		return BORROWERCELLPHONENUMBER;
	}
	public void setBORROWERCELLPHONENUMBER(String bORROWERCELLPHONENUMBER) {
		BORROWERCELLPHONENUMBER = bORROWERCELLPHONENUMBER;
	}
	public String getBORROWEREMAILADDRESS() {
		return BORROWEREMAILADDRESS;
	}
	public void setBORROWEREMAILADDRESS(String bORROWEREMAILADDRESS) {
		BORROWEREMAILADDRESS = bORROWEREMAILADDRESS;
	}
	public String getBORROWER2FIRSTNAME() {
		return BORROWER2FIRSTNAME;
	}
	public void setBORROWER2FIRSTNAME(String bORROWER2FIRSTNAME) {
		BORROWER2FIRSTNAME = bORROWER2FIRSTNAME;
	}
	public String getBORROWER2MIDDLEINITIAL() {
		return BORROWER2MIDDLEINITIAL;
	}
	public void setBORROWER2MIDDLEINITIAL(String bORROWER2MIDDLEINITIAL) {
		BORROWER2MIDDLEINITIAL = bORROWER2MIDDLEINITIAL;
	}
	public String getBORROWER2LASTNAME() {
		return BORROWER2LASTNAME;
	}
	public void setBORROWER2LASTNAME(String bORROWER2LASTNAME) {
		BORROWER2LASTNAME = bORROWER2LASTNAME;
	}
	public String getBORROWER2SSN() {
		return BORROWER2SSN;
	}
	public void setBORROWER2SSN(String bORROWER2SSN) {
		BORROWER2SSN = bORROWER2SSN;
	}
	public int getBORROWER2CREDITSCORE() {
		return BORROWER2CREDITSCORE;
	}
	public void setBORROWER2CREDITSCORE(int bORROWER2CREDITSCORE) {
		BORROWER2CREDITSCORE = bORROWER2CREDITSCORE;
	}
	public String getBORROWER2CELLPHONENUMBER() {
		return BORROWER2CELLPHONENUMBER;
	}
	public void setBORROWER2CELLPHONENUMBER(String bORROWER2CELLPHONENUMBER) {
		BORROWER2CELLPHONENUMBER = bORROWER2CELLPHONENUMBER;
	}
	public String getBORROWER2EMAILADDRESS() {
		return BORROWER2EMAILADDRESS;
	}
	public void setBORROWER2EMAILADDRESS(String bORROWER2EMAILADDRESS) {
		BORROWER2EMAILADDRESS = bORROWER2EMAILADDRESS;
	}
	public String getBORROWER3FIRSTNAME() {
		return BORROWER3FIRSTNAME;
	}
	public void setBORROWER3FIRSTNAME(String bORROWER3FIRSTNAME) {
		BORROWER3FIRSTNAME = bORROWER3FIRSTNAME;
	}
	public String getBORROWER3MIDDLEINITIAL() {
		return BORROWER3MIDDLEINITIAL;
	}
	public void setBORROWER3MIDDLEINITIAL(String bORROWER3MIDDLEINITIAL) {
		BORROWER3MIDDLEINITIAL = bORROWER3MIDDLEINITIAL;
	}
	public String getBORROWER3LASTNAME() {
		return BORROWER3LASTNAME;
	}
	public void setBORROWER3LASTNAME(String bORROWER3LASTNAME) {
		BORROWER3LASTNAME = bORROWER3LASTNAME;
	}
	public String getBORROWER3SSN() {
		return BORROWER3SSN;
	}
	public void setBORROWER3SSN(String bORROWER3SSN) {
		BORROWER3SSN = bORROWER3SSN;
	}
	public int getBORROWER3CREDITSCORE() {
		return BORROWER3CREDITSCORE;
	}
	public void setBORROWER3CREDITSCORE(int bORROWER3CREDITSCORE) {
		BORROWER3CREDITSCORE = bORROWER3CREDITSCORE;
	}
	public String getBORROWER3CELLPHONENUMBER() {
		return BORROWER3CELLPHONENUMBER;
	}
	public void setBORROWER3CELLPHONENUMBER(String bORROWER3CELLPHONENUMBER) {
		BORROWER3CELLPHONENUMBER = bORROWER3CELLPHONENUMBER;
	}
	public String getBORROWER3EMAILADDRESS() {
		return BORROWER3EMAILADDRESS;
	}
	public void setBORROWER3EMAILADDRESS(String bORROWER3EMAILADDRESS) {
		BORROWER3EMAILADDRESS = bORROWER3EMAILADDRESS;
	}
	public String getBORROWER4FIRSTNAME() {
		return BORROWER4FIRSTNAME;
	}
	public void setBORROWER4FIRSTNAME(String bORROWER4FIRSTNAME) {
		BORROWER4FIRSTNAME = bORROWER4FIRSTNAME;
	}
	public String getBORROWER4MIDDLEINITIAL() {
		return BORROWER4MIDDLEINITIAL;
	}
	public void setBORROWER4MIDDLEINITIAL(String bORROWER4MIDDLEINITIAL) {
		BORROWER4MIDDLEINITIAL = bORROWER4MIDDLEINITIAL;
	}
	public String getBORROWER4LASTNAME() {
		return BORROWER4LASTNAME;
	}
	public void setBORROWER4LASTNAME(String bORROWER4LASTNAME) {
		BORROWER4LASTNAME = bORROWER4LASTNAME;
	}
	public String getBORROWER4SSN() {
		return BORROWER4SSN;
	}
	public void setBORROWER4SSN(String bORROWER4SSN) {
		BORROWER4SSN = bORROWER4SSN;
	}
	public int getBORROWER4CREDITSCORE() {
		return BORROWER4CREDITSCORE;
	}
	public void setBORROWER4CREDITSCORE(int bORROWER4CREDITSCORE) {
		BORROWER4CREDITSCORE = bORROWER4CREDITSCORE;
	}
	public String getBORROWER4CELLPHONENUMBER() {
		return BORROWER4CELLPHONENUMBER;
	}
	public void setBORROWER4CELLPHONENUMBER(String bORROWER4CELLPHONENUMBER) {
		BORROWER4CELLPHONENUMBER = bORROWER4CELLPHONENUMBER;
	}
	public String getBORROWER4EMAILADDRESS() {
		return BORROWER4EMAILADDRESS;
	}
	public void setBORROWER4EMAILADDRESS(String bORROWER4EMAILADDRESS) {
		BORROWER4EMAILADDRESS = bORROWER4EMAILADDRESS;
	}
	public String getBORROWER5FIRSTNAME() {
		return BORROWER5FIRSTNAME;
	}
	public void setBORROWER5FIRSTNAME(String bORROWER5FIRSTNAME) {
		BORROWER5FIRSTNAME = bORROWER5FIRSTNAME;
	}
	public String getBORROWER5MIDDLEINITIAL() {
		return BORROWER5MIDDLEINITIAL;
	}
	public void setBORROWER5MIDDLEINITIAL(String bORROWER5MIDDLEINITIAL) {
		BORROWER5MIDDLEINITIAL = bORROWER5MIDDLEINITIAL;
	}
	public String getBORROWER5LASTNAME() {
		return BORROWER5LASTNAME;
	}
	public void setBORROWER5LASTNAME(String bORROWER5LASTNAME) {
		BORROWER5LASTNAME = bORROWER5LASTNAME;
	}
	public String getBORROWER5SSN() {
		return BORROWER5SSN;
	}
	public void setBORROWER5SSN(String bORROWER5SSN) {
		BORROWER5SSN = bORROWER5SSN;
	}
	public int getBORROWER5CREDITSCORE() {
		return BORROWER5CREDITSCORE;
	}
	public void setBORROWER5CREDITSCORE(int bORROWER5CREDITSCORE) {
		BORROWER5CREDITSCORE = bORROWER5CREDITSCORE;
	}
	public String getBORROWER5CELLPHONENUMBER() {
		return BORROWER5CELLPHONENUMBER;
	}
	public void setBORROWER5CELLPHONENUMBER(String bORROWER5CELLPHONENUMBER) {
		BORROWER5CELLPHONENUMBER = bORROWER5CELLPHONENUMBER;
	}
	public String getBORROWER5EMAILADDRESS() {
		return BORROWER5EMAILADDRESS;
	}
	public void setBORROWER5EMAILADDRESS(String bORROWER5EMAILADDRESS) {
		BORROWER5EMAILADDRESS = bORROWER5EMAILADDRESS;
	}
	public BigDecimal getORIGINALMORTGAGEAMOUNT() {
		//return ServiceValidateUtility.getRoundedValue(ORIGINALMORTGAGEAMOUNT);
		return ORIGINALMORTGAGEAMOUNT;
	}
	public void setORIGINALMORTGAGEAMOUNT(BigDecimal oRIGINALMORTGAGEAMOUNT) {
		ORIGINALMORTGAGEAMOUNT = oRIGINALMORTGAGEAMOUNT;
	}
	public BigDecimal getANNUALINTERESTRATE() {
		//return ServiceValidateUtility.getRoundedValue(ANNUALINTERESTRATE);
		return ANNUALINTERESTRATE;
	}
	public void setANNUALINTERESTRATE(BigDecimal aNNUALINTERESTRATE) {
		ANNUALINTERESTRATE = aNNUALINTERESTRATE;
	}
	public String getLOANPROGRAMID() {
		return LOANPROGRAMID;
	}
	public void setLOANPROGRAMID(String lOANPROGRAMID) {
		LOANPROGRAMID = lOANPROGRAMID;
	}
	public String getPROPERTYTYPE() {
		return PROPERTYTYPE;
	}
	public void setPROPERTYTYPE(String pROPERTYTYPE) {
		PROPERTYTYPE = pROPERTYTYPE;
	}
	public String getLOANPURPOSE() {
		return LOANPURPOSE;
	}
	public void setLOANPURPOSE(String lOANPURPOSE) {
		LOANPURPOSE = lOANPURPOSE;
	}
	public BigDecimal getLTV() {
		return ServiceValidateUtility.getRoundedValue(LTV);
	}
	public void setLTV(BigDecimal lTV) {
		LTV = lTV;
	}
	public BigDecimal getCLTV() {
		return ServiceValidateUtility.getRoundedValue(CLTV);
	}
	public void setCLTV(BigDecimal cLTV) {
		CLTV = cLTV;
	}
	public BigDecimal getDTI() {
		return ServiceValidateUtility.getRoundedValue(DTI);
	}
	public void setDTI(BigDecimal dTI) {
		DTI = dTI;
	}
	public String getOCCUPYCODE() {
		return OCCUPYCODE;
	}
	public void setOCCUPYCODE(String oCCUPYCODE) {
		OCCUPYCODE = oCCUPYCODE;
	}
	public int getLOANTERM() {
		return LOANTERM;
	}
	public void setLOANTERM(int lOANTERM) {
		LOANTERM = lOANTERM;
	}
	public String getFUNDSSOURCE() {
		return FUNDSSOURCE;
	}
	public void setFUNDSSOURCE(String fUNDSSOURCE) {
		FUNDSSOURCE = fUNDSSOURCE;
	}
	public String getPIPAYMENT() {
		return PIPAYMENT;
	}
	public void setPIPAYMENT(String pIPAYMENT) {
		PIPAYMENT = pIPAYMENT;
	}
	public BigDecimal getESCROWMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(ESCROWMONTHLYPAYMENT);
	}
	public void setESCROWMONTHLYPAYMENT(BigDecimal eSCROWMONTHLYPAYMENT) {
		ESCROWMONTHLYPAYMENT = eSCROWMONTHLYPAYMENT;
	}
	public BigDecimal getTOTALPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(TOTALPAYMENT);
	}
	public void setTOTALPAYMENT(BigDecimal tOTALPAYMENT) {
		TOTALPAYMENT = tOTALPAYMENT;
	}
	public BigDecimal getCURRENTPRINCIPALBALANCE() {
		return ServiceValidateUtility.getRoundedValue(CURRENTPRINCIPALBALANCE);
	}
	public void setCURRENTPRINCIPALBALANCE(BigDecimal cURRENTPRINCIPALBALANCE) {
		CURRENTPRINCIPALBALANCE = cURRENTPRINCIPALBALANCE;
	}
	public Date getFIRSTDUEDATE() {
		return FIRSTDUEDATE;
	}
	public void setFIRSTDUEDATE(Date fIRSTDUEDATE) {
		FIRSTDUEDATE = fIRSTDUEDATE;
	}
	public Date getCURRENTDUEDATE() {
		return CURRENTDUEDATE;
	}
	public void setCURRENTDUEDATE(Date cURRENTDUEDATE) {
		CURRENTDUEDATE = cURRENTDUEDATE;
	}
	public Date getMATURITYDATE() {
		return MATURITYDATE;
	}
	public void setMATURITYDATE(Date mATURITYDATE) {
		MATURITYDATE = mATURITYDATE;
	}
	public String getPREPAYPENALTYFLAG() {
		return PREPAYPENALTYFLAG;
	}
	public void setPREPAYPENALTYFLAG(String pREPAYPENALTYFLAG) {
		PREPAYPENALTYFLAG = pREPAYPENALTYFLAG;
	}
	public String getPREPAYPENALTYDESCRIPTION() {
		return PREPAYPENALTYDESCRIPTION;
	}
	public void setPREPAYPENALTYDESCRIPTION(String pREPAYPENALTYDESCRIPTION) {
		PREPAYPENALTYDESCRIPTION = pREPAYPENALTYDESCRIPTION;
	}
	public String getINTERESTONLYFLAG() {
		return INTERESTONLYFLAG;
	}
	public void setINTERESTONLYFLAG(String iNTERESTONLYFLAG) {
		INTERESTONLYFLAG = iNTERESTONLYFLAG;
	}
	public Date getINTERESTONLYEXPIRATIONDATE() {
		return INTERESTONLYEXPIRATIONDATE;
	}
	public void setINTERESTONLYEXPIRATIONDATE(Date iNTERESTONLYEXPIRATIONDATE) {
		INTERESTONLYEXPIRATIONDATE = iNTERESTONLYEXPIRATIONDATE;
	}
	public String getINTERESTONLYPERIOD() {
		return INTERESTONLYPERIOD;
	}
	public void setINTERESTONLYPERIOD(String iNTERESTONLYPERIOD) {
		INTERESTONLYPERIOD = iNTERESTONLYPERIOD;
	}
	public BigDecimal getESCROWBALANCE() {
		return ServiceValidateUtility.getRoundedValue(ESCROWBALANCE);
	}
	public void setESCROWBALANCE(BigDecimal eSCROWBALANCE) {
		ESCROWBALANCE = eSCROWBALANCE;
	}
	public String getARMPLAN() {
		return ARMPLAN;
	}
	public void setARMPLAN(String aRMPLAN) {
		ARMPLAN = aRMPLAN;
	}
	public String getARMINDEX() {
		return ARMINDEX;
	}
	public void setARMINDEX(String aRMINDEX) {
		ARMINDEX = aRMINDEX;
	}
	public String getARMMARGIN() {
		return ARMMARGIN;
	}
	public void setARMMARGIN(String aRMMARGIN) {
		ARMMARGIN = aRMMARGIN;
	}
	public Date getARMORIGINALIRCHANGEDATE() {
		return ARMORIGINALIRCHANGEDATE;
	}
	public void setARMORIGINALIRCHANGEDATE(Date aRMORIGINALIRCHANGEDATE) {
		ARMORIGINALIRCHANGEDATE = aRMORIGINALIRCHANGEDATE;
	}
	public Date getARMORIGINALPICHANGEDATE() {
		return ARMORIGINALPICHANGEDATE;
	}
	public void setARMORIGINALPICHANGEDATE(Date aRMORIGINALPICHANGEDATE) {
		ARMORIGINALPICHANGEDATE = aRMORIGINALPICHANGEDATE;
	}
	public Date getARMNEXIRCHANGEDATE() {
		return ARMNEXIRCHANGEDATE;
	}
	public void setARMNEXIRCHANGEDATE(Date aRMNEXIRCHANGEDATE) {
		ARMNEXIRCHANGEDATE = aRMNEXIRCHANGEDATE;
	}
	public Date getARMNEXPICHANGEDATE() {
		return ARMNEXPICHANGEDATE;
	}
	public void setARMNEXPICHANGEDATE(Date aRMNEXPICHANGEDATE) {
		ARMNEXPICHANGEDATE = aRMNEXPICHANGEDATE;
	}
	public String getARMIRCHANGEPERIOD() {
		return ARMIRCHANGEPERIOD;
	}
	public void setARMIRCHANGEPERIOD(String aRMIRCHANGEPERIOD) {
		ARMIRCHANGEPERIOD = aRMIRCHANGEPERIOD;
	}
	public String getARMPICHANGEPERIOD() {
		return ARMPICHANGEPERIOD;
	}
	public void setARMPICHANGEPERIOD(String aRMPICHANGEPERIOD) {
		ARMPICHANGEPERIOD = aRMPICHANGEPERIOD;
	}
	public BigDecimal getFLOODINSMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(FLOODINSMONTHLYPAYMENT);
	}
	public void setFLOODINSMONTHLYPAYMENT(BigDecimal fLOODINSMONTHLYPAYMENT) {
		FLOODINSMONTHLYPAYMENT = fLOODINSMONTHLYPAYMENT;
	}
	public BigDecimal getHAZARDINSMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(HAZARDINSMONTHLYPAYMENT);
	}
	public void setHAZARDINSMONTHLYPAYMENT(BigDecimal hAZARDINSMONTHLYPAYMENT) {
		HAZARDINSMONTHLYPAYMENT = hAZARDINSMONTHLYPAYMENT;
	}
	public BigDecimal getCOUNTYMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(COUNTYMONTHLYPAYMENT);
	}
	public void setCOUNTYMONTHLYPAYMENT(BigDecimal cOUNTYMONTHLYPAYMENT) {
		COUNTYMONTHLYPAYMENT = cOUNTYMONTHLYPAYMENT;
	}
	public BigDecimal getCITYMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(CITYMONTHLYPAYMENT);
	}
	public void setCITYMONTHLYPAYMENT(BigDecimal cITYMONTHLYPAYMENT) {
		CITYMONTHLYPAYMENT = cITYMONTHLYPAYMENT;
	}
	public BigDecimal getLIENMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(LIENMONTHLYPAYMENT);
	}
	public void setLIENMONTHLYPAYMENT(BigDecimal lIENMONTHLYPAYMENT) {
		LIENMONTHLYPAYMENT = lIENMONTHLYPAYMENT;
	}
	public BigDecimal getPMIMONTHLYPAYMENT() {
		return ServiceValidateUtility.getRoundedValue(PMIMONTHLYPAYMENT);
	}
	public void setPMIMONTHLYPAYMENT(BigDecimal pMIMONTHLYPAYMENT) {
		PMIMONTHLYPAYMENT = pMIMONTHLYPAYMENT;
	}
	public Date getCLOSINGDATE() {
		return CLOSINGDATE;
	}
	public void setCLOSINGDATE(Date cLOSINGDATE) {
		CLOSINGDATE = cLOSINGDATE;
	}
	public String getPROPERTYSTREETADDRESS() {
		return PROPERTYSTREETADDRESS;
	}
	public void setPROPERTYSTREETADDRESS(String pROPERTYSTREETADDRESS) {
		PROPERTYSTREETADDRESS = pROPERTYSTREETADDRESS;
	}
	public String getPROPERTYCITY() {
		return PROPERTYCITY;
	}
	public void setPROPERTYCITY(String pROPERTYCITY) {
		PROPERTYCITY = pROPERTYCITY;
	}
	public String getPROPERTYSTATE() {
		return PROPERTYSTATE;
	}
	public void setPROPERTYSTATE(String pROPERTYSTATE) {
		PROPERTYSTATE = pROPERTYSTATE;
	}
	public String getPROPERTYZIPPLUS4() {
		return PROPERTYZIPPLUS4;
	}
	public void setPROPERTYZIPPLUS4(String pROPERTYZIPPLUS4) {
		PROPERTYZIPPLUS4 = pROPERTYZIPPLUS4;
	}
	public String getCOUNTYCODE() {
		return COUNTYCODE;
	}
	public void setCOUNTYCODE(String cOUNTYCODE) {
		COUNTYCODE = cOUNTYCODE;
	}
	public String getMAILINGSTREETADDRESS() {
		return MAILINGSTREETADDRESS;
	}
	public void setMAILINGSTREETADDRESS(String mAILINGSTREETADDRESS) {
		MAILINGSTREETADDRESS = mAILINGSTREETADDRESS;
	}
	public String getMAILINGCITY() {
		return MAILINGCITY;
	}
	public void setMAILINGCITY(String mAILINGCITY) {
		MAILINGCITY = mAILINGCITY;
	}
	public String getMAILINGSTATE() {
		return MAILINGSTATE;
	}
	public void setMAILINGSTATE(String mAILINGSTATE) {
		MAILINGSTATE = mAILINGSTATE;
	}
	public String getMAILINGZIPPLUS4() {
		return MAILINGZIPPLUS4;
	}
	public void setMAILINGZIPPLUS4(String mAILINGZIPPLUS4) {
		MAILINGZIPPLUS4 = mAILINGZIPPLUS4;
	}
	public String getTELEPHONENUMBER() {
		return TELEPHONENUMBER;
	}
	public void setTELEPHONENUMBER(String tELEPHONENUMBER) {
		TELEPHONENUMBER = tELEPHONENUMBER;
	}
	public String getSECONDTELEPHONENUMBER() {
		return SECONDTELEPHONENUMBER;
	}
	public void setSECONDTELEPHONENUMBER(String sECONDTELEPHONENUMBER) {
		SECONDTELEPHONENUMBER = sECONDTELEPHONENUMBER;
	}
	public BigDecimal getORIGINALAPPRAISEDVALUE() {
		return ServiceValidateUtility.getRoundedValue(ORIGINALAPPRAISEDVALUE);
	}
	public void setORIGINALAPPRAISEDVALUE(BigDecimal oRIGINALAPPRAISEDVALUE) {
		ORIGINALAPPRAISEDVALUE = oRIGINALAPPRAISEDVALUE;
	}
	public Date getORIGINALAPPRAISEDDATE() {
		return ORIGINALAPPRAISEDDATE;
	}
	public void setORIGINALAPPRAISEDDATE(Date oRIGINALAPPRAISEDDATE) {
		ORIGINALAPPRAISEDDATE = oRIGINALAPPRAISEDDATE;
	}
	public BigDecimal getCURRENTAPPRAISEDVALUE() {
		return ServiceValidateUtility.getRoundedValue(CURRENTAPPRAISEDVALUE);
	}
	public void setCURRENTAPPRAISEDVALUE(BigDecimal cURRENTAPPRAISEDVALUE) {
		CURRENTAPPRAISEDVALUE = cURRENTAPPRAISEDVALUE;
	}
	public Date getCURRENTAPPRAISEDDATE() {
		return CURRENTAPPRAISEDDATE;
	}
	public void setCURRENTAPPRAISEDDATE(Date cURRENTAPPRAISEDDATE) {
		CURRENTAPPRAISEDDATE = cURRENTAPPRAISEDDATE;
	}
	public String getMERSMINNUMBER() {
		return MERSMINNUMBER;
	}
	public void setMERSMINNUMBER(String mERSMINNUMBER) {
		MERSMINNUMBER = mERSMINNUMBER;
	}
	public Date getMERSREGISTRATIONDATE() {
		return MERSREGISTRATIONDATE;
	}
	public void setMERSREGISTRATIONDATE(Date mERSREGISTRATIONDATE) {
		MERSREGISTRATIONDATE = mERSREGISTRATIONDATE;
	}
	public String getMERSREGFLAG() {
		return MERSREGFLAG;
	}
	public void setMERSREGFLAG(String mERSREGFLAG) {
		MERSREGFLAG = mERSREGFLAG;
	}
	public String getMERSMOMINDICATOR() {
		return MERSMOMINDICATOR;
	}
	public void setMERSMOMINDICATOR(String mERSMOMINDICATOR) {
		MERSMOMINDICATOR = mERSMOMINDICATOR;
	}
	public BigDecimal getINTERESTCOLLECTEDATCLOSING() {
		return ServiceValidateUtility.getRoundedValue(INTERESTCOLLECTEDATCLOSING);
	}
	public void setINTERESTCOLLECTEDATCLOSING(BigDecimal iNTERESTCOLLECTEDATCLOSING) {
		INTERESTCOLLECTEDATCLOSING = iNTERESTCOLLECTEDATCLOSING;
	}
	public BigDecimal getPOINTSPAIDBYBORROWER() {
		return ServiceValidateUtility.getRoundedValue(POINTSPAIDBYBORROWER);
	}
	public void setPOINTSPAIDBYBORROWER(BigDecimal pOINTSPAIDBYBORROWER) {
		POINTSPAIDBYBORROWER = pOINTSPAIDBYBORROWER;
	}
	public String getBILLMODE() {
		return BILLMODE;
	}
	public void setBILLMODE(String bILLMODE) {
		BILLMODE = bILLMODE;
	}
	public BigDecimal getESCROWCUSHION() {
		return ESCROWCUSHION;
	}
	public void setESCROWCUSHION(BigDecimal eSCROWCUSHION) {
		ESCROWCUSHION = eSCROWCUSHION;
	}
	public String getTAXSERVICECOMPANY() {
		return TAXSERVICECOMPANY;
	}
	public void setTAXSERVICECOMPANY(String tAXSERVICECOMPANY) {
		TAXSERVICECOMPANY = tAXSERVICECOMPANY;
	}
	public String getTAXSERVICECONTRACTNUMBER() {
		return TAXSERVICECONTRACTNUMBER;
	}
	public void setTAXSERVICECONTRACTNUMBER(String tAXSERVICECONTRACTNUMBER) {
		TAXSERVICECONTRACTNUMBER = tAXSERVICECONTRACTNUMBER;
	}
	public BigDecimal getSERVICEFEERATE() {
		return ServiceValidateUtility.getRoundedValue(SERVICEFEERATE);
	}
	public void setSERVICEFEERATE(BigDecimal sERVICEFEERATE) {
		SERVICEFEERATE = sERVICEFEERATE;
	}
	public int getGRACEDAYS() {
		return GRACEDAYS;
	}
	public void setGRACEDAYS(int gRACEDAYS) {
		GRACEDAYS = gRACEDAYS;
	}
	public BigDecimal getPURCHASEPRICE() {
		return ServiceValidateUtility.getRoundedValue(PURCHASEPRICE);
	}
	public void setPURCHASEPRICE(BigDecimal pURCHASEPRICE) {
		PURCHASEPRICE = pURCHASEPRICE;
	}
	public String getCENSUSTRACT() {
		return CENSUSTRACT;
	}
	public void setCENSUSTRACT(String cENSUSTRACT) {
		CENSUSTRACT = cENSUSTRACT;
	}
	public int getNUMBEROFUNITS() {
		return NUMBEROFUNITS;
	}
	public void setNUMBEROFUNITS(int nUMBEROFUNITS) {
		NUMBEROFUNITS = nUMBEROFUNITS;
	}
	public String getLEGALDESCRIPTION() {
		return LEGALDESCRIPTION;
	}
	public void setLEGALDESCRIPTION(String lEGALDESCRIPTION) {
		LEGALDESCRIPTION = lEGALDESCRIPTION;
	}
	public String getBALLOONTERM() {
		return BALLOONTERM;
	}
	public void setBALLOONTERM(String bALLOONTERM) {
		BALLOONTERM = bALLOONTERM;
	}
	public String getROUNDINGFACTOR() {
		return ROUNDINGFACTOR;
	}
	public void setROUNDINGFACTOR(String rOUNDINGFACTOR) {
		ROUNDINGFACTOR = rOUNDINGFACTOR;
	}
	public String getLOOKBACKPERIOD() {
		return LOOKBACKPERIOD;
	}
	public void setLOOKBACKPERIOD(String lOOKBACKPERIOD) {
		LOOKBACKPERIOD = lOOKBACKPERIOD;
	}
	public String getMAXCEILINGPERCENTAGE() {
		return MAXCEILINGPERCENTAGE;
	}
	public void setMAXCEILINGPERCENTAGE(String mAXCEILINGPERCENTAGE) {
		MAXCEILINGPERCENTAGE = mAXCEILINGPERCENTAGE;
	}
	public String getMAXFLOORPERCENTAGE() {
		return MAXFLOORPERCENTAGE;
	}
	public void setMAXFLOORPERCENTAGE(String mAXFLOORPERCENTAGE) {
		MAXFLOORPERCENTAGE = mAXFLOORPERCENTAGE;
	}
	public String getIRCAPATFIRSTCHANGE() {
		return IRCAPATFIRSTCHANGE;
	}
	public void setIRCAPATFIRSTCHANGE(String iRCAPATFIRSTCHANGE) {
		IRCAPATFIRSTCHANGE = iRCAPATFIRSTCHANGE;
	}
	public String getNEXTCAPPERCHANGE() {
		return NEXTCAPPERCHANGE;
	}
	public void setNEXTCAPPERCHANGE(String nEXTCAPPERCHANGE) {
		NEXTCAPPERCHANGE = nEXTCAPPERCHANGE;
	}
	public String getLIFEPAYEE() {
		return LIFEPAYEE;
	}
	public void setLIFEPAYEE(String lIFEPAYEE) {
		LIFEPAYEE = lIFEPAYEE;
	}
	public String getLIFECERTNUMBER() {
		return LIFECERTNUMBER;
	}
	public void setLIFECERTNUMBER(String lIFECERTNUMBER) {
		LIFECERTNUMBER = lIFECERTNUMBER;
	}
	public BigDecimal getLIFEDISBAMT() {
		return LIFEDISBAMT;
	}
	public void setLIFEDISBAMT(BigDecimal lIFEDISBAMT) {
		LIFEDISBAMT = lIFEDISBAMT;
	}
	public Date getLIFEDUEDATE() {
		return LIFEDUEDATE;
	}
	public void setLIFEDUEDATE(Date lIFEDUEDATE) {
		LIFEDUEDATE = lIFEDUEDATE;
	}
	public String getDISBPAYEE() {
		return DISBPAYEE;
	}
	public void setDISBPAYEE(String dISBPAYEE) {
		DISBPAYEE = dISBPAYEE;
	}
	public String getDISBCERTNUMBER() {
		return DISBCERTNUMBER;
	}
	public void setDISBCERTNUMBER(String dISBCERTNUMBER) {
		DISBCERTNUMBER = dISBCERTNUMBER;
	}
	public BigDecimal getDISBDISBAMT() {
		return DISBDISBAMT;
	}
	public void setDISBDISBAMT(BigDecimal dISBDISBAMT) {
		DISBDISBAMT = dISBDISBAMT;
	}
	public Date getDISBDUEDATE() {
		return DISBDUEDATE;
	}
	public void setDISBDUEDATE(Date dISBDUEDATE) {
		DISBDUEDATE = dISBDUEDATE;
	}
	public String getFLOODREQUIREDFLAG() {
		return FLOODREQUIREDFLAG;
	}
	public void setFLOODREQUIREDFLAG(String fLOODREQUIREDFLAG) {
		FLOODREQUIREDFLAG = fLOODREQUIREDFLAG;
	}
	public String getFLOODPROGRAM() {
		return FLOODPROGRAM;
	}
	public void setFLOODPROGRAM(String fLOODPROGRAM) {
		FLOODPROGRAM = fLOODPROGRAM;
	}
	public Date getFLOODDETERMINATIONDATE() {
		return FLOODDETERMINATIONDATE;
	}
	public void setFLOODDETERMINATIONDATE(Date fLOODDETERMINATIONDATE) {
		FLOODDETERMINATIONDATE = fLOODDETERMINATIONDATE;
	}
	public String getFLOODCOMMUNITYNUMBER() {
		return FLOODCOMMUNITYNUMBER;
	}
	public void setFLOODCOMMUNITYNUMBER(String fLOODCOMMUNITYNUMBER) {
		FLOODCOMMUNITYNUMBER = fLOODCOMMUNITYNUMBER;
	}
	public String getFLOODZONE() {
		return FLOODZONE;
	}
	public void setFLOODZONE(String fLOODZONE) {
		FLOODZONE = fLOODZONE;
	}
	public Date getFLOODFIRMDATE() {
		return FLOODFIRMDATE;
	}
	public void setFLOODFIRMDATE(Date fLOODFIRMDATE) {
		FLOODFIRMDATE = fLOODFIRMDATE;
	}
	public String getFLOODCERTNUMBER() {
		return FLOODCERTNUMBER;
	}
	public void setFLOODCERTNUMBER(String fLOODCERTNUMBER) {
		FLOODCERTNUMBER = fLOODCERTNUMBER;
	}
	public String getFLOODCMPCO() {
		return FLOODCMPCO;
	}
	public void setFLOODCMPCO(String fLOODCMPCO) {
		FLOODCMPCO = fLOODCMPCO;
	}
	public String getFLOODMAPCO() {
		return FLOODMAPCO;
	}
	public void setFLOODMAPCO(String fLOODMAPCO) {
		FLOODMAPCO = fLOODMAPCO;
	}
	public String getPMIMIPGUARCERTNO() {
		return PMIMIPGUARCERTNO;
	}
	public void setPMIMIPGUARCERTNO(String pMIMIPGUARCERTNO) {
		PMIMIPGUARCERTNO = pMIMIPGUARCERTNO;
	}
	public BigDecimal getPMIMIPPERCENTOFCOVERAGE() {
		return PMIMIPPERCENTOFCOVERAGE;
	}
	public void setPMIMIPPERCENTOFCOVERAGE(BigDecimal pMIMIPPERCENTOFCOVERAGE) {
		PMIMIPPERCENTOFCOVERAGE = pMIMIPPERCENTOFCOVERAGE;
	}
	public BigDecimal getPMIRATEPERCENTAGE() {
		return ServiceValidateUtility.getRoundedValue(PMIRATEPERCENTAGE);
	}
	public void setPMIRATEPERCENTAGE(BigDecimal pMIRATEPERCENTAGE) {
		PMIRATEPERCENTAGE = pMIRATEPERCENTAGE;
	}
	public String getPMIMIPPAYEE() {
		return PMIMIPPAYEE;
	}
	public void setPMIMIPPAYEE(String pMIMIPPAYEE) {
		PMIMIPPAYEE = pMIMIPPAYEE;
	}
	public Date getPMIMIPDISBURSEMENTDUEDATE() {
		return PMIMIPDISBURSEMENTDUEDATE;
	}
	public void setPMIMIPDISBURSEMENTDUEDATE(Date pMIMIPDISBURSEMENTDUEDATE) {
		PMIMIPDISBURSEMENTDUEDATE = pMIMIPDISBURSEMENTDUEDATE;
	}
	public BigDecimal getPMIMIPDISBURSEMENTAMOUNT() {
		return ServiceValidateUtility.getRoundedValue(PMIMIPDISBURSEMENTAMOUNT);
	}
	public void setPMIMIPDISBURSEMENTAMOUNT(BigDecimal pMIMIPDISBURSEMENTAMOUNT) {
		PMIMIPDISBURSEMENTAMOUNT = pMIMIPDISBURSEMENTAMOUNT;
	}
	public int getPMIMIPBILLCODE() {
		return PMIMIPBILLCODE;
	}
	public void setPMIMIPBILLCODE(int pMIMIPBILLCODE) {
		PMIMIPBILLCODE = pMIMIPBILLCODE;
	}
	public BigDecimal getPMIMIPANNUALPREMIUM() {
		return ServiceValidateUtility.getRoundedValue(PMIMIPANNUALPREMIUM);
	}
	public void setPMIMIPANNUALPREMIUM(BigDecimal pMIMIPANNUALPREMIUM) {
		PMIMIPANNUALPREMIUM = pMIMIPANNUALPREMIUM;
	}
	public Date getPMIMIPEXPIRATIONDATE() {
		return PMIMIPEXPIRATIONDATE;
	}
	public void setPMIMIPEXPIRATIONDATE(Date pMIMIPEXPIRATIONDATE) {
		PMIMIPEXPIRATIONDATE = pMIMIPEXPIRATIONDATE;
	}
	public String getFNMAEXPANDEDAPPROVALCODE() {
		return FNMAEXPANDEDAPPROVALCODE;
	}
	public void setFNMAEXPANDEDAPPROVALCODE(String fNMAEXPANDEDAPPROVALCODE) {
		FNMAEXPANDEDAPPROVALCODE = fNMAEXPANDEDAPPROVALCODE;
	}
	public String getFNMAFLEX97CODE() {
		return FNMAFLEX97CODE;
	}
	public void setFNMAFLEX97CODE(String fNMAFLEX97CODE) {
		FNMAFLEX97CODE = fNMAFLEX97CODE;
	}
	public String getFHACASENUMBER() {
		return FHACASENUMBER;
	}
	public void setFHACASENUMBER(String fHACASENUMBER) {
		FHACASENUMBER = fHACASENUMBER;
	}
	public String getFHAADPCODE() {
		return FHAADPCODE;
	}
	public void setFHAADPCODE(String fHAADPCODE) {
		FHAADPCODE = fHAADPCODE;
	}
	public String getVANUMBER() {
		return VANUMBER;
	}
	public void setVANUMBER(String vANUMBER) {
		VANUMBER = vANUMBER;
	}
	public String getHELOCFLAG() {
		return HELOCFLAG;
	}
	public void setHELOCFLAG(String hELOCFLAG) {
		HELOCFLAG = hELOCFLAG;
	}
//	public String getHELOCPLAN() {
//		return HELOCPLAN;
//	}
//	public void setHELOCPLAN(String hELOCPLAN) {
//		HELOCPLAN = hELOCPLAN;
//	}
	public BigDecimal getHELOCDRAWAMOUNT() {
		return ServiceValidateUtility.getRoundedValue(HELOCDRAWAMOUNT);
	}
	public void setHELOCDRAWAMOUNT(BigDecimal hELOCDRAWAMOUNT) {
		HELOCDRAWAMOUNT = hELOCDRAWAMOUNT;
	}
	public Date getHELOCDRAWDATE() {
		return HELOCDRAWDATE;
	}
	public void setHELOCDRAWDATE(Date hELOCDRAWDATE) {
		HELOCDRAWDATE = hELOCDRAWDATE;
	}
	public Date getHELOCDUEDATE() {
		return HELOCDUEDATE;
	}
	public void setHELOCDUEDATE(Date hELOCDUEDATE) {
		HELOCDUEDATE = hELOCDUEDATE;
	}
	public String getHELOCRATEMARGIN() {
		return HELOCRATEMARGIN;
	}
	public void setHELOCRATEMARGIN(String hELOCRATEMARGIN) {
		HELOCRATEMARGIN = hELOCRATEMARGIN;
	}
	public String getHELOCCEILING() {
		return HELOCCEILING;
	}
	public void setHELOCCEILING(String hELOCCEILING) {
		HELOCCEILING = hELOCCEILING;
	}
	public String getHELOCPROCESSSTOP() {
		return HELOCPROCESSSTOP;
	}
	public void setHELOCPROCESSSTOP(String hELOCPROCESSSTOP) {
		HELOCPROCESSSTOP = hELOCPROCESSSTOP;
	}
	public String getDISTTYPE() {
		return DISTTYPE;
	}
	public void setDISTTYPE(String dISTTYPE) {
		DISTTYPE = dISTTYPE;
	}
	public String getREPLACEMENTRESERVE() {
		return REPLACEMENTRESERVE;
	}
	public void setREPLACEMENTRESERVE(String rEPLACEMENTRESERVE) {
		REPLACEMENTRESERVE = rEPLACEMENTRESERVE;
	}
	public String getPERIODMONTHS() {
		return PERIODMONTHS;
	}
	public void setPERIODMONTHS(String pERIODMONTHS) {
		PERIODMONTHS = pERIODMONTHS;
	}
	public Date getFIRSTSUBSIDYDUEDATE() {
		return FIRSTSUBSIDYDUEDATE;
	}
	public void setFIRSTSUBSIDYDUEDATE(Date fIRSTSUBSIDYDUEDATE) {
		FIRSTSUBSIDYDUEDATE = fIRSTSUBSIDYDUEDATE;
	}
	public String getCOOPINDICATOR() {
		return COOPINDICATOR;
	}
	public void setCOOPINDICATOR(String cOOPINDICATOR) {
		COOPINDICATOR = cOOPINDICATOR;
	}
	public String getHIGHPRICELOANINDICATOR() {
		return HIGHPRICELOANINDICATOR;
	}
	public void setHIGHPRICELOANINDICATOR(String hIGHPRICELOANINDICATOR) {
		HIGHPRICELOANINDICATOR = hIGHPRICELOANINDICATOR;
	}
	public String getLateChargeCode() {
		return LateChargeCode;
	}
	public void setLateChargeCode(String lateChargeCode) {
		LateChargeCode = lateChargeCode;
	}
	public BigDecimal getLateChargeFactor() {
		return ServiceValidateUtility.getRoundedValue(LateChargeFactor);
	}
	public void setLateChargeFactor(BigDecimal lateChargeFactor) {
		LateChargeFactor = lateChargeFactor;
	}
	public BigDecimal getVAFUNDINGMIPUPFRONT() {
		return ServiceValidateUtility.getRoundedValue(VAFUNDINGMIUPFRONT);
	}
	public void setVAFUNDINGMIPUPFRONT(BigDecimal vAFUNDING_MIUPFRONT) {
		VAFUNDINGMIUPFRONT = vAFUNDING_MIUPFRONT;
	}
	public boolean getPurchased(){
		return PURCHASED;
	}
	public void setPurchased(boolean purchased){
		PURCHASED = purchased;
	}
}
