package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")

public class ProductEntity {
	@Id
	private int productId;
	private int storeId;
	private String productName;
	private int productPrice;
	private String productImg;
	private String productContents;
	private String maker;
	private int status;
	private int stock;
	
}
