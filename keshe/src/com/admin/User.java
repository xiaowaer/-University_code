package com.admin;


public class User {
	public User(String name, String applicantname) {
		super();
		this.name = name;
		Applicantname = applicantname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApplicantname() {
		return Applicantname;
	}
	public void setApplicantname(String applicantname) {
		Applicantname = applicantname;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String name;
	public String Applicantname;
	
}
