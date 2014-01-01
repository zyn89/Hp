package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.ExchangeInfo;

public interface IExchangeInfoDao {

	public List<ExchangeInfo> GetExchangeInfos();
	
	public ExchangeInfo GetExchangeInfo(long eid);
	
	public int insertExchangeInfo(ExchangeInfo info);
	
	public int updateExchangeInfo(ExchangeInfo info);
	
	public int deleteExchangeInfo(long eid);
}
