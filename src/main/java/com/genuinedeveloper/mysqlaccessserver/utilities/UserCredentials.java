package com.genuinedeveloper.mysqlaccessserver.utilities;

public class UserCredentials {

	private char[] username;
	
	private char[] password;
	
	private Integer id;
	
	public char[] getUsername() {
		
		return username;
		
	}
	
	public void setUsername(char[] username) {
		
		this.username = username;
		
	}
	
	public char[] getPassword() {
		
		return password;
		
	}
	
	public void setPassword(char[] password) {
		
		this.password = password;
		
	}
	
	public Integer getId () {
		
		return id;
		
	}
	
	public void setId (Integer id) {
		this.id = id;
	}
}
