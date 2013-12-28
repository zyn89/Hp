package com.joyque.service;

import java.io.IOException;

import net.sf.json.JSONObject;

public interface IActivityService {

	void GetActivityList(String uid);

	JSONObject AddActivity(String image, String formate, String image1,
			String formate1, String uid, String type, int score, int credit) throws IOException;

	JSONObject DeleteActivity(long aid, String uid);

	JSONObject UpdateActivity(long aid, String uid, String image,
			String formate, String image1, String descFormate, int score, int credit) throws IOException;

}
