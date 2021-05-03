package com.shoping.kiku.until;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItem implements Serializable {
	
	public String orderId;
	public Integer productId;
}
