package com.joyque.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.common.exception.BaseDaoException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.dao.IAdminInfoDao;
import com.joyque.pojo.AdminInfo;

public class AdminInfoDaoImpl extends SqlMapClientDaoSupport implements IAdminInfoDao{

	@Override
	public AdminInfo GetAdminInfo(String account) {
		AdminInfo result = null;
		try{
			result = (AdminInfo) this.getSqlMapClientTemplate().queryForObject("GetAdminInfo", account);
		} catch (DataAccessException e) {
			throw new BaseDaoException(ExceptionUtil.IllegalSqlOperation);
		}
		return result;
	}

}
