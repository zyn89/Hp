package com.joyque.service;

import net.sf.json.JSONObject;

public interface IUserBasicService {

	public JSONObject register(String phone, String pw, String name, String shopName);
}
