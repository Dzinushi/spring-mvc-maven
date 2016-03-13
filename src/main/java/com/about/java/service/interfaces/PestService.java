package com.about.java.service.interfaces;


import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PestService {
    Long add(List<PoisonPestDTO> poisonPestDTOs) throws ObjectAlreadyExistsException;
    Long update(List<PoisonPestDTO> poisonPestDTOs) throws NoSuchObjectException;
    List<PoisonPestDTO> get(Long id) throws NoSuchObjectException;
    List<PoisonPestDTO> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    PestDTO toPestDTO(Pest pest);
    Pest toPest(PestDTO pestDTO);
    Pest toPest(List<PoisonPestDTO> poisonPestDTOs);
    List<PoisonPestDTO> toPoisonPestDTOs(Pest pest) throws NoSuchObjectException;
    List<PoisonPestDTO> toPoisonPestDTOs(List<Pest> pests) throws NoSuchObjectException;
}