package com.joyque.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joyque.common.util.FileUtil;
import com.joyque.dao.IExchangeInfoDao;
import com.joyque.dao.IPrizeInfoDao;
import com.joyque.dao.IUserCreditDao;
import com.joyque.dao.IUserPrizeDao;
import com.joyque.pojo.ExchangeInfo;
import com.joyque.pojo.PrizeInfo;
import com.joyque.service.IPresentServiceDao;
import com.opensymphony.xwork2.ActionContext;

public class PresentServiceImpl implements IPresentServiceDao{
	private IExchangeInfoDao exchangeInfoDao;
	private IPrizeInfoDao prizeInfoDao;
	private IUserPrizeDao userPrizeDao;
	private IUserCreditDao userCreditDao;

	@Override
	public void GetExchanges() {		
		ActionContext.getContext().put("exchanges", GetExchangeArray());
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
}
