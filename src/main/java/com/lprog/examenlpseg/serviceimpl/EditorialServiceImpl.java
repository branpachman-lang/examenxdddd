package com.lprog.examenlpseg.serviceimpl;

import com.lprog.examenlpseg.model.Editorial;
import com.lprog.examenlpseg.repository.EditorialRepository;
import com.lprog.examenlpseg.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EditorialServiceImpl implements EditorialService {
    @Autowired
    private EditorialRepository editorialRepository;
    @Override
    public Editorial create(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Editorial update(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Optional<Editorial> read(Long aLong) {
        return editorialRepository.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        editorialRepository.deleteById(aLong);
    }

    @Override
    public Iterable<Editorial> readAll() {
        return editorialRepository.findAll();
    }
}
