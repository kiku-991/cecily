package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OrderProInfoEntity {
	private Integer userId;
	@Id
	private String orderId;
	private Timestamp createdate;
	private String paymentId;
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
