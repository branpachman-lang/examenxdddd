package com.lprog.examenlpseg.repository;

import com.lprog.examenlpseg.model.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends CrudRepository<Libro,Long> {
}
