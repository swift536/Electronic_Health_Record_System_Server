package com.genuinedeveloper.mysqlaccessserver.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.utilities.DatabaseLookup.Repo;

@SpringBootTest
class DatabaseLookupTest {

	@Autowired
	DatabaseLookup lookup;
	
	@Test
	void test() {
		
		Medications[] meds = (Medications[]) lookup.getAllById(1, Repo.Medications);
		
		assert meds != null;

	}

}
