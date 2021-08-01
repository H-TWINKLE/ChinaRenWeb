package com.jwj.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.jwj.dao.MessDao;
import com.jwj.dao.impl.MessImpl;
import com.jwj.dbc.DatabaseConnection;
import com.jwj.entity.ClazzMess;
import com.jwj.entity.Replayuq;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;

public class MessDaoProxy implements MessDao {

	private DatabaseConnection dbc = null;
	private MessDao dao = null;

	public MessDaoProxy() {
		try {
			this.dbc = new DatabaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dao = new MessImpl(dbc.geConnection());
	}

	@Override
	public List<UserMess> finadAllU(int begin, int end,int id,String col) throws Exception {
		List<UserMess> list = new ArrayList<>();

		try {
			list = this.dao.finadAllU(begin, end,id,col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean doInsert(String table,String mess, String u_id, String ru_id) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.doInsert(table,mess, u_id, ru_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<ClazzMess> findAllC(int begin, int end, int id, String col) throws Exception {
		List<ClazzMess> list = new ArrayList<>();

		try {
			list = this.dao.findAllC(begin, end, id, col);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public List<UserMess> finadAllUS(int begin, int end, int id) throws Exception {
		List<UserMess> list = new ArrayList<>();

		try {
			list = this.dao.finadAllUS(begin, end, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean uploadUserMess(String img, int u_id, String mess) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.uploadUserMess(img, u_id, mess);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<UserQues> findAllQues(int id,int begin,int end) throws Exception {
		List<UserQues> list = new ArrayList<>();
		try {
			list = this.dao.findAllQues(id,begin,end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean insertOneQues(UserQues ques) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.insertOneQues(ques);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<Replayuq> getReplayById(int id) throws Exception {
		List<Replayuq> list = new ArrayList<>();

		try {
			list = this.dao.getReplayById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return list;
	}

	@Override
	public boolean addReplayById(Replayuq replayuq) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.addReplayById(replayuq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean addTip(int u_id, String content, int isread) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.addTip(u_id, content, isread);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean isReadTip(int id) throws Exception {
		boolean flag = false;

		try {
			flag = this.dao.isReadTip(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.close();
		}
		return flag;
	}

}
