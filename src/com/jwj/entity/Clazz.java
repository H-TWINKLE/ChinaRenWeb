package com.jwj.entity;

public class Clazz {

	public Clazz(int id, String clazzn, String graden, String majorn, String collegen, String auto) {
		super();
		this.id = id;
		this.clazzn = clazzn;
		this.graden = graden;
		this.majorn = majorn;
		this.collegen = collegen;
		this.auto = auto;
	}

	private int id;
	private String clazzn;
	private String graden;
	private String majorn;
	private String collegen;
	private String dates;
	private String auto;

	public Clazz() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	

	public Clazz(int id,String clazzn, String graden, String majorn, String collegen) {
		super();
		this.id = id;
		this.clazzn = clazzn;
		this.graden = graden;
		this.majorn = majorn;
		this.collegen = collegen;
	}
	
	
	public Clazz(String clazzn, String graden, String majorn, String collegen) {
		super();
		this.clazzn = clazzn;
		this.graden = graden;
		this.majorn = majorn;
		this.collegen = collegen;
	}



	public Clazz(int id) {
		super();
		this.id = id;
	}



	public Clazz(int id, String clazzn, String graden, String majorn, String collegen, String dates, String auto) {
		super();
		this.id = id;
		this.clazzn = clazzn;
		this.graden = graden;
		this.majorn = majorn;
		this.collegen = collegen;
		this.dates = dates;
		this.auto = auto;
	}

	



	public Clazz(String clazzn, String graden, String majorn, String collegen, String auto) {
		super();
		this.clazzn = clazzn;
		this.graden = graden;
		this.majorn = majorn;
		this.collegen = collegen;		
		this.auto = auto;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClazzn() {
		return clazzn;
	}

	public void setClazzn(String clazzn) {
		this.clazzn = clazzn;
	}

	public String getGraden() {
		return graden;
	}

	public void setGraden(String graden) {
		this.graden = graden;
	}

	public String getMajorn() {
		return majorn;
	}

	public void setMajonr(String majorn) {
		this.majorn = majorn;
	}

	public String getCollegen() {
		return collegen;
	}

	public void setCollegen(String collegen) {
		this.collegen = collegen;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}



	public void setMajorn(String majorn) {
		this.majorn = majorn;
	}
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return "id:"+getId()+"clazz:"+getClazzn()+"grade:"+getGraden()+"major:"+getMajorn()+
				"college:"+getCollegen()+"dates"+getDates()+"auto"+getAuto();
	}
	
	

}
