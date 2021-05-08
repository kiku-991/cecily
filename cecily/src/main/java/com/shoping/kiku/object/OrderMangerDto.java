package com.shoping.kiku.object;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderMangerDto {

	//ID

	private String orderId;
	private Integer orderStatus;
	private String paymentId;
	private String shippingId;
	private Timestamp createdate;
	private String productImg;
	private String dtodoufuken;
	private String dshikucyouson;
	private String dcyoumebanchi;
	private String renrakuname;
	private String renrakuphone;
	private Integer payTotal;
	private Integer payQuantity;
	private Integer payMethod;
	private Timestamp payTime;
	private String courierCompany;
	private String trackingNumber;
	private Timestamp deliveryTime;
	private Timestamp receiptTime;
	private String name;
	private Integer qqt;
	private Integer total;
	private Timestamp certainTime;

	//商品

	private List<ProductInfoForOrderIdDto> product;

}
