package com.jwj.entity;

public class ClazzPic {
	
	private int id;
	private Clazz clazz_id;
	private String pic;
	private User u_id;
	private String dates;
	
	public ClazzPic(int id, Clazz clazz_id, String pic, User u_id, String dates) {
		super();
		this.id = id;
		this.clazz_id = clazz_id;
		this.pic = pic;
		this.u_id = u_id;
		this.dates = dates;
	}

	public ClazzPic() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clazz getClazz_id() {
		return clazz_id;
	}

	public void setClazz_id(Clazz clazz_id) {
		this.clazz_id = clazz_id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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
