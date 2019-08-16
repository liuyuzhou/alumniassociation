package com.alumniassociation.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentResult implements Serializable{

	private String orderId;
	
	/** 1 支付成功 2 支付失败  **/
	private String resultCode;
	
	private String paymentMethod;
	
	private BigDecimal payAmount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
}
