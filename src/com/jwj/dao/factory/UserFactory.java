package com.jwj.dao.factory;

import com.jwj.dao.UserDao;
import com.jwj.dao.proxy.UserDaoProxy;

public class UserFactory {
	
	/*private static UserDao instance = new UserDaoProxy();
	
	private UserFactory() {}

	public static UserDao getInstance() {
		return instance;
	}*/
	
	public static UserDao getInstance() {
		return new UserDaoProxy();
	}
	

}
