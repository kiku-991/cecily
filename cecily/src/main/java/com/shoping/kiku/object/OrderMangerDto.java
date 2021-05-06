package com.shoping.kiku.object;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderMangerDto {

	//ID

	private List<OrderIdGroupDto> id;

	//商品

	private List<ProductInfoForOrderIdDto> product;

}
