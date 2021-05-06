package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	private String  orderId ;
	private Integer productId ;
	private String productName ;
	private Integer productPrice ;
	private Integer productQuantity ;
}
