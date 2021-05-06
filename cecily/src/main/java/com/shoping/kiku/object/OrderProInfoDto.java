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
	private String paymentId;
	private String shippingId;
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
	private Integer payTotal;
	private Integer payQuantity;
	private Integer payMethod;
	private Timestamp payTime;
}
