package com.jwj.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.ManageDao;
import com.jwj.dao.impl.ManageImpl;
import com.jwj.dbc.DatabaseConnection;
import com.jwj.entity.Clazz;
import com.jwj.entity.Manage;
import com.jwj.entity.Tip;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;

public class ManageDaoProxy implements ManageDao{
	
	private DatabaseConnection dbc = null;
	private ManageDao dao = null;
	
	

	public ManageDaoProxy() {
		try {
			this.dbc = new DatabaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dao = new ManageImpl(dbc.geConnection());
	}



	@Override
	public Manage doAdminLogin(String admin, String pass) throws Exception {
		Manage manage = null;
		try {
			manage = this.dao.doAdminLogin(admin, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return manage;
	}



	@Override
	public List<Clazz> findAllClazz() throws Exception {
		List<Clazz> list = new ArrayList<>();

		try {
			list = this.dao.findAllClazz();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	@Override
	public boolean doUpClazz(Clazz clazz) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.doUpClazz(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}



	@Override
	public List<UserQues> findAllQues(int begin,int end) throws Exception {
		List<UserQues> list = new ArrayList<>();

		try {
			list = this.dao.findAllQues(begin,end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	



	@Override
	public List<UserMess> findAllUserMess() throws Exception {
		List<UserMess> list = new ArrayList<>();

		try {
			list = this.dao.findAllUserMess();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	@Override
	public boolean deleteComm(int id, String table, String col) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.deleteComm(id, table, col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}



	@Override
	public List<UserComplaint> findAllUserComplaint() throws Exception {
		List<UserComplaint> list = new ArrayList<>();

		try {
			list = this.dao.findAllUserComplaint();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	@Override
	public Clazz findOneClazz(int id) throws Exception {
		Clazz clazz = new Clazz();
		
		try {
			clazz = this.dao.findOneClazz(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return clazz;
	}



	@Override
	public List<UserMess> findAllMess() throws Exception {
		List<UserMess> list = new ArrayList<>();

		try {
			list = this.dao.findAllMess();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	@Override
	public List<Tip> findAllTip() throws Exception {
		List<Tip> list = new ArrayList<>();

		try {
			list = this.dao.findAllTip();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}



	@Override
	public int getOneTableCount(String table, int id, String col) throws Exception {
		int num = 0;

		try {
			num = this.dao.getOneTableCount(table,id,col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return num;
	}





}
