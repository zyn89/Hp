package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IQuestionService {

	void GetQuestions(long aid);

	JSONObject DoneQuestion(String uid, int score, long aid);

	JSONObject QueryUserScore(long aid, int start, int end);

	JSONObject GetQaActivity();

	JSONObject AddQuestion(String uid, long aid, List<File> pics, List<String> picsContentType, String a1,
			String a2, String a3, int aIndex, int score) throws IOException;

	JSONObject UpdateQuestion(String uid, long qid, List<File> pics, List<String> picsContentType,
			String a1, String a2, String a3, int aIndex, int score, long aid) throws IOException;

	JSONObject DeleteQuestion(long qid, long aid);

}
