package com.joyque.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.dao.IActivityInfoDao;
import com.joyque.dao.IUserActivityDao;
import com.joyque.pojo.ActivityInfo;
import com.joyque.pojo.UserActivity;
import com.joyque.service.IActivityService;
import com.opensymphony.xwork2.ActionContext;

public class ActivityServiceImpl implements IActivityService{
	private IActivityInfoDao activityInfoDao;
	private IUserActivityDao userActivityDao;

	@Override
	public void GetActivityList(String uid) {
		List<ActivityInfo> infos = activityInfoDao.getActivityInfos();
		JSONArray jArray = new JSONArray();
		for(ActivityInfo info : infos)
		{
			JSONObject j = new JSONObject();
			UserActivity ua = userActivityDao.getUserActivity(uid, info.getAid());
			if(ua == null)
			{
				j.accumulate("done", 0);
			}
			else
			{
				j.accumulate("done", 1);
			}
			j.accumulate("aid", info.getAid());
			j.accumulate("type", info.getType());
			j.accumulate("imageUrl", info.getImageUrl());
		}
		ActionContext.getContext().put("activities", jArray);
	}
	
	@Override
	public JSONObject AddActivity(String image, String formate) {
		JSONObject json = new JSONObject();
		ActivityInfo activity = new ActivityInfo();
		
		return json;
	}

	public IActivityInfoDao getActivityInfoDao() {
		return activityInfoDao;
	}

	public void setActivityInfoDao(IActivityInfoDao activityInfoDao) {
		this.activityInfoDao = activityInfoDao;
	}

	public IUserActivityDao getUserActivityDao() {
		return userActivityDao;
	}

	public void setUserActivityDao(IUserActivityDao userActivityDao) {
		this.userActivityDao = userActivityDao;
	}
}
