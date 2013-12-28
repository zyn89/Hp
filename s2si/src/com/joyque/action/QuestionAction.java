package com.joyque.action;

import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IQuestionService;

public class QuestionAction extends BaseAction{

	private static final long serialVersionUID = 8613682701644797369L;
	
	private IQuestionService questionService; 

	private long aid;
	
	private long qid;

	private String image;
	
	private String formate;
	
	private String a1;
	
	private String a2;
	
	private String a3;
	
	private int aIndex;
	
	private int score;
	
	private int start;
	
	private int end;
	
	@SuppressWarnings("unchecked")
	public String GetQuestionList()
	{
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			
			if(!isValidateAid(aid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			questionService.GetQuestions(aid);
		}catch(Exception e){			
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void DoneQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateScore(score) || !isValdateAid(aid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = questionService.DoneQuestion(uid, score, aid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void GetQaActivity()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			json = questionService.GetQaActivity();
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void QueryUserScore()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateAid(aid) || !isValidatePage(start) || !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = questionService.QueryUserScore(aid, start, end);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(image) || !isValidateFormate(formate)
					|| !isValidateAnswer(a1) || !isValidateAnswer(a2)
					|| !isValidateAnswer(a3) || !isValidateAindex(aIndex)
					|| !isValidateScore(score) || !isValidateAid(aid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = questionService.AddQuestion(uid, aid, image, formate, a1, a2, a3, aIndex, score);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidateAid(aid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = questionService.UpdateQuestion(uid, qid, image, formate,
					a1, a2, a3, aIndex, score, aid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void deleteQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidateAid(aid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = questionService.DeleteQuestion(qid, aid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	private boolean isValidateQid(long qid) {
		if(qid > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateAindex(int aIndex) {
		if(aIndex > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateAnswer(String answer) {
		if(answer != null && !answer.trim().equals(""))
		{
			return true;
		}
		return false;
	}

	private boolean isValidateFormate(String formate) {
	    if(formate != null && !formate.trim().equals(""))
	    {
	    	return true;
	    }
		return false;
	}

	private boolean isValidateImage(String image) {
		if(image != null && !image.trim().equals(""))
		{
			return true;
		}
		return false;
	}

	private boolean isValidatePage(int page) {
		if(page >= 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValdateAid(long aid) {
		if(aid >= 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateScore(int score) {
		if(score >= 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateAid(long aid) {
		if(aid > 0)
		{
			return true;
		}
		return false;
	}

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFormate() {
		return formate;
	}

	public void setFormate(String formate) {
		this.formate = formate;
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
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
