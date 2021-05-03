package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderProInfoDto {

	private Integer userId;
	private String orderId;
	private Timestamp createdate;
	private Integer paymentId;
	private Integer shippingId;
	private Integer productId;
	private Integer productPrice;
	private String productName;
	private Integer productQuantity;
	private String productImg;
	private String productContents;
	private String maker;
	private Integer storeId;
	private String storeName;
	private Integer orderStatus;
	private Integer purchasingPrice;
	private Timestamp modifyTime;
}
