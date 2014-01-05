package com.joyque.pojo;

public class OneLevel {

	private long oid;
	
	private String url;
	
	private String imageUrl;
	
	private int isStatic = -1;
	
	public void setDefaultValue()
	{
		isStatic = 0;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getIsStatic() {
		return isStatic;
	}

	public void setIsStatic(int isStatic) {
		this.isStatic = isStatic;
	}
}
