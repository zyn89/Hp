package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.LotteryInfo;

public interface ILotteryInfoDao {

	public List<LotteryInfo> GetLotteryInfos();
	
	public LotteryInfo GetLotteryInfo(long lid);
	
	public int insertLotteryInfo(LotteryInfo info);
	
	public int updateLotteryInfo(LotteryInfo info);
	
	public int deleteLotteryInfo(long lid);
}
