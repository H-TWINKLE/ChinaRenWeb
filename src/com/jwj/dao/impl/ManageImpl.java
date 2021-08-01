package com.jwj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.ManageDao;
import com.jwj.entity.Clazz;
import com.jwj.entity.Manage;
import com.jwj.entity.Tip;
import com.jwj.entity.User;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;
import com.jwj.init.InitString;

public class ManageImpl implements ManageDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public ManageImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Manage doAdminLogin(String admin, String pass) throws Exception {
		Manage manage = null;

		try {
			this.pstmt = this.conn.prepareStatement(InitString.ManageLogin);
			this.pstmt.setString(1, admin);
			this.pstmt.setString(2, pass);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				manage = new Manage(rs.getInt("id"), rs.getString("admin"), rs.getString("password"),
						rs.getString("name"));
			}

			return manage;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<Clazz> findAllClazz() throws Exception {
		List<Clazz> list = new ArrayList<>();
		Clazz clazz = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.manageFindAll);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				clazz = new Clazz();
				clazz.setId(rs.getInt("id"));
				clazz.setClazzn(rs.getString("clazzn"));
				clazz.setGraden(rs.getString("graden"));
				clazz.setMajorn(rs.getString("majorn"));
				clazz.setCollegen(rs.getString("collegen"));
				clazz.setDates(rs.getString("dates"));
				clazz.setAuto(rs.getString("auto"));
				list.add(clazz);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public boolean doUpClazz(Clazz clazz) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.upClazz);
			this.pstmt.setString(1, clazz.getClazzn());
			this.pstmt.setString(2, clazz.getGraden());
			this.pstmt.setString(3, clazz.getMajorn());
			this.pstmt.setString(4, clazz.getCollegen());
			this.pstmt.setString(5, clazz.getDates());
			this.pstmt.setString(6, clazz.getAuto());
			this.pstmt.setInt(7, clazz.getId());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<UserQues> findAllQues(int begin, int end) throws Exception {
		List<UserQues> list = new ArrayList<>();
		UserQues ques = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findAllQues);
			this.pstmt.setInt(1, begin);
			this.pstmt.setInt(2, end);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				ques = new UserQues();
				ques.setQ_id(rs.getInt("q_id"));
				ques.setTitle(rs.getString("title"));
				ques.setContent(rs.getString("content"));
				ques.setDates(rs.getString("dates"));
				ques.setU_id(new User(rs.getInt("u_id"), rs.getString("name")));
				list.add(ques);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public boolean deleteComm(int id, String table, String col) throws Exception {
		String sql = "DELETE FROM " + table + " WHERE " + col + "=" + id;

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<UserMess> findAllUserMess() throws Exception {
		List<UserMess> list = new ArrayList<>();
		UserMess mess = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findAllUserMess);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				mess = new UserMess();
				mess.setM_id(rs.getInt("m_id"));
				mess.setMessage(rs.getString("message"));
				mess.setU_id(new User(rs.getInt("u_id"), rs.getString("uname")));
				mess.setRu_id(new User(rs.getInt("ru_id"), rs.getString("runame")));
				mess.setDates(rs.getString("dates"));
				list.add(mess);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public List<UserComplaint> findAllUserComplaint() throws Exception {
		List<UserComplaint> list = new ArrayList<>();
		UserComplaint complaint = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findAllUserComplaint);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				complaint = new UserComplaint();
				complaint.setId(rs.getInt("id"));
				complaint.setContent(rs.getString("content"));
				complaint.setU_id(new User(rs.getInt("u_id"), rs.getString("admin")));
				complaint.setDates(rs.getString("dates"));
				complaint.setTypes(rs.getString("types"));
				list.add(complaint);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public Clazz findOneClazz(int id) throws Exception {
		Clazz clazz = null;

		try {
			this.pstmt = this.conn.prepareStatement(InitString.findOneClazz);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				clazz = new Clazz(rs.getInt("id"), rs.getString("clazzn"), rs.getString("graden"),
						rs.getString("majorn"), rs.getString("collegen"), rs.getString("auto"));
			}

			return clazz;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<UserMess> findAllMess() throws Exception {
		List<UserMess> list = new ArrayList<>();
		UserMess mess = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.manageFindUserMess);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				mess = new UserMess();
				mess.setM_id(rs.getInt("m_id"));
				mess.setMessage(rs.getString("message"));
				mess.setU_id(new User(rs.getInt("u_id"), rs.getString("uname")));
				mess.setRu_id(new User(rs.getInt("ru_id"), rs.getString("runame")));
				mess.setDates(rs.getString("dates"));
				list.add(mess);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public List<Tip> findAllTip() throws Exception {
		List<Tip> list = new ArrayList<>();
		Tip tip = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.manageFindTip);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				tip = new Tip();
				tip.setId(rs.getInt("id"));
				tip.setContent(rs.getString("content"));
				tip.setU_id(new User(rs.getInt("u_id"), rs.getString("uname")));
				tip.setDates(rs.getString("dates"));
				tip.setIsread(rs.getInt("isread"));
				list.add(tip);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public int getOneTableCount(String table, int id, String col) throws Exception {
		
		String sql = "SELECT count(*) 'count' from " + table + " WHERE " + col + " = " + id;
		
		if(id==0) {
			sql = "SELECT count(*) 'count' from " + table;
		}

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? rs.getInt("count") : 0;
		} finally {
			this.pstmt.close();
		}
	}

}
