package com.oss.model;

public class Member {
	private String userid;		// 아이디
	private String password;	// 비밀번호 
	private String name;		// 이름
	
	public Member() {
		
	}
	
	public Member(String userid, String password, String name) {
		this.userid = userid;
		this.password = password;
		this.name = name;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

