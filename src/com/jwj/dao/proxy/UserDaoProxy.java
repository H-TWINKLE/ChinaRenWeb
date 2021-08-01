package com.jwj.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.UserDao;
import com.jwj.dao.impl.UserImpl;
import com.jwj.dbc.DatabaseConnection;
import com.jwj.entity.Clazz;
import com.jwj.entity.ClazzPic;
import com.jwj.entity.Tip;
import com.jwj.entity.User;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserFocus;
import com.jwj.entity.UserMore;

public class UserDaoProxy implements UserDao {

	private DatabaseConnection dbc = null;
	private UserDao dao = null;

	public UserDaoProxy() {
		try {
			this.dbc = new DatabaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dao = new UserImpl(dbc.geConnection());
	}

	@Override
	public User login(String admin, String pass) throws Exception {
		User user = null;
		try {
			user = this.dao.login(admin, pass);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return user;

	}

	@Override
	public boolean register(String admin, String code) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.register(admin, code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean find(String user) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.find(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean update(String admin, String pass) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.update(admin, pass);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public DatabaseConnection getDbc() {
		return dbc;
	}

	public void setDbc(DatabaseConnection dbc) {
		this.dbc = dbc;
	}

	@Override
	public String findRegister(String user) throws Exception {
		String flag ="";
		try {
			flag = 	 this.dao.findRegister(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean registerUser(String admin, String pass) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.registerUser(admin, pass);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public User getUser(int id) throws Exception {
		User user = null;
		try {
			user = this.dao.getUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return user;
	}

	@Override
	public boolean updateImg(String img, String id ) throws Exception {
		boolean flags = false;
		try {
			flags = this.dao.updateImg(img, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flags;
	}

	@Override
	public boolean updateUserCode(String admin, String code) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.updateUserCode(admin, code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public User findUserCode(String user) throws Exception {
		User u = new User() ;
		try {
			u = 	 this.dao.findUserCode(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return u;
	}

	@Override
	public List<Clazz> findClazzTable(String clazz,String graden,String major,String college) throws Exception {
		List<Clazz> list = new ArrayList<>();

		try {
			list = this.dao.findClazzTable(clazz, graden, major, college);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public int findUserMore(UserMore userMore) throws Exception {
		int flag = 0;
		try {
			flag = this.dao.findUserMore(userMore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean setUserMore(UserMore userMore) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.setUserMore(userMore);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public int findCollege(Clazz clazz) throws Exception {
		int flag = 0;
		try {
			flag = this.dao.findCollege(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean setCollege(Clazz clazz) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.setCollege(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean setUserFocus(int u_id, int ru_id) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.setUserFocus(u_id, ru_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<UserFocus> findAllUserFocus(int begin, int end, int u_id,String col) throws Exception {
		List<UserFocus> list = new ArrayList<>();

		try {
			list = this.dao.findAllUserFocus(begin, end, u_id,col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}


	@Override
	public List<Clazz> findAllM_C() throws Exception {
		List<Clazz> list = new ArrayList<>();

		try {
			list = this.dao.findAllM_C();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<Integer> findCount(int id) throws Exception {
		List<Integer> list = new ArrayList<>();

		try {
			list = this.dao.findCount(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<User> findClazzUser(int flag) throws Exception {
		List<User> list = new ArrayList<>();

		try {
			list = this.dao.findClazzUser(flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean uploadClazzImg(String img, int u_id, int clazz_id) throws Exception {
		boolean flags = false;
		try {
			flags = this.dao.uploadClazzImg(img, u_id, clazz_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flags;
	}

	@Override
	public List<ClazzPic> findAllPic(int begin, int end, int c_id) throws Exception {
		List<ClazzPic> list = new ArrayList<>();

		try {
			list = this.dao.findAllPic(begin, end, c_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean getFocus(int u_id, int ru_id) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.getFocus(u_id, ru_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean disFocus(int id) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.disFocus(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean updateUserRegis(User user) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.updateUserRegis(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<User> findUserTable(String flag) throws Exception {
		List<User> list = new ArrayList<>();

		try {
			list = this.dao.findUserTable(flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean JoinClazz(int clazzid, int u_id) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.JoinClazz(clazzid, u_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean setUserComplaint(int id,UserComplaint complaint) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.setUserComplaint(id,complaint);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean setUserPointAdd(int id, int value, String col) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.setUserPointAdd(id, value, col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public int getBestClazzAdmin(int clazz) throws Exception {
		int id = 0;
		try {
			id = this.dao.getBestClazzAdmin(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return id;
	}

	@Override
	public boolean isAdminExist(int clazz) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.isAdminExist(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<Tip> findOneTip(int id) throws Exception {
		List<Tip> list = new ArrayList<>();
		try {
			list = this.dao.findOneTip(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}
	
	

}
