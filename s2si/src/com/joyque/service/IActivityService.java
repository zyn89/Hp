package com.joyque.service;

import net.sf.json.JSONObject;

public interface IActivityService {

	void GetActivityList(String uid);

	JSONObject AddActivity(String image, String formate);

}
