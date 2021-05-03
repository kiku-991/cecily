package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderDto {


	private String orderId;
	private int orderStatus;
	private int purchasingPrice;
	private Timestamp createTime;
	private Timestamp modifyTime;
}
