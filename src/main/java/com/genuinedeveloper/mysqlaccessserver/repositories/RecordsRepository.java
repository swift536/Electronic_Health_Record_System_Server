package com.genuinedeveloper.mysqlaccessserver.repositories;

import org.springframework.core.Ordered;
import org.springframework.data.repository.CrudRepository;
import com.genuinedeveloper.mysqlaccessserver.db_entities.Records;

public interface RecordsRepository extends CrudRepository<Records, Integer>,Ordered {

    @Override
    public default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+4;
    }
    
}