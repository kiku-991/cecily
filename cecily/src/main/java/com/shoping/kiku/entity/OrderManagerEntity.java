package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OrderManagerEntity {

	@Id
	private String orderId;
	private Integer orderStatus;
	private String paymentId;
	private String shippingId;
	private Timestamp createdate;
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

}
