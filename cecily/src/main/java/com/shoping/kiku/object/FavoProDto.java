package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoProDto {
	
	private int productId;
	private String productName;
	private int productPrice;
	private String productImg;
	private String productContents;
	private String maker;
	private int status;
	private int userId;
	private Timestamp createdate;
	private int favoriteId;
	

}
