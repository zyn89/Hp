package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IActivityInfoDao;
import com.joyque.pojo.ActivityInfo;

public class ActivityInfoDaoImpl extends SqlMapClientDaoSupport implements IActivityInfoDao{

	@Override
	public int deleteActivityInfo(long aid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete(
					"deleteActivityInfo", aid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public ActivityInfo getActivityInfo(long aid) {
		ActivityInfo result = null;
		try{
			result = (ActivityInfo) this.getSqlMapClientTemplate().queryForObject(
					"getActivityInfo", aid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityInfo> getActivityInfos() {
		List<ActivityInfo> result = null;
		try{
			result = (List<ActivityInfo>) this.getSqlMapClientTemplate().queryForList(
					"getActivityInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertActivityInfo(ActivityInfo info) {
		int result = 0;
		try{
		    this.getSqlMapClientTemplate().insert(
		    		"insertActivityInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateActivityInfo(ActivityInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update(
					"updateActivityInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
