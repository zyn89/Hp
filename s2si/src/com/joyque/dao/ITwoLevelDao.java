package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.TwoLevel;

public interface ITwoLevelDao {

	public List<TwoLevel> GetTwoLevels(long tid);
	
	public int insertTwoLevel(TwoLevel info);
	
	public int updateTwoLevel(TwoLevel info);
	
	public int deleteTwoLevel(long id);
}
