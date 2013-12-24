package com.joyque.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserInfoDao;
import com.joyque.pojo.UserInfo;

public class UserInfoDaoImpl extends SqlMapClientDaoSupport implements IUserInfoDao{

	@Override
	public UserInfo getUserInfo(String phone) {
		UserInfo result = null;
		try{
			result = (UserInfo)this.getSqlMapClientTemplate().queryForObject(
					"getUserInfo", phone);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public UserInfo getUserInfoByUid(String uid) {
		UserInfo result = null;
		try{
			result = (UserInfo)this.getSqlMapClientTemplate().queryForObject(
					"getUserInfoByUid", uid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUserInfo(UserInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert(
					"insertUserInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateUserInfo(UserInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update(
					"updateUserInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
