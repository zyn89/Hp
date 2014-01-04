package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.ICarouselInfoDao;
import com.joyque.pojo.CarouselInfo;

public class CarouselInfoDaoImpl extends SqlMapClientDaoSupport implements ICarouselInfoDao{

	@Override
	public CarouselInfo GetCarouselInfo(long cid) {
		CarouselInfo result = null;
		try{
			result = (CarouselInfo) this.getSqlMapClientTemplate().queryForObject(
					"GetCarouselInfo", cid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarouselInfo> GetCarouselInfos() {
		List<CarouselInfo> result = null;
		try{
			result = (List<CarouselInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetCarouselInfos");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteCarouselInfo(long cid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteCarouselInfo", cid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertCarouselInfo(CarouselInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertCarouselInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateCarouselInfo(CarouselInfo info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateCarouselInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
