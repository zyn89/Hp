package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.exception.BaseServiceException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.common.util.FileUtil;
import com.joyque.dao.IExchangeInfoDao;
import com.joyque.dao.ILotteryInfoDao;
import com.joyque.dao.IPrizeInfoDao;
import com.joyque.dao.IUserCreditDao;
import com.joyque.dao.IUserInfoDao;
import com.joyque.dao.IUserPrizeDao;
import com.joyque.pojo.ExchangeInfo;
import com.joyque.pojo.LotteryInfo;
import com.joyque.pojo.PrizeInfo;
import com.joyque.pojo.UserCredit;
import com.joyque.pojo.UserInfo;
import com.joyque.pojo.UserPrize;
import com.joyque.service.IPresentServiceDao;

public class PresentServiceImpl implements IPresentServiceDao{
	private IExchangeInfoDao exchangeInfoDao;
	private IPrizeInfoDao prizeInfoDao;
	private IUserPrizeDao userPrizeDao;
	private IUserCreditDao userCreditDao;
	private IUserInfoDao userInfoDao;
	private ILotteryInfoDao lotteryInfoDao;

	@Override
	public JSONObject GetExchanges() {
		JSONObject json = new JSONObject();
		json.accumulate("exchanges", GetExchangeArray());
		return json;
	}
	
	private JSONArray GetExchangeArray()
	{
		List<ExchangeInfo> infos = exchangeInfoDao.GetExchangeInfos();
		JSONArray jArray = new JSONArray();
		for(ExchangeInfo info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("eid", info.getEid());
			j.accumulate("exchangeUrl", info.getExchangeUrl());
			j.accumulate("descUrl", info.getDescUrl());
			j.accumulate("credit", info.getCredit());
			PrizeInfo prize = prizeInfoDao.GetPrizeInfo(info.getPid());
			j.accumulate("prizeUrl", prize.getDescUrl());
			jArray.add(j);
		}
		return jArray;
	}
	
	@Override
	public JSONObject AddExchange(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String exTitleIndex, String exDescIndex, String prizeTitleIndex,
			String prizeDescIndex) throws IOException {
		int exTitle = 0;
		int exDesc = 0;
		int prizeTitle = 0;
		int prizeDesc = 0;
		for(int i = 0; i < picsName.size(); i ++)
		{
			if(exTitleIndex.equals(picsName.get(i)))
			{
				exTitle = i;
			}
			else if(exDescIndex.equals(picsName.get(i)))
			{
				exDesc = i;
			}
			else if(prizeTitleIndex.equals(picsName.get(i)))
			{
				prizeTitle = i;
			}
			else
			{
				prizeDesc = i;
			}
		}
		JSONObject json = new JSONObject();
		PrizeInfo prize = new PrizeInfo();
		String url = FileUtil.SavePrizeAsMedia(uid, pics.get(prizeTitle), picsContentType.get(prizeTitle));
		prize.setPrizeUrl(url);
		url = FileUtil.SavePrizeDescAsMedia(uid, pics.get(prizeDesc), picsContentType.get(prizeDesc));
		prize.setDescUrl(url);
		long pid = prizeInfoDao.insertPrizeInfo(prize);
		ExchangeInfo exchange = new ExchangeInfo();
		exchange.setPid(pid);
		exchange.setCredit(credit);
		url = FileUtil.SaveExchangeAsMedia(uid, pics.get(exTitle), picsContentType.get(exTitle));
		exchange.setExchangeUrl(url);
		url = FileUtil.SaveExchangeDescAsMedia(uid, pics.get(exDesc), picsContentType.get(exDesc));
		exchange.setDescUrl(url);
		exchangeInfoDao.insertExchangeInfo(exchange);
		
		json.accumulate("exchanges", GetExchangeArray());
		return json;
	}
	
	@Override
	public JSONObject UpdateExchange(String uid, long eid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String exTitleIndex, String exDescIndex, String prizeTitleIndex,
			String prizeDescIndex) throws IOException {
		JSONObject json = new JSONObject();
		ExchangeInfo exchange = exchangeInfoDao.GetExchangeInfo(eid);
		PrizeInfo prize = prizeInfoDao.GetPrizeInfo(exchange.getPid());
		int exTitle = 0;
		int exDesc = 0;
		int prizeTitle = 0;
		int prizeDesc = 0;
		for(int i = 0; i < picsName.size(); i ++)
		{
			if(exTitleIndex != null && exTitleIndex.equals(picsName.get(i)))
			{
				exTitle = i;
			}
			else if(exDescIndex != null && exDescIndex.equals(picsName.get(i)))
			{
				exDesc = i;
			}
			else if(prizeTitleIndex != null && prizeTitleIndex.equals(picsName.get(i)))
			{
				prizeTitle = i;
			}
			else if(prizeDescIndex != null && prizeDescIndex.equals(picsName.get(i)))
			{
				prizeDesc = i;
			}
		}
		exchange.setCredit(credit);
		String url = null;
		if(exTitleIndex != null)
		{
			url = FileUtil.SavePrizeAsMedia(uid, pics.get(prizeTitle), picsContentType.get(prizeTitle));
			exchange.setExchangeUrl(url);
		}
		if(exDescIndex != null)
		{
			url = FileUtil.SavePrizeDescAsMedia(uid, pics.get(prizeDesc), picsContentType.get(prizeDesc));
			exchange.setDescUrl(url);
		}
		if(prizeTitleIndex != null)
		{
			url = FileUtil.SaveExchangeAsMedia(uid, pics.get(exTitle), picsContentType.get(exTitle));
			prize.setPrizeUrl(url);
		}
		if(prizeDescIndex != null)
		{
			url = FileUtil.SaveExchangeDescAsMedia(uid, pics.get(exDesc), picsContentType.get(exDesc));
			prize.setDescUrl(url);
		}
		exchangeInfoDao.updateExchangeInfo(exchange);
		prizeInfoDao.updatePrizeInfo(prize);
		
		json.accumulate("exchanges", GetExchangeArray());
		return json;
	}
	
	@Override
	public JSONObject DeleteExchange(long eid) {
		JSONObject json = new JSONObject();
		ExchangeInfo exchange = exchangeInfoDao.GetExchangeInfo(eid);
		exchangeInfoDao.deleteExchangeInfo(eid);
		prizeInfoDao.deletePrizeInfo(exchange.getPid());
		
		json.accumulate("exchanges", GetExchangeArray());
		return json;
	}
	
	@Override
	public JSONObject DoneExchange(String uid, long eid) {
		JSONObject json = new JSONObject();
		ExchangeInfo exchange = exchangeInfoDao.GetExchangeInfo(eid);
		UserCredit credit = userCreditDao.getUserCredit(uid);
		if(credit.getCredit() < exchange.getCredit())
		{
			throw new BaseServiceException(ExceptionUtil.CreditLack,uid);
		}
		UserPrize ue = new UserPrize();
		ue.setUid(uid);
		ue.setPid(exchange.getPid());
		userPrizeDao.insertUserPrize(ue);
		
		credit.setCredit(credit.getCredit() - exchange.getCredit());
		userCreditDao.updateUserCredit(credit);
		
		json.accumulate("credit", credit.getCredit());
		return json;
	}
	
	@Override
	public JSONObject QueryExchangers(long eid, int start, int end) {
		JSONObject json = new JSONObject();
		ExchangeInfo exchange = exchangeInfoDao.GetExchangeInfo(eid);
		long pid = exchange.getPid();
		List<UserPrize> ups = userPrizeDao.GetUserPirzesByPid(pid, start, end);
		JSONArray jArray = new JSONArray();
		for(UserPrize info : ups)
		{
			String uid = info.getUid();
			JSONObject j = new JSONObject();
			UserInfo user = userInfoDao.getUserInfoByUid(uid);
			j.accumulate("name", user.getName());
			jArray.add(j);
		}
		json.accumulate("users", jArray);
		return json;
	}
	
	@Override
	public JSONObject AddLottery(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String prizeTitleName, String prizeDescName, String bg1Name,
			String bg2Name, String bg3Name, String lotteryName) throws IOException {
		JSONObject json = new JSONObject();
		int lotteryIndex = 0;
		int prizeTitleIndex = 0;
		int prizeDescIndex = 0;
		int bg1Index = 0;
		int bg2Index = 0;
		int bg3Index = 0;
		for(int i = 0; i < picsName.size(); i ++)
		{
			if(prizeTitleName.equals(picsName.get(i)))
			{
				prizeTitleIndex = i;
			}
			else if(prizeDescName.equals(picsName.get(i)))
			{
				prizeDescIndex = i;
			}
			else if(lotteryName.equals(picsName.get(i)))
			{
				lotteryIndex = i;
			}
			else if(bg1Name.equals(picsName.get(i)))
			{
				bg1Index = i;
			}
			else if(bg2Name.equals(picsName.get(i)))
			{
				bg2Index = i;
			}
			else
			{
				bg3Index = i;
			}
		}
		PrizeInfo prize = new PrizeInfo();
		String url = FileUtil.SavePrizeAsMedia(uid, pics.get(prizeTitleIndex), picsContentType.get(prizeTitleIndex));
		prize.setPrizeUrl(url);
		url = FileUtil.SavePrizeDescAsMedia(uid, pics.get(prizeDescIndex), picsContentType.get(prizeDescIndex));
		prize.setDescUrl(url);
		long pid = prizeInfoDao.insertPrizeInfo(prize);
		
		LotteryInfo lottery = new LotteryInfo();
		lottery.setCredit(credit);
		lottery.setPid(pid);
		url = FileUtil.SaveLotteryAsMedia(uid, pics.get(lotteryIndex), picsContentType.get(lotteryIndex));
		lottery.setImageUrl(url);
		url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg1Index), picsContentType.get(bg1Index));
		lottery.setBg1Url(url);
		url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg2Index), picsContentType.get(bg2Index));
		lottery.setBg2Url(url);
		url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg3Index), picsContentType.get(bg3Index));
		lottery.setBg3Url(url);
		lotteryInfoDao.insertLotteryInfo(lottery);
		
		json = GetLotterys(uid);
		return json;
	}
	
	@Override
	public JSONObject GetLotterys(String uid) {
		List<LotteryInfo> infos = lotteryInfoDao.GetLotteryInfos();
		JSONArray jArray = new JSONArray();
		JSONObject json = new JSONObject();
		for(LotteryInfo info : infos)
		{
			JSONObject j = new JSONObject();
			j.accumulate("lid", info.getLid());
			j.accumulate("credit", info.getCredit());
			j.accumulate("lotteryUrl", info.getImageUrl());
			JSONArray array = new JSONArray();
			List<String> choiceItems = new ArrayList<String>();
			choiceItems.add(info.getBg1Url());
			choiceItems.add(info.getBg2Url());
			choiceItems.add(info.getBg3Url());
			Random random = new Random();
			int r = random.nextInt(3);
			array.add(choiceItems.get(r));
			random = new Random();
			r = random.nextInt(3);
			array.add(choiceItems.get(r));
			random = new Random();
			r = random.nextInt(3);
			array.add(choiceItems.get(r));
			j.accumulate("choiceItems", array);
			PrizeInfo prize = prizeInfoDao.GetPrizeInfo(info.getPid());
			j.accumulate("prizeUrl", prize.getPrizeUrl());
			j.accumulate("descUrl", prize.getDescUrl());
			jArray.add(j);
		}
		
		json.accumulate("lotterys", jArray);
		UserCredit credit = userCreditDao.getUserCredit(uid);
		json.accumulate("credit", credit.getCredit());
		json.accumulate("lotteryCount", credit.getLotteryCount());
		return json;
	}
	
	@Override
	public JSONObject UpdateLottery(String uid, List<File> pics,
			List<String> picsContentType, List<String> picsName, int credit,
			String prizeTitleName, String prizeDescName, String bg1Name,
			String bg2Name, String bg3Name, String lotteryName, int lid)
			throws IOException {
		LotteryInfo lottery = lotteryInfoDao.GetLotteryInfo(lid);
		PrizeInfo prize = prizeInfoDao.GetPrizeInfo(lottery.getPid());
		int lotteryIndex = 0;
		int prizeTitleIndex = 0;
		int prizeDescIndex = 0;
		int bg1Index = 0;
		int bg2Index = 0;
		int bg3Index = 0;
		for(int i = 0; i < picsName.size(); i ++)
		{
			if(prizeTitleName != null && prizeTitleName.equals(picsName.get(i)))
			{
				prizeTitleIndex = i;
			}
			else if(prizeDescName != null && prizeDescName.equals(picsName.get(i)))
			{
				prizeDescIndex = i;
			}
			else if(lotteryName != null && lotteryName.equals(picsName.get(i)))
			{
				lotteryIndex = i;
			}
			else if(bg1Name !=null && bg1Name.equals(picsName.get(i)))
			{
				bg1Index = i;
			}
			else if(bg2Name != null && bg2Name.equals(picsName.get(i)))
			{
				bg2Index = i;
			}
			else if(bg3Name != null && bg3Name.equals(picsName.get(i)))
			{
				bg3Index = i;
			}
		}
		
		lottery.setCredit(credit);
		String url = null;
		if(prizeTitleName != null)
		{
			url = FileUtil.SavePrizeAsMedia(uid, pics.get(prizeTitleIndex), picsContentType.get(prizeTitleIndex));
			prize.setPrizeUrl(url);
		}
		if(prizeDescName != null)
		{
			url = FileUtil.SavePrizeDescAsMedia(uid, pics.get(prizeDescIndex), picsContentType.get(prizeDescIndex));
			prize.setDescUrl(url);
		}
		if(lotteryName != null)
		{
			url = FileUtil.SaveLotteryAsMedia(uid, pics.get(lotteryIndex), picsContentType.get(lotteryIndex));
			lottery.setImageUrl(url);
		}
		if(bg1Name != null)
		{
			url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg1Index), picsContentType.get(bg1Index));
			lottery.setBg1Url(url);
		}
		if(bg2Name != null)
		{
			url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg2Index), picsContentType.get(bg2Index));
			lottery.setBg2Url(url);
		}
		if(bg3Name != null)
		{
			url = FileUtil.SaveLotteryBgAsMedia(uid, pics.get(bg3Index), picsContentType.get(bg3Index));
			lottery.setBg3Url(url);
		}
		
		lotteryInfoDao.updateLotteryInfo(lottery);
		prizeInfoDao.updatePrizeInfo(prize);
		
		JSONObject json = GetLotterys(uid);
		return json;
	}
	
	@Override
	public JSONObject DeleteLottery(int lid, String uid) {
		JSONObject json = new JSONObject();
		lotteryInfoDao.deleteLotteryInfo(lid);
		json = GetLotterys(uid);
		return json;
	}
	
	@Override
	public JSONObject DrawLottery(String uid, int lid, int isAward) {
		JSONObject json = new JSONObject();
		LotteryInfo lottery = lotteryInfoDao.GetLotteryInfo(lid);
		UserCredit credit = userCreditDao.getUserCredit(uid);
		if(credit.getLotteryCount() < 1)
		{
			throw new BaseServiceException(ExceptionUtil.DrawLotteryCountLack,uid);
		}
		if(credit.getCredit() < lottery.getCredit())
		{
			throw new BaseServiceException(ExceptionUtil.CreditLack,uid);
		}
		credit.setCredit(credit.getCredit() - lottery.getCredit());
		credit.setLotteryCount(credit.getLotteryCount() - 1);
		userCreditDao.updateUserCredit(credit);
		if(isAward == 1)
		{
			long pid = lottery.getPid();
			UserPrize up = new UserPrize();
			up.setUid(uid);
			up.setPid(pid);
			userPrizeDao.insertUserPrize(up);
		}				
		return json;
	}
	
	@Override
	public JSONObject QueryLottery(String uid) {
		JSONObject json = new JSONObject();
		List<UserPrize> infos = userPrizeDao.GetUserPrizes(uid);
		JSONArray jArray = new JSONArray();
		for(UserPrize info : infos)
		{
			PrizeInfo prize = prizeInfoDao.GetPrizeInfo(info.getPid());
			if(prize != null)
			{
				JSONObject j = new JSONObject();
				j.accumulate("prizeUrl", prize.getPrizeUrl());
				j.accumulate("descUrl", prize.getDescUrl());
				jArray.add(j);
			}
		}
		json.accumulate("lotterys", jArray);
		return json;
	}

	@Override
	public JSONObject QueryLotteryUser(String id, int lid, int start, int end) {
		JSONObject json = new JSONObject();
		LotteryInfo lottery = lotteryInfoDao.GetLotteryInfo(lid);
		long pid = lottery.getPid();
		List<UserPrize> infos = userPrizeDao.GetUserPirzesByPid(pid, start, end);
		JSONArray jArray = new JSONArray();
		for(UserPrize info : infos)
		{
			String uid = info.getUid();
			UserInfo user = userInfoDao.getUserInfo(uid);
			if(user != null)
			{
				JSONObject j = new JSONObject();
				j.accumulate("name", user.getName());
				jArray.add(j);
			}
		}
		
		json.accumulate("lottery_users", jArray);
		return json;
	}
	
	public IExchangeInfoDao getExchangeInfoDao() {
		return exchangeInfoDao;
	}

	public void setExchangeInfoDao(IExchangeInfoDao exchangeInfoDao) {
		this.exchangeInfoDao = exchangeInfoDao;
	}

	public IPrizeInfoDao getPrizeInfoDao() {
		return prizeInfoDao;
	}

	public void setPrizeInfoDao(IPrizeInfoDao prizeInfoDao) {
		this.prizeInfoDao = prizeInfoDao;
	}

	public IUserPrizeDao getUserPrizeDao() {
		return userPrizeDao;
	}

	public void setUserPrizeDao(IUserPrizeDao userPrizeDao) {
		this.userPrizeDao = userPrizeDao;
	}

	public IUserCreditDao getUserCreditDao() {
		return userCreditDao;
	}

	public void setUserCreditDao(IUserCreditDao userCreditDao) {
		this.userCreditDao = userCreditDao;
	}

	public IUserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public ILotteryInfoDao getLotteryInfoDao() {
		return lotteryInfoDao;
	}

	public void setLotteryInfoDao(ILotteryInfoDao lotteryInfoDao) {
		this.lotteryInfoDao = lotteryInfoDao;
	}
}
