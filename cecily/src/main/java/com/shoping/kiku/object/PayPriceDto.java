package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayPriceDto {
	
	private String  orderId;
	private Integer userId;
	private Integer productPrice;
	private Integer productId;
	private Integer productQuantity;
	

}
