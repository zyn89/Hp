package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IWebService;

public class WebAction  extends BaseAction{

	private static final long serialVersionUID = 1245325427298680570L;
	
	private IWebService webService;
	
	private long id;
	
	private long tid;
	
	private long hid;
	
	private long fid;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private long cid;
	
	private String url;
	
	private String content;
	
	@SuppressWarnings("unchecked")
	public void GetWebs()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			json = webService.GetWebs();
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void GetWeb()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			json = webService.GetWeb();
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void GetTwoLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(tid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetTwoLevel(tid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void GetThreeLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(hid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetThreeLevel(hid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void GetFourLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(fid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetFourLevel(fid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddCarouselInfo()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddCarouselInfo(uid, pics, picsContentType);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateCarouselInfo()
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
					|| !isValidateId(cid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateCarouselInfo(uid, cid, pics, picsContentType);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteCarouselInfo()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(cid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteCarouselInfo(cid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddOneLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddOneLevel(uid, pics, picsContentType, url);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateOneLevel()
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
					|| !isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateOneLevel(uid, pics, picsContentType, url, id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteOneLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteOneLevel(id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddTwoLevel()
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
					|| !isValidateId(tid) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddTwoLevel(uid, pics, picsContentType, content, tid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateTwoLevel()
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
					|| !isValidateId(id) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateTwoLevel(uid, pics, picsContentType, content, id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteTwoLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteTwoLevel(id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddThreeLevel()
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
					|| !isValidateId(hid) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddThreeLevel(uid, pics, picsContentType, content, hid, url);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateThreeLevel()
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
					|| !isValidateId(id) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateThreeLevel(uid, pics, picsContentType, content, id, url);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteThreeLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteThreeLevel(id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddFourLevel()
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
					|| !isValidateId(fid) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddFourLevel(uid, pics, picsContentType, content, fid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateFourLevel()
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
					|| !isValidateId(id) || !isValidateContent(content))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateFourLevel(uid, pics, picsContentType, content, id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteFourLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteFourLevel(id);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}

	private boolean isValidateContent(String content) {
		if(content != null && !content.trim().equals(""))
		{
			return true;
		}
		return false;
	}

	private boolean isValidateType(List<String> picsContentType) {
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

	private boolean isValidateId(long id) {
		if(id >= 0)
		{
			return true;
		}
		return false;
	}

	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
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

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
}
