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
			String uid = userBasicService.register(phone, pw, name, shopName);
			Map session = getSession();
			session.put("uid", uid);
		}catch(Exception e){			
			
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
			String uid = userBasicService.Login(phone, pw);
			Map session = getSession();
			if(session.get("uid") == null)
			{
				session.put("uid", uid);
			}
			else
			{
				session.remove("uid");
				session.put("uid", uid);
			}

		}catch(Exception e){
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void MotifyInfo()
	{
		JSONObject json = new JSONObject();
		String uid = null;
		Map session = getSession();
		uid = (String) session.get("uid");
		if(uid == null)
		{
			
		}
		try{
			json = userBasicService.MotifyInfo(uid, phone, pw, name, shopName);
		}catch(Exception e){
			json.put("status", 400);
			json.put("message", e.getMessage());
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
		if(uid == null)
		{
			
		}
		try{
			json = userBasicService.CheckIn(uid);
		}catch(Exception e){
			json.put("status", 400);
			json.put("message", e.getMessage());
		}
		ajaxReturn(json.toString());
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
}
