package com.joyque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

public interface IPresentService {

	JSONObject GetExchanges();

	JSONObject AddExchange(String uid, List<File> pics,
			List<String> picsContentType, int credit,
			int exTitleIndex, int exDescIndex, int prizeTitleIndex,
			int prizeDescIndex) throws IOException;

	JSONObject DeleteExchange(long eid);

	JSONObject DoneExchange(String uid, long eid);

	JSONObject QueryExchangers(long eid, int start, int end);

	JSONObject AddLottery(String uid, List<File> pics,
			List<String> picsContentType,int credit,
			int prizeTitleIndex, int prizeDescIndex, int bg1Index,
			int bg2Index, int bg3Index, int lotteryIndex) throws IOException;

	JSONObject GetLotterys(String uid);

	JSONObject UpdateLottery(String uid, List<File> pics,
			List<String> picsContentType, int credit,
			int prizeTitleIndex, int prizeDescIndex, int bg1Index,
			int bg2Index, int bg3Index, int lotteryIndex, int lid) throws IOException;

	JSONObject DeleteLottery(int lid, String uid);

	JSONObject DrawLottery(String uid, int lid, int isAward);

	JSONObject QueryLottery(String uid);

	JSONObject QueryLotteryUser(String uid, int lid, int start, int end);

	JSONObject UpdateExchange(String uid, long eid, List<File> pics,
			List<String> picsContentType, int credit, int exTitleIndex,
			int exDescIndex, int prizeTitleIndex, int prizeDescIndex) throws IOException;

}
