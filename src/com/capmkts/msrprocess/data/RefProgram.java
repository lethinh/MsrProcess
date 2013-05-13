package com.capmkts.msrprocess.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class RefProgram {

	/*[LoanProgramID] [int] NOT NULL,
	[ProgramDesc] [nvarchar](255) NULL,
	[LoanTerm] [int] NULL,
	[AmortTerm] [int] NULL,
	[AmortType] [nvarchar](255) NULL,
	[LoanType] [int] NULL,
	[LoanTypeDesc] [nvarchar](255) NULL,
	[LienPosition] [int] NULL,
	[FNMASETProductName] [nvarchar](255) NULL,
	[LC_LOType] [int] NULL,*/
	
	@Id
	@GeneratedValue
	private Integer tableID;
	
	@Column(name = "LoanProgramID")
	public int LoanProgramID = 0;
	
	@Column(name = "ProgramDesc")
	public String ProgramDesc = null;
	
	@Column(name = "LoanTerm")
	public int LoanTerm = 0;
	
	@Column(name = "AmortTerm")
	public int AmortTerm = 0;
	
	@Column(name = "AmortType")
	public String AmortType = null;
	
	@Column(name = "LoanType")
	public int LoanType = 0;
	
	@Column(name = "LienPosition")
	public int LienPosition = 0;
	
	@Column(name = "FNMASETProductName")
	public String FNMASETProductName = null;
	
	@Column(name = "LC_LOType")
	public int LC_LOType = 0;

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer tableID) {
		this.tableID = tableID;
	}

	public int getLoanProgramID() {
		return LoanProgramID;
	}

	public void setLoanProgramID(int loanProgramID) {
		LoanProgramID = loanProgramID;
	}

	public String getProgramDesc() {
		return ProgramDesc;
	}

	public void setProgramDesc(String programDesc) {
		ProgramDesc = programDesc;
	}

	public int getLoanTerm() {
		return LoanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		LoanTerm = loanTerm;
	}

	public int getAmortTerm() {
		return AmortTerm;
	}

	public void setAmortTerm(int amortTerm) {
		AmortTerm = amortTerm;
	}

	public String getAmortType() {
		return AmortType;
	}

	public void setAmortType(String amortType) {
		AmortType = amortType;
	}

	public int getLoanType() {
		return LoanType;
	}

	public void setLoanType(int loanType) {
		LoanType = loanType;
	}

	public int getLienPosition() {
		return LienPosition;
	}

	public void setLienPosition(int lienPosition) {
		LienPosition = lienPosition;
	}

	public String getFNMASETProductName() {
		return FNMASETProductName;
	}

	public void setFNMASETProductName(String fNMASETProductName) {
		FNMASETProductName = fNMASETProductName;
	}

	public int getLC_LOType() {
		return LC_LOType;
	}

	public void setLC_LOType(int lC_LOType) {
		LC_LOType = lC_LOType;
	}
	
}
