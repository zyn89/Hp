package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.QuestionInfo;

public interface IQuestionInfoDao {

	public List<QuestionInfo> GetQuestionInfoList(long aid);
	
	public QuestionInfo getQuestionInfo(long qid);
	
	public int insertQuestionInfo(QuestionInfo info);
	
	public int updateQuestionInfo(QuestionInfo info);
	
	public int deleteQuestionInfoList(long aid);
	
	public int deleteQuestionInfo(long pid);
}
