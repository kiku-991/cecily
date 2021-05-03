package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FavoriteDto extends ProductDto{
	
	public int userId;
	public int productId;
	public int favoriteId;
	public Timestamp createdate;
	

}
