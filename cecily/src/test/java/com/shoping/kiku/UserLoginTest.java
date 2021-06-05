package com.shoping.kiku;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserLoginService;

@SpringBootTest
class UserLoginTest {

	@Autowired
	UserLoginService userLoginService;

	/*	@Test
		void createUsertest() {
			UserLoginDto user = new UserLoginDto();
			user.setUserId(17);
			user.setUserMail("admin@123.com");
			user.setUserPassword("96e79218965eb72c92a549dd5a330112");
			user.setCreateDate(new Timestamp(System.currentTimeMillis()));
			user.setRole("admin");
			user.setStatus(1);
			
			assertEquals(1,userLoginService.createUser(user));
		}*/

	@Test
	void getUserIdtest() throws Exception {

		assertEquals(18, userLoginService.getId("store@123.com"));
	}

	@Test
	void getPwdtest() throws Exception {

		assertEquals("0b4e7a0e5fe84ad35fb5f95b9ceeac79", userLoginService.getPwd("admin@123.com"));
	}

	@Test
	void getRoletest() throws Exception {

		assertEquals("admin", userLoginService.getRole("admin@123.com"));
	}

	@Test
	void roleChangetest() throws Exception {

		assertEquals("admin", userLoginService.getRole("admin@123.com"));
	}

	@Test
	void getStatustest() throws Exception {
		assertEquals(1, userLoginService.getStatus("admin@123.com"));
	}

	@Test
	void getAllUsertest() throws Exception {
		List<UserLoginDto> allUser = userLoginService.getAllUser();
		
		assertEquals(4, allUser.size());
	}

}
