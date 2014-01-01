package com.joyque.pojo;

import com.joyque.common.util.DefaultValue;

public class UserCredit {

	private String uid;
	
	private int isCheck = -1;
	
	private int credit = -1;
	
	private int lotteryCount = -1;
	
	public void setDefaultValue()
	{
		isCheck = 0;
		credit = 0;
		lotteryCount = DefaultValue.LotteryCount;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getLotteryCount() {
		return lotteryCount;
	}

	public void setLotteryCount(int lotteryCount) {
		this.lotteryCount = lotteryCount;
	}
}
