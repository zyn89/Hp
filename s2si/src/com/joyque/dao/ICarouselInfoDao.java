package com.joyque.dao;

import java.util.List;

import com.joyque.pojo.CarouselInfo;

public interface ICarouselInfoDao {

	public List<CarouselInfo> GetCarouselInfos();
	
	public CarouselInfo GetCarouselInfo(long cid);
	
	public int insertCarouselInfo(CarouselInfo info);
	
	public int updateCarouselInfo(CarouselInfo info);
	
	public int deleteCarouselInfo(long cid);
}
