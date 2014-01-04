package com.joyque.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.dao.ICarouselInfoDao;
import com.joyque.dao.IFourLevelDao;
import com.joyque.dao.IOneLevelDao;
import com.joyque.dao.IThreeLevelDao;
import com.joyque.dao.ITwoLevelDao;
import com.joyque.pojo.CarouselInfo;
import com.joyque.pojo.FourLevel;
import com.joyque.pojo.OneLevel;
import com.joyque.pojo.ThreeLevel;
import com.joyque.pojo.TwoLevel;
import com.joyque.service.IWebService;

public class WebServiceImpl implements IWebService{

	private ICarouselInfoDao carouselInfoDao;
	
	private IOneLevelDao oneLevelDao;
	
	private ITwoLevelDao twoLevelDao;
	
	private IThreeLevelDao threeLevelDao;
	
	private IFourLevelDao fourLevelDao;
	
	@Override
	public JSONObject GetWebs() {
		JSONObject json = new JSONObject();
		//一级界面
		json = GetOneLevel();
		//轮播图
		List<CarouselInfo> carouselInfos = carouselInfoDao.GetCarouselInfos();
		JSONArray carouselArray = new JSONArray();
		for(CarouselInfo info : carouselInfos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("image1Url", info.getImage1Url());
			j.accumulate("image2Url", info.getImage2Url());
			j.accumulate("image3Url", info.getImage3Url());
			carouselArray.add(j);
		}
		json.accumulate("carousels", carouselArray);
		return json;
	}
	
	@Override
	public JSONObject GetWeb() {
		JSONObject json = new JSONObject();
		//一级界面
		json = GetOneLevel();
		//轮播图
		List<CarouselInfo> carouselInfos = carouselInfoDao.GetCarouselInfos();
		JSONArray carouselArray = new JSONArray();
		CarouselInfo carousel = carouselInfos.get(0);
		carouselArray.add(carousel.getImage1Url());
		carouselArray.add(carousel.getImage2Url());
		carouselArray.add(carousel.getImage3Url());
		json.accumulate("carousels", carouselArray);
		return json;
	}
	
	private JSONObject GetOneLevel()
	{
		List<OneLevel> oneLevels = oneLevelDao.GetOneLevels();
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		for(OneLevel info : oneLevels)
		{
			JSONObject j = new JSONObject();
			j.accumulate("id", info.getOid());
			j.accumulate("tid", info.getTid());
			j.accumulate("url", info.getUrl());
			j.accumulate("imageUrl", info.getImageUrl());
			jArray.add(j);
		}
		json.accumulate("one_level", jArray);
		return json;
	}
	
	@Override
	public JSONObject GetTwoLevel(long tid) {
		JSONObject json = new JSONObject();
		List<TwoLevel> twoLevels = twoLevelDao.GetTwoLevels(tid);
		JSONArray jArray = new JSONArray();
		for(TwoLevel info : twoLevels)
		{
			JSONObject j = new JSONObject();
			j.accumulate("id", info.getId());
			j.accumulate("hid", info.getHid());
			j.accumulate("content", info.getContent());
			j.accumulate("imageUrl", info.getImageUrl());
			jArray.add(j);
		}
		json.accumulate("two_level", jArray);
		return json;
	}
	
	@Override
	public JSONObject GetThreeLevel(long hid) {
		JSONObject json = new JSONObject();
		List<ThreeLevel> threeLevels = threeLevelDao.GetThreeLevels(hid);
		JSONArray jArray = new JSONArray();
		for(ThreeLevel info : threeLevels)
		{
			JSONObject j = new JSONObject();
			j.accumulate("id", info.getId());
			j.accumulate("fid", info.getFid());
			j.accumulate("content", info.getContent());
			j.accumulate("imageUrl", info.getImageUrl());
			j.accumulate("url", info.getUrl());
			jArray.add(j);
		}
		json.accumulate("three_level", jArray);
		return json;
	}
	
	@Override
	public JSONObject GetFourLevel(long fid) {
		JSONObject json = new JSONObject();
		List<FourLevel> fourLevels = fourLevelDao.GetFourLevels(fid);
		JSONArray jArray = new JSONArray();
		for(FourLevel info : fourLevels)
		{
			JSONObject j = new JSONObject();
			j.accumulate("id", info.getId());
			j.accumulate("content", info.getContent());
			j.accumulate("imageUrl", info.getImageUrl());
			jArray.add(j);
		}
		json.accumulate("four_level", jArray);
		return json;
	}

	public ICarouselInfoDao getCarouselInfoDao() {
		return carouselInfoDao;
	}

	public void setCarouselInfoDao(ICarouselInfoDao carouselInfoDao) {
		this.carouselInfoDao = carouselInfoDao;
	}

	public IOneLevelDao getOneLevelDao() {
		return oneLevelDao;
	}

	public void setOneLevelDao(IOneLevelDao oneLevelDao) {
		this.oneLevelDao = oneLevelDao;
	}

	public ITwoLevelDao getTwoLevelDao() {
		return twoLevelDao;
	}

	public void setTwoLevelDao(ITwoLevelDao twoLevelDao) {
		this.twoLevelDao = twoLevelDao;
	}

	public IThreeLevelDao getThreeLevelDao() {
		return threeLevelDao;
	}

	public void setThreeLevelDao(IThreeLevelDao threeLevelDao) {
		this.threeLevelDao = threeLevelDao;
	}

	public IFourLevelDao getFourLevelDao() {
		return fourLevelDao;
	}

	public void setFourLevelDao(IFourLevelDao fourLevelDao) {
		this.fourLevelDao = fourLevelDao;
	}
}
