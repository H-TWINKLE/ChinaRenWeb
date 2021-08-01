package com.jwj.dao;

import java.util.List;

import com.jwj.entity.ClazzMess;
import com.jwj.entity.Replayuq;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;

public interface MessDao {
	
	public List<UserMess> finadAllU(int begin,int end, int id ,String col)throws Exception;
	
	public List<UserMess> finadAllUS(int begin,int end, int id)throws Exception;
	
	public boolean doInsert(String table,String mess,String u_id,String ru_id) throws Exception;
	
	public List<ClazzMess> findAllC(int begin,int end,int id,String col)throws Exception;
	
	public boolean uploadUserMess(String img, int u_id,String mess) throws Exception;
	
	public List<UserQues> findAllQues(int id ,int begin,int end)throws Exception;
	
	public boolean insertOneQues(UserQues ques)throws Exception;
	
	public List<Replayuq> getReplayById(int id)throws Exception;
	
	public boolean addReplayById(Replayuq replayuq)throws Exception;
	
	public boolean addTip(int u_id ,String content,int isread)throws Exception;
	
	public boolean isReadTip(int id)throws Exception;
	
	

}
