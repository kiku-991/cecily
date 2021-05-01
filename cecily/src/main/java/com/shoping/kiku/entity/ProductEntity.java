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
	private int userId;
	private String productName;
	private int productPrice;
	private String productImg;
	private String productContents;
	private String maker;
	private String status;
	private int stock;
	
}
