package com.genuinedeveloper.mysqlaccessserver.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;

@SpringBootTest
class PatientServicesTest {
	
	@Autowired
	PatientServices pServices;
	
	@Test
	void test() {

		/*Allergies[] allergies = pServices.getAllergies(1);
		
		assert allergies != null;*/
		
		getPatients();

	}
	
	private boolean getPatients () {
		
		boolean returnValue = false;
		
		Patients[] patients = pServices.getPatients();
		
		if (patients != null) {
			
			returnValue = true;
			
		}
		
		return returnValue;
		
	}

}
