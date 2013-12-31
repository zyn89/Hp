package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.ISurveyInfoDao;
import com.joyque.pojo.SurveyInfo;

public class SurveyInfoDaoImpl extends SqlMapClientDaoSupport implements ISurveyInfoDao{

	@Override
	public SurveyInfo GetSurveyInfo(long sid) {
		SurveyInfo result = null;
		try{
			result = (SurveyInfo) this.getSqlMapClientTemplate().queryForObject(
					"GetSurveyInfo", sid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyInfo> GetSurveyInfos() {
		List<SurveyInfo> result = null;
        try{
			result = (List<SurveyInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetSurveyInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteSurveyInfo(long sid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteSurveyInfo", sid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertSurveyInfo(SurveyInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertSurveyInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateSurveyInfo(SurveyInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateSurveyInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
