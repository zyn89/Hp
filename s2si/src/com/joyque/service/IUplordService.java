package com.joyque.service;

import net.sf.json.JSONObject;

public interface IUplordService {

	JSONObject UplordPic(String uid, long aid, String image1, String image2,
			String image3, String image4, String image5, String image6,
			String formate1, String formate2, String formate3, String formate4,
			String formate5, String formate6, String content);

}
