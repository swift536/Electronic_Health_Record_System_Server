package com.genuinedeveloper.mysqlaccessserver.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.genuinedeveloper.mysqlaccessserver.MainController;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.repositories.AllergiesRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.MedicationsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.PatientsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.RecordsRepository;
import com.genuinedeveloper.mysqlaccessserver.repositories.UsersRepository;

@Component
public class DatabaseLookup {
	
	//A list of injected repositories for dynamic access.
	@Autowired
	List<CrudRepository> repos;
	
	int iterator;
	
	public DatabaseLookup () {
		iterator = 0;
	}
	
	//Debugging
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
	
	public Object[] getAllById (int id, Repo repo) {
		
		ArrayList<Integer> lookupId = new ArrayList<Integer>();
		
		lookupId.add(id);
		
		Iterable iter = null;
		
		ArrayList itemList = null;
		
		switch (repo) {
		
			case Allergies:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Allergies>();
				iterator = 0;
				break;
			case Conditions:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Conditions>();
				iterator = 1;
				break;
			case Medications:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Medications>();
				iterator = 2;
				break;
			case Patients:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Patients>();
				iterator = 3;
				break;
			case Records:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Records>();
				iterator = 4;
				break;
			case Users:
				iter =  repos.get(iterator).findAllById(lookupId);
				itemList = new ArrayList<Users>();
				iterator = 5;
				break;
				
		}
		
		for (Object item: iter) {
			  
			itemList.add(item);
			  
		}
		  
		Object[] objectArray = new Object[itemList.size()];
		  
		int counter = 0;
		  
		for (Object item: itemList) {
			  
			logger.info(item.toString());
			  
			objectArray[counter] = item;
			  
			counter++;
			  
		}
		  
		return objectArray;
		
	}
	
	/*public T[] getArrayPatientsWithinRange (int startIndex, int returnedAmount) {
		
		
		//ArrayList<Integer> lookupId = new ArrayList<Integer>();
		  
		//lookupId.add(id);
		  
		//Iterable<T> iter =  repository.findAllById(lookupId);
		
		Pageable page = PageRequest.of(startIndex, returnedAmount);
		logger.info(repository.getClass().toString());
		ArrayList<T> recordsList =  (ArrayList<T>) ((PatientsRepository) repository).findFromTo(page);
		  
		@SuppressWarnings("unchecked")
		T[] records = (T[]) recordsList.toArray();
		  
		return records;
		
	}*/
	
}
