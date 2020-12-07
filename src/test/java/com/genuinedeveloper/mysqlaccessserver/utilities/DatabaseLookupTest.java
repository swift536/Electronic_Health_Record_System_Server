package com.genuinedeveloper.mysqlaccessserver.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.repositories.AllergiesRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.PatientsRepository;

@SpringBootTest
class DatabaseLookupTest {

	@Autowired
	DatabaseLookup lookup;
	
	@Test
	void test() {
		
		lookup.test();

	}

}
