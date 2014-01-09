package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
	public JSONObject UplordPic(String uid, long aid, List<File> pics,
			List<String> picsContentType, String content) throws IOException {
		JSONObject json = new JSONObject();
		UplordInfo ui = new UplordInfo();
		ui.setUid(uid);
		ui.setAid(aid);
		ui.setContent(content);
		Date date = new Date();
		ui.setDate(date.getTime());
		
		String url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(0), picsContentType.get(0));
		ui.setPicUrl1(url);
		
		if(pics.size() > 1)
		{
			url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(1), picsContentType.get(1));
			ui.setPicUrl2(url);
		}		
		
		if(pics.size() > 2)
		{
			url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(2), picsContentType.get(2));
			ui.setPicUrl3(url);
		}		
		
		if(pics.size() > 3)
		{
			url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(3), picsContentType.get(3));
			ui.setPicUrl4(url);
		}	
		
		if(pics.size() > 4)
		{
			url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(4), picsContentType.get(4));
			ui.setPicUrl5(url);
		}		
		
		if(pics.size() > 5)
		{
			url = FileUtil.SaveUplordStringAsMedia(uid, pics.get(5), picsContentType.get(5));
			ui.setPicUrl6(url);
		}		
		
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
		int count = uplordInfoDao.GetUplordInfosCount(aid);
		json.accumulate("count", count);
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
