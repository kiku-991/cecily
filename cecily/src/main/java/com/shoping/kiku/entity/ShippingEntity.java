package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "shipping")
public class ShippingEntity {

	@Id
	private String shippingId;
	private String courierCompany;
	private String trackingNumber;
	private Timestamp deliveryTime;
	private Timestamp receiptTime;

}
