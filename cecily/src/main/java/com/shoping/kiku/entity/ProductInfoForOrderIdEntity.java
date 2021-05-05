package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.shoping.kiku.until.OrderItem;

import lombok.Data;

@Entity
@Data
@IdClass(value = OrderItem.class)
public class ProductInfoForOrderIdEntity {

	@Id
	private String orderId;
	@Id
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private String productImg;
}
