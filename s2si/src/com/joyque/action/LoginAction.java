package com.joyque.action;

import com.joyque.service.ILoginService;


public class LoginAction{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private ILoginService loginService;
	
	public String execute(){
		return "login";
	}
	
	public String login(){
		loginService.login();
		System.out.println("LoginAction_login(),userName="+userName+",password="+password);
		return "login";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
}
