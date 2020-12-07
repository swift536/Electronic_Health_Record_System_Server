package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.repository.CrudRepository;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Allergies;

public interface AllergiesRepository extends CrudRepository<Allergies, Integer>, Ordered {

	 
    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}