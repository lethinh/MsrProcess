package com.capmkts.msrprocess.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[ServRateSheets]")
public class ServRateSheets implements Serializable{
	
	@Id
	@Column(name = "ActiveTS")
	private Date activeTS;
	
	@Column(name = "NoteRate")
	private BigDecimal noteRate;

	@Column(name = "ServPrice")
	private BigDecimal servPrice;
	
	@Column(name = "ProgramID")
	private int programID;
	
	public BigDecimal getNoteRate() {
		return noteRate;
	}
	public void setNoteRate(BigDecimal noteRate) {
		this.noteRate = noteRate;
	}
	public BigDecimal getServPrice() {
		return servPrice;
	}
	public void setServPrice(BigDecimal servPrice) {
		this.servPrice = servPrice;
	}
	public int getProgramID(){
		return programID;
	}
	public void setProgramID(int programID){
		this.programID = programID;
	}
	public Date getActiveTS(){
		return activeTS;
	}
	public void setActiveTS(Date activeTS){
		this.activeTS = activeTS;
	}
}
