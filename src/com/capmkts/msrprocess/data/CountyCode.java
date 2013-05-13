package com.capmkts.msrprocess.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[StateCountyCodes]")
public class CountyCode implements Serializable {

	@Id
	@Column(name = "CountyCode")
	private String countyCode;
	
	@Column(name = "CountyName")
	private String countyName;

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
}
