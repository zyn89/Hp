package com.joyque.pojo;

public class UserCredit {

	private String uid;
	
	private int isCheck = -1;
	
	private int credit = -1;
	
	public void setDefaultValue()
	{
		isCheck = 0;
		credit = 0;
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
}
