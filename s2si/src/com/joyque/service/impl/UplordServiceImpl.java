package com.joyque.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import com.joyque.dao.IUplordInfoDao;
import com.joyque.pojo.UplordInfo;
import com.joyque.service.IUplordService;

public class UplordServiceImpl implements IUplordService{
	private IUplordInfoDao uplordInfoDao;

	@Override
	public JSONObject UplordPic(String uid, long aid, String image1,
			String image2, String image3, String image4, String image5,
			String image6, String formate1, String formate2, String formate3,
			String formate4, String formate5, String formate6, String content) {
		JSONObject json = new JSONObject();
		UplordInfo ui = new UplordInfo();
		ui.setUid(uid);
		ui.setAid(aid);
		ui.setContent(content);
		Date date = new Date();
		ui.setDate(date.getTime());
		

		return json;
	}

	public IUplordInfoDao getUplordInfoDao() {
		return uplordInfoDao;
	}

	public void setUplordInfoDao(IUplordInfoDao uplordInfoDao) {
		this.uplordInfoDao = uplordInfoDao;
	}
}
