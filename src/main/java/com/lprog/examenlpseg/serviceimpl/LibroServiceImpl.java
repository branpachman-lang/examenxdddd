package com.lprog.examenlpseg.serviceimpl;

import com.lprog.examenlpseg.model.Libro;
import com.lprog.examenlpseg.repository.LibroRepository;
import com.lprog.examenlpseg.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Override
    public Libro create(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> read(Long aLong) {
        return libroRepository.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        libroRepository.deleteById(aLong);
    }

    @Override
    public Iterable<Libro> readAll() {
        return libroRepository.findAll();
    }
}
