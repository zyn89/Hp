package com.joyque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IUplordInfoDao;
import com.joyque.pojo.UplordInfo;

public class UplordInfoDaoImpl extends SqlMapClientDaoSupport implements IUplordInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UplordInfo> GetUplordInfos(long aid, int start, int end) {
		List<UplordInfo> result = null;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("start", start);
		parameter.put("aid", aid);
		parameter.put("end", end);
		try{
			result = (List<UplordInfo>) this.getSqlMapClientTemplate().queryForList(
					"GetUplordInfos", parameter);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

	@Override
	public int insertUplordInfo(UplordInfo info) {
		int result = 0;
        try{
			this.getSqlMapClientTemplate().insert("insertUplordInfo", info);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
