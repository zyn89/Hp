package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserPrize;

public interface IUserPrizeDao {

	public List<UserPrize> GetUserPrizes(String uid);
	
	public int insertUserPrize(UserPrize info);
	
	public int deleteUserPrize(long id);
}
