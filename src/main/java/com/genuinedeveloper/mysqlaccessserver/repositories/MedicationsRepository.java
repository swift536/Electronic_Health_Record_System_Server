package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Medications;

public interface MedicationsRepository extends CrudRepository<Medications, Integer>,Ordered {

	@Query("select a from Medications a where a.id = :id")
	Iterable<Medications> findAllByPatientId(@Param("id") Integer id);
	
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+2;
    }
}