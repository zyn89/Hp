package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.OneLevel;

public interface IOneLevelDao {

	public List<OneLevel> GetOneLevels();
	
	public OneLevel GetOneLevel(long oid);
	
	public int insertOneLevel(OneLevel info);
	
	public int updateOneLevel(OneLevel info);
	
	public int deleteOneLevel(long oid);
}
