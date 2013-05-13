package com.capmkts.msrprocess.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[CMC_CommitNumber]")
public class CMCCommitNumber {

	@Id
	@GeneratedValue
	private int tableID;

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	
}
