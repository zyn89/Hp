package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.ThreeLevel;

public interface IThreeLevelDao {

	public List<ThreeLevel> GetThreeLevels(long hid);
	
	public int insertThreeLevel(ThreeLevel info);
	
	public int updateThreeLevel(ThreeLevel info);
	
	public int deleteThreeLevel(long id);
}
