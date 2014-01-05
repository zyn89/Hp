package com.joyque.action;

import com.joyque.common.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class InteractAction extends BaseAction {

	
	private static final long serialVersionUID = 7080719316643411393L;
	
	private String aid;
	private String descUrl;
	private String type;
	private String eid;
	
	
	public String getEid() {
		return eid;
	}



	public void setEid(String eid) {
		this.eid = eid;
	}



	public String getAid() {
		return aid;
	}



	public void setAid(String aid) {
		this.aid = aid;
	}



	public String getDescUrl() {
		return descUrl;
	}



	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String beginActivity() {
		
		ActionContext.getContext().put("aid", aid);
		ActionContext.getContext().put("descUrl", descUrl);
		ActionContext.getContext().put("type", type);
		
		return "beginactivity";
	}
	
	public String beginAnswer() {
		return "beginanswer";
	}
	
	
	public String prizeInfo() {
		ActionContext.getContext().put("eid", eid);
		return "prizeinfo";
	}
}
