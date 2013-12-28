package com.joyque.service;

import java.io.IOException;

import net.sf.json.JSONObject;

public interface IQuestionService {

	void GetQuestions(long aid);

	JSONObject DoneQuestion(String uid, int score, long aid);

	JSONObject QueryUserScore(long aid, int start, int end);

	JSONObject GetQaActivity();

	JSONObject AddQuestion(String uid, long aid, String image, String formate, String a1,
			String a2, String a3, int aIndex, int score) throws IOException;

	JSONObject UpdateQuestion(String uid, long qid, String image, String formate,
			String a1, String a2, String a3, int aIndex, int score, long aid) throws IOException;

	JSONObject DeleteQuestion(long qid, long aid);

}
