package com.jwj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.MessDao;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;
import com.jwj.entity.ClazzMess;
import com.jwj.entity.Replayuq;
import com.jwj.entity.User;
import com.jwj.init.InitString;

public class MessImpl implements MessDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public MessImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<UserMess> finadAllU(int begin, int end, int id, String col) throws Exception {

		String findAllMessage = "SELECT usermess.m_id,\r\n" + "       usermess.message,\r\n"
				+ "       usermess.pic,\r\n" + "       usermess.dates 'um_dates',\r\n" + "       u.id 'u_id',\r\n"
				+ "       u.admin 'u_admin',\r\n" + "       u.nickname 'u_nickname',\r\n"
				+ "       u.name 'u_name',\r\n" + "       u.auto 'u_auto',\r\n" + "       ru.id 'ru_id',\r\n"
				+ "       ru.admin 'ru_admin',\r\n" + "       ru.nickname 'ru_nickname',\r\n"
				+ "       ru.name 'ru_name',\r\n" + "       ru.auto 'ru_auto'\r\n"
				+ "FROM (SELECT * FROM usermess ORDER BY usermess.dates DESC LIMIT " + begin + "," + end
				+ ") usermess \r\n" + "JOIN user u ON (u.id=usermess.u_id) \r\n"
				+ "JOIN user ru ON (ru.id=usermess.ru_id)\r\n" + "WHERE " + col + " = " + id + " AND ru_id != " + id
				+ ";";

		List<UserMess> list = new ArrayList<>();
		UserMess mess;

		try {
			this.pstmt = this.conn.prepareStatement(findAllMessage);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				mess = new UserMess();
				mess.setM_id(rs.getInt("m_id"));
				mess.setMessage(rs.getString("message"));
				mess.setDates(rs.getString("um_dates"));
				mess.setU_id(new User(rs.getInt("u_id"), rs.getString("u_admin"), rs.getString("u_name"),
						rs.getString("u_nickname"), rs.getString("u_auto")));
				mess.setRu_id(new User(rs.getInt("ru_id"), rs.getString("ru_admin"), rs.getString("ru_name"),
						rs.getString("ru_nickname"), rs.getString("ru_auto")));
				list.add(mess);
			}
		} finally {
			this.pstmt.close();
		}
		return list;

	}

	@Override
	public boolean doInsert(String table, String mess, String u_id, String ru_id) throws Exception {

		String InsertMess = "";

		if (table.equals("usermess")) {

			InsertMess = "INSERT INTO " + table + "(message,u_id,ru_id,dates) VALUES(?,?,?,?)";
		} else if (table.equals("clazzmess")) {

			InsertMess = "INSERT INTO " + table + "(content,u_id,c_id,dates) VALUES(?,?,?,?)";
		}

		try {
			this.pstmt = this.conn.prepareStatement(InsertMess);
			this.pstmt.setString(1, mess);
			this.pstmt.setInt(2, Integer.parseInt(u_id));
			this.pstmt.setInt(3, Integer.parseInt(ru_id));
			this.pstmt.setString(4, String.valueOf(System.currentTimeMillis()));
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<ClazzMess> findAllC(int begin, int end, int id, String col) throws Exception {
		String findAllMessage = "SELECT \r\n" + "clazzmess.id,\r\n" + "clazzmess.content,\r\n" + "clazzmess.dates,\r\n"
				+ "user.id 'u_id',\r\n" + "user.name\r\n"
				+ "FROM (SELECT * FROM clazzmess ORDER BY clazzmess.dates DESC LIMIT " + begin + "," + end
				+ ") clazzmess\r\n" + "JOIN user ON (user.id=clazzmess.u_id)\r\n" + "WHERE " + col + " = " + id
				+ ";\r\n" + "";

		List<ClazzMess> list = new ArrayList<>();
		ClazzMess clazzMess;

		try {
			this.pstmt = this.conn.prepareStatement(findAllMessage);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				clazzMess = new ClazzMess();
				clazzMess.setId(rs.getInt("id"));
				clazzMess.setContent(rs.getString("content"));
				clazzMess.setDates(rs.getString("dates"));
				clazzMess.setU_id(new User(rs.getInt("u_id"), rs.getString("name")));
				list.add(clazzMess);
			}
		} finally {
			this.pstmt.close();
		}
		return list;
	}

	@Override
	public List<UserMess> finadAllUS(int begin, int end, int id) throws Exception {
		String findAllMessage = "SELECT usermess.m_id,usermess.message,usermess.pic,usermess.dates 'um_dates' "
				+ "FROM (SELECT * FROM usermess ORDER BY usermess.dates DESC LIMIT " + begin + "," + end
				+ ") usermess WHERE u_id=? AND ru_id=?;";

		List<UserMess> list = new ArrayList<>();
		UserMess mess;

		try {
			this.pstmt = this.conn.prepareStatement(findAllMessage);
			this.pstmt.setInt(1, id);
			this.pstmt.setInt(2, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				mess = new UserMess();
				mess.setM_id(rs.getInt("m_id"));
				mess.setMessage(rs.getString("message"));
				mess.setDates(rs.getString("um_dates"));
				mess.setPic(rs.getString("pic"));
				list.add(mess);
			}
		} finally {
			this.pstmt.close();
		}
		return list;
	}

	@Override
	public boolean uploadUserMess(String img, int u_id, String mess) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.uploadUserMess);
			this.pstmt.setString(1, mess);
			this.pstmt.setInt(2, u_id);
			this.pstmt.setInt(3, u_id);
			this.pstmt.setString(4, String.valueOf(System.currentTimeMillis()));
			this.pstmt.setString(5, img);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<UserQues> findAllQues(int id,int begin,int end) throws Exception {

		List<UserQues> list = new ArrayList<>();
		UserQues userQues = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findAllOneQues);
			this.pstmt.setInt(1, id);
			this.pstmt.setInt(2, begin);
			this.pstmt.setInt(3, end);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				userQues = new UserQues();
				userQues.setQ_id(rs.getInt("q_id"));
				userQues.setTitle(rs.getString("title"));
				userQues.setContent(rs.getString("content"));
				userQues.setU_id(new User(rs.getInt("u_id")));
				userQues.setDates(rs.getString("dates"));
				list.add(userQues);
			}
		} finally {
			this.pstmt.close();
		}
		return list;
	}

	@Override
	public boolean insertOneQues(UserQues ques) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.insertOneQues);
			this.pstmt.setString(1, ques.getTitle());
			this.pstmt.setString(2, ques.getContent());
			this.pstmt.setInt(3, ques.getU_id().getId());
			this.pstmt.setString(4, System.currentTimeMillis() + "");
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<Replayuq> getReplayById(int id) throws Exception {
		String findAllQuesReplay = "SELECT replayuq.id,replayuq.ques_id,replayuq.content,replayuq.u_id,replayuq.dates,"
				+ "user.name FROM replayuq JOIN user ON (user.id=replayuq.u_id) WHERE replayuq.ques_id=?";

		List<Replayuq> list = new ArrayList<>();
		Replayuq replayuq;

		try {
			this.pstmt = this.conn.prepareStatement(findAllQuesReplay);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				replayuq = new Replayuq();
				replayuq.setId(rs.getInt("id"));
				replayuq.setContent(rs.getString("content"));
				replayuq.setDates(rs.getString("dates"));
				replayuq.setQues_id(rs.getInt("replayuq.ques_id"));
				replayuq.setU_id(new User(rs.getInt("u_id"), rs.getString("name")));
				list.add(replayuq);
			}
		} finally {
			this.pstmt.close();
		}
		return list;
	}

	@Override
	public boolean addReplayById(Replayuq replayuq) throws Exception {
		
		try {
			this.pstmt = this.conn.prepareStatement(InitString.insertOneQuesReplay);
			this.pstmt.setInt(1, replayuq.getQues_id());
			this.pstmt.setString(2, replayuq.getContent());
			this.pstmt.setInt(3, replayuq.getU_id().getId());
			this.pstmt.setString(4, System.currentTimeMillis() + "");
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean addTip(int u_id, String content, int isread) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.tips);
			this.pstmt.setInt(1, u_id);
			this.pstmt.setString(2, content);
			this.pstmt.setInt(3, isread);
			this.pstmt.setString(4, System.currentTimeMillis() + "");
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean isReadTip(int id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.updateTips);
			this.pstmt.setInt(1, 1);
			this.pstmt.setInt(2, id);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}


}
