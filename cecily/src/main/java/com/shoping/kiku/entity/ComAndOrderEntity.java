package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ComAndOrderEntity {
	private Integer userId;
	@Id
	private String orderId;
	private Integer productId;
	private Integer paymentId;
	private Integer shippingId;
	private Integer orderStatus;
	private Integer purchasingPrice;
	private Timestamp modifyTime;
	//支払い成功時 Commerce myOrder table 更新用
}
