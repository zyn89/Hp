package com.joyque.service.impl;

import java.util.Random;

import net.sf.json.JSONObject;

import com.joyque.common.exception.BaseServiceException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserInfoDao;
import com.joyque.pojo.UserInfo;
import com.joyque.service.IUserBasicService;

public class UserBasicServiceImpl implements IUserBasicService{
	private IUserInfoDao userInfoDao;

	@Override
	public JSONObject register(String phone, String pw, String name,
			String shopName) {
		JSONObject json = new JSONObject();
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
		json.accumulate("uid", uid);
		return json;
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

}
