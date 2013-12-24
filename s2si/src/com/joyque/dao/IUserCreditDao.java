package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserCredit;

public interface IUserCreditDao {

	public UserCredit getUserCredit(String uid);
	
	public int insertUserCredit(UserCredit info);
	
	public int updateUserCredit(UserCredit info);
	
	public List<UserCredit> getUserCreditInfos();
}
