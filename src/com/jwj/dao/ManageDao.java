package com.jwj.dao;

import java.util.List;

import com.jwj.entity.Clazz;
import com.jwj.entity.Manage;
import com.jwj.entity.Tip;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserMess;
import com.jwj.entity.UserQues;

public interface ManageDao {
	
	public int getOneTableCount(String table,int id,String col)throws Exception;

	public Manage doAdminLogin(String admin, String pass) throws Exception;

	public List<Clazz> findAllClazz() throws Exception;

	public boolean doUpClazz(Clazz clazz) throws Exception;

	public List<UserQues> findAllQues(int begin,int end) throws Exception;

	public boolean deleteComm(int id,String table,String col) throws Exception;

	public List<UserMess> findAllUserMess() throws Exception;
	
	public List<UserComplaint> findAllUserComplaint() throws Exception;
	
	public Clazz findOneClazz(int id)throws Exception;
	
	public List<UserMess> findAllMess()throws Exception;
	
	public List<Tip> findAllTip()throws Exception;

}
