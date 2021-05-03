package com.shoping.kiku.until;

import java.io.Serializable;

import lombok.Data;

@Data
public class Favorite implements Serializable {

	public int userId;
	public int productId;
	
}
