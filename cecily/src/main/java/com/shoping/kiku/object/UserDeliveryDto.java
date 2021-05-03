package com.shoping.kiku.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeliveryDto extends UserInfoDto {

	private Integer deliveryId;
	private Integer dpostcode;
	private String dtodoufuken;
	private String dshikucyouson;
	private String dcyoumebanchi;
	private String renrakuname;
	private String renrakuphone;
	private Integer defaultadd;

}
