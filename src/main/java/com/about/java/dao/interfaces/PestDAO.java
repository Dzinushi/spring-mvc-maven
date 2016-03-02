package com.about.java.dao.interfaces;


import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface PestDAO {
    Long addPest(Pest pest);
    Long updatePest(Pest pest);
    Pest getPest(Long id);
    List<Pest> getPest();
    void deletePest(Long id) throws NoSuchObjectException;
    boolean find(String name);
}
