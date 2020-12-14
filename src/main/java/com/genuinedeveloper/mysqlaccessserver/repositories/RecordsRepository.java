package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;

public interface RecordsRepository extends CrudRepository<Records, Integer>,Ordered {

	@Query("select a from Records a where a.id = :id")
	Iterable<Records> findAllByPatientId(@Param("id") Integer id);
	
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+4;
    }
    
}