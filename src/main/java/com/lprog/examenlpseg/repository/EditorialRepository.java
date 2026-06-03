package com.lprog.examenlpseg.repository;

import com.lprog.examenlpseg.model.Editorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends CrudRepository<Editorial,Long> {
}
