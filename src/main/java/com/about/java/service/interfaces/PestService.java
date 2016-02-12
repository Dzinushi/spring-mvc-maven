package com.about.java.service.interfaces;


import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PestService {
    Long add(Pest pest) throws ObjectAlreadyExistsException;
    Long update(Pest pest) throws NoSuchObjectException;
    Pest get(Long id) throws NoSuchObjectException;
    List<Pest> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
}
