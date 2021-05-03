package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.shoping.kiku.until.OrderItem;

import lombok.Data;

@Entity
@Data
@Table(name = "order_item")
@IdClass(value = OrderItem.class)

public class MyOrderItemEntity {

	@Id
	private String orderId;
	@Id
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;

}
