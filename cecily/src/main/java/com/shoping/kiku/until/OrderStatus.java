package com.shoping.kiku.until;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

	WHITPAY(0, "待付款"), 
	WHITPRODUCT(1, "待发货"), 
	WHITDELIVERY(2,"待收货"),
	COMPLETE(3, "已完成");

	private final Integer value;
	private final String text;

}
