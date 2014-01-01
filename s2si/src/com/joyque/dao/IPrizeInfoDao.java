package com.joyque.dao;

import com.joyque.pojo.PrizeInfo;

public interface IPrizeInfoDao {

	public PrizeInfo GetPrizeInfo(long pid);
	
	public long insertPrizeInfo(PrizeInfo info);
	
	public int updatePrizeInfo(PrizeInfo info);
	
	public int deletePrizeInfo(long pid);
}
