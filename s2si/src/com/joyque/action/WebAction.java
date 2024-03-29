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
	
	private File pic1;
	
	private File pic2;
	
	private File pic3;
	
	private String pic1ContentType;
	
	private String pic2ContentType;
	
	private String pic3ContentType;
	
	private long cid;
	
	private String url;
	
	private String content;
	
	private int isStatic;
	
	private int isFinal;
	
	public void GetWebs()
	{
		JSONObject json = new JSONObject();
		try{
			json = webService.GetWebs();
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void GetWeb()
	{
		JSONObject json = new JSONObject();
		try{
			json = webService.GetWeb();
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void GetTwoLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(tid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetTwoLevel(tid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void GetThreeLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(hid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetThreeLevel(hid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void GetFourLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(fid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.GetFourLevel(fid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidatePic(pic1) || !isValidatePicType(pic1ContentType)
					|| !isValidatePic(pic2) || !isValidatePicType(pic2ContentType)
					|| !isValidatePic(pic3) || !isValidatePicType(pic3ContentType))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddCarouselInfo(uid, pic1, pic2, pic3, pic1ContentType, pic2ContentType, pic3ContentType);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(cid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateCarouselInfo(uid, cid, pic1, pic2, pic3, pic1ContentType, pic2ContentType, pic3ContentType);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteCarouselInfo()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(cid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteCarouselInfo(cid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateIsStatic(isStatic))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddOneLevel(uid, pics, picsContentType, url, isStatic);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(id)|| !isValidateIsStatic(isStatic))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateOneLevel(uid, pics, picsContentType, url, id, isStatic);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteOneLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteOneLevel(id);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(tid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddTwoLevel(uid, pics, picsContentType, content, tid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateTwoLevel(uid, pics, picsContentType, content, id);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteTwoLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteTwoLevel(id);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	/**
	 * isFinal 是否静态
	 * url链接
	 */
	@SuppressWarnings("unchecked")
	public void AddThreeLevel()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateId(hid) || !isValidateIsFinal(isFinal))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddThreeLevel(uid, pics, picsContentType, content, hid, url, isFinal);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(id)
					|| !isValidateIsFinal(isFinal))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateThreeLevel(uid, pics, picsContentType, content, id, url, isFinal);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteThreeLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteThreeLevel(id);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(fid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.AddFourLevel(uid, pics, picsContentType, content, fid, url);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
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
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.UpdateFourLevel(uid, pics, picsContentType, content, id, url);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteFourLevel()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateId(id))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = webService.DeleteFourLevel(id);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	private boolean isValidatePicType(String type) {
		if(type != null && !type.trim().equals(""))
		{
			return true;
		}
		return false;
	}

	private boolean isValidatePic(File pic) {
		if(pic != null)
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
	
	private boolean isValidateIsStatic(int isStatic) {
		if(isStatic == 0 || isStatic == 1)
		{
			return true;
		}
		return false;
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

	public int getIsStatic() {
		return isStatic;
	}

	public void setIsStatic(int isStatic) {
		this.isStatic = isStatic;
	}

	public int getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(int isFinal) {
		this.isFinal = isFinal;
	}

	public File getPic1() {
		return pic1;
	}

	public void setPic1(File pic1) {
		this.pic1 = pic1;
	}

	public File getPic2() {
		return pic2;
	}

	public void setPic2(File pic2) {
		this.pic2 = pic2;
	}

	public File getPic3() {
		return pic3;
	}

	public void setPic3(File pic3) {
		this.pic3 = pic3;
	}
	
	public String getPic1ContentType() {
		return pic1ContentType;
	}

	public void setPic1ContentType(String pic1ContentType) {
		this.pic1ContentType = pic1ContentType;
	}

	public String getPic2ContentType() {
		return pic2ContentType;
	}

	public void setPic2ContentType(String pic2ContentType) {
		this.pic2ContentType = pic2ContentType;
	}

	public String getPic3ContentType() {
		return pic3ContentType;
	}

	public void setPic3ContentType(String pic3ContentType) {
		this.pic3ContentType = pic3ContentType;
	}
}
