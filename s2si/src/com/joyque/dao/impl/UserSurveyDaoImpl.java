package com.joyque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserSurveyDao;
import com.joyque.pojo.UserSurvey;

public class UserSurveyDaoImpl extends SqlMapClientDaoSupport implements IUserSurveyDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserSurvey> GetUserSurveys(long qid) {
		List<UserSurvey> result = null;
		try{
			result = (List<UserSurvey>) this.getSqlMapClientTemplate().queryForList(
					"GetUserSurveys", qid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserSurvey> GetUserSurveysByPage(long qid, int start, int end) {
		List<UserSurvey> result = null;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("start", start);
		parameter.put("qid", qid);
		parameter.put("end", end);
		try{
			result = (List<UserSurvey>) this.getSqlMapClientTemplate().queryForList(
					"GetUserSurveysByPage", parameter);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUserSurvey(UserSurvey info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertUserSurvey", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public UserSurvey GetUserSurvey(String uid, long qid) {
		UserSurvey result = null;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("uid", uid);
		parameter.put("qid", qid);
		try{
			result = (UserSurvey) this.getSqlMapClientTemplate().queryForObject(
					"GetUserSurvey", parameter);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int GetUserSurveysCount(long qid) {
		int result = 0;
		try{
			result = (Integer) this.getSqlMapClientTemplate().queryForObject("GetUserSurveysCount", qid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
