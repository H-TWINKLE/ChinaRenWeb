package com.jwj.dao.factory;

import com.jwj.dao.MessDao;

import com.jwj.dao.proxy.MessDaoProxy;


public class MessFactory {
	public static MessDao getInstance() {
		return new MessDaoProxy();
	}
	
}
