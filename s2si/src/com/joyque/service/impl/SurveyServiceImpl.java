package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.util.FileUtil;
import com.joyque.dao.ISurveyInfoDao;
import com.joyque.dao.ISurveyQuestionDao;
import com.joyque.dao.IUserCreditDao;
import com.joyque.dao.IUserSurveyDao;
import com.joyque.pojo.SurveyInfo;
import com.joyque.pojo.SurveyQuestion;
import com.joyque.pojo.UserCredit;
import com.joyque.pojo.UserSurvey;
import com.joyque.service.ISurveyService;
import com.opensymphony.xwork2.ActionContext;

public class SurveyServiceImpl implements ISurveyService{
	private ISurveyInfoDao surveyInfoDao;
	private IUserSurveyDao userSurveyDao;
	private ISurveyQuestionDao surveyQuestionDao;
	private IUserCreditDao userCreditDao;

	@Override
	public void GetSurveyList() {		
		ActionContext.getContext().put("surveys", GetSurveyArray());
	}
	
	private JSONArray GetSurveyArray()
	{
		JSONArray jArray = new JSONArray();
		List<SurveyInfo> infos = surveyInfoDao.GetSurveyInfos();
		for(SurveyInfo info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("sid", info.getSid());
			j.accumulate("imageUrl", info.getImageUrl());
			j.accumulate("credit", info.getCredit());
			j.accumulate("date", info.getDate());
			jArray.add(j);
		}
		return jArray;
	}
	
	@Override
	public JSONObject AddSurvey(String uid, List<File> pics, List<String> picsContentType,
			int credit) throws IOException {
		JSONObject json = new JSONObject();
		SurveyInfo survey = new SurveyInfo();
		survey.setCredit(credit);
		Date date = new Date();
		survey.setDate(date.getTime());
		String url = FileUtil.SaveSurveyAsMedia(uid, pics.get(0), picsContentType.get(0));
		survey.setImageUrl(url);
		surveyInfoDao.insertSurveyInfo(survey);
		
		json.accumulate("surveys", GetSurveyArray());
		return json;
	}
	
	@Override
	public JSONObject DeleteSurvey(long sid) {
		JSONObject json = new JSONObject();
		surveyInfoDao.deleteSurveyInfo(sid);
		surveyQuestionDao.deleteSurveyQuestions(sid);
		json.accumulate("surveys", GetSurveyArray());
		return json;
	}
	
	@Override
	public void GetSurveyQuestions(long sid, String uid) {				
		ActionContext.getContext().put("questions", GetSurveyQutionArray(sid, uid));
	}
	
	private JSONArray GetSurveyQutionArray(long sid, String uid)
	{
		List<SurveyQuestion> infos = surveyQuestionDao.GetSurveyQuestions(sid);
		JSONArray jArray = new JSONArray();
		for(SurveyQuestion info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("qid", info.getQid());
			j.accumulate("imageUrl", info.getImageUrl());
			j.accumulate("a1", info.getA1());
			j.accumulate("a2", info.getA2());
			j.accumulate("a3", info.getA3());
			UserSurvey us = userSurveyDao.GetUserSurvey(uid, info.getQid());
			if(us == null)
			{
				j.accumulate("done", 0);
			}
			else
			{
				j.accumulate("done", 1);
			}
			jArray.add(j);
		}
		return jArray;
	}
	
	@Override
	public JSONObject AddSurveyQuestion(long sid, List<File> pics,
			List<String> picsContentType, String a1, String a2, String a3, String uid) throws IOException {
		JSONObject json = new JSONObject();
		SurveyQuestion sq = new SurveyQuestion();
		sq.setSid(sid);
		sq.setA1(a1);
		sq.setA2(a2);
		sq.setA3(a3);
		String url = FileUtil.SaveSurveyQuestionAsMedia(uid, pics.get(0), picsContentType.get(0));
		sq.setImageUrl(url);
		surveyQuestionDao.insertSurveyQuestion(sq);
		json.accumulate("questions", GetSurveyQutionArray(sid, uid));
		return json;
	}
	
	@Override
	public JSONObject UpdateSurveyQuestion(long sid, long qid, List<File> pics,
			List<String> picsContentType, String a1, String a2, String a3,
			String uid) throws IOException {
		JSONObject json = new JSONObject();
		SurveyQuestion sq = new SurveyQuestion();
		sq.setQid(qid);
		sq.setA1(a1);
		sq.setA2(a2);
		sq.setA3(a3);
		if(pics != null && pics.size() > 0)
		{
			String url = FileUtil.SaveSurveyQuestionAsMedia(uid, pics.get(0), picsContentType.get(0));
			sq.setImageUrl(url);
		}
		
		surveyQuestionDao.updateSurveyQuestion(sq);
		json.accumulate("questions", GetSurveyQutionArray(sid, uid));
		return json;
	}
	
	@Override
	public JSONObject DeleteSurveyQuestion(String uid, long qid, long sid) {
		JSONObject json = new JSONObject();
		surveyQuestionDao.deleteSurveyQuestion(qid);
		json.accumulate("questions", GetSurveyQutionArray(sid, uid));
		return json;
	}	
	
	@Override
	public JSONObject DoneSurveyQuestion(String uid, long qid, long sid, int isFinal, int aIndex) {
		JSONObject json = new JSONObject();
		UserSurvey uq = new UserSurvey();
		uq.setaIndex(aIndex);
		uq.setQid(qid);
		uq.setSid(sid);
		uq.setUid(uid);
		userSurveyDao.insertUserSurvey(uq);
		List<UserSurvey> infos = userSurveyDao.GetUserSurveys(qid);
		double count1 = 0;
		double count2 = 0;
		double count3 = 0;
		for(UserSurvey info : infos)
		{
			if(info.getaIndex() == 1)
			{
				count1 ++;
			}
			else if(info.getaIndex() == 2)
			{
				count2 ++;
			}
			else
			{
				count3 ++;
			}
		}
		double sum = count1 + count2 + count3;
		if(sum == 0)
		{
			json.accumulate("p1", 0);
			json.accumulate("p2", 0);
			json.accumulate("p3", 0);
		}
		else
		{
			json.accumulate("p1", count1 / sum);
			json.accumulate("p2", count2 / sum);
			json.accumulate("p3", 1 - ((count1 + count2) / sum));
		}
		if(isFinal == 1)
		{
			SurveyInfo si = surveyInfoDao.GetSurveyInfo(sid);
			UserCredit uc = userCreditDao.getUserCredit(uid);
			uc.setCredit(uc.getCredit() + si.getCredit());
			userCreditDao.updateUserCredit(uc);
			json.accumulate("credit", uc.getCredit());
		}
		return json;
	}

	public ISurveyInfoDao getSurveyInfoDao() {
		return surveyInfoDao;
	}

	public void setSurveyInfoDao(ISurveyInfoDao surveyInfoDao) {
		this.surveyInfoDao = surveyInfoDao;
	}

	public IUserSurveyDao getUserSurveyDao() {
		return userSurveyDao;
	}

	public void setUserSurveyDao(IUserSurveyDao userSurveyDao) {
		this.userSurveyDao = userSurveyDao;
	}

	public ISurveyQuestionDao getSurveyQuestionDao() {
		return surveyQuestionDao;
	}

	public void setSurveyQuestionDao(ISurveyQuestionDao surveyQuestionDao) {
		this.surveyQuestionDao = surveyQuestionDao;
	}

	public IUserCreditDao getUserCreditDao() {
		return userCreditDao;
	}

	public void setUserCreditDao(IUserCreditDao userCreditDao) {
		this.userCreditDao = userCreditDao;
	}	
}
