package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.ILotteryInfoDao;
import com.joyque.pojo.LotteryInfo;

public class LotteryInfoDaoImpl extends SqlMapClientDaoSupport implements ILotteryInfoDao{

	@Override
	public LotteryInfo GetLotteryInfo(long lid) {
		LotteryInfo result = null;
		try{
			result = (LotteryInfo) this.getSqlMapClientTemplate().queryForObject(
					"GetLotteryInfo", lid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LotteryInfo> GetLotteryInfos() {
		List<LotteryInfo> result = null;
		try{
			result = (List<LotteryInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetLotteryInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteLotteryInfo(long lid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteLotteryInfo", lid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertLotteryInfo(LotteryInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertLotteryInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateLotteryInfo(LotteryInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateLotteryInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
