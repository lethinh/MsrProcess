package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DATFileBean - DAT file Fields. 
 * @author Rama Yerramilli
 *
 */

@Entity
@Table(name = "[CoIssue_Test].[dbo].[AgencyPurchAdvicesManualCheck]")
public class AgencyPurchAdvicesManualCheck implements Serializable {


	@Id
	@GeneratedValue
	private int tableID;
	
	@Column(name = "PURCHASE_DATE")
	public Date PURCHASE_DATE = null;
	
	@Column(name = "REMITTANCE_TYPE")
	public int REMITTANCE_TYPE = 0;
	
	@Column(name = "SELLER_NUMBER")
	public int SELLER_NUMBER = 0;
	
	@Column(name = "SERVICER_NUMBER")
	public int SERVICER_NUMBER = 0;
	
	@Column(name = "PAYEE_CODE")
	public int PAYEE_CODE = 0;
	
	@Column(name = "FANNIE_MAE_LN")
	public String FANNIE_MAE_LN = null;
	
	@Column(name = "LENDER_LOAN_NO")
	public String LENDER_LOAN_NO = null;
	
	@Column(name = "MTG_ID_NO")
	public String MTG_ID_NO = null;
	
	@Column(name = "PARTICIPATION")
	public int PARTICIPATION = 0;
	
	@Column(name = "CONTRACT_NO")
	public int CONTRACT_NO = 0;
	
	@Column(name = "CONTRACT_TYPE")
	public String CONTRACT_TYPE = null;
	
	@Column(name = "AMORTIZATION_TYPE")
	public int AMORTIZATION_TYPE = 0;
	
	@Column(name = "PLAN_NUMBER")
	public int PLAN_NUMBER = 0;
	
	@Column(name = "FUNDING_OPTION")
	public String FUNDING_OPTION = null;
	
	@Column(name = "CONTRACT_YIELD")
	public String CONTRACT_YIELD = null;
	
	@Column(name = "REQRD_YIELD")
	public String REQRD_YIELD = null;
	
	@Column(name = "CONTRACT_MRGN")
	public BigDecimal CONTRACT_MRGN = null;
	
	@Column(name = "REQRD_MRGN")
	public BigDecimal REQRD_MRGN = null;
	
	@Column(name = "MRTG_CEILING")
	public BigDecimal MRTG_CEILING = null;
	
	@Column(name = "REQRD_CEILING")
	public BigDecimal REQRD_CEILING = null;
	
	@Column(name = "YDA_BEF")
	public String YDA_BEF = null;
	
	@Column(name = "YDA_AFT")
	public String YDA_AFT = null;
	
	@Column(name = "PASS_THRU_RATE")
	public BigDecimal PASS_THRU_RATE = null; 
	
	@Column(name = "INTEREST_RATE")
	public BigDecimal INTEREST_RATE = null;
	
	@Column(name = "MRTG_MRG")
	public BigDecimal MRTG_MRG = null;
	
	@Column(name = "COMMIT_FEE_RATE")
	public BigDecimal COMMIT_FEE_RATE = null;
	
	@Column(name = "SERVICE_FEE_PCT_AMT_RATE")
	public BigDecimal SERVICE_FEE_PCT_AMT_RATE = null;
	
	@Column(name = "FIXED_INSTALLMN")
	public BigDecimal FIXED_INSTALLMN = null;
	
	@Column(name = "FIRST_DUE_DATE")
	public Date FIRST_DUE_DATE = null;
	
	@Column(name = "INT_EFFECTIVE_DATE")
	public Date INT_EFFECTIVE_DATE = null;
	
	@Column(name = "PMT_EFFECTIVE_DT")
	public Date PMT_EFFECTIVE_DT = null;
	
	@Column(name = "LPI_DATE")
	public Date LPI_DATE = null;
	
	@Column(name = "SCHED_LPI_DATE")
	public Date SCHED_LPI_DATE = null;
	
	@Column(name = "ORIGINAL_TERM")
	public int ORIGINAL_TERM = 0;
	
	@Column(name = "PURCHASE_PRICE")
	public BigDecimal PURCHASE_PRICE = null;
	
	@Column(name = "OWNER_OCCUPIED")
	public int OWNER_OCCUPIED = 0;
	
	@Column(name = "STREET_ADDRESS")
	public String STREET_ADDRESS = null;
	
	@Column(name = "CITY")
	public String CITY = null;
	
	@Column(name = "STATE")
	public String STATE = null;
	
	@Column(name = "ZIP")
	public String ZIP = null;
	
	@Column(name = "PRIN_PURCHASED")
	public BigDecimal PRIN_PURCHASED = null;
	
	@Column(name = "INT_PURCHASED")
	public String INT_PURCHASED = null;
	
	@Column(name = "DISCNT_PREM")
	public String DISCNT_PREM = null;
	
	@Column(name = "INT_SHRTFALL_AD")
	public String INT_SHRTFALL_AD = null;
	
	@Column(name = "PRICE_ADJMNTS_INVESTOR_OWNED")
	public String PRICE_ADJMNTS_INVESTOR_OWNED = null;
	
	@Column(name = "PRICE_ADJMNTS_RATE_DIFFERTL")
	public String PRICE_ADJMNTS_RATE_DIFFERTL = null;
	
	@Column(name = "PRICE_ADJMNTS_NEGOTIATED")
	public String PRICE_ADJMNTS_NEGOTIATED = null;
	
	@Column(name = "PRICE_ADJMNTS_MISC_RISK_ADJ")
	public String PRICE_ADJMNTS_MISC_RISK_ADJ = null;
	
	@Column(name = "PRICE_ADJMNTS_COMMITMENT_FEE")
	public String PRICE_ADJMNTS_COMMITMENT_FEE = null;
	
	@Column(name = "AMT_DISBURSED")
	public String AMT_DISBURSED = null;
	
	@Column(name = "AMT_NON_DISBURS")
	public String AMT_NON_DISBURS = null;
	
	@Column(name = "AMT_ASAP")
	public String AMT_ASAP = null;
	
	@Column(name = "SFC")
	public String SFC = null;
	
	@Column(name = "LTV")
	public BigDecimal LTV = null;
	
	@Column(name = "FICO")
	public int FICO = 0;
	
	@Column(name = "Cash_Out_Refinance")
	public String Cash_Out_Refinance = null;
	
	@Column(name = "Standard_Subordinate_Financing")
	public String Standard_Subordinate_Financing = null;
	
	@Column(name = "Investment_Property")
	public String Investment_Property = null;
	
	@Column(name = "Credit_Score_LTV_Adj_Flow")
	public String Credit_Score_LTV_Adj_Flow = null;
	
	@Column(name = "Adverse_Market_Delivery_Charge")
	public String Adverse_Market_Delivery_Charge = null;
	
	@Column(name = "Condo_Properties")
	public String Condo_Properties = null;
	
	@Column(name = "Property_Inspection_Waiver")
	public String Property_Inspection_Waiver = null;
	
	@Column(name = "Pricing_Cap_Offset")
	public String Pricing_Cap_Offset = null;
	
	@Column(name = "Affinity_Reverse_Credit")
	public String Affinity_Reverse_Credit = null;
	
		
	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	
	public Date getPURCHASE_DATE() {
		return PURCHASE_DATE;
	}
	public void setPURCHASE_DATE(Date pURCHASE_DATE) {
		PURCHASE_DATE = pURCHASE_DATE;
	}
	public int getREMITTANCE_TYPE() {
		return REMITTANCE_TYPE;
	}
	public void setREMITTANCE_TYPE(int rEMITTANCE_TYPE) {
		REMITTANCE_TYPE = rEMITTANCE_TYPE;
	}
	public int getSELLER_NUMBER() {
		return SELLER_NUMBER;
	}
	public void setSELLER_NUMBER(int sELLER_NUMBER) {
		SELLER_NUMBER = sELLER_NUMBER;
	}
	public int getSERVICER_NUMBER() {
		return SERVICER_NUMBER;
	}
	public void setSERVICER_NUMBER(int sERVICER_NUMBER) {
		SERVICER_NUMBER = sERVICER_NUMBER;
	}
	public int getPAYEE_CODE() {
		return PAYEE_CODE;
	}
	public void setPAYEE_CODE(int pAYEE_CODE) {
		PAYEE_CODE = pAYEE_CODE;
	}
	public String getFANNIE_MAE_LN() {
		return FANNIE_MAE_LN;
	}
	public void setFANNIE_MAE_LN(String fANNIE_MAE_LN) {
		FANNIE_MAE_LN = fANNIE_MAE_LN;
	}
	public String getLENDER_LOAN_NO() {
		return LENDER_LOAN_NO;
	}
	public void setLENDER_LOAN_NO(String lENDER_LOAN_NO) {
		LENDER_LOAN_NO = lENDER_LOAN_NO;
	}
	public String getMTG_ID_NO() {
		return MTG_ID_NO;
	}
	public void setMTG_ID_NO(String mTG_ID_NO) {
		MTG_ID_NO = mTG_ID_NO;
	}
	public int getPARTICIPATION() {
		return PARTICIPATION;
	}
	public void setPARTICIPATION(int pARTICIPATION) {
		PARTICIPATION = pARTICIPATION;
	}
	public int getCONTRACT_NO() {
		return CONTRACT_NO;
	}
	public void setCONTRACT_NO(int cONTRACT_NO) {
		CONTRACT_NO = cONTRACT_NO;
	}
	public String getCONTRACT_TYPE() {
		return CONTRACT_TYPE;
	}
	public void setCONTRACT_TYPE(String cONTRACT_TYPE) {
		CONTRACT_TYPE = cONTRACT_TYPE;
	}
	public int getAMORTIZATION_TYPE() {
		return AMORTIZATION_TYPE;
	}
	public void setAMORTIZATION_TYPE(int aMORTIZATION_TYPE) {
		AMORTIZATION_TYPE = aMORTIZATION_TYPE;
	}
	public int getPLAN_NUMBER() {
		return PLAN_NUMBER;
	}
	public void setPLAN_NUMBER(int pLAN_NUMBER) {
		PLAN_NUMBER = pLAN_NUMBER;
	}
	public String getFUNDING_OPTION() {
		return FUNDING_OPTION;
	}
	public void setFUNDING_OPTION(String fUNDING_OPTION) {
		FUNDING_OPTION = fUNDING_OPTION;
	}
	public String getCONTRACT_YIELD() {
		return CONTRACT_YIELD;
	}
	public void setCONTRACT_YIELD(String cONTRACT_YIELD) {
		CONTRACT_YIELD = cONTRACT_YIELD;
	}
	public String getREQRD_YIELD() {
		return REQRD_YIELD;
	}
	public void setREQRD_YIELD(String rEQRD_YIELD) {
		REQRD_YIELD = rEQRD_YIELD;
	}
	public BigDecimal getCONTRACT_MRGN() {
		return CONTRACT_MRGN;
	}
	public void setCONTRACT_MRGN(BigDecimal cONTRACT_MRGN) {
		CONTRACT_MRGN = cONTRACT_MRGN;
	}
	public BigDecimal getREQRD_MRGN() {
		return REQRD_MRGN;
	}
	public void setREQRD_MRGN(BigDecimal rEQRD_MRGN) {
		REQRD_MRGN = rEQRD_MRGN;
	}
	public BigDecimal getMRTG_CEILING() {
		return MRTG_CEILING;
	}
	public void setMRTG_CEILING(BigDecimal mRTG_CEILING) {
		MRTG_CEILING = mRTG_CEILING;
	}
	public BigDecimal getREQRD_CEILING() {
		return REQRD_CEILING;
	}
	public void setREQRD_CEILING(BigDecimal rEQRD_CEILING) {
		REQRD_CEILING = rEQRD_CEILING;
	}
	public String getYDA_BEF() {
		return YDA_BEF;
	}
	public void setYDA_BEF(String yDA_BEF) {
		YDA_BEF = yDA_BEF;
	}
	public String getYDA_AFT() {
		return YDA_AFT;
	}
	public void setYDA_AFT(String yDA_AFT) {
		YDA_AFT = yDA_AFT;
	}
	public BigDecimal getPASS_THRU_RATE() {
		return PASS_THRU_RATE;
	}
	public void setPASS_THRU_RATE(BigDecimal pASS_THRU_RATE) {
		PASS_THRU_RATE = pASS_THRU_RATE;
	}
	public BigDecimal getINTEREST_RATE() {
		return INTEREST_RATE;
	}
	public void setINTEREST_RATE(BigDecimal iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
	public BigDecimal getMRTG_MRG() {
		return MRTG_MRG;
	}
	public void setMRTG_MRG(BigDecimal mRTG_MRG) {
		MRTG_MRG = mRTG_MRG;
	}
	public BigDecimal getCOMMIT_FEE_RATE() {
		return COMMIT_FEE_RATE;
	}
	public void setCOMMIT_FEE_RATE(BigDecimal cOMMIT_FEE_RATE) {
		COMMIT_FEE_RATE = cOMMIT_FEE_RATE;
	}
	public BigDecimal getSERVICE_FEE_PCT_AMT_RATE() {
		return SERVICE_FEE_PCT_AMT_RATE;
	}
	public void setSERVICE_FEE_PCT_AMT_RATE(BigDecimal sERVICE_FEE_PCT_AMT_RATE) {
		SERVICE_FEE_PCT_AMT_RATE = sERVICE_FEE_PCT_AMT_RATE;
	}
	public BigDecimal getFIXED_INSTALLMN() {
		return FIXED_INSTALLMN;
	}
	public void setFIXED_INSTALLMN(BigDecimal fIXED_INSTALLMN) {
		FIXED_INSTALLMN = fIXED_INSTALLMN;
	}
	public Date getFIRST_DUE_DATE() {
		return FIRST_DUE_DATE;
	}
	public void setFIRST_DUE_DATE(Date fIRST_DUE_DATE) {
		FIRST_DUE_DATE = fIRST_DUE_DATE;
	}
	public Date getINT_EFFECTIVE_DATE() {
		return INT_EFFECTIVE_DATE;
	}
	public void setINT_EFFECTIVE_DATE(Date iNT_EFFECTIVE_DATE) {
		INT_EFFECTIVE_DATE = iNT_EFFECTIVE_DATE;
	}
	public Date getPMT_EFFECTIVE_DT() {
		return PMT_EFFECTIVE_DT;
	}
	public void setPMT_EFFECTIVE_DT(Date pMT_EFFECTIVE_DT) {
		PMT_EFFECTIVE_DT = pMT_EFFECTIVE_DT;
	}
	public Date getLPI_DATE() {
		return LPI_DATE;
	}
	public void setLPI_DATE(Date lPI_DATE) {
		LPI_DATE = lPI_DATE;
	}
	public Date getSCHED_LPI_DATE() {
		return SCHED_LPI_DATE;
	}
	public void setSCHED_LPI_DATE(Date sCHED_LPI_DATE) {
		SCHED_LPI_DATE = sCHED_LPI_DATE;
	}
	public int getORIGINAL_TERM() {
		return ORIGINAL_TERM;
	}
	public void setORIGINAL_TERM(int oRIGINAL_TERM) {
		ORIGINAL_TERM = oRIGINAL_TERM;
	}
	public BigDecimal getPURCHASE_PRICE() {
		return PURCHASE_PRICE;
	}
	public void setPURCHASE_PRICE(BigDecimal pURCHASE_PRICE) {
		PURCHASE_PRICE = pURCHASE_PRICE;
	}
	public int getOWNER_OCCUPIED() {
		return OWNER_OCCUPIED;
	}
	public void setOWNER_OCCUPIED(int oWNER_OCCUPIED) {
		OWNER_OCCUPIED = oWNER_OCCUPIED;
	}
	public String getSTREET_ADDRESS() {
		return STREET_ADDRESS;
	}
	public void setSTREET_ADDRESS(String sTREET_ADDRESS) {
		STREET_ADDRESS = sTREET_ADDRESS;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getZIP() {
		return ZIP;
	}
	public void setZIP(String zIP) {
		ZIP = zIP;
	}
	public BigDecimal getPRIN_PURCHASED() {
		return PRIN_PURCHASED;
	}
	public void setPRIN_PURCHASED(BigDecimal pRIN_PURCHASED) {
		PRIN_PURCHASED = pRIN_PURCHASED;
	}
	public String getINT_PURCHASED() {
		return INT_PURCHASED;
	}
	public void setINT_PURCHASED(String iNT_PURCHASED) {
		INT_PURCHASED = iNT_PURCHASED;
	}
	public String getDISCNT_PREM() {
		return DISCNT_PREM;
	}
	public void setDISCNT_PREM(String dISCNT_PREM) {
		DISCNT_PREM = dISCNT_PREM;
	}
	public String getINT_SHRTFALL_AD() {
		return INT_SHRTFALL_AD;
	}
	public void setINT_SHRTFALL_AD(String iNT_SHRTFALL_AD) {
		INT_SHRTFALL_AD = iNT_SHRTFALL_AD;
	}
	public String getPRICE_ADJMNTS_INVESTOR_OWNED() {
		return PRICE_ADJMNTS_INVESTOR_OWNED;
	}
	public void setPRICE_ADJMNTS_INVESTOR_OWNED(String pRICE_ADJMNTS_INVESTOR_OWNED) {
		PRICE_ADJMNTS_INVESTOR_OWNED = pRICE_ADJMNTS_INVESTOR_OWNED;
	}
	public String getPRICE_ADJMNTS_RATE_DIFFERTL() {
		return PRICE_ADJMNTS_RATE_DIFFERTL;
	}
	public void setPRICE_ADJMNTS_RATE_DIFFERTL(String pRICE_ADJMNTS_RATE_DIFFERTL) {
		PRICE_ADJMNTS_RATE_DIFFERTL = pRICE_ADJMNTS_RATE_DIFFERTL;
	}
	public String getPRICE_ADJMNTS_NEGOTIATED() {
		return PRICE_ADJMNTS_NEGOTIATED;
	}
	public void setPRICE_ADJMNTS_NEGOTIATED(String pRICE_ADJMNTS_NEGOTIATED) {
		PRICE_ADJMNTS_NEGOTIATED = pRICE_ADJMNTS_NEGOTIATED;
	}
	public String getPRICE_ADJMNTS_MISC_RISK_ADJ() {
		return PRICE_ADJMNTS_MISC_RISK_ADJ;
	}
	public void setPRICE_ADJMNTS_MISC_RISK_ADJ(String pRICE_ADJMNTS_MISC_RISK_ADJ) {
		PRICE_ADJMNTS_MISC_RISK_ADJ = pRICE_ADJMNTS_MISC_RISK_ADJ;
	}
	public String getPRICE_ADJMNTS_COMMITMENT_FEE() {
		return PRICE_ADJMNTS_COMMITMENT_FEE;
	}
	public void setPRICE_ADJMNTS_COMMITMENT_FEE(String pRICE_ADJMNTS_COMMITMENT_FEE) {
		PRICE_ADJMNTS_COMMITMENT_FEE = pRICE_ADJMNTS_COMMITMENT_FEE;
	}
	public String getAMT_DISBURSED() {
		return AMT_DISBURSED;
	}
	public void setAMT_DISBURSED(String aMT_DISBURSED) {
		AMT_DISBURSED = aMT_DISBURSED;
	}
	public String getAMT_NON_DISBURS() {
		return AMT_NON_DISBURS;
	}
	public void setAMT_NON_DISBURS(String aMT_NON_DISBURS) {
		AMT_NON_DISBURS = aMT_NON_DISBURS;
	}
	public String getAMT_ASAP() {
		return AMT_ASAP;
	}
	public void setAMT_ASAP(String aMT_ASAP) {
		AMT_ASAP = aMT_ASAP;
	}
	public String getSFC() {
		return SFC;
	}
	public void setSFC(String sFC) {
		SFC = sFC;
	}
	public BigDecimal getLTV() {
		return LTV;
	}
	public void setLTV(BigDecimal lTV) {
		LTV = lTV;
	}
	public int getFICO() {
		return FICO;
	}
	public void setFICO(int fICO) {
		FICO = fICO;
	}
	public String getCash_Out_Refinance() {
		return Cash_Out_Refinance;
	}
	public void setCash_Out_Refinance(String cash_Out_Refinance) {
		Cash_Out_Refinance = cash_Out_Refinance;
	}
	public String getStandard_Subordinate_Financing() {
		return Standard_Subordinate_Financing;
	}
	public void setStandard_Subordinate_Financing(
			String standard_Subordinate_Financing) {
		Standard_Subordinate_Financing = standard_Subordinate_Financing;
	}
	public String getInvestment_Property() {
		return Investment_Property;
	}
	public void setInvestment_Property(String investment_Property) {
		Investment_Property = investment_Property;
	}
	public String getCredit_Score_LTV_Adj_Flow() {
		return Credit_Score_LTV_Adj_Flow;
	}
	public void setCredit_Score_LTV_Adj_Flow(String credit_Score_LTV_Adj_Flow) {
		Credit_Score_LTV_Adj_Flow = credit_Score_LTV_Adj_Flow;
	}
	public String getAdverse_Market_Delivery_Charge() {
		return Adverse_Market_Delivery_Charge;
	}
	public void setAdverse_Market_Delivery_Charge(
			String adverse_Market_Delivery_Charge) {
		Adverse_Market_Delivery_Charge = adverse_Market_Delivery_Charge;
	}
	public String getCondo_Properties() {
		return Condo_Properties;
	}
	public void setCondo_Properties(String condo_Properties) {
		Condo_Properties = condo_Properties;
	}
	public String getProperty_Inspection_Waiver() {
		return Property_Inspection_Waiver;
	}
	public void setProperty_Inspection_Waiver(String property_Inspection_Waiver) {
		Property_Inspection_Waiver = property_Inspection_Waiver;
	}
	public String getPricing_Cap_Offset() {
		return Pricing_Cap_Offset;
	}
	public void setPricing_Cap_Offset(String pricing_Cap_Offset) {
		Pricing_Cap_Offset = pricing_Cap_Offset;
	}
	public String getAffinity_Reverse_Credit() {
		return Affinity_Reverse_Credit;
	}
	public void setAffinity_Reverse_Credit(String affinity_Reverse_Credit) {
		Affinity_Reverse_Credit = affinity_Reverse_Credit;
	}
	
	
	
	
	


}
