package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private int productId;
	private int storeId;
	private String productName;
	private int productPrice;
	private String productImg;
	private String productContents;
	private String maker;
	private int status;
	private int stock;
	
	
	//詳細画面判断気に入り用
	private int userId;
	

}
