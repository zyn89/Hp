package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.ISurveyQuestionDao;
import com.joyque.pojo.SurveyQuestion;

public class SurveyQuestionDaoImpl extends SqlMapClientDaoSupport implements ISurveyQuestionDao{

	@Override
	public SurveyQuestion GetSurveyQuestion(long qid) {
		SurveyQuestion result = null;
		try{
			result = (SurveyQuestion) this.getSqlMapClientTemplate().queryForObject(
					"GetSurveyQuestion", qid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyQuestion> GetSurveyQuestions(long sid) {
		List<SurveyQuestion> result = null;
		try{
			result = (List<SurveyQuestion>) this.getSqlMapClientTemplate().queryForList(
					"GetSurveyQuestions", sid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteSurveyQuestion(long qid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete(
					"deleteSurveyQuestion", qid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteSurveyQuestions(long sid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteSurveyQuestions", sid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertSurveyQuestion(SurveyQuestion info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertSurveyQuestion", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateSurveyQuestion(SurveyQuestion info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateSurveyQuestion", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
