package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.ActivityInfo;

public interface IActivityInfoDao {

	public List<ActivityInfo> getActivityInfos();
	
	public ActivityInfo getActivityInfo(long aid);
	
	public int insertActivityInfo(ActivityInfo info);
	
	public int updateActivityInfo(ActivityInfo info);
	
	public int deleteActivityInfo(long aid);
}
