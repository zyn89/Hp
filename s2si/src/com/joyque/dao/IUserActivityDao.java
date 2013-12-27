package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserActivity;

public interface IUserActivityDao {

	public List<UserActivity> getUserActivities(long aid);
	
	public UserActivity getUserActivity(String uid, long aid);
}
