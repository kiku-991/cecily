package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "commerce")

public class CommerceEntity {

	@Id
	private String orderId;
	private String paymentId;
	private String shippingId;
	private Integer userId;
	private Timestamp createdate;

}
