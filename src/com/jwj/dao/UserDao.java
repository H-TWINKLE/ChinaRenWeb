package com.jwj.dao;

import java.util.List;

import com.jwj.entity.Clazz;
import com.jwj.entity.ClazzPic;
import com.jwj.entity.Tip;
import com.jwj.entity.User;
import com.jwj.entity.UserComplaint;
import com.jwj.entity.UserFocus;
import com.jwj.entity.UserMore;

public interface UserDao {

	public User login(String admin, String pass) throws Exception;

	public boolean register(String admin, String code) throws Exception;

	public boolean find(String user) throws Exception;

	public String findRegister(String user) throws Exception;

	public boolean update(String admin, String pass) throws Exception;

	public boolean registerUser(String admin, String pass) throws Exception;

	public boolean updateUser(User user) throws Exception;
	
	public boolean updateUserRegis(User user) throws Exception;

	public int findUserMore(UserMore userMore) throws Exception;

	public boolean setUserMore(UserMore userMore) throws Exception;

	public User getUser(int id) throws Exception;

	public boolean updateImg(String img, String id ) throws Exception;
	
	public boolean uploadClazzImg(String img, int u_id ,int clazz_id) throws Exception;

	public int findCollege(Clazz clazz) throws Exception;

	public boolean setCollege(Clazz clazz) throws Exception;

	public boolean updateUserCode(String admin, String code) throws Exception;

	public User findUserCode(String user) throws Exception;

	public List<Clazz> findClazzTable(String clazz,String graden,String major,String college) throws Exception;
	
	public List<User> findUserTable(String flag) throws Exception;
	
	public List<User> findClazzUser(int flag) throws Exception;

	public boolean setUserFocus(int u_id, int ru_id) throws Exception;

	public List<UserFocus> findAllUserFocus(int begin, int end, int u_id,String col) throws Exception;

	public boolean JoinClazz(int clazzid,int u_id) throws Exception;
	
	public List<Clazz> findAllM_C() throws Exception;
	
	public List<Integer> findCount(int id) throws Exception; 
	
	public List<ClazzPic> findAllPic(int begin, int end, int c_id)throws Exception;
		
	public boolean getFocus(int u_id,int ru_id)throws Exception;
	
	public boolean disFocus(int id)throws Exception;
	
	public boolean setUserComplaint(int id,UserComplaint complaint)throws Exception;
	
	public boolean setUserPointAdd(int id,int value,String col)throws Exception;
	
	public int getBestClazzAdmin(int clazz)throws Exception;
		
	public boolean isAdminExist(int clazz) throws Exception;
	
	public List<Tip> findOneTip(int id)throws Exception;

}
