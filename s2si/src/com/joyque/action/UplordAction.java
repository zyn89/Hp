package com.joyque.action;

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
	
	private String image1;
	
	private String formate1;
	
	private String image2;
	
	private String formate2;
	
	private String image3;
	
	private String formate3;
	
	private String image4;
	
	private String formate4;
	
	private String image5;
	
	private String formate5;
	
	private String image6;
	
	private String formate6;
	
	private String content;
	
	@SuppressWarnings("unchecked")
	public void UplordPic()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateAid(aid) || !isValidateImage(image1)
					|| !isValidateImage(image2) || !isValidateImage(image3)
					|| !isValidateImage(image4) || !isValidateImage(image5)
					|| !isValidateImage(image6) || !isValdateFormate(formate1)
					|| !isValdateFormate(formate2) || !isValdateFormate(formate3)
					|| !isValdateFormate(formate4) || !isValdateFormate(formate5)
					|| !isValdateFormate(formate6))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = uplordService.UplordPic(uid, aid, image1, image2, image3,
					image4, image5, image6, formate1, formate2, formate3,
					formate4, formate5, formate6, content);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}

	private boolean isValdateFormate(String formate) {
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

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getFormate1() {
		return formate1;
	}

	public void setFormate1(String formate1) {
		this.formate1 = formate1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getFormate2() {
		return formate2;
	}

	public void setFormate2(String formate2) {
		this.formate2 = formate2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getFormate3() {
		return formate3;
	}

	public void setFormate3(String formate3) {
		this.formate3 = formate3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getFormate4() {
		return formate4;
	}

	public void setFormate4(String formate4) {
		this.formate4 = formate4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	public String getFormate5() {
		return formate5;
	}

	public void setFormate5(String formate5) {
		this.formate5 = formate5;
	}

	public String getImage6() {
		return image6;
	}

	public void setImage6(String image6) {
		this.image6 = image6;
	}

	public String getFormate6() {
		return formate6;
	}

	public void setFormate6(String formate6) {
		this.formate6 = formate6;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
