package com.joyque.service;

import net.sf.json.JSONObject;

public interface IWebService {

	JSONObject GetWebs();

	JSONObject GetWeb();

	JSONObject GetTwoLevel(long tid);

	JSONObject GetThreeLevel(long hid);

	JSONObject GetFourLevel(long fid);

}
