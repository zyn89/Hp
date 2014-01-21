package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseServiceException;
import com.joyque.common.util.DefaultValue;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.common.util.FileUtil;
import com.joyque.dao.IActivityInfoDao;
import com.joyque.dao.IQuestionInfoDao;
import com.joyque.dao.IUserActivityDao;
import com.joyque.dao.IUserCreditDao;
import com.joyque.dao.IUserInfoDao;
import com.joyque.pojo.ActivityInfo;
import com.joyque.pojo.QuestionInfo;
import com.joyque.pojo.UserActivity;
import com.joyque.pojo.UserCredit;
import com.joyque.pojo.UserInfo;
import com.joyque.service.IQuestionService;

public class QuestionServiceImpl extends BaseAction implements IQuestionService{

	private static final long serialVersionUID = 7826621048803837552L;
	private IQuestionInfoDao questionInfoDao;
	private IUserActivityDao userActivityDao;
	private IActivityInfoDao activityInfoDao;
	private IUserInfoDao userInfoDao;
	private IUserCreditDao userCreditDao;

	@Override
	public JSONObject GetQuestions(long aid) {
		JSONObject json = new JSONObject();
		json.accumulate("questions", GetQuestionArray(aid));
		return json;
	}

	private JSONArray GetQuestionArray(long aid)
	{
		JSONArray jArray = new JSONArray();
		List<QuestionInfo> infos = questionInfoDao.GetQuestionInfoList(aid);
		for(QuestionInfo info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("qid", info.getQid());
			j.accumulate("questionUrl", info.getQuestionUrl());
			JSONArray array = new JSONArray();
			array.add(info.getA1());
			array.add(info.getA2());
			array.add(info.getA3());
			j.accumulate("choiceitems", array);
			j.accumulate("aIndex", info.getaIndex());
			j.accumulate("score", info.getScore());
			jArray.add(j);
		}
		return jArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject DoneQuestion(String uid, int score, long aid) {
		JSONObject json = new JSONObject();
		UserActivity ua = userActivityDao.getUserActivity(uid, aid);
		if(ua != null)
		{
			throw new BaseServiceException(ExceptionUtil.DoneActiity,uid);
		}
		ua = new UserActivity();
		ua.setUid(uid);
		ua.setAid(aid);
		ua.setScore(score);
		userActivityDao.insertUserActivity(ua);
		ActivityInfo ai = activityInfoDao.getActivityInfo(aid);
		UserCredit uc = userCreditDao.getUserCredit(uid);
		if(score >= ai.getScore())
		{	
			uc.setCredit(uc.getCredit() + ai.getCredit());
			userCreditDao.updateUserCredit(uc);
		}
		
		Map session = getSession();
		session.remove("credit");
		session.put("credit", uc.getCredit());
		return json;
	}
	
	@Override
	public JSONObject QueryUserScore(long aid, int start, int end) {
		JSONObject json = new JSONObject();
		List<UserActivity> uas = userActivityDao.getUserActivities(aid, start, end);
		JSONArray jArray = new JSONArray();
		for(UserActivity ua : uas)
		{
			JSONObject j = new JSONObject();
			String uid = ua.getUid();
			UserInfo user = userInfoDao.getUserInfoByUid(uid);
			if(user != null)
			{
				j.accumulate("name", user.getName());
			}
			
			j.accumulate("score", ua.getScore());
			jArray.add(j);
		}
		int count = userActivityDao.GetUserActivitiesCount(aid);
		json.accumulate("count", count);
		json.accumulate("scores", jArray);
		return json;
	}
	
	@Override
	public JSONObject GetQaActivity() {
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		List<ActivityInfo> ais = activityInfoDao.getActivityInfos();
		for(ActivityInfo ai : ais)
		{
			if(ai.getType().equals(DefaultValue.ActivityQA))
			{
				JSONObject j = new JSONObject();
				j.accumulate("aid", ai.getAid());
				j.accumulate("imageUrl", ai.getImageUrl());
				j.accumulate("date", ai.getDate());
				jArray.add(j);
			}
		}
		json.accumulate("activitys", jArray);
		return json;
	}
	
	@Override
	public JSONObject AddQuestion(String uid, long aid, List<File> pics, List<String> picsContentType,
			String a1, String a2, String a3, int aIndex, int score) throws IOException {
		JSONObject json = new JSONObject();
		QuestionInfo qi = new QuestionInfo();
		qi.setDefaultValue();
		qi.setAid(aid);
		qi.setA1(a1);
		qi.setA2(a2);
		qi.setA3(a3);
		qi.setaIndex(aIndex);
		qi.setScore(score);
		
		String url = FileUtil.SaveQuestionStringAsMedia(uid, pics.get(0), picsContentType.get(0));
		qi.setQuestionUrl(url);
		questionInfoDao.insertQuestionInfo(qi);
		json.accumulate("questions", GetQuestionArray(aid));
		return json;
	}
	
	@Override
	public JSONObject UpdateQuestion(String uid, long qid, List<File> pics, List<String> picsContentType,
			String a1, String a2, String a3, int aIndex, int score, long aid) throws IOException {
		JSONObject json = new JSONObject();
		QuestionInfo qi = new QuestionInfo();
		qi.setAid(aid);
		qi.setQid(qid);
		qi.setA1(a1);
		qi.setA2(a2);
		qi.setA3(a3);
		qi.setaIndex(aIndex);
		qi.setScore(score);
		
		if(pics != null && pics.size() == 1)
		{
			String url = FileUtil.SaveQuestionStringAsMedia(uid, pics.get(0), picsContentType.get(0));
			qi.setQuestionUrl(url);
		}

		questionInfoDao.updateQuestionInfo(qi);
		
		json.accumulate("questions", GetQuestionArray(aid));
		return json;
	}
	
	@Override
	public JSONObject DeleteQuestion(long qid, long aid) {
		JSONObject json = new JSONObject();
		questionInfoDao.deleteQuestionInfo(qid);
		json.accumulate("questions", GetQuestionArray(aid));
		return json;
	}

	public IQuestionInfoDao getQuestionInfoDao() {
		return questionInfoDao;
	}

	public void setQuestionInfoDao(IQuestionInfoDao questionInfoDao) {
		this.questionInfoDao = questionInfoDao;
	}

	public IUserActivityDao getUserActivityDao() {
		return userActivityDao;
	}

	public void setUserActivityDao(IUserActivityDao userActivityDao) {
		this.userActivityDao = userActivityDao;
	}

	public IActivityInfoDao getActivityInfoDao() {
		return activityInfoDao;
	}

	public void setActivityInfoDao(IActivityInfoDao activityInfoDao) {
		this.activityInfoDao = activityInfoDao;
	}

	public IUserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public IUserCreditDao getUserCreditDao() {
		return userCreditDao;
	}

	public void setUserCreditDao(IUserCreditDao userCreditDao) {
		this.userCreditDao = userCreditDao;
	}
}
