package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.ISurveyService;

public class SurveyAction extends BaseAction{

	private static final long serialVersionUID = -5708646771512210218L;

	private ISurveyService surveyService;
	
	private long sid;
	
	private int credit;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private String a1;
	
	private String a2;
	
	private String a3;
	
	private long qid;
	
	private int isFinal;
	
	private int aIndex;
	
	private int start;
	
	private int end;
	
	@SuppressWarnings("unchecked")
	public String GetSurveyList()
	{
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			surveyService.GetSurveyList();
		}catch(Exception e){			
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void AddSurvey()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateCredit(credit))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.AddSurvey(uid, pics, picsContentType, credit);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteSurvey()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateSid(sid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.DeleteSurvey(sid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public String GetSurveyQuestion()
	{
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateSid(sid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			surveyService.GetSurveyQuestions(sid, uid);
		}catch(Exception e){			
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void AddSurveyQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateSid(sid) || !isValidateAnswer(a1)
					|| !isValidateAnswer(a2) || !isValidateAnswer(a3))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.AddSurveyQuestion(sid, pics, picsContentType, a1, a2, a3, uid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateSurveyQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidateSid(sid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.UpdateSurveyQuestion(sid, qid, pics, picsContentType, a1, a2, a3, uid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteSurveyQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidateSid(sid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.DeleteSurveyQuestion(uid, qid, sid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DoneSurveyQuestion()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidateSid(sid)
					|| !isValidateIsFinal(isFinal) || !isValidateIndex(aIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.DoneSurveyQuestion(uid, qid, sid, isFinal, aIndex);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void QueryUserSurvey()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateQid(qid) || !isValidatePage(start)
					|| !isValidatePage(end) || !isValidateSid(sid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = surveyService.QuerySurvey(uid, qid, start, end, sid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}

	private boolean isValidatePage(int page) {
		if(page >= 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateIndex(int aIndex) {
		if(aIndex > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateIsFinal(int isFinal) {
		if(isFinal == 0 || isFinal == 1)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateQid(long qid) {
		if(qid > 0)
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

	private boolean isValidateSid(long sid) {
		if(sid > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateCredit(int credit) {
		if(credit > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateType(List<String> picsContentType) {
		if(picsContentType != null && picsContentType.size() == 1)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateImage(List<File> pics) {
		if(pics != null && pics.size() == 1)
		{
			return true;
		}
		return false;
	}

	public ISurveyService getSurveyService() {
		return surveyService;
	}

	public void setSurveyService(ISurveyService surveyService) {
		this.surveyService = surveyService;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public List<File> getPics() {
		return pics;
	}

	public void setPics(List<File> pics) {
		this.pics = pics;
	}

	public List<String> getPicsContentType() {
		return picsContentType;
	}

	public void setPicsContentType(List<String> picsContentType) {
		this.picsContentType = picsContentType;
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

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public int getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(int isFinal) {
		this.isFinal = isFinal;
	}

	public int getaIndex() {
		return aIndex;
	}

	public void setaIndex(int aIndex) {
		this.aIndex = aIndex;
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
