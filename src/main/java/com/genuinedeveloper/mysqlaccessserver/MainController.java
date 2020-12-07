package com.genuinedeveloper.mysqlaccessserver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Surgeries;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.repositories.*;
import com.genuinedeveloper.mysqlaccessserver.utilities.Authentication;
import com.genuinedeveloper.mysqlaccessserver.utilities.DatabaseLookup;

@Controller
@RequestMapping(path="/request")
public class MainController {
	
  @Autowired
  private PatientsRepository patientsRepository;
  
  @Autowired
  private AllergiesRepository allergiesRepository;
  
  @Autowired
  private MedicationsRepository medicationsRepository;
  
  @Autowired
  private RecordsRepository recordsRepository;
  
  @Autowired
  private UsersRepository usersRepository;
  
  @Autowired
  private ConditionsRepository conditionsRepository;
  
  @Autowired
  private SurgeriesRepository surgeriesRepository;
  
  @Autowired
  ResourceLoader resourceLoader;
  
  Logger logger = LoggerFactory.getLogger(MainController.class);

  /*
   * Post Endpoints
   */
  @PostMapping(path="/login")
  public @ResponseBody String login() {
	  
	  return "true";
	  
  }
  
  @PostMapping(path="/allergies")
  public @ResponseBody String addNewAllergy (@RequestBody Allergies allergy) {
	  
	  allergiesRepository.save(allergy);
	  
	  return "saved";
	  
  }
  
  @PostMapping(path="/patients")
  public @ResponseBody String addNewPatient (@RequestBody Patients patient) {

    patientsRepository.save(patient);
    
    return patient.getDobDate().toString();
    
  }
  
  @PostMapping(path="/records")
  public @ResponseBody String addNewRecord (@RequestBody Records record) {
	  
	  recordsRepository.save(record);
	  
	  return "saved";
	  
  }
  
  @PostMapping(path="/medications")
  public @ResponseBody String addNewMedication (@RequestBody Medications medication) {
	  
	  medicationsRepository.save(medication);
	  
	  return "saved";
	  
  }
  
  /*
   * GET Endpoints
   */
  @GetMapping(path="/records/{id}")
  public @ResponseBody  Records[] getRecord (@PathVariable(name="id") Integer id) {

	  ArrayList<Integer> lookupId = new ArrayList<Integer>();
	  
	  lookupId.add(id);
	  
	  Iterable<Records> iter =  recordsRepository.findAllById(lookupId);
	  
	  ArrayList<Records> recordsList = new ArrayList<Records>();
	  
	  for (Records record: iter) {
		  
		  recordsList.add(record);
		  
	  }
	  
	  Records[] recordsArray = new Records[recordsList.size()];
	  
	  int counter = 0;
	  
	  for (Records record: recordsList) {
		  
		  logger.info(record.toString());
		  
		  recordsArray[counter] = record;
		  
		  counter++;
		  
	  }
	  
	  return recordsArray;
	  
  }
  
  @GetMapping(path="/allergies/{id}")
  public @ResponseBody Allergies[] getAllergies (@PathVariable(name="id") Integer id) {
	  
	  ArrayList<Integer> lookupId = new ArrayList<Integer>();
	  
	  lookupId.add(id);
	  
	  Iterable<Allergies> iter =  allergiesRepository.findAllById(lookupId);
	  
	  ArrayList<Allergies> allergiesList = new ArrayList<Allergies>();
	  
	  for (Allergies allergy: iter) {
		  
		  allergiesList.add(allergy);
		  
	  }
	  
	  Allergies[] allergiesArray = new Allergies[allergiesList.size()];
	  
	  int counter = 0;
	  
	  for (Allergies allergy: allergiesList) {
		  
		  allergiesArray[counter] = allergy;
		  
		  counter++;
		  
	  }
	  
	  return allergiesArray;
	  
  }
 
  
  @GetMapping(path="/medications/{id}")
  public @ResponseBody Medications[] getMedications (@PathVariable(name="id") Integer id) {
	  
	  ArrayList<Integer> lookupId = new ArrayList<Integer>();
	  
	  lookupId.add(id);
	  
	  Iterable<Medications> iter =  medicationsRepository.findAllById(lookupId);
	  
	  ArrayList<Medications> medsList = new ArrayList<Medications>();
	  
	  for (Medications med: iter) {
		  
		  medsList.add(med);
		  
	  }
	  
	  Medications[] medicationsArray = new Medications[medsList.size()];
	  int counter = 0;
	  for (Medications med: medsList) {
		  medicationsArray[counter] = med;
		  counter++;
	  }
	  
	  
	  return medicationsArray;
	  
  }
  
  @GetMapping(value="/patients")
  public @ResponseBody Patients[] getPatients() {
	  
	  //DatabaseLookup lookup = new DatabaseLookup(new Patients());
	  
	  logger.info("Endpoint hit");
	  
	  Iterable<Patients> iterable = patientsRepository.findAll();
	  ArrayList<Patients> patients = new ArrayList<Patients>();
	  
	  iterable.forEach((new Consumer<Patients>() {  

		@Override
		public void accept(Patients patient) {
			
			ClassLoader cl;
			
			URL url;

			cl = getClass().getClassLoader();
			
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
					InputStream is = cl.getResourceAsStream("patient_images/" + patient.getImageUrl())) {
				
				if (is != null) {
					
					//patient.setImage(url.getPath());
					BufferedImage bImage = ImageIO.read(is);
				    ImageIO.write(bImage, "jpg", bos);
					patient.setImage(bos.toByteArray());
					patients.add(patient);
					
				}

				
			} catch (Exception e) {
				
				//try with resources will throw error if "is" is null
				
			}
		}
		
	  }));
	  
	  Patients[] patientsArray = new Patients[patients.size()];
	  int counter = 0;
	  for (Patients patient: patients) {
		  patientsArray[counter] = patient;
		  counter++;
	  }
			  
    return patientsArray;
  }
  
  @GetMapping(value="/conditions")
  public @ResponseBody Conditions[] getConditions(@PathVariable(name="id") Integer id) {
	  
	  ArrayList<Integer> lookupId = new ArrayList<Integer>();
	  
	  lookupId.add(id);
	  
	  Iterable<Conditions> iter =  conditionsRepository.findAllById(lookupId);
	  
	  ArrayList<Conditions> condList = new ArrayList<Conditions>();
	  
	  for (Conditions cond: iter) {
		  
		  condList.add(cond);
		  
	  }
	  
	  Conditions[] conditionsArray = new Conditions[condList.size()];
	  int counter = 0;
	  for (Conditions cond: condList) {
		  conditionsArray[counter] = cond;
		  counter++;
	  }
	  
	  
	  return conditionsArray;
	  
  }
  
  /*@GetMapping(value="/patients")
  public @ResponseBody Surgeries[] getSurgeries(@PathVariable(name="id") Integer id) {
	  
	  ArrayList<Integer> lookupId = new ArrayList<Integer>();
	  
	  lookupId.add(id);
	  
	  Iterable<Medications> iter =  medicationsRepository.findAllById(lookupId);
	  
	  ArrayList<Medications> medsList = new ArrayList<Medications>();
	  
	  for (Medications med: iter) {
		  
		  medsList.add(med);
		  
	  }
	  
	  Medications[] medicationsArray = new Medications[medsList.size()];
	  int counter = 0;
	  for (Medications med: medsList) {
		  medicationsArray[counter] = med;
		  counter++;
	  }
	  
	  
	  return medicationsArray;
	  
  }*/
  
}