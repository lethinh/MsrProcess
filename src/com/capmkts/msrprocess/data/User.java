package com.capmkts.msrprocess.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[CoIssue_Test].[dbo].[User]")
public class User {

	/*    [USERNAME] [nchar](25) NOT NULL,
    [PASSWORD] [nchar](25) NOT NULL,
    [FIRST] [nchar](25) NOT NULL,
    [LAST] [nchar](25) NOT NULL,
    [ORIGINATORID] [int] NOT NULL,
    [EMAIL] [nchar](40) NULL,
    [PHONE] [nchar](25) NULL*/

	@Id
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRST")
	private String firstName;
	
	@Column(name = "LAST")
	private String lastName;
	
	@Column(name = "ORIGINATORID")
	private int originationId;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE")
	private String phone;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getOriginationId() {
		return originationId;
	}

	public void setOriginationId(int originationId) {
		this.originationId = originationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
