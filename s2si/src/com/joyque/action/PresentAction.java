package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IPresentService;

public class PresentAction extends BaseAction{

	private static final long serialVersionUID = 8151774968613927657L;
	
	private IPresentService presentService;

	private long eid;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private int exTitleIndex;
	
	private int exDescIndex;
	
	private int prizeTitleIndex;
	
	private int prizeDescIndex;
	
	private int lotteryIndex;
	
	private int bg1Index;
	
	private int bg2Index;
	
	private int bg3Index;
	
	private int credit;
	
	private int start;
	
	private int end;
	
	private int lid;
	
	private int isAward;
	
	public void GetExchanges()
	{
		JSONObject json = new JSONObject();
		try{
			json = presentService.GetExchanges();
		}catch(Exception e){			
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddExchange()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateCredit(credit)
					|| !isValidateIndex(exTitleIndex) || !isValidateIndex(exDescIndex)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.AddExchange(uid, pics, picsContentType,
					credit, exTitleIndex, exDescIndex, prizeTitleIndex, prizeDescIndex);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void updateExchange()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateCredit(credit)
					|| !isValidateIndex(exTitleIndex) || !isValidateIndex(exDescIndex)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex)
					|| !isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.UpdateExchange(uid, eid, pics, picsContentType,
					credit, exTitleIndex, exDescIndex, prizeTitleIndex, prizeDescIndex);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void DeleteExchange()
	{
		JSONObject json = new JSONObject();
		try{
			if(!isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DeleteExchange(eid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DoneExchange()
	{
		JSONObject json = new JSONObject();
		try
		{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DoneExchange(uid, eid);
		}
		catch(Exception e){			
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void ExchangerList()
	{
		JSONObject json = new JSONObject();
		try
		{
			if(!isValidateEid(eid) || !isValidatePage(start)
					|| !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.QueryExchangers(eid, start, end);
		}
		catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void LotteryList()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			json = presentService.GetLotterys(uid);
		}catch(Exception e){			
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	public void LotteryList_Web()
	{
		LotteryList();
	}
	
	@SuppressWarnings("unchecked")
	public void AddLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateCredit(credit)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex)
					|| !isValidateIndex(bg1Index) || !isValidateIndex(bg2Index)
					|| !isValidateIndex(bg3Index) || !isValidateIndex(lotteryIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.AddLottery(uid, pics, picsContentType,
					credit, prizeTitleIndex, prizeDescIndex, bg1Index, bg2Index, bg3Index,
					lotteryIndex);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateLid(lid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.UpdateLottery(uid, pics, picsContentType,
					credit, prizeTitleIndex, prizeDescIndex, bg1Index, bg2Index, bg3Index,
					lotteryIndex, lid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateLid(lid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DeleteLottery(lid, uid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DrawLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateLid(lid)|| !isValidateIsAward(isAward))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DrawLottery(uid, lid, isAward);
		}catch(Exception e){			
			json.accumulate("status", 400);
			json.accumulate("message", e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	

	@SuppressWarnings("unchecked")
	public void QueryLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");		
			json = presentService.QueryLottery(uid);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void QueryLotteryUser()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(!isValidateLid(lid) || !isValidatePage(start)
					|| !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.QueryLotteryUser(uid, lid, start, end);
		}catch(Exception e){			
			throw new BaseException(e.getMessage());
		}
		ajaxReturn(json.toString());
	}
	
	private boolean isValidateIsAward(int isAward) {
		if(isAward == 0 || isAward == 1)
		{
			return true;
		}
		return false;
	}
	
	private boolean isValidateLid(int lid) {
		if(lid > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidatePage(int page) {
		if(page >= 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateEid(long eid) {
		if(eid > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateIndex(int Index) {
		if(Index >= -1)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateCredit(int credit) {
		if(credit > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateType(List<String> picsContentType) {
		if(picsContentType != null && picsContentType.size() > 0)
		{
			return true;
		}
		return false;
	}

	private boolean isValidateImage(List<File> pics) {
		if(pics != null && pics.size() > 0)
		{
			return true;
		}
		return false;
	}

	public IPresentService getPresentService() {
		return presentService;
	}

	public void setPresentService(IPresentService presentService) {
		this.presentService = presentService;
	}

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public List<File> getPics() {
		return pics;
	}

	public void setPics(List<File> pics) {
		this.pics = pics;
	}

	public List<String> getPicsContentType() {
		return picsContentType;
	}

	public void setPicsContentType(List<String> picsContentType) {
		this.picsContentType = picsContentType;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getIsAward() {
		return isAward;
	}

	public void setIsAward(int isAward) {
		this.isAward = isAward;
	}

	public int getExTitleIndex() {
		return exTitleIndex;
	}

	public void setExTitleIndex(int exTitleIndex) {
		this.exTitleIndex = exTitleIndex;
	}

	public int getExDescIndex() {
		return exDescIndex;
	}

	public void setExDescIndex(int exDescIndex) {
		this.exDescIndex = exDescIndex;
	}

	public int getPrizeTitleIndex() {
		return prizeTitleIndex;
	}

	public void setPrizeTitleIndex(int prizeTitleIndex) {
		this.prizeTitleIndex = prizeTitleIndex;
	}

	public int getPrizeDescIndex() {
		return prizeDescIndex;
	}

	public void setPrizeDescIndex(int prizeDescIndex) {
		this.prizeDescIndex = prizeDescIndex;
	}

	public int getLotteryIndex() {
		return lotteryIndex;
	}

	public void setLotteryIndex(int lotteryIndex) {
		this.lotteryIndex = lotteryIndex;
	}

	public int getBg1Index() {
		return bg1Index;
	}

	public void setBg1Index(int bg1Index) {
		this.bg1Index = bg1Index;
	}

	public int getBg2Index() {
		return bg2Index;
	}

	public void setBg2Index(int bg2Index) {
		this.bg2Index = bg2Index;
	}

	public int getBg3Index() {
		return bg3Index;
	}

	public void setBg3Index(int bg3Index) {
		this.bg3Index = bg3Index;
	}
}
