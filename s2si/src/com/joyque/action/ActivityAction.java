package com.joyque.action;

import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IActivityService;

public class ActivityAction extends BaseAction{

	private static final long serialVersionUID = 4821479538597309024L;

	private IActivityService activityService;
	
	private long aid;
	
	private String image;
	
	private String formate;
	
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
	
	public void AddActivity()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateImage(image) || !isValidateFormate(formate))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = activityService.AddActivity(image, formate);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
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
}
