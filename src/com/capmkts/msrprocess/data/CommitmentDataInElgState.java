package com.capmkts.msrprocess.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[CommitmentData_InElgState]")
public class CommitmentDataInElgState implements Serializable {

	@Id
	@GeneratedValue
	private int tableID;

	@Column(name = "Active")
	private Boolean active;

	@Column(name = "State")
	private String state;

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
