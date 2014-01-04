package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.FourLevel;

public interface IFourLevelDao {

	public List<FourLevel> GetFourLevels(long fid);
	
	public int insertFourLevel(FourLevel info);
	
	public int updateFourLevel(FourLevel info);
	
	public int deleteFourLevel(long id);
}
