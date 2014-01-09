package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.UplordInfo;

public interface IUplordInfoDao {

	public List<UplordInfo> GetUplordInfos(long aid, int start, int end);
	
	public int insertUplordInfo(UplordInfo info);
	
	public int GetUplordInfosCount(long aid);
}
