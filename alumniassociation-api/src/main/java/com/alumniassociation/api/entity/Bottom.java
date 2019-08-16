package com.alumniassociation.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wangyan
 * @date:   2018年11月2日 下午6:17:45
 */
public class Bottom implements Serializable {
	private static final long serialVersionUID = 1L;
	/****/
	private String menuName;
	/****/
	private String menuNum;
	/****/
	private String menuIcon;
	
	private String isShow;

	/**
	 * 设置：
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * 获取：
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * 设置：
	 */
	public void setMenuNum(String menuNum) {
		this.menuNum = menuNum;
	}
	/**
	 * 获取：
	 */
	public String getMenuNum() {
		return menuNum;
	}
	/**
	 * 设置：
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	/**
	 * 获取：
	 */
	public String getMenuIcon() {
		return menuIcon;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
}
