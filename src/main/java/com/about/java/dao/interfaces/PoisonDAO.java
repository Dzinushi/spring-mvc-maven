package com.about.java.dao.interfaces;


import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface PoisonDAO {
    Long addPoison(Poison poison);
    Long updatePoison(Poison poison) throws NoSuchObjectException;
    Poison getPoison(Long id);
    List<Poison> getPoison();
    void deletePoison(Long id) throws NoSuchObjectException;
    boolean find(String name);
}
