package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
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
