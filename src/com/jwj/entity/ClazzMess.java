package com.jwj.entity;

public class ClazzMess {
	private int id;
	private String content;
	private User u_id;
	private Clazz c_id;
	private String dates;
	
	public ClazzMess() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public ClazzMess(int id, String content, User u_id, Clazz c_id, String dates) {
		super();
		this.id = id;
		this.content = content;
		this.u_id = u_id;
		this.c_id = c_id;
		this.dates = dates;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getU_id() {
		return u_id;
	}
	public void setU_id(User u_id) {
		this.u_id = u_id;
	}
	public Clazz getC_id() {
		return c_id;
	}
	public void setC_id(Clazz c_id) {
		this.c_id = c_id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	

}
