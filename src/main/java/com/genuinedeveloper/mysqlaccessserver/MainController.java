package com.genuinedeveloper.mysqlaccessserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.services.PatientServices;
import com.genuinedeveloper.mysqlaccessserver.services.UserServices;

/*
 * REST endpoints on the server are precluded by an interceptor (RESTAuthenticationInterceptor.java)
 * that handles authentication. Authentication is handled via sending the user credentials with 
 * each request within the "Authentication" header of the HTTP request.
 * 
 * The endpoints defer to the services (PatientServices.java and UserServices.java) that then interface
 * with the database class (DatabaseLookup.java) which handles requests for all entity types.
 */

/*TODO  Future development will look into including a controller class per entity and the addition of advanced
searching/paging to improve performance client side (i.e. scrolling to bottom of current list on client application
can make a request for the next page. PER STATELESS REQUIREMENT, THE CLIENT WILL HAVE TO MAINTAIN PAGE COUNT*/

/*TODO Caching patient related information is somewhat irrelevent and wasteful because each patient should only
 * be referenced once per significant period of time (1 day or more) but the entire Patient list might benefit from
 * caching for more common characters ('J', 'M', 'S', 'C' and 'D' most common for first names)
 */

@Controller
@RequestMapping(path = "/request")
public class MainController {

	@Autowired
	PatientServices patientServices;

	@Autowired
	UserServices userServices;

	Logger logger = LoggerFactory.getLogger(MainController.class);

	
	
	/************************************************User Endpoints*******************************************************/
	@PostMapping(path = "/login")
	public @ResponseBody boolean login() {

		return true;

	}
	
	// Initial query to get security questions
	@GetMapping(path = "/users/forgot/{id}")
	public @ResponseBody String[] forgotPassword(@PathVariable(name = "id") Integer id) {
		
		String[] response = userServices.forgotPassword(id);
		
		return response;

	}
	
	// Posts answers to security questions, returns unencrypted password (maintains TLS encryption).
	@PostMapping(path = "/users/security-questions/{id}")
	public @ResponseBody char[] postSecurityAnswers(@RequestBody String[] answers, @PathVariable(name = "id") Integer id) {

		char[] response = userServices.postSecurityAnswers(answers, id);
		
		return response;

	}
	
	@PostMapping(path = "/users/add")
	public @ResponseBody boolean addUser(@RequestBody Users user) {

		boolean response = userServices.postUser(user);

		return response;

	}
	
	/************************************************POST Endpoints*******************************************************/
	@PostMapping(path = "/allergies")
	public @ResponseBody boolean postAllergy(@RequestBody Allergies allergy) {

		boolean response = patientServices.postAllergy(allergy);

		return response;

	}

	@PostMapping(path = "/patients")
	public @ResponseBody boolean postPatient(@RequestBody Patients patient) {

		boolean response = patientServices.postPatient(patient);

		return response;
		
	}

	@PostMapping(path = "/records")
	public @ResponseBody boolean postRecord(@RequestBody Records record) {

		boolean response = patientServices.postRecord(record);

		return response;

	}

	@PostMapping(path = "/medications")
	public @ResponseBody boolean postMedication(@RequestBody Medications medication) {

		boolean response = patientServices.postMedication(medication);

		return response;

	}
	
	/************************************************GET Endpoints*******************************************************/
	@GetMapping(path = "/records/{id}")
	public @ResponseBody Records[] getRecords(@PathVariable(name = "id") Integer id) {

		Records[] response = patientServices.getRecords(id);
		
		return response;

	}

	@GetMapping(path = "/allergies/{id}")
	public @ResponseBody Allergies[] getAllergies(@PathVariable(name = "id") Integer id) {

		Allergies[] response = patientServices.getAllergies(id);
		
		return response;

	}

	@GetMapping(path = "/medications/{id}")
	public @ResponseBody Medications[] getMedications(@PathVariable(name = "id") Integer id) {

		Medications[] response = patientServices.getMedications(id);
		
		return response;

	}

	@GetMapping(value = "/patients")
	public @ResponseBody Patients[] getPatients() {
		
		Patients[] response = patientServices.getPatients();
		
		return response;
		
	}

	@GetMapping(value = "/conditions")
	public @ResponseBody Conditions[] getConditions(@PathVariable(name = "id") Integer id) {

		Conditions[] response = patientServices.getConditions(id);
		
		return response;

	}



}