package com.about.java.service.interfaces;


import com.about.java.dto.PestDTO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PestService {
    Long add(PestDTO pest) throws ObjectAlreadyExistsException;
    Long update(PestDTO pest) throws NoSuchObjectException;
    PestDTO get(Long id) throws NoSuchObjectException;
    List<PestDTO> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
}