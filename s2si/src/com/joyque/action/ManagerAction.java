package com.joyque.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.joyque.common.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class ManagerAction extends BaseAction{
	private String data;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String execute() {
		return "success";
	}
	public void send(){
		System.out.println(data);
		Map session=getSession();
		session.put("aid", data);
		System.out.println(session.get("aid"));
	}

}
