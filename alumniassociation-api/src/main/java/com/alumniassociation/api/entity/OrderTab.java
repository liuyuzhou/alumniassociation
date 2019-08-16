package com.alumniassociation.api.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2018-10-28 14:29:50
 */
public class OrderTab implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**订单状态名称**/
	private String orderType;
	/**订单排序**/
	private String orderNum;
	/**订单状态**/
	private String orderStatus;
	/**订单总数**/
	private String orderCount = "0";
	/**订单状态图标**/
	private String orderIcon;
	/**订单链接**/
	private String orderHref;
	
	private Integer orderAmount = 0;

	
	/**
	 * 设置：
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：
	 */
	public void setOrderIcon(String orderIcon) {
		this.orderIcon = orderIcon;
	}
	/**
	 * 获取：
	 */
	public String getOrderIcon() {
		return orderIcon;
	}
	/**
	 * 设置：
	 */
	public void setOrderHref(String orderHref) {
		this.orderHref = orderHref;
	}
	/**
	 * 获取：
	 */
	public String getOrderHref() {
		return orderHref;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	
}
