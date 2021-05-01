package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
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
