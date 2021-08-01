package com.jwj.entity;

public class UserComplaint {

	private int id;
	private User u_id;
	private String content;
	private String types;
	private String dates;

	public UserComplaint() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public UserComplaint( String content, String types) {
		super();		
		this.content = content;
		this.types = types;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

}
