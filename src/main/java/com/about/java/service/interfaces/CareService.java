package com.about.java.service.interfaces;

import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface CareService {
    long add(Care care) throws ObjectAlreadyExistsException;
    long update(Care care) throws NoSuchObjectException;
    Care getCare(long id) throws NoSuchObjectException;
    List<Care> getCare() throws NoSuchObjectException;
    void delete(long id) throws NoSuchObjectException;
}
