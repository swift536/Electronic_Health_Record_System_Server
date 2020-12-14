package com.genuinedeveloper.mysqlaccessserver.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.genuinedeveloper.mysqlaccessserver.MainController;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Entity;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.repositories.AllergiesRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.ConditionsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.MedicationsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.RecordsRepository;

@Component
public class DatabaseLookup {
	
	//A list of injected repositories for dynamic access.
	@Autowired
	List<CrudRepository> repos;
	
	int iterator;
	
	public DatabaseLookup () {
		iterator = 0;
	}
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	public enum Repo {
		Allergies,
		Conditions,
		Medications,
		Patients,
		Records,
		Users
	}
	
	public void test () {
		return;
	}
	
	public Entity[] getAllById (int id, Repo repo) {
		
		ArrayList<Integer> lookupId = new ArrayList<Integer>();
		
		lookupId.add(id);
		
		Iterable iter = null;
		
		ArrayList itemList = null;
		
		Entity[] objectArray = null;
		
		switch (repo) {
		
			case Allergies:
				iterator = 0;
				iter =  ((AllergiesRepository) repos.get(iterator)).findAllByPatientId(id);
				itemList = new ArrayList<Allergies>();
				break;
			case Conditions:
				iterator = 1;
				iter =  ((ConditionsRepository) repos.get(iterator)).findAllByPatientId(id);
				itemList = new ArrayList<Conditions>();
				break;
			case Medications:
				iterator = 2;
				iter =  ((MedicationsRepository) repos.get(iterator)).findAllByPatientId(id);
				itemList = new ArrayList<Medications>();
				break;
			case Patients:
				iterator = 3;
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Patients>();
				break;
			case Records:
				iterator = 4;
				iter =  ((RecordsRepository) repos.get(iterator)).findAllByPatientId(id);
				itemList = new ArrayList<Records>();
				break;
			case Users:
				iterator = 5;
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Users>();
				break;
				
		}
		
		for (Object item: iter) {
			  
			itemList.add(item);
			  
		}
		  
		switch (repo) {
		
		case Allergies:
			objectArray = new Allergies[itemList.size()];
			break;
		case Conditions:
			objectArray = new Conditions[itemList.size()];
			break;
		case Medications:
			objectArray = new Medications[itemList.size()];
			break;
		case Patients:
			objectArray = new Patients[itemList.size()];
			break;
		case Records:
			objectArray = new Records[itemList.size()];
			break;
		case Users:
			objectArray = new Users[itemList.size()];
			break;
			
	}

		  
		int counter = 0;
		  
		for (Object item: itemList) {
			  
			logger.info(item.toString());
			  
			objectArray[counter] = (Entity) item;
			  
			counter++;
			  
		}
		  
		return objectArray;		
	}
	
}
