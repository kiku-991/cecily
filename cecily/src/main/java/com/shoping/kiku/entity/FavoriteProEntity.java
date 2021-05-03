package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class FavoriteProEntity {
	@Id
	private int productId;
	private String productName;
	private int productPrice;
	private String productImg;
	private String productContents;
	private String maker;
	private int status;
	@Column(nullable = true)
	private Integer userId;
	private Timestamp createdate;

}
