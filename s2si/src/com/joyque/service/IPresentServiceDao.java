package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IPresentServiceDao {

	void GetExchanges();

	JSONObject AddExchange(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String exTitleIndex, String exDescIndex, String prizeTitleIndex,
			String prizeDescIndex) throws IOException;

}
