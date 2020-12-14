package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;

public interface AllergiesRepository extends CrudRepository<Allergies, Integer>, Ordered {

	@Query("select a from Allergies a where a.id = :id")
	Iterable<Allergies> findAllByPatientId(@Param("id") Integer id);
	
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}