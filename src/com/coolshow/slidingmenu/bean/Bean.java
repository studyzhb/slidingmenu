package com.coolshow.slidingmenu.bean;

public class Bean {

	private int resourceId;
	private String count;
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Bean(int resourceId, String count) {
		super();
		this.resourceId = resourceId;
		this.count = count;
	}
	

}
