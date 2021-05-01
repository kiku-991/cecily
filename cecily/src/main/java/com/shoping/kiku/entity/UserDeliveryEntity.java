package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_delivery")
public class UserDeliveryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer userId;
	private Integer dpostcode;
	private String dtodoufuken;
	private String dshikucyouson;
	private String dcyoumebanchi;
	private String renrakuname;
	private String renrakuphone;

}
