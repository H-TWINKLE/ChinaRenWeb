package com.jwj.entity;

public class UserFocus {
	
	private int id;
	private User u_id;
	private User ru_id;
	private String dates;
	
	
	public UserFocus() {
		super();
	}

	public UserFocus(int id, User u_id, User ru_id, String dates) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.ru_id = ru_id;
		this.dates = dates;
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
