package com.joyque.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Decoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.util.FileUtil;
import com.joyque.dao.IUplordInfoDao;
import com.joyque.dao.IUserInfoDao;
import com.joyque.pojo.UplordInfo;
import com.joyque.pojo.UserInfo;
import com.joyque.service.IUplordService;

public class UplordServiceImpl implements IUplordService{
	private IUplordInfoDao uplordInfoDao;
	private IUserInfoDao userInfoDao;

	@Override
	public JSONObject UplordPic(String uid, long aid, String image1,
			String image2, String image3, String image4, String image5,
			String image6, String formate1, String formate2, String formate3,
			String formate4, String formate5, String formate6, String content) throws IOException {
		JSONObject json = new JSONObject();
		UplordInfo ui = new UplordInfo();
		ui.setUid(uid);
		ui.setAid(aid);
		ui.setContent(content);
		Date date = new Date();
		ui.setDate(date.getTime());
		
		image1 = image1.replace(' ', '+');
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buffer = decoder.decodeBuffer(image1);
		String url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate1);
		ui.setPicUrl1(url);
		
		image2 = image2.replace(' ', '+');
		buffer = decoder.decodeBuffer(image2);
		url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate2);
		ui.setPicUrl2(url);
		
		image3 = image3.replace(' ', '+');
		buffer = decoder.decodeBuffer(image3);
		url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate3);
		ui.setPicUrl3(url);
		
		image4 = image4.replace(' ', '+');
		buffer = decoder.decodeBuffer(image4);
		url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate4);
		ui.setPicUrl4(url);
		
		image5 = image5.replace(' ', '+');
		buffer = decoder.decodeBuffer(image5);
		url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate5);
		ui.setPicUrl5(url);
		
		image6 = image6.replace(' ', '+');
		buffer = decoder.decodeBuffer(image6);
		url = FileUtil.SaveUplordStringAsMedia(uid, buffer, formate6);
		ui.setPicUrl6(url);
		
		uplordInfoDao.insertUplordInfo(ui);
		return json;
	}
	
	@Override
	public JSONObject QueryUplord(long aid, int start, int end) {
		JSONObject json = new JSONObject();
		List<UplordInfo> infos = uplordInfoDao.GetUplordInfos(aid, start, end);
		JSONArray jArray = new JSONArray();
		for(UplordInfo info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("id", info.getId());
			j.accumulate("date", info.getDate());
			if(info.getContent() != null)
			{
				j.accumulate("content", info.getContent());
			}
			j.accumulate("picUrl1", info.getPicUrl1());
			j.accumulate("picUrl2", info.getPicUrl2());
			j.accumulate("picUrl3", info.getPicUrl3());
			j.accumulate("picUrl4", info.getPicUrl4());
			j.accumulate("picUrl5", info.getPicUrl5());
			j.accumulate("picUrl6", info.getPicUrl6());
			String uid = info.getUid();
			UserInfo user = userInfoDao.getUserInfoByUid(uid);
			if(user != null)
			{
				j.accumulate("name", user.getName());
			}
			jArray.add(j);
		}
		json.accumulate("uplord", jArray);
		return json;
	}

	public IUplordInfoDao getUplordInfoDao() {
		return uplordInfoDao;
	}

	public void setUplordInfoDao(IUplordInfoDao uplordInfoDao) {
		this.uplordInfoDao = uplordInfoDao;
	}

	public IUserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
}
