package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IWebService {

	JSONObject GetWebs();

	JSONObject GetWeb();

	JSONObject GetTwoLevel(long tid);

	JSONObject GetThreeLevel(long hid);

	JSONObject GetFourLevel(long fid);

	JSONObject DeleteCarouselInfo(long cid);

	JSONObject AddOneLevel(String uid, List<File> pics,
			List<String> picsContentType, String url, int isStatic) throws IOException;

	JSONObject UpdateOneLevel(String uid, List<File> pics,
			List<String> picsContentType, String url, long id, int isStatic) throws IOException;

	JSONObject DeleteOneLevel(long id);

	JSONObject AddTwoLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long tid) throws IOException;

	JSONObject UpdateTwoLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id) throws IOException;

	JSONObject DeleteTwoLevel(long id);

	JSONObject AddThreeLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long hid, String url, int isFinal) throws IOException;

	JSONObject UpdateThreeLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id, String url, int isFinal) throws IOException;

	JSONObject DeleteThreeLevel(long id);

	JSONObject AddFourLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long fid, String url) throws IOException;

	JSONObject UpdateFourLevel(String uid, List<File> pics,
			List<String> picsContentType, String content, long id, String url) throws IOException;

	JSONObject DeleteFourLevel(long id);

	JSONObject AddCarouselInfo(String uid, File pic1, File pic2, File pic3,
			String pic1Type, String pic2Type, String pic3Type)throws IOException;

	JSONObject UpdateCarouselInfo(String uid, long cid, File pic1, File pic2,
			File pic3, String pic1Type, String pic2Type, String pic3Type) throws IOException;

}
