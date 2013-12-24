package com.joyque.dao;

import com.joyque.pojo.UserInfo;

public interface IUserInfoDao {

	public UserInfo getUserInfo(String phone);
	
	public UserInfo getUserInfoByUid(String uid);
	
	public int insertUserInfo(UserInfo info);
	
	public int updateUserInfo(UserInfo info);	
}
