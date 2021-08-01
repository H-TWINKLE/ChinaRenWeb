package com.jwj.entity;

public class UserQues {
	
	private int q_id;
	private String title;
	private String content;
	private User u_id;
	private String dates;
	
	
	
	
	public UserQues(String title, String content, User u_id) {
		super();
		this.title = title;
		this.content = content;
		this.u_id = u_id;
		
	}
	
	public UserQues() {
		super();
	}
	
	public UserQues(int q_id, String title, String content, User u_id, String dates) {
		super();
		this.q_id = q_id;
		this.title = title;
		this.content = content;
		this.u_id = u_id;
		this.dates = dates;
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
