package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IUplordService;

public class UplordAction extends BaseAction{

	private static final long serialVersionUID = -2133201524525949700L;
	
	private IUplordService uplordService;

	private long aid;
	
	private String content;
	
	private int start;
	
	private int end;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	@SuppressWarnings("unchecked")
	public void UplordPic()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateAid(aid) || !isValidateImage(pics)
					|| !isValdateFormate(picsContentType))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = uplordService.UplordPic(uid, aid, pics, picsContentType, content);
		}catch(Exception e){			
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}

	public void QueryUplord()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateAid(aid) || !isValidatePage(start)
					|| !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = uplordService.QueryUplord(aid, start, end);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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

	private boolean isValdateFormate(List<String> picsContentType) {
		if(picsContentType != null && picsContentType.size() > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateImage(List<File> pics) {
		if(pics != null && pics.size() > 0)
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

	public IUplordService getUplordService() {
		return uplordService;
	}

	public void setUplordService(IUplordService uplordService) {
		this.uplordService = uplordService;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}
