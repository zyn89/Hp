package com.joyque.action;

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
	
	private String activityImage;
	
	private String activityFormate;
	
	private String descImage;
	
	private String descFormate;
	
	private String type;
	
	private int score;
	
	private int credit;
	
	@SuppressWarnings("unchecked")
	public String ActivityList()
	{
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			activityService.GetActivityList(uid);
		}catch(Exception e){			
			
		}
		return SUCCESS;
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
			if(!isValidateImage(activityImage) || !isValidateFormate(activityFormate)
					|| !isValidateImage(descImage) || !isValidateFormate(descFormate)
					|| !isValidateType(type) || !isValidateScore(score)
					|| !isValidateCredit(credit))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = activityService.AddActivity(
					activityImage, activityFormate, descImage,
					descFormate ,uid, type, score, credit);
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
					aid, uid, activityImage, activityFormate,
					descImage, descFormate, score, credit);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
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

	public String getActivityImage() {
		return activityImage;
	}

	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}

	public String getActivityFormate() {
		return activityFormate;
	}

	public void setActivityFormate(String activityFormate) {
		this.activityFormate = activityFormate;
	}

	public String getDescImage() {
		return descImage;
	}

	public void setDescImage(String descImage) {
		this.descImage = descImage;
	}

	public String getDescFormate() {
		return descFormate;
	}

	public void setDescFormate(String descFormate) {
		this.descFormate = descFormate;
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
}
