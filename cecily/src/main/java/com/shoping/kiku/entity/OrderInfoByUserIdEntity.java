package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OrderInfoByUserIdEntity {

	@Id
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
	private Timestamp trackingNumber;
	private Timestamp deliveryTime;
	private Timestamp receiptTime;

}
