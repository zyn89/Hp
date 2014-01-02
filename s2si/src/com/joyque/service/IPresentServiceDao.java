package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IPresentServiceDao {

	JSONObject GetExchanges();

	JSONObject AddExchange(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String exTitleIndex, String exDescIndex, String prizeTitleIndex,
			String prizeDescIndex) throws IOException;

	JSONObject UpdateExchange(String uid, long eid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String exTitleIndex, String exDescIndex, String prizeTitleIndex,
			String prizeDescIndex) throws IOException;

	JSONObject DeleteExchange(long eid);

	JSONObject DoneExchange(String uid, long eid);

	JSONObject QueryExchangers(long eid, int start, int end);

	JSONObject AddLottery(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String prizeTitleIndex, String prizeDescIndex, String bg1Index,
			String bg2Index, String bg3Index, String lotteryIndex) throws IOException;

	JSONObject GetLotterys();

	JSONObject UpdateLottery(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String prizeTitleIndex, String prizeDescIndex, String bg1Index,
			String bg2Index, String bg3Index, String lotteryIndex, int lid) throws IOException;

	JSONObject DeleteLottery(int lid);

	JSONObject DrawLottery(String uid, int lid, int isAward);

	JSONObject QueryLottery(String uid);

	JSONObject QueryLotteryUser(String uid, int lid, int start, int end);

}
