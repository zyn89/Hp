package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.util.FileUtil;
import com.joyque.dao.IActivityInfoDao;
import com.joyque.dao.IQuestionInfoDao;
import com.joyque.dao.IUserActivityDao;
import com.joyque.pojo.ActivityInfo;
import com.joyque.pojo.UserActivity;
import com.joyque.service.IActivityService;

public class ActivityServiceImpl implements IActivityService{
	private IActivityInfoDao activityInfoDao;
	private IUserActivityDao userActivityDao;
	private IQuestionInfoDao questionInfoDao;

	@Override
	public JSONObject GetActivityList(String uid) {
		JSONObject json = new JSONObject();
		json.accumulate("activities", GetActivityArray(uid));
		return json;
	}
	
	private JSONArray GetActivityArray(String uid)
	{
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
			j.accumulate("activityUrl", info.getImageUrl());
			j.accumulate("aid", info.getAid());
			j.accumulate("type", info.getType());		
			j.accumulate("descUrl", info.getDescUrl());
			j.accumulate("score", info.getScore());
			j.accumulate("credit", info.getCredit());
			jArray.add(j);
		}
		return jArray;
	}
	
	@Override
	public JSONObject AddActivity(List<File> pics, List<String> picsContentType,
			int activityIndex, int descIndex,
			String uid, String type, int score, int credit) throws IOException {
		JSONObject json = new JSONObject();
		ActivityInfo activity = new ActivityInfo();
		activity.setDefaultValue();
		
		String url = FileUtil.SaveActivityStringAsMedia(uid, pics.get(activityIndex), picsContentType.get(activityIndex));
		activity.setImageUrl(url);
		
		url = FileUtil.SaveActivityDescStringAsMedia(uid, pics.get(descIndex), picsContentType.get(descIndex));
		activity.setDescUrl(url);
		
		activity.setCredit(credit);
		activity.setType(type);
		Date date = new Date();
		activity.setDate(date.getTime());
		activity.setScore(score);
		activityInfoDao.insertActivityInfo(activity);
		json.accumulate("activities", GetActivityArray(uid));
		return json;
	}
	
	@Override
	public JSONObject DeleteActivity(long aid, String uid) {
		JSONObject json = new JSONObject();
		activityInfoDao.deleteActivityInfo(aid);
		questionInfoDao.deleteQuestionInfoList(aid);
		json.accumulate("activities", GetActivityArray(uid));
		return json;
	}
	
	@Override
	public JSONObject UpdateActivity(long aid, String uid, List<File> pics, List<String> picsContentType, 
			int activityIndex, int descIndex, int score, int credit) throws IOException {
		JSONObject json = new JSONObject();
		ActivityInfo activity = new ActivityInfo();
		activity.setAid(aid);
		
		if(activityIndex != -1)
		{
			String activityUrl = FileUtil.SaveActivityStringAsMedia(uid, pics.get(activityIndex), picsContentType.get(activityIndex));
			activity.setImageUrl(activityUrl);
		}
		
		if(descIndex != -1)
		{
			String descUrl = FileUtil.SaveActivityDescStringAsMedia(uid, pics.get(descIndex), picsContentType.get(descIndex));
			activity.setDescUrl(descUrl);
		}
		
		activity.setCredit(credit);
		activity.setScore(score);
		Date date = new Date();
		activity.setDate(date.getTime());
		activityInfoDao.updateActivityInfo(activity);
		json.accumulate("activities", GetActivityArray(uid));
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

	public IQuestionInfoDao getQuestionInfoDao() {
		return questionInfoDao;
	}

	public void setQuestionInfoDao(IQuestionInfoDao questionInfoDao) {
		this.questionInfoDao = questionInfoDao;
	}
}
