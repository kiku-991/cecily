package com.shoping.kiku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoping.kiku.service.ProductService;

class ProuctTest {

	@Autowired
	ProductService productService;
	

	@Test
	void searchAllproduct_test1() {
		
		assertEquals(1,productService.getProByUserId(18));
		  
	}
	
	

}
