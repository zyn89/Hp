package com.joyque.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.ITwoLevelDao;
import com.joyque.pojo.TwoLevel;

public class TwoLevelDaoImpl extends SqlMapClientDaoSupport implements ITwoLevelDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TwoLevel> GetTwoLevels(long tid) {
		List<TwoLevel> result = null;
		try{
			result = (List<TwoLevel>) this.getSqlMapClientTemplate().queryForList(
					"GetTwoLevels", tid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteTwoLevel(long id) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteTwoLevel", id);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertTwoLevel(TwoLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertTwoLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateTwoLevel(TwoLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateTwoLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public TwoLevel GetTwoLevel(long id) {
		TwoLevel result = null;
		try{
			result = (TwoLevel) this.getSqlMapClientTemplate().queryForObject("GetTwoLevel", id);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
