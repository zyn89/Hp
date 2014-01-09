package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UserSurvey;

public interface IUserSurveyDao {

	public List<UserSurvey> GetUserSurveys(long qid);
	
	public List<UserSurvey> GetUserSurveysByPage(long qid, int start, int end);
	
	public UserSurvey GetUserSurvey(String uid, long qid);
	
	public int insertUserSurvey(UserSurvey info);
	
	public int GetUserSurveysCount(long qid);
}
