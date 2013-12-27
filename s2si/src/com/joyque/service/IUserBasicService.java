package com.joyque.service;

import net.sf.json.JSONObject;


public interface IUserBasicService {

	public String register(String phone, String pw, String name, String shopName);

	public String Login(String phone, String pw);

	public JSONObject MotifyInfo(String uid, String phone, String pw,
			String name, String shopName);

	public JSONObject CheckIn(String uid);
}
