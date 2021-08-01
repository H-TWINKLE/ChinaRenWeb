package com.jwj.entity;

public class Tip {
	
	private int id;
	private User u_id;
	private String content;
	private int isread;
	private String dates;
	
	
	
	
	
	public Tip(User u_id, String content, int isread, String dates) {
		super();
		
		this.u_id = u_id;
		this.content = content;
		this.isread = isread;
		this.dates = dates;
	}
	public Tip() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getU_id() {
		return u_id;
	}
	public void setU_id(User u_id) {
		this.u_id = u_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	

}
