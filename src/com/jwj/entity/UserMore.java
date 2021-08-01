package com.jwj.entity;

public class UserMore {
	private int id;
	private String year;
	private String tel;
	private String birth;
	private String home;
	private String live;

	public UserMore() {
		super();
	}

	public UserMore(int id) {
		super();
		this.id = id;
	}
	
	

	

	public UserMore(String year, String tel, String birth, String home, String live) {
		super();
		this.year = year;
		this.tel = tel;
		this.birth = birth;
		this.home = home;
		this.live = live;
	}

	public UserMore(int id, String year, String tel, String birth, String home, String live) {
		super();
		this.id = id;
		this.year = year;
		this.tel = tel;
		this.birth = birth;
		this.home = home;
		this.live = live;
	}

	

	public UserMore(String tel) {
		super();
		this.tel = tel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}

}
