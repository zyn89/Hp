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
			if(!isValidateId(fid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddCarouselInfo(fid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
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
}
