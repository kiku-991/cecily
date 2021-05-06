package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.shoping.kiku.until.OrderItem;

import lombok.Data;

@Entity
@Data
@IdClass(value = OrderItem.class)
public class OrderProInfoEntity {
	
	@Id
	private String orderId;
	private Integer userId;
	private Timestamp createdate;
	@Column(nullable = true)
	private String paymentId;
	@Column(nullable = true)
	private String shippingId;
	@Id
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
	@Column(nullable = true)
	private Timestamp modifyTime;
	@Column(nullable = true)
	private Integer payTotal;
	@Column(nullable = true)
	private Integer payQuantity;
	@Column(nullable = true)
	private Integer payMethod;
	@Column(nullable = true)
	private Timestamp payTime;

}
