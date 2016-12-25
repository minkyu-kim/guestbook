package com.basecamp.guestbook;

public class Message {
	private int id;
	private String email;
	private int pass;
	private String message;
	private String submitTime;
	private String updateTime;
	
	public int getId() {
		return id;
	}
	
	public Message setId(int id) {
		this.id=id;
		return this;
	}
	
	public int getPass() {
		return pass;
	}
	
	public Message setPass(int pass) {
		this.pass=pass;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Message setEmail(String email) {
		this.email=email;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Message setMessage(String message) {
		this.message=message;
		return this;
	}
	
	public String getSubmitTime() {
		return submitTime;
	}
		
	public Message setSubmitTime(String submitTime) {
		this.submitTime=submitTime;
		return this;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public Message setUpdateTime(String updateTime) {
		this.updateTime=updateTime;
		return this;
	}
}