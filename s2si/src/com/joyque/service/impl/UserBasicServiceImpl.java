package com.joyque.service.impl;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import com.joyque.common.exception.BaseServiceException;
import com.joyque.common.util.DefaultValue;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserCreditDao;
import com.joyque.dao.IUserInfoDao;
import com.joyque.pojo.UserCredit;
import com.joyque.pojo.UserInfo;
import com.joyque.service.IUserBasicService;
import com.opensymphony.xwork2.ActionContext;

public class UserBasicServiceImpl implements IUserBasicService{
	private IUserInfoDao userInfoDao;
	private IUserCreditDao userCreditDao;

	@Override
	public String register(String phone, String pw, String name,
			String shopName) {
		//判断号码是否注册
		UserInfo user = userInfoDao.getUserInfo(phone);
		if(user != null)
		{
			throw new BaseServiceException(ExceptionUtil.UserExsits,phone);
		}
		user = new UserInfo();
		//产生uid
		String uid = ProduceUid();
		while(userInfoDao.getUserInfoByUid(uid) != null)
		{
			uid = ProduceUid();
		}
		user.setUid(uid);
		user.setPhone(phone);
		user.setName(name);
		user.setPw(pw);
		user.setShopName(shopName);
		userInfoDao.insertUserInfo(user);
		
		UserCredit credit = new UserCredit();
		credit.setDefaultValue();
		credit.setUid(uid);
		userCreditDao.insertUserCredit(credit);
		
		if(user.getShopName() != null)
		{
			ActionContext.getContext().put("shopName", user.getShopName());
		}
		ActionContext.getContext().put("name", user.getName());
		ActionContext.getContext().put("isCheck", credit.getIsCheck());
		ActionContext.getContext().put("credit", credit.getCredit());
		return uid;
	}
	
	@Override
	public String Login(String phone, String pw) {
		UserInfo user = userInfoDao.getUserInfo(phone);
		if(user == null)
		{
			throw new BaseServiceException(ExceptionUtil.NoUser,phone);
		}
		if(!user.getPw().equals(pw))
		{
			throw new BaseServiceException(ExceptionUtil.PwError,phone);
		}
		
		UserCredit credit = userCreditDao.getUserCredit(user.getUid());
		
		if(user.getShopName() != null)
		{
			ActionContext.getContext().put("shopName", user.getShopName());
		}
		ActionContext.getContext().put("name", user.getName());
		ActionContext.getContext().put("isCheck", credit.getIsCheck());
		ActionContext.getContext().put("credit", credit.getCredit());
		return user.getUid();
	}
	
	@Override
	public JSONObject MotifyInfo(String uid, String phone, String pw,
			String name, String shopName) {
		JSONObject json = new JSONObject();
		UserInfo user = userInfoDao.getUserInfoByUid(uid);
		if(phone != null)
		{
			user.setPhone(phone);
		}
		if(pw != null)
		{
			user.setPw(pw);
		}
		if(name != null)
		{
			user.setName(name);
		}
		if(shopName != null)
		{
			user.setShopName(shopName);
		}
		userInfoDao.updateUserInfo(user);
		UserCredit credit = userCreditDao.getUserCredit(user.getUid());
		json.accumulate("name", user.getName());
		json.accumulate("isCheck", credit.getIsCheck());
		json.accumulate("credit", credit.getCredit());
		json.accumulate("phone", user.getPhone());
		if(user.getShopName() != null)
		{
			json.accumulate("shopName", user.getShopName());
		}
		return json;
	}
	
	@Override
	public JSONObject CheckIn(String uid) {
		JSONObject json = new JSONObject();
		UserCredit credit = userCreditDao.getUserCredit(uid);
		if(credit.getIsCheck() == DefaultValue.IsCheck)
		{
			throw new BaseServiceException(ExceptionUtil.IsCheck,uid);
		}
		credit.setIsCheck(DefaultValue.IsCheck);
		credit.setCredit(credit.getCredit() + DefaultValue.CheckCredit);
		userCreditDao.updateUserCredit(credit);
		json.accumulate("isCheck", credit.getIsCheck());
		json.accumulate("credit", credit.getCredit());
		return json;
	}
	
	public void UpdateCheckInfo()
	{
		List<UserCredit> infos = userCreditDao.getUserCreditInfos();
		for(UserCredit info : infos)
		{
			info.setIsCheck(DefaultValue.NotCheck);
			info.setLotteryCount(DefaultValue.LotteryCount);
			userCreditDao.updateUserCredit(info);
		}
	}
	
	private String ProduceUid()
	{
		String result = "1";
	    for(int i = 0; i < 7; i++)
	    {
	    	Random random = new Random(); 
			int r = random.nextInt(10);
			result += r;
	    }
	    return result;
	}

	public IUserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public IUserCreditDao getUserCreditDao() {
		return userCreditDao;
	}

	public void setUserCreditDao(IUserCreditDao userCreditDao) {
		this.userCreditDao = userCreditDao;
	}
}
