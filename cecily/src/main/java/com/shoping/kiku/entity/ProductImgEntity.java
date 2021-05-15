package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.shoping.kiku.until.ProductImg;

import lombok.Data;

@Entity
@Data
@Table(name = "product_img")
@IdClass(value = ProductImg.class)
public class ProductImgEntity {

	@Id
	private Integer productId;
	@Id
	private String productImg;
}
