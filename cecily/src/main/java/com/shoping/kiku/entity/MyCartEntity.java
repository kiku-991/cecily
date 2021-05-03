package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.shoping.kiku.until.MyCart;

import lombok.Data;

@Data
@Entity
@Table(name = "mycart")
@IdClass(value = MyCart.class)
public class MyCartEntity {

	@Id
	/**
	* @description 主键
	*/
	public int userId;
	@Id
	/**
	* @description 主键
	*/
	public int productId;

	public int quantity;

	public int checkstatus;

}

