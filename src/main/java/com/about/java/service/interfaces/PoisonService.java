package com.about.java.service.interfaces;

import com.about.java.dto.PoisonDTO;
import com.about.java.dto.TreeDTO;
import com.about.java.models.Poison;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PoisonService {
    Long add(PoisonDTO poison) throws ObjectAlreadyExistsException;
    Long update(PoisonDTO poison) throws NoSuchObjectException;
    PoisonDTO get(Long id) throws NoSuchObjectException;
    List<PoisonDTO> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    Poison toPoison(PoisonDTO poisonDTO);
    PoisonDTO toPoisonDTO(Poison poison) throws NoSuchObjectException;
}