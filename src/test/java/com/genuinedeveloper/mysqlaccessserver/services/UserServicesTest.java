package com.genuinedeveloper.mysqlaccessserver.services;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;

@SpringBootTest
class UserServicesTest {

	@Autowired
	UserServices userService;
	
	@Test
	void test() {

		boolean bool = userService.postUser(new Users("Test", "McTester", new Date(1980-1900,10,10), "testusername", "testpassword", "sq1", "sq2", "sq3", "sa1", "sa2", "sa3"));
		
		assert bool == true;
		
	}

}
