package com.genuinedeveloper.mysqlaccessserver.repositories;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Patients;

// This will be AUTO IMPLEMENTED by Spring into a Bean called patientRepository
// CRUD refers Create, Read, Update, Delete

public interface PatientsRepository extends CrudRepository<Patients, Integer>,Ordered {

	@Query ("SELECT p FROM Patients p ORDER BY name_last ASC")
	public List<Patients> findFromTo (Pageable pageable);
	//public Iterable<Patients> findFromTo (@Param("startindex") int startIndex, @Param("returnamount") int returnAmount);
	
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+3;
    }
}