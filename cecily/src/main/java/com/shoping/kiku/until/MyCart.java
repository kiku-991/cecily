package com.shoping.kiku.until;

import java.io.Serializable;

import lombok.Data;

@Data
public class MyCart implements Serializable {
	
	public int userId;
	public int productId;

}
