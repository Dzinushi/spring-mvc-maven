package com.about.java.dao.interfaces;


import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface CareDAO {
    Long addCare(Care care);
    Long updateCare(Care care) throws NoSuchObjectException;
    Care getCare(Long id);
    List<Care> getCare();
    void delete(Long id) throws NoSuchObjectException;
    boolean find(String describe);
}
