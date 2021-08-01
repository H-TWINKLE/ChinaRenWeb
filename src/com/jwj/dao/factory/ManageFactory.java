package com.jwj.dao.factory;

import com.jwj.dao.ManageDao;
import com.jwj.dao.proxy.ManageDaoProxy;

public class ManageFactory {
	
	public static ManageDao getInstance() {
		return new ManageDaoProxy();
	}

}
