package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "store")
public class StoreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer storeId;
	private Integer userId;
	private String storeName;
	private String storePostcode;
	private String storeTodoufuken;
	private String storeShikucyouson;
	private String storeCyomebanchi;
	private String storePhone;
	private Integer storeStatus;

}
