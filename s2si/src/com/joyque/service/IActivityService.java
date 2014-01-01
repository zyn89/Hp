package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IActivityService {

	void GetActivityList(String uid);

	JSONObject AddActivity(List<File> pics, List<String> picsContentType, String activityIndex,
			String descIndex, String uid, String type, int score, int credit, List<String> picsName) throws IOException;

	JSONObject DeleteActivity(long aid, String uid);

	JSONObject UpdateActivity(long aid, String uid, List<File> pics,
			List<String> picsContentType, String activityIndex,
			String descIndex, int score, int credit, List<String> picsName) throws IOException;

}
