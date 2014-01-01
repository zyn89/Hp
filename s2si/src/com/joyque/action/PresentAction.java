package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IPresentServiceDao;

public class PresentAction extends BaseAction{

	private static final long serialVersionUID = 8151774968613927657L;
	
	private IPresentServiceDao presentService;

	private long eid;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private List<String> picsName;
	
	private String exTitleIndex;
	
	private String exDescIndex;
	
	private String prizeTitleIndex;
	
	private String prizeDescIndex;
	
	private int credit;
	
	@SuppressWarnings("unchecked")
	public String GetExchanges()
	{
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			presentService.GetExchanges();
		}catch(Exception e){			
			
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public void AddExchange()
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
					|| !isValidateName(picsName) || !isValidateCredit(credit)
					|| !isValidateIndex(exTitleIndex) || !isValidateIndex(exDescIndex)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.AddExchange(uid, pics, picsContentType, picsName,
					credit, exTitleIndex, exDescIndex, prizeTitleIndex, prizeDescIndex);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}

	private boolean isValidateIndex(String Index) {
		if(Index != null && !Index.trim().equals(""))
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

	private boolean isValidateName(List<String> picsName) {
		if(picsName != null && picsName.size() == 4)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateType(List<String> picsContentType) {
		if(picsContentType != null && picsContentType.size() == 4)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateImage(List<File> pics) {
		if(pics != null && pics.size() == 4)
		{
			return true;
		}
		return false;
	}

	public IPresentServiceDao getPresentService() {
		return presentService;
	}

	public void setPresentService(IPresentServiceDao presentService) {
		this.presentService = presentService;
	}

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getExTitleIndex() {
		return exTitleIndex;
	}

	public void setExTitleIndex(String exTitleIndex) {
		this.exTitleIndex = exTitleIndex;
	}

	public String getExDescIndex() {
		return exDescIndex;
	}

	public void setExDescIndex(String exDescIndex) {
		this.exDescIndex = exDescIndex;
	}

	public String getPrizeTitleIndex() {
		return prizeTitleIndex;
	}

	public void setPrizeTitleIndex(String prizeTitleIndex) {
		this.prizeTitleIndex = prizeTitleIndex;
	}

	public String getPrizeDescIndex() {
		return prizeDescIndex;
	}

	public void setPrizeDescIndex(String prizeDescIndex) {
		this.prizeDescIndex = prizeDescIndex;
	}

	public List<String> getPicsName() {
		return picsName;
	}

	public void setPicsName(List<String> picsName) {
		this.picsName = picsName;
	}
}
