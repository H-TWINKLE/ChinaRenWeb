package com.jwj.entity;

public class Manage {

	private int id;
	private String admin;
	private String password;
	private String name;

	public Manage() {
		super();
	}

	public Manage(int id, String admin, String password, String name) {
		super();
		this.id = id;
		this.admin = admin;
		this.password = password;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
