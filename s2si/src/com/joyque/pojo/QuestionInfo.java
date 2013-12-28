package com.joyque.pojo;

public class QuestionInfo {

	private long qid;
	
	private long aid;
	
	private String questionUrl;
	
	private String a1;
	
	private String a2;
	
	private String a3;
	
	private int aIndex = -1;
	
	private int score = -1;
	
	public void setDefaultValue()
	{
		aIndex = 0;
		score = 0;
	}

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getQuestionUrl() {
		return questionUrl;
	}

	public void setQuestionUrl(String questionUrl) {
		this.questionUrl = questionUrl;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public int getaIndex() {
		return aIndex;
	}

	public void setaIndex(int aIndex) {
		this.aIndex = aIndex;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
