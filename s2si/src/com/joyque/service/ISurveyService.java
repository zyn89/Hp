package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface ISurveyService {

	void GetSurveyList();

	JSONObject AddSurvey(String uid, List<File> pics, List<String> picsContentType,
			int credit) throws IOException;

	JSONObject DeleteSurvey(long sid);

	void GetSurveyQuestions(long sid, String uid);

	JSONObject AddSurveyQuestion(long sid, List<File> pics,
			List<String> picsContentType, String a1, String a2, String a3, String uid) throws IOException;

	JSONObject UpdateSurveyQuestion(long sid, long qid, List<File> pics,
			List<String> picsContentType, String a1, String a2, String a3,
			String uid) throws IOException;

	JSONObject DeleteSurveyQuestion(String uid, long qid, long sid);

	JSONObject DoneSurveyQuestion(String uid, long qid, long sid, int isFinal, int aIndex);

	JSONObject QuerySurvey(String uid, long qid, int start, int end, long sid);

}
