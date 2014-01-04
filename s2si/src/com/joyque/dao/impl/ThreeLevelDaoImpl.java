package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IThreeLevelDao;
import com.joyque.pojo.ThreeLevel;

public class ThreeLevelDaoImpl extends SqlMapClientDaoSupport implements IThreeLevelDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ThreeLevel> GetThreeLevels(long hid) {
		List<ThreeLevel> result = null;
		try{
			result = (List<ThreeLevel>) this.getSqlMapClientTemplate().queryForList(
					"GetThreeLevels", hid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteThreeLevel(long id) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteThreeLevel", id);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertThreeLevel(ThreeLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertThreeLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateThreeLevel(ThreeLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateThreeLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
