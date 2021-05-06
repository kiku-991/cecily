package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ShippingDto {
	private String shippingId;
	private String courierCompany;
	private String trackingNumber;
	private Timestamp deliveryTime;
	private Timestamp receiptTime;

}
