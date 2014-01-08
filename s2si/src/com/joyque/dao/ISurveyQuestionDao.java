package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.SurveyQuestion;

public interface ISurveyQuestionDao {

	public List<SurveyQuestion> GetSurveyQuestions(long sid);
	
	public SurveyQuestion GetSurveyQuestion(long qid);
	
	public long insertSurveyQuestion(SurveyQuestion info);
	
	public int updateSurveyQuestion(SurveyQuestion info);
	
	public int deleteSurveyQuestions(long sid);
	
	public int deleteSurveyQuestion(long qid);
}
