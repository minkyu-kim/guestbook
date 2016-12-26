package com.basecamp.guestbook;

import java.util.Date;

public class Message {
	private int id;
	private String email;
	private int pass;
	private String message;
	private Date submitTime;
	private Date updateTime;
	
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
	
	public Date getSubmitTime() {
		return submitTime;
	}
		
	public Message setSubmitTime(Date submitTime) {
		this.submitTime=submitTime;
		return this;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public Message setUpdateTime(Date updateTime) {
		this.updateTime=updateTime;
		return this;
	}
}