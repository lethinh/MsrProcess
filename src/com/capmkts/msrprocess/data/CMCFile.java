package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[CMCFiles]")
public class CMCFile implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer tableID;
	
	@Column(name = "FileName")
	private String fileName;
	
	@Column(name = "FileData")
	private byte[] fileData;
	
	@Column(name = "FileType")
	private String fileType;
	
	@Column(name = "CreatedDate")
	private Date createdDate;
	
	@Column(name = "Valid")
	private Boolean valid;
	
	@Column(name = "InvalidMessage")
	private String invalidMessage;

	@Column(name = "CMCCommitmentNumber")
	private Integer cmcCommitmentNumber;

	@Column(name = "AgencyCommitmentNumber")
	private Integer agencyCommitmentNumber;
		
	@Column(name = "LoanNumber")
	private String loanNumber;


	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer tableID) {
		this.tableID = tableID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] file) {
		this.fileData = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	public Integer getCmcCommitmentNumber() {
		return cmcCommitmentNumber;
	}

	public void setCmcCommitmentNumber(Integer cmcCommitmentNumber) {
		this.cmcCommitmentNumber = cmcCommitmentNumber;
	}

	public Integer getAgencyCommitmentNumber() {
		return agencyCommitmentNumber;
	}

	public void setAgencyCommitmentNumber(Integer agencyCommitmentNumber) {
		this.agencyCommitmentNumber = agencyCommitmentNumber;
	}

			
}
