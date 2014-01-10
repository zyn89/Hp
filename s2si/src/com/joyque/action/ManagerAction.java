package com.joyque.action;


import java.util.Map;
import com.joyque.common.action.BaseAction;

public class ManagerAction extends BaseAction{
	
	private static final long serialVersionUID = -1651378209203174029L;
	
	
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
	
	
	public String wdy() {
		return "wdy";
	}
	public String questions() {
		return "questions";
	}
	public String exchange() {
		return "exchange";
	}

}
