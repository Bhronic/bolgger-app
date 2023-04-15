package com.blogger.model;

public class User {
	private int id;

	private String name;

	private String emailId;

	private String age;

	private String contactNo;

	public User() {
	}

	public User(int id, String name, String emailId, String age, String contactNo) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.age = age;
		this.contactNo = contactNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
