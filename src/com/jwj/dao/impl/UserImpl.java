package com.jwj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.UserDao;
import com.jwj.entity.Clazz;
import com.jwj.entity.ClazzPic;
import com.jwj.entity.Tip;
import com.jwj.entity.User;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserFocus;
import com.jwj.entity.UserMore;
import com.jwj.init.InitString;

public class UserImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserImpl(Connection conn) {
		this.conn = conn;
		// TODO 自动生成的构造函数存根
	}

	@Override
	public User login(String admin, String pass) throws Exception {

		User user = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.Login);
			this.pstmt.setString(1, admin);
			this.pstmt.setString(2, pass);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setAdmin(rs.getString("admin"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getString("sex"));
				user.setDates(rs.getString("dates"));
				user.setImg(rs.getString("img"));
				user.setAuto(rs.getString("auto"));
				user.setPoint(rs.getInt("point"));
				user.setIsadmin(rs.getInt("isadmin"));
				user.setClazz(new Clazz(rs.getInt("c_id"), rs.getString("clazzn"), rs.getString("graden"),
						rs.getString("majorn"), rs.getString("collegen")));
				user.setUsermore(new UserMore(rs.getString("year"), rs.getString("tel"), rs.getString("birth"),
						rs.getString("live"), rs.getString("home")));
			}

			return user;
		} finally {
			this.pstmt.close();
		}

	}

	@Override
	public boolean register(String admin, String code) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.Register);
			this.pstmt.setString(1, admin);
			this.pstmt.setString(2, code);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean find(String user) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findSome);
			this.pstmt.setString(1, user);
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean update(String admin, String pass) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.updatePass);
			this.pstmt.setString(1, pass);
			this.pstmt.setString(2, admin);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public String findRegister(String user) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findRegister);
			this.pstmt.setString(1, user);
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? rs.getString("admin") : "";
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean registerUser(String admin, String pass) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.RegisterUser);
			this.pstmt.setString(1, admin);
			this.pstmt.setString(2, pass);
			this.pstmt.setString(3, String.valueOf(System.currentTimeMillis()));
			this.pstmt.setString(4, "新用户" + admin.substring(0, admin.length() / 2));
			this.pstmt.setString(5, "新用户" + admin.substring(admin.length() / 2));
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.UpdateUser);
			this.pstmt.setString(1, user.getName());
			this.pstmt.setString(2, user.getNickname());
			this.pstmt.setString(3, user.getAuto());
			this.pstmt.setString(4, user.getUsermore().getTel());
			this.pstmt.setString(5, user.getUsermore().getLive());
			this.pstmt.setString(6, user.getUsermore().getHome());
			this.pstmt.setInt(7, user.getId());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public User getUser(int id) throws Exception {
		User user = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.getUser);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setAdmin(rs.getString("admin"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getString("sex"));
				user.setImg(rs.getString("img"));
				user.setAuto(rs.getString("auto"));
				user.setIsadmin(rs.getInt("isadmin"));
				user.setPoint(rs.getInt("point"));
				user.setClazz(new Clazz(rs.getInt("c_id"), rs.getString("clazzn"), rs.getString("graden"),
						rs.getString("majorn"), rs.getString("collegen")));
				user.setUsermore(new UserMore(rs.getString("year"), rs.getString("tel"), rs.getString("birth"),
						rs.getString("live"), rs.getString("home")));
			}

			return user;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean updateImg(String img, String id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.UpdateImg);
			this.pstmt.setString(1, img);
			this.pstmt.setString(2, id);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean updateUserCode(String admin, String code) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.updateUserCode);
			this.pstmt.setString(1, code);
			this.pstmt.setString(2, admin);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public User findUserCode(String code) throws Exception {

		User u = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findUserCode);
			this.pstmt.setString(1, code);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setAdmin(rs.getString("admin"));
			}
		} finally {
			this.pstmt.close();
		}

		return u;
	}

	@Override
	public List<Clazz> findClazzTable(String clazz, String graden, String major, String college) throws Exception { // ???????
		List<Clazz> list = new ArrayList<>();
		Clazz cl = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findClazzTable);
			this.pstmt.setString(1, clazz);
			this.pstmt.setString(2, graden);
			this.pstmt.setString(3, major);
			this.pstmt.setString(4, college);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				cl = new Clazz(rs.getInt("c_id"), rs.getString("clazzn"), rs.getString("graden"),
						rs.getString("majorn"), rs.getString("collegen"),rs.getString("dates"),rs.getString("auto"));
				list.add(cl);
			}
		} finally {
			this.pstmt.close();
		}

		return list;

	}

	@Override
	public int findCollege(Clazz clazz) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findCollege);
			this.pstmt.setString(1, clazz.getClazzn());
			this.pstmt.setString(2, clazz.getGraden());
			this.pstmt.setString(3, clazz.getMajorn());
			this.pstmt.setString(4, clazz.getCollegen());
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? rs.getInt("id") : 0;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean setCollege(Clazz clazz) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.setCollege);
			this.pstmt.setString(1, clazz.getClazzn());
			this.pstmt.setString(2, clazz.getGraden());
			this.pstmt.setString(3, clazz.getMajorn());
			this.pstmt.setString(4, clazz.getCollegen());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public int findUserMore(UserMore userMore) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findUserMore);
			this.pstmt.setString(1, userMore.getYear());
			this.pstmt.setString(2, userMore.getTel());
			this.pstmt.setString(3, userMore.getBirth());
			this.pstmt.setString(4, userMore.getLive());
			this.pstmt.setString(5, userMore.getHome());
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? rs.getInt("id") : 0;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean setUserMore(UserMore userMore) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.setUserMore);
			this.pstmt.setString(1, userMore.getYear());
			this.pstmt.setString(2, userMore.getTel());
			this.pstmt.setString(3, userMore.getBirth());
			this.pstmt.setString(4, userMore.getLive());
			this.pstmt.setString(5, userMore.getHome());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean setUserFocus(int u_id, int ru_id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.setUserFocus);
			this.pstmt.setInt(1, u_id);
			this.pstmt.setInt(2, ru_id);
			this.pstmt.setString(3, String.valueOf(System.currentTimeMillis()));
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<UserFocus> findAllUserFocus(int begin, int end, int u_id, String col) throws Exception {

		String sql = "SELECT userfocus.id,\r\n" + "       userfocus.dates,\r\n" + "       u.id 'u_id',\r\n"
				+ "       u.admin 'u_admin',\r\n" + "       u.name 'u_name',\r\n"
				+ "       u.nickname 'u_nickname',\r\n" + "       ru.id 'ru_id',\r\n"
				+ "       ru.admin 'ru_admin',\r\n" + "       ru.name 'ru_name',\r\n"
				+ "       ru.nickname 'ru_nickname'\r\n"
				+ "FROM (SELECT * FROM userfocus ORDER BY userfocus.dates DESC LIMIT " + begin + "," + end
				+ ") userfocus\r\n" + "JOIN user u ON (userfocus.u_id=u.id)\r\n"
				+ "JOIN user ru ON (userfocus.ru_id=ru.id)\r\n" + "WHERE " + col + " = " + u_id + ";";

		List<UserFocus> list = new ArrayList<>();
		UserFocus userFocus = null;

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				userFocus = new UserFocus();
				userFocus.setId(rs.getInt("id"));
				userFocus.setDates(rs.getString("dates"));
				userFocus.setU_id(new User(rs.getInt("u_id"), rs.getString("u_admin"), rs.getString("u_name"),
						rs.getString("u_nickname")));
				userFocus.setRu_id(new User(rs.getInt("ru_id"), rs.getString("ru_admin"), rs.getString("ru_name"),
						rs.getString("ru_nickname")));
				list.add(userFocus);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public List<Clazz> findAllM_C() throws Exception {
		List<Clazz> list = new ArrayList<>();
		Clazz clazz = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.fingAllM_C);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				clazz = new Clazz();
				clazz.setMajonr(rs.getString("majorn"));
				clazz.setCollegen(rs.getString("collegen"));
				list.add(clazz);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public List<Integer> findCount(int id) throws Exception {
		List<Integer> list = new ArrayList<>();
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findCount);
			this.pstmt.setInt(1, id);
			this.pstmt.setInt(2, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("count"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<User> findClazzUser(int flag) throws Exception {
		List<User> list = new ArrayList<>();
		User user = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findClazzUser);
			this.pstmt.setInt(1, flag);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setIsadmin(rs.getInt("isadmin"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setUsermore(new UserMore(rs.getString("tel")));
				list.add(user);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public boolean uploadClazzImg(String img, int u_id, int clazz_id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.UploadClazzImg);
			this.pstmt.setInt(1, clazz_id);
			this.pstmt.setString(2, img);
			this.pstmt.setInt(3, u_id);
			this.pstmt.setString(4, String.valueOf(System.currentTimeMillis()));
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<ClazzPic> findAllPic(int begin, int end, int c_id) throws Exception {
		String sql = "SELECT clazzpic.id,\r\n" + "       clazzpic.dates,\r\n" + "       clazzpic.pic,\r\n"
				+ "       u.id 'u_id',\r\n" + "       u.admin 'u_admin',\r\n" + "       u.name 'u_name',\r\n"
				+ "       u.nickname 'u_nickname',\r\n" + "       clazzpic.clazz_id \r\n"
				+ "FROM (SELECT * FROM clazzpic ORDER BY clazzpic.dates DESC LIMIT " + begin + "," + end
				+ ") clazzpic\r\n" + "JOIN user u ON (clazzpic.u_id=u.id)\r\n" + "WHERE clazz_id = " + c_id + ";";

		List<ClazzPic> list = new ArrayList<>();
		ClazzPic clazzPic = null;

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				clazzPic = new ClazzPic();
				clazzPic.setId(rs.getInt("id"));
				clazzPic.setDates(rs.getString("dates"));
				clazzPic.setU_id(new User(rs.getInt("u_id"), rs.getString("u_admin"), rs.getString("u_name"),
						rs.getString("u_nickname")));
				clazzPic.setClazz_id(new Clazz(rs.getInt("clazz_id")));
				clazzPic.setPic(rs.getString("pic"));
				list.add(clazzPic);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public boolean getFocus(int u_id, int ru_id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.setUserFocus);
			this.pstmt.setInt(1, u_id);
			this.pstmt.setInt(2, ru_id);
			this.pstmt.setString(3, String.valueOf(System.currentTimeMillis()));
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean disFocus(int id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.deleteUserFocus);
			this.pstmt.setInt(1, id);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean updateUserRegis(User user) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.UpdateUserRegist);
			this.pstmt.setString(1, user.getName());
			this.pstmt.setString(2, user.getNickname());
			this.pstmt.setString(3, user.getSex());
			this.pstmt.setInt(4, user.getClazz().getId());
			this.pstmt.setInt(5, user.getUsermore().getId());
			this.pstmt.setString(6, user.getAdmin());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public List<User> findUserTable(String flag) throws Exception {
		List<User> list = new ArrayList<>();
		User user = null;
		flag = "%" + flag + "%";
		try {
			this.pstmt = this.conn.prepareStatement(InitString.findUserTable);
			this.pstmt.setString(1, flag);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setClazz(new Clazz(rs.getInt("c_id"), rs.getString("clazzn"), rs.getString("graden"),
						rs.getString("majorn"), rs.getString("collegen")));
				list.add(user);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

	@Override
	public boolean JoinClazz(int clazzid, int u_id) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.JoinClazz);
			this.pstmt.setInt(1, clazzid);
			this.pstmt.setInt(2, u_id);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean setUserComplaint(int id, UserComplaint complaint) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.setUserComplaint);
			this.pstmt.setInt(1, id);
			this.pstmt.setString(2, complaint.getContent());
			this.pstmt.setString(3, complaint.getTypes());
			this.pstmt.setString(4, System.currentTimeMillis() + "");
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean setUserPointAdd(int id, int value, String col) throws Exception {

		String sql = "UPDATE user SET " + col + "=? WHERE id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, value);
			this.pstmt.setInt(2, id);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public int getBestClazzAdmin(int clazz) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.getBestClazzAdmin);
			this.pstmt.setInt(1, clazz);
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? rs.getInt("id") : 0;
		} finally {
			this.pstmt.close();
		}
	}

	@Override
	public boolean isAdminExist(int clazz) throws Exception {
		try {
			this.pstmt = this.conn.prepareStatement(InitString.isAdminExist);
			this.pstmt.setInt(1, clazz);
			ResultSet rs = this.pstmt.executeQuery();
			return rs.next() ? true : false;
		} finally {
			this.pstmt.close();
		}
	}
	
	
	
	@Override
	public List<Tip> findOneTip(int id) throws Exception {
		List<Tip> list = new ArrayList<>();
		Tip tip = null;
		try {
			this.pstmt = this.conn.prepareStatement(InitString.manageOneTip);
			this.pstmt.setInt(1, id);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				tip = new Tip();
				tip.setId(rs.getInt("id"));
				tip.setContent(rs.getString("content"));
				tip.setU_id(new User(rs.getInt("u_id"),rs.getString("uname")));
				tip.setDates(rs.getString("dates"));
				list.add(tip);
			}
		} finally {
			this.pstmt.close();
		}

		return list;
	}

}
