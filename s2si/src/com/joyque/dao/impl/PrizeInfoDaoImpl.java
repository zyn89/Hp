package com.joyque.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IPrizeInfoDao;
import com.joyque.pojo.PrizeInfo;

public class PrizeInfoDaoImpl extends SqlMapClientDaoSupport implements IPrizeInfoDao{

	@Override
	public PrizeInfo GetPrizeInfo(long pid) {
		PrizeInfo result = null;
		try{
			result = (PrizeInfo) this.getSqlMapClientTemplate().queryForObject(
					"GetPrizeInfo", pid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deletePrizeInfo(long pid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deletePrizeInfo", pid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public long insertPrizeInfo(PrizeInfo info) {
		long result = 0;
		try{
			result = (Long)this.getSqlMapClientTemplate().insert("insertPrizeInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updatePrizeInfo(PrizeInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updatePrizeInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
