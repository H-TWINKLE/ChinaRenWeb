package com.jwj.entity;

public class User {

	private int id;
	private String admin;
	private String password;
	private String name;
	private String nickname;
	private String sex;
	private String code;
	private String dates;
	private String img;
	private Clazz clazz;
	private UserMore usermore;

	private String auto;

	private int isadmin;
	private int point;

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return getAdmin() + " // " + getName() + "  //  ";
	}

	public User(int id, String admin, String name, String nickname) {
		super();
		this.id = id;
		this.admin = admin;
		this.name = name;
		this.nickname = nickname;
	}

	public User(String admin, String name, String nickname, String sex, Clazz clazz, UserMore usermore) {
		super();
		this.admin = admin;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.clazz = clazz;
		this.usermore = usermore;
	}

	public User(int id, String admin, String password, String name, String nickname, String sex, String code,
			String dates, String img, Clazz clazz, UserMore usermore, String auto) {
		super();
		this.id = id;
		this.admin = admin;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.code = code;
		this.dates = dates;
		this.img = img;
		this.clazz = clazz;
		this.usermore = usermore;
		this.auto = auto;
	}

	public User(int id, String admin, String name, String nickname, String auto) {
		super();
		this.id = id;
		this.admin = admin;
		this.name = name;
		this.nickname = nickname;
		this.auto = auto;
	}

	public User() {
		// TODO 自动生成的构造函数存根
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public UserMore getUsermore() {
		return usermore;
	}

	public void setUsermore(UserMore usermore) {
		this.usermore = usermore;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

}
