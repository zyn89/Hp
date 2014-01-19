package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.util.FileUtil;
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
			j.accumulate("cid", info.getCid());
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
			j.accumulate("url", info.getUrl());
			j.accumulate("imageUrl", info.getImageUrl());
			j.accumulate("isStatic", info.getIsStatic());
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
			j.accumulate("content", info.getContent());
			j.accumulate("imageUrl", info.getImageUrl());
			j.accumulate("url", info.getUrl());
			j.accumulate("isFinal", info.getIsFinal());
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
			j.accumulate("url", info.getUrl());
			jArray.add(j);
		}
		json.accumulate("four_level", jArray);
		return json;
	}
	
	@Override
	public JSONObject AddCarouselInfo(String uid, File pic1, File pic2, File pic3,
			String pic1Type, String pic2Type, String pic3Type) throws IOException {
		JSONObject json = new JSONObject();
		CarouselInfo carouselInfo = new CarouselInfo();
		String url = FileUtil.SaveCarouselAsMedia(uid, pic1, pic1Type);
		carouselInfo.setImage1Url(url);
		url = FileUtil.SaveCarouselAsMedia(uid, pic2, pic2Type);
		carouselInfo.setImage2Url(url);
		url = FileUtil.SaveCarouselAsMedia(uid, pic3, pic3Type);
		carouselInfo.setImage3Url(url);
		carouselInfoDao.insertCarouselInfo(carouselInfo);
		
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject UpdateCarouselInfo(String uid, long cid, File pic1, File pic2,
			File pic3, String pic1Type, String pic2Type, String pic3Type) throws IOException {
		JSONObject json = new JSONObject();
		CarouselInfo carouselInfo = carouselInfoDao.GetCarouselInfo(cid);
		if(pic1 != null)
		{
			String url = FileUtil.SaveCarouselAsMedia(uid, pic1, pic1Type);
			carouselInfo.setImage1Url(url);
		}
		if(pic2 != null)
		{
			String url = FileUtil.SaveCarouselAsMedia(uid, pic2, pic2Type);
			carouselInfo.setImage2Url(url);
		}
		if(pic3 != null)
		{
			String url = FileUtil.SaveCarouselAsMedia(uid, pic3, pic3Type);
			carouselInfo.setImage3Url(url);
		}		
		carouselInfoDao.updateCarouselInfo(carouselInfo);
		
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject DeleteCarouselInfo(long cid) {
		JSONObject json = new JSONObject();
		carouselInfoDao.deleteCarouselInfo(cid);
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject AddOneLevel(String uid, List<File> pics,
			List<String> picsContentType, String url, int isStatic) throws IOException {
		JSONObject json = new JSONObject();
		OneLevel one = new OneLevel();
		one.setUrl(url);
		one.setIsStatic(isStatic);
		String imageUrl = FileUtil.SaveOneLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
		one.setImageUrl(imageUrl);
		oneLevelDao.insertOneLevel(one);
		
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject UpdateOneLevel(String uid, List<File> pics,
			List<String> picsContentType, String url, long id, int isStatic) throws IOException {
		JSONObject json = new JSONObject();
		OneLevel one = oneLevelDao.GetOneLevel(id);
		one.setUrl(url);
		one.setIsStatic(isStatic);
		if(pics != null && pics.size() > 0)
		{
			String imageUrl = FileUtil.SaveOneLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
			one.setImageUrl(imageUrl);
		}
		
		oneLevelDao.updateOneLevel(one);
		
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject DeleteOneLevel(long id) {
		JSONObject json = new JSONObject();
		OneLevel one = oneLevelDao.GetOneLevel(id);
		oneLevelDao.deleteOneLevel(id);
		List<TwoLevel> two = twoLevelDao.GetTwoLevels(one.getOid());
		if(two != null)
		{
			for(TwoLevel info : two)
			{
				twoLevelDao.deleteTwoLevel(info.getId());
				List<ThreeLevel> three = threeLevelDao.GetThreeLevels(info.getId());
				if(three != null)
				{
					for(ThreeLevel tl : three)
					{
						threeLevelDao.deleteThreeLevel(tl.getId());
						List<FourLevel> four = fourLevelDao.GetFourLevels(tl.getId());
						if(four != null)
						{
							for(FourLevel fl : four)
							{
								fourLevelDao.deleteFourLevel(fl.getId());
							}
						}
					}
				}
			}
		}
		json = GetWebs();
		return json;
	}
	
	@Override
	public JSONObject AddTwoLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long tid) throws IOException {
		
		JSONObject json = new JSONObject();
		TwoLevel two = new TwoLevel();
		two.setContent(content);
		two.setTid(tid);
		String imageUrl = FileUtil.SaveTwoLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
		two.setImageUrl(imageUrl);
		twoLevelDao.insertTwoLevel(two);
		
		json = GetTwoLevel(tid);
		return json;
	}
	
	@Override
	public JSONObject UpdateTwoLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id) throws IOException {
		JSONObject json = new JSONObject();
		TwoLevel two = twoLevelDao.GetTwoLevel(id);
		two.setContent(content);
		if(pics != null && pics.size() > 0)
		{
			String imageUrl = FileUtil.SaveTwoLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
			two.setImageUrl(imageUrl);
		}
		
		twoLevelDao.updateTwoLevel(two);
		
		json = GetTwoLevel(two.getTid());
		return json;
	}
	
	@Override
	public JSONObject DeleteTwoLevel(long id) {
		JSONObject json = new JSONObject();
		TwoLevel two = twoLevelDao.GetTwoLevel(id);
		twoLevelDao.deleteTwoLevel(id);
		List<ThreeLevel> three = threeLevelDao.GetThreeLevels(id);
		if(three != null)
		{
			for(ThreeLevel tl : three)
			{
				threeLevelDao.deleteThreeLevel(tl.getId());
				List<FourLevel> four = fourLevelDao.GetFourLevels(tl.getId());
				if(four != null)
				{
					for(FourLevel fl : four)
					{
						fourLevelDao.deleteFourLevel(fl.getId());
					}
				}
			}
		}
		json = GetTwoLevel(two.getTid());
		return json;
	}
	
	@Override
	public JSONObject AddThreeLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long hid, String url, int isFinal) throws IOException {
		JSONObject json = new JSONObject();
		ThreeLevel three = new ThreeLevel();
		three.setContent(content);
		three.setHid(hid);
		three.setUrl(url);
		String imageUrl = FileUtil.SaveThreeLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
		three.setImageUrl(imageUrl);
		three.setIsFinal(isFinal);
		threeLevelDao.insertThreeLevel(three);
		
		json = GetThreeLevel(hid);
		return json;
	}
	
	@Override
	public JSONObject UpdateThreeLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id, String url, int isFinal) throws IOException {
		JSONObject json = new JSONObject();
		ThreeLevel three = threeLevelDao.GetThreeLevel(id);
		three.setContent(content);
		three.setUrl(url);
		three.setIsFinal(isFinal);
		if(pics != null && pics.size() > 0)
		{
			String imageUrl = FileUtil.SaveThreeLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
			three.setImageUrl(imageUrl);
		}
		
		threeLevelDao.updateThreeLevel(three);
		
		json = GetThreeLevel(three.getHid());
		return json;
	}
	
	@Override
	public JSONObject DeleteThreeLevel(long id) {
		JSONObject json = new JSONObject();
		ThreeLevel three = threeLevelDao.GetThreeLevel(id);
		threeLevelDao.deleteThreeLevel(id);
		List<FourLevel> four = fourLevelDao.GetFourLevels(id);
		if(four != null)
		{
			for(FourLevel fl : four)
			{
				fourLevelDao.deleteFourLevel(fl.getId());
			}
		}
		json = GetThreeLevel(three.getHid());
		return json;
	}
	
	@Override
	public JSONObject AddFourLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long fid, String url) throws IOException {
		JSONObject json = new JSONObject();
		FourLevel four = new FourLevel();
		four.setContent(content);
		four.setFid(fid);	
		four.setUrl(url);
		if(pics != null && pics.size() > 0)
		{
			String imageUrl = FileUtil.SaveFourLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
			four.setImageUrl(imageUrl);
		}
		
		fourLevelDao.insertFourLevel(four);
		
		json = GetFourLevel(fid);
		return json;
	}
	
	@Override
	public JSONObject UpdateFourLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id, String url) throws IOException {
		JSONObject json = new JSONObject();
		FourLevel four = fourLevelDao.GetFourLevel(id);
		four.setContent(content);
		if(pics != null && pics.size() > 0)
		{
			String imageUrl = FileUtil.SaveFourLevelAsMedia(uid, pics.get(0), picsContentType.get(0));
			four.setImageUrl(imageUrl);
		}
		four.setUrl(url);
		fourLevelDao.updateFourLevel(four);
		
		json = GetFourLevel(four.getFid());
		return json;
	}
	
	@Override
	public JSONObject DeleteFourLevel(long id) {
		JSONObject json = new JSONObject();
		FourLevel four = fourLevelDao.GetFourLevel(id);
		fourLevelDao.deleteFourLevel(id);
		
		json = GetFourLevel(four.getFid());
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
