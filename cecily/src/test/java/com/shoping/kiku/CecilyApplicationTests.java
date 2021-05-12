package com.shoping.kiku;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class CecilyApplicationTests {

	@Test
	void contextLoads() {
		
		  String springVersion = SpringVersion.getVersion();
	        System.out.println(springVersion);
	        String springBootVersion = SpringBootVersion.getVersion();
	        System.out.println(springBootVersion);
	}

}
