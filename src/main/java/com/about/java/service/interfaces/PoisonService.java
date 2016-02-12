package com.about.java.service.interfaces;


import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PoisonService {
    Long add(Poison poison) throws ObjectAlreadyExistsException;
    Long update(Poison poison) throws NoSuchObjectException;
    Poison get(Long id) throws NoSuchObjectException;
    List<Poison> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
}
