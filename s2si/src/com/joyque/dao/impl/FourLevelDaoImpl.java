package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IFourLevelDao;
import com.joyque.pojo.FourLevel;

public class FourLevelDaoImpl extends SqlMapClientDaoSupport implements IFourLevelDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<FourLevel> GetFourLevels(long fid) {
		List<FourLevel> result = null;
		try{
			result = (List<FourLevel>) this.getSqlMapClientTemplate().queryForList(
					"GetFourLevels", fid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteFourLevel(long id) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().delete("deleteFourLevel", id);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertFourLevel(FourLevel info) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().insert("insertFourLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateFourLevel(FourLevel info) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().update("updateFourLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}
}
