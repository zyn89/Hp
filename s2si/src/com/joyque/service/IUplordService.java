package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IUplordService {

	JSONObject QueryUplord(long aid, int start, int end);

	JSONObject UplordPic(String uid, long aid, List<File> pics,
			List<String> picsContentType, String content) throws IOException;

}
