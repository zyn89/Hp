package com.joyque.action;

import java.util.Map;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.DefaultValue;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IUserBasicService;

import net.sf.json.JSONObject;

public class UserBasicAction extends BaseAction{

	private static final long serialVersionUID = -7830452477700257474L;

	private String phone;
	
	private String pw;
	
	private String name;
	
	private String shopName;
	
	private String account;
	
	private IUserBasicService userBasicService;
	
	@SuppressWarnings("unchecked")
	public String register()
	{
		try{
			if(!isValidatePhone() || !isValidatePw()
					|| !isValidateName())
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			JSONObject json = userBasicService.register(phone, pw, name, shopName);
			Map session = getSession();
			session.remove("uid");
			session.put("uid", json.get("uid"));
			session.remove("phone");
			session.put("phone", json.get("phone"));
			session.remove("name");
			session.put("name", json.get("name"));
			session.remove("isCheck");
			session.put("isCheck", json.get("isCheck"));
			session.remove("credit");
			session.put("credit", json.get("credit"));
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String Login()
	{
		try{
			if(!isValidatePhone() || !isValidatePw())
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			JSONObject json = userBasicService.Login(phone, pw);
			Map session = getSession();
			session.remove("uid");
			session.put("uid", json.get("uid"));
			session.remove("phone");
			session.put("phone", json.get("phone"));
			session.remove("name");
			session.put("name", json.get("name"));
			session.remove("isCheck");
			session.put("isCheck", json.get("isCheck"));
			session.remove("credit");
			session.put("credit", json.get("credit"));
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void Logout()
	{
		Map session = getSession();
		session.remove("uid");
		session.remove("phone");
		session.remove("name");
		session.remove("isCheck");
		session.remove("credit");
	}
	
	@SuppressWarnings("unchecked")
     public void AdminLogin()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateAccount() || !isValidatePw())
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = userBasicService.AdminLogin(account, pw);
			Map session = getSession();
			session.remove("uid");
			session.put("uid", json.get("uid"));
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	

	@SuppressWarnings("unchecked")
	public void MotifyInfo()
	{
		JSONObject json = new JSONObject();
		String uid = null;
		Map session = getSession();
		uid = (String) session.get("uid");
		try{
			json = userBasicService.MotifyInfo(uid, phone, pw, name, shopName);
		}catch(Exception e){
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void CheckIn()
	{
		JSONObject json = new JSONObject();
		String uid = null;
		Map session = getSession();
		uid = (String) session.get("uid");
		try{
			json = userBasicService.CheckIn(uid);
		}catch(Exception e){
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	private boolean isValidateAccount() {
		if(account != null && !account.trim().equals(""))
		{
			return true;
		}
		return false;
	}
	

	private boolean isValidateName() {
		if(name != null && !name.trim().equals("")
				&& name.trim().length() >= DefaultValue.minNameLength
				&& name.trim().length() <= DefaultValue.maxNameLength)
		{
			return true;
		}
		return false;
	}

	private boolean isValidatePw() {
		if(pw != null && !pw.trim().equals("") 
				&& pw.trim().length() >= DefaultValue.minPwLength 
				&& pw.trim().length() <= DefaultValue.maxPwLength)
		{
			return true;
		}
		return false;
	}

	private boolean isValidatePhone() {
		if(phone != null && !phone.trim().equals("") && phone.trim().length() == 11)
		{
			return true;
		}
		return false;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public IUserBasicService getUserBasicService() {
		return userBasicService;
	}

	public void setUserBasicService(IUserBasicService userBasicService) {
		this.userBasicService = userBasicService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}	
}
