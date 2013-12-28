package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IQuestionInfoDao;
import com.joyque.pojo.QuestionInfo;

public class QuestionInfoDaoImpl extends SqlMapClientDaoSupport implements IQuestionInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInfo> GetQuestionInfoList(long aid) {
		List<QuestionInfo> result = null;
		try{
			result = (List<QuestionInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetQuestionInfoList", aid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteQuestionInfo(long pid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteQuestionInfo", pid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteQuestionInfoList(long aid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteQuestionInfoList", aid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public QuestionInfo getQuestionInfo(long qid) {
		QuestionInfo result = null;
		try{
			result = (QuestionInfo) this.getSqlMapClientTemplate().queryForObject("getQuestionInfo", qid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertQuestionInfo(QuestionInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertQuestionInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateQuestionInfo(QuestionInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateQuestionInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
