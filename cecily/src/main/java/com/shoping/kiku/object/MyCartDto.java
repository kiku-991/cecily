package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyCartDto {

	public int userId;

	public int productId;

	public int quantity;
	
	public int checkstatus;

}
