package com.about.java.dao.interfaces;


import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface PestDAO {
    Long addPest(Pest pest);
    Long updatePest(Pest pest) throws NoSuchObjectException;
    Pest getPest(Long id);
    Pest getPest(String name);
    List<Pest> getPest();
    void deletePest(Long id) throws NoSuchObjectException;
    boolean find(String name);

}
