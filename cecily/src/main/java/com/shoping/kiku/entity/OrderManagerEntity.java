package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.shoping.kiku.until.OrderItem;

import lombok.Data;

@Entity
@Data
@IdClass(value = OrderItem.class)
public class OrderManagerEntity {

	@Id
	private String orderId;
	private Timestamp createdate;
	private String paymentId;
	private Integer shippingId;
	@Id
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
