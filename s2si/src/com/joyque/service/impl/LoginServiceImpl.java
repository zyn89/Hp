package com.joyque.service.impl;

import com.joyque.dao.ILoginDAO;
import com.joyque.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
	private ILoginDAO loginDao;
	public String login() {
		System.out.println("LoginServiceImpl.login()");
		loginDao.login();
		return "success";
	}
	public ILoginDAO getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(ILoginDAO loginDao) {
		this.loginDao = loginDao;
	}
	
}
