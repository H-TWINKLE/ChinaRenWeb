package com.jwj.entity;

public class Replayuq {
	
	private int id;
	private int ques_id;
	private String content;
	private User u_id;
	private String dates;
	
	
	
	
	
	public Replayuq( int ques_id, String content, User u_id) {
		super();		
		this.ques_id = ques_id;
		this.content = content;
		this.u_id = u_id;		
	}

	public Replayuq() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
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

	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	
	

}
