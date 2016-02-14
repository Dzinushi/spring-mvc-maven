package com.about.java.dao.interfaces;


import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface CareDAO {
    long addCare(Care care);
    long updateCare(Care care) throws NoSuchObjectException;
    Care getCare(long id);
    List<Care> getCare();
    void delete(long id) throws NoSuchObjectException;
}
