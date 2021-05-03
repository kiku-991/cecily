package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductInCartDto {

	private Integer productId;
	private Integer userId;
	private String  productName;
	private Integer productPrice;
	private String  productImg;
	private String  productContents;
	private Integer quantity;
	private Integer total;
	private Integer amount;
	private Integer checkbox;
	private boolean allchecked;
}
