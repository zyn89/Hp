package com.joyque.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.joyque.common.action.BaseAction;
import com.joyque.common.exception.BaseException;
import com.joyque.common.util.ExceptionUtil;
import com.joyque.service.IPresentServiceDao;

public class PresentAction extends BaseAction{

	private static final long serialVersionUID = 8151774968613927657L;
	
	private IPresentServiceDao presentService;

	private long eid;
	
	private List<File> pics;
	
	private List<String> picsContentType;
	
	private List<String> picsName;
	
	private String exTitleIndex;
	
	private String exDescIndex;
	
	private String prizeTitleIndex;
	
	private String prizeDescIndex;
	
	private String lotteryIndex;
	
	private String bg1Index;
	
	private String bg2Index;
	
	private String bg3Index;
	
	private int credit;
	
	private int start;
	
	private int end;
	
	private int lid;
	
	private int isAward;
	
	@SuppressWarnings("unchecked")
	public void GetExchanges()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			json = presentService.GetExchanges();
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateName(picsName) || !isValidateCredit(credit)
					|| !isValidateIndex(exTitleIndex) || !isValidateIndex(exDescIndex)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.AddExchange(uid, pics, picsContentType, picsName,
					credit, exTitleIndex, exDescIndex, prizeTitleIndex, prizeDescIndex);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateName(picsName) || !isValidateCredit(credit)
					|| !isValidateIndex(exTitleIndex) || !isValidateIndex(exDescIndex)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex)
					|| !isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.UpdateExchange(uid, eid, pics, picsContentType, picsName,
					credit, exTitleIndex, exDescIndex, prizeTitleIndex, prizeDescIndex);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void DeleteExchange()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DeleteExchange(eid);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateEid(eid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DoneExchange(uid, eid);
		}
		catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void ExchangerList()
	{
		JSONObject json = new JSONObject();
		try
		{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateEid(eid) || !isValidatePage(start)
					|| !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.QueryExchangers(eid, start, end);
		}
		catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			json = presentService.GetLotterys(uid);
		}catch(Exception e){			
			
		}
		ajaxReturn(json.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void AddLottery()
	{
		JSONObject json = new JSONObject();
		try{
			String uid = null;
			Map session = getSession();
			uid = (String) session.get("uid");
			if(uid == null)
			{
				
			}
			if(!isValidateImage(pics) || !isValidateType(picsContentType)
					|| !isValidateName(picsName) || !isValidateCredit(credit)
					|| !isValidateIndex(prizeTitleIndex) || !isValidateIndex(prizeDescIndex)
					|| !isValidateIndex(bg1Index) || !isValidateIndex(bg2Index)
					|| !isValidateIndex(bg3Index) || !isValidateIndex(lotteryIndex))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.AddLottery(uid, pics, picsContentType, picsName,
					credit, prizeTitleIndex, prizeDescIndex, bg1Index, bg2Index, bg3Index,
					lotteryIndex);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateLid(lid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.UpdateLottery(uid, pics, picsContentType, picsName,
					credit, prizeTitleIndex, prizeDescIndex, bg1Index, bg2Index, bg3Index,
					lotteryIndex, lid);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateLid(lid))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DeleteLottery(lid, uid);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}
			if(!isValidateLid(lid)|| !isValidateIsAward(isAward))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.DrawLottery(uid, lid, isAward);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}			
			json = presentService.QueryLottery(uid);
		}catch(Exception e){			
			
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
			if(uid == null)
			{
				
			}	
			if(!isValidateLid(lid) || !isValidatePage(start)
					|| !isValidatePage(end))
			{
				throw new BaseException(ExceptionUtil.IllegalInput);
			}
			json = presentService.QueryLotteryUser(uid, lid, start, end);
		}catch(Exception e){			
			
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

	private boolean isValidateIndex(String Index) {
		if(Index != null && !Index.trim().equals(""))
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

	private boolean isValidateName(List<String> picsName) {
		if(picsName != null && picsName.size() > 0)
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

	public IPresentServiceDao getPresentService() {
		return presentService;
	}

	public void setPresentService(IPresentServiceDao presentService) {
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

	public String getExTitleIndex() {
		return exTitleIndex;
	}

	public void setExTitleIndex(String exTitleIndex) {
		this.exTitleIndex = exTitleIndex;
	}

	public String getExDescIndex() {
		return exDescIndex;
	}

	public void setExDescIndex(String exDescIndex) {
		this.exDescIndex = exDescIndex;
	}

	public String getPrizeTitleIndex() {
		return prizeTitleIndex;
	}

	public void setPrizeTitleIndex(String prizeTitleIndex) {
		this.prizeTitleIndex = prizeTitleIndex;
	}

	public String getPrizeDescIndex() {
		return prizeDescIndex;
	}

	public void setPrizeDescIndex(String prizeDescIndex) {
		this.prizeDescIndex = prizeDescIndex;
	}

	public List<String> getPicsName() {
		return picsName;
	}

	public void setPicsName(List<String> picsName) {
		this.picsName = picsName;
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

	public String getLotteryIndex() {
		return lotteryIndex;
	}

	public void setLotteryIndex(String lotteryIndex) {
		this.lotteryIndex = lotteryIndex;
	}

	public String getBg1Index() {
		return bg1Index;
	}

	public void setBg1Index(String bg1Index) {
		this.bg1Index = bg1Index;
	}

	public String getBg2Index() {
		return bg2Index;
	}

	public void setBg2Index(String bg2Index) {
		this.bg2Index = bg2Index;
	}

	public String getBg3Index() {
		return bg3Index;
	}

	public void setBg3Index(String bg3Index) {
		this.bg3Index = bg3Index;
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
}
