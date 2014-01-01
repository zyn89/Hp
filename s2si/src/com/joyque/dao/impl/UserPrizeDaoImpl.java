package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUserPrizeDao;
import com.joyque.pojo.UserPrize;

public class UserPrizeDaoImpl extends SqlMapClientDaoSupport implements IUserPrizeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPrize> GetUserPrizes(String uid) {
		List<UserPrize> result = null;
		try{
			result = (List<UserPrize>) this.getSqlMapClientTemplate().queryForObject(
					"GetUserPrizes", uid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteUserPrize(long id) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteUserPrize", id);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUserPrize(UserPrize info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertUserPrize", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
