package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.DefaultValue;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IActivityService;

public class ActivityAction extends BaseAction{

	private static final long serialVersionUID = 4821479538597309024L;

	private IActivityService activityService;
	
	private long aid;
	
	private String type;
	
	private int score;
	
	private int credit;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private String activityIndex;
	
	private String descIndex;
	
	private List<String> picsName;
	
	@SuppressWarnings("unchecked")
	public void ActivityList()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			json = activityService.GetActivityList(uid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddActivity()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateType(type) || !isValidateScore(score)
					|| !isValidateCredit(credit) || !isValidatePics(pics)
					|| !isValidatePicsType(picsContentType) || !isValidatePicIndex(activityIndex)
					|| !isValidatePicIndex(descIndex) || !isValidatePicName(picsName))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = activityService.AddActivity(
					pics, picsContentType, activityIndex,
					descIndex ,uid, type, score, credit, picsName);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}	

	@SuppressWarnings("unchecked")
	public void DeleteActivity()
	{
		JSONObject json = new JSONObject();
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
			json = activityService.DeleteActivity(aid, uid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateActivity()
	{
		JSONObject json = new JSONObject();
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
			json = activityService.UpdateActivity(
					aid, uid, pics, picsContentType,
					activityIndex, descIndex, score, credit, picsName);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	private boolean isValidatePicName(List<String> picsName) {
		if(picsName != null && picsName.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	private boolean isValidatePicIndex(String activityIndex) {
		if(activityIndex != null && !activityIndex.trim().equals(""))
		{
			return true;
		}
		return false;
	}
	
	private boolean isValidatePicsType(List<String> picsContentType) {
		if(picsContentType != null && picsContentType.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	private boolean isValidatePics(List<File> pics) {
		if(pics != null && pics.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	private boolean isValidateCredit(int credit) {
		if(credit >= 0)
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

	private boolean isValidateType(String type) {
		if(type != null && (type.equals(DefaultValue.ActivityQA)
				|| type.equals(DefaultValue.ActivityPicWithoutWord)
				|| type.equals(DefaultValue.ActivityPicWithWord)))
		{
			return true;
		}
		return false;
	}

	public IActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public String getActivityIndex() {
		return activityIndex;
	}

	public void setActivityIndex(String activityIndex) {
		this.activityIndex = activityIndex;
	}

	public String getDescIndex() {
		return descIndex;
	}

	public void setDescIndex(String descIndex) {
		this.descIndex = descIndex;
	}

	public List<String> getPicsName() {
		return picsName;
	}

	public void setPicsName(List<String> picsName) {
		this.picsName = picsName;
	}
}
