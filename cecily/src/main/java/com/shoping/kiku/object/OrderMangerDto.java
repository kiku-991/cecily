package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderMangerDto {
	
	private String orderId;
	private Timestamp createdate;
	private String paymentId;
	private Integer shippingId;
	private Integer productId;
	private Integer productPrice;
	private String productName;
	private Integer productQuantity;
	private Integer userId;
	private String productImg;
	private Integer storeId;
	private Integer orderStatus;
	private Integer purchasingPrice;
	private Timestamp modifyTime;
	private Integer payTotal;
	private Integer payQuantity;
	private Integer payMethod;
	private Timestamp payTime;
	private String dtodoufuken;
	private String dshikucyouson;
	private String dcyoumebanchi;
	private String renrakuname;
	private String renrakuphone;

}
