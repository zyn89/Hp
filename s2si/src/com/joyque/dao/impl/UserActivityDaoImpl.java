package com.joyque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserActivityDao;
import com.joyque.pojo.UserActivity;

public class UserActivityDaoImpl extends SqlMapClientDaoSupport implements IUserActivityDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserActivity> getUserActivities(long aid, int start, int end) {
		List<UserActivity> result = null;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("start", start);
		parameter.put("aid", aid);
		parameter.put("end", end);
		try{
			result = (List<UserActivity>) this.getSqlMapClientTemplate().queryForList(
					"getUserActivities", parameter);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public UserActivity getUserActivity(String uid, long aid) {
		UserActivity result = null;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("uid", uid);
		parameter.put("aid", aid);
		try{
			result = (UserActivity) this.getSqlMapClientTemplate().queryForObject(
					"getUserActivity", parameter);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUserActivity(UserActivity info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertUserActivity", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int GetUserActivitiesCount(long aid) {
		int result = 0;
		try{
			result = (Integer) this.getSqlMapClientTemplate().queryForObject("GetUserActivitiesCount", aid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
