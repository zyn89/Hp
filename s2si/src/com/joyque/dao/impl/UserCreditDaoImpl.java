package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserCreditDao;
import com.joyque.pojo.UserCredit;

public class UserCreditDaoImpl extends SqlMapClientDaoSupport implements IUserCreditDao{

	@Override
	public UserCredit getUserCredit(String uid) {
		UserCredit result = null;
		try{
			result = (UserCredit)this.getSqlMapClientTemplate().queryForObject(
					"getUserCredit", uid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserCredit> getUserCreditInfos() {
		List<UserCredit> result = null;
		try{
			result = (List<UserCredit>)this.getSqlMapClientTemplate().queryForList(
					"getUserCreditInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUserCredit(UserCredit info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert(
					"insertUserCredit", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateUserCredit(UserCredit info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update(
					"updateUserCredit", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}
}
