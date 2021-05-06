package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PayPriceEntity {
	@Id
	private Integer productPrice;
	private Integer productQuantity;
//支払い画面の金額用
}
