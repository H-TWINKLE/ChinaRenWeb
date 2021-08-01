package com.jwj.entity;

public class UserMess {
	
	private int m_id;
	private String message;
	private User u_id;
	private User ru_id;
	private String dates;
	private String pic;
	
	
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public User getU_id() {
		return u_id;
	}
	public void setU_id(User u_id) {
		this.u_id = u_id;
	}
	public User getRu_id() {
		return ru_id;
	}
	public void setRu_id(User ru_id) {
		this.ru_id = ru_id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	

}
