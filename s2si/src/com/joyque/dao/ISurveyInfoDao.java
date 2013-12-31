package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.SurveyInfo;

public interface ISurveyInfoDao {

	public List<SurveyInfo> GetSurveyInfos();
	
	public SurveyInfo GetSurveyInfo(long sid);
	
	public int insertSurveyInfo(SurveyInfo info);
	
	public int updateSurveyInfo(SurveyInfo info);
	
	public int deleteSurveyInfo(long sid);
}
