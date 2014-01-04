package com.joyque.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IOneLevelDao;
import com.joyque.pojo.OneLevel;

public class OneLevelDaoImpl extends SqlMapClientDaoSupport implements IOneLevelDao{

	@Override
	public OneLevel GetOneLevel(long oid) {
		OneLevel result = null;
		try{
			result = (OneLevel) this.getSqlMapClientTemplate().queryForObject(
					"GetOneLevel", oid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OneLevel> GetOneLevels() {
		List<OneLevel> result = null;
		try{
			result = (List<OneLevel>) this.getSqlMapClientTemplate().queryForList(
					"GetOneLevels");
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int deleteOneLevel(long oid) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().delete("deleteOneLevel", oid);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertOneLevel(OneLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().insert("insertOneLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int updateOneLevel(OneLevel info) {
		int result = 0;
		try{
			this.getSqlMapClientTemplate().update("updateOneLevel", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
