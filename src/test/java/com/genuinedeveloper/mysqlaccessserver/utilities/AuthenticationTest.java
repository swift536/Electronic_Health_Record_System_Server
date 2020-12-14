package com.genuinedeveloper.mysqlaccessserver.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationTest {

	@Autowired
	Authentication auth;
	
	@Test
	void test() {

		/*String str = auth.authenticateUserByAnswers(1, new String[] {"sa1","sa2","sa3"});
		
		assert str == "testpassword";*/
		
		boolean bool = auth.headerAuthentication(new String("4:testusername:testpassword").toCharArray());
		
		assert bool == true;
		
	}

}
