package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "myorder")
public class MyOrderEntity {

	/**
	* @description 主键
	*/
	@Id
	private String orderId;
	private int orderStatus;
	private int purchasingPrice;
	private Timestamp createTime;
	private Timestamp modifyTime;

}
