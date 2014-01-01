package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IExchangeInfoDao;
import com.joyque.pojo.ExchangeInfo;

public class ExchangeInfoDaoImpl extends SqlMapClientDaoSupport implements IExchangeInfoDao{

	@Override
	public ExchangeInfo GetExchangeInfo(long eid) {
		ExchangeInfo result = null;
		try{
			result = (ExchangeInfo) this.getSqlMapClientTemplate().queryForObject(
					"GetExchangeInfo", eid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExchangeInfo> GetExchangeInfos() {
		List<ExchangeInfo> result = null;
		try{
			result = (List<ExchangeInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetExchangeInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteExchangeInfo(long eid) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().delete("deleteExchangeInfo", eid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertExchangeInfo(ExchangeInfo info) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().insert("insertExchangeInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateExchangeInfo(ExchangeInfo info) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().update("updateExchangeInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
