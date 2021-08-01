package com.jwj.dbc;

import java.sql.DriverManager;
import java.sql.Connection;
public class DatabaseConnection {
	private static final String DBDRIVER ="org.gjt.mm.mysql.Driver";
	private static final String DBURL=
		"jdbc:mysql://localhost:3306/chinaren"+
		"?useUnicode=true&characterEncoding=UTF-8";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "123";
	private Connection conn;
	public DatabaseConnection()throws Exception{
		Class.forName(DBDRIVER);
		this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
		
	}
	public Connection geConnection(){
		return this.conn;
	}
	public void close() throws Exception{
		if(this.conn!=null){
			try{this.conn.close();}
		catch (Exception e) {
			throw e;
		}
	}
	}
}
