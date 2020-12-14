package com.genuinedeveloper.mysqlaccessserver.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;
import com.genuinedeveloper.mysqlaccessserver.repositories.AllergiesRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.ConditionsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.MedicationsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.PatientsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.RecordsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.SurgeriesRepository;
import com.genuinedeveloper.mysqlaccessserver.utilities.Authentication;
import com.genuinedeveloper.mysqlaccessserver.utilities.DatabaseLookup;
import com.genuinedeveloper.mysqlaccessserver.utilities.DatabaseLookup.Repo;

@Service
public class PatientServices {

	@Autowired
	private PatientsRepository patientsRepository;
	
	@Autowired
	private AllergiesRepository allergiesRepository;
	
	@Autowired
	private MedicationsRepository medicationsRepository;
	
	@Autowired
	private RecordsRepository recordsRepository;
	
	@Autowired
	private ConditionsRepository conditionsRepository;
	
	@Autowired
	private SurgeriesRepository surgeriesRepository;
	
	@Autowired
	private Authentication auth;
	
	@Autowired
	DatabaseLookup lookup;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	Logger logger = LoggerFactory.getLogger(PatientServices.class);
	
	
	
	
	
	//POST endpoints
	public boolean postAllergy (Allergies allergy) {
		
		boolean response = false;
		
		try {
			
			allergiesRepository.save(allergy);
			
			response = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Error saving allergy");
			
		} 
		
		return response;
		
	}

	public boolean postPatient (Patients patient) {

		boolean response = false;
		
		try {
			
			patientsRepository.save(patient);
			
			response = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Error saving patient");
			
		} 
		
		return response;
	
	}

	public boolean postRecord (Records record) {
		
		boolean response = false;
		
		try {
			
			recordsRepository.save(record);
			
			response = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Error saving record");
			
		} 
		
		return response;
		
	}

	public boolean postMedication (Medications medication) {
		
		boolean response = false;
		
		try {
			
			medicationsRepository.save(medication);
			
			response = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Error saving medication");
			
		} 
		
		return response;
		
	}
	

	
	
	//GET endpoints
	public Patients[] getPatients() {
		
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
						
					}
					
					patients.add(patient);

					
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
	
	public Records[] getRecords (Integer id) {

		Records[] response = (Records[]) lookup.getAllById(id, Repo.Records);
		
		return response;
		
	}
	
	public Allergies[] getAllergies (Integer id) {
		
		Allergies[] response = (Allergies[]) lookup.getAllById(id, Repo.Allergies);
		
		return response;
		
	}
	 
	public Medications[] getMedications (Integer id) {
		
		Medications[] response = (Medications[]) lookup.getAllById(id, Repo.Medications);
		
		return response;
		
	}
	
	public Conditions[] getConditions(Integer id) {
		
		Conditions[] response = (Conditions[]) lookup.getAllById(id, Repo.Conditions);
		
		return response;
		
	}
	
}
