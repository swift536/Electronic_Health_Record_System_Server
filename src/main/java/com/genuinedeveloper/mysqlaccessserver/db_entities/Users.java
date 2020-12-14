package com.genuinedeveloper.mysqlaccessserver.db_entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name_first;

	private String name_last;

	private Date dob;

	private String hashed_username;

	private String hashed_password;

	private String security_question1;

	private String security_question2;

	private String security_question3;

	private String security_answer1;

	private String security_answer2;

	private String security_answer3;

	private boolean admin_status;

	public Users(String firstName, String lastName, Date dob, String username, String password, String sq1, String sq2,
			String sq3, String sa1, String sa2, String sa3) {

		name_first = firstName;
		name_last = lastName;
		this.dob = dob;
		hashed_username = username;
		hashed_password = password;
		security_question1 = sq1;
		security_question2 = sq2;
		security_question3 = sq3;
		security_answer1 = sa1;
		security_answer2 = sa2;
		security_answer3 = sa3;

	}
	
	public Users () {
		
	}

	@Column(name = "id")
	public Integer getId() {

		return id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	@Column(name = "name_first")
	public String getName_first() {

		return name_first;

	}

	public void setName_first(String name_first) {

		this.name_first = name_first;

	}

	@Column(name = "name_last")
	public String getName_last() {

		return name_last;

	}

	public void setName_last(String name_last) {

		this.name_last = name_last;

	}

	@Column(name = "dob")
	public Date getDob() {

		return dob;

	}

	public void setDob(Date dob) {

		this.dob = dob;

	}

	@Column(name = "hashed_username")
	public String getHashedUsername() {

		return hashed_username;

	}

	public void setHashedUsername(String hashed_username) {

		this.hashed_username = hashed_username;

	}

	@Column(name = "hashed_password")
	public String getHashedPassword() {

		return hashed_password;

	}

	public void setHashedPassword(String hashed_password) {

		this.hashed_password = hashed_password;

	}

	@Column(name = "security_question1")
	public String getSecurityQuestion1() {

		return security_question1;

	}

	public void setSecurityQuestion1(String security_question1) {

		this.security_question1 = security_question1;

	}

	@Column(name = "security_question2")
	public String getSecurityQuestion2() {

		return security_question2;

	}

	public void setSecurityQuestion2(String security_question2) {

		this.security_question2 = security_question2;

	}

	@Column(name = "security_question3")
	public String getSecurityQuestion3() {

		return security_question3;

	}

	public void setSecurity_question3(String security_question3) {

		this.security_question3 = security_question3;

	}

	@Column(name = "security_answer1")
	public String getSecurityAnswer1() {

		return security_answer1;

	}

	public void setSecurityAnswer1(String security_answer1) {

		this.security_answer1 = security_answer1;

	}

	@Column(name = "security_answer2")
	public String getSecurityAnswer2() {

		return security_answer2;

	}

	public void setSecurityAnswer2(String security_answer2) {

		this.security_answer2 = security_answer2;

	}

	@Column(name = "security_answer3")
	public String getSecurityAnswer3() {

		return security_answer3;

	}

	public void setSecurityAnswer3(String security_answer3) {

		this.security_answer3 = security_answer3;

	}

	@Column(name = "admin_status")
	public boolean getAdminStatus() {

		return admin_status;

	}
	
	public void setAdminStatus(boolean status) {

		admin_status = status;

	}

}
