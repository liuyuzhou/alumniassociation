package com.alumniassociation.api.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2018-10-28 14:00:02
 */
public class MyTab implements Serializable {
	private static final long serialVersionUID = 1L;
	/****/
	private String tabName;
	/****/
	private String tabNum;
	/****/
	private String tabHref;

	
	/**
	 * 设置：
	 */
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	/**
	 * 获取：
	 */
	public String getTabName() {
		return tabName;
	}
	/**
	 * 设置：
	 */
	public void setTabNum(String tabNum) {
		this.tabNum = tabNum;
	}
	/**
	 * 获取：
	 */
	public String getTabNum() {
		return tabNum;
	}
	/**
	 * 设置：
	 */
	public void setTabHref(String tabHref) {
		this.tabHref = tabHref;
	}
	/**
	 * 获取：
	 */
	public String getTabHref() {
		return tabHref;
	}
}
