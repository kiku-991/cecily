package com.shoping.kiku.until;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductImg implements Serializable{

	private Integer productId;
	private String productImg;
}
