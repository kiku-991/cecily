package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ProductInCartEntity {

	@Id
	private Integer productId;
	private Integer userId;
	private String  productName;
	private Integer productPrice;
	private String  productImg;
	private String  productContents;
	private Integer quantity;
	private Integer total;

	
}
