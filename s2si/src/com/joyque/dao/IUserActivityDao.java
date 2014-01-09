package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserActivity;

public interface IUserActivityDao {

	public List<UserActivity> getUserActivities(long aid, int start, int end);
	
	public UserActivity getUserActivity(String uid, long aid);
	
	public int insertUserActivity(UserActivity info);
	
	public int GetUserActivitiesCount(long aid);
}
