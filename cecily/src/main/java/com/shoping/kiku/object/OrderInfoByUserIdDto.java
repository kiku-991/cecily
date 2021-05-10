package com.shoping.kiku.object;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoByUserIdDto {
	private String orderId;
	private Integer orderStatus;
	private Integer purchasingPrice;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private String paymentId;
	private Integer payTotal;
	private Integer payQuantity;
	private Integer payMethod;
	private Timestamp payTime;
	private String shippingId;
	private String courierCompany;
	private String trackingNumber;
	private Timestamp deliveryTime;
	private Timestamp receiptTime;
	private Integer total;
	private Integer qqt;
	private Timestamp certainTime;
	private String ordStatus;
	
	List<ProductInfoForOrderIdDto> product;
}
