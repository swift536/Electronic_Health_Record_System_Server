package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Conditions;

public interface ConditionsRepository extends CrudRepository<Conditions, Integer>, Ordered {

	@Query("select a from Conditions a where a.id = :id")
	Iterable<Conditions> findAllByPatientId(@Param("id") Integer id);
	
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+1;
    }
}