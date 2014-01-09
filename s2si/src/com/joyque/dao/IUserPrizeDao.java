package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserPrize;

public interface IUserPrizeDao {

	public List<UserPrize> GetUserPrizes(String uid);
	
	public List<UserPrize> GetUserPirzesByPid(long pid, int start, int end);
	
	public int insertUserPrize(UserPrize info);
	
	public int deleteUserPrize(long id);
	
	public int GetUserPirzesCount(long pid);
}
