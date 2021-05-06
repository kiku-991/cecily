package com.shoping.kiku.until;

import java.io.Serializable;

import lombok.Data;

@Data
public class Commerce implements Serializable {

	private String orderId;
	private String paymentId;
	private String shippingId;
}