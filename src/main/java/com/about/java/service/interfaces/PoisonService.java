package com.about.java.service.interfaces;

import com.about.java.dto.PoisonDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.dto.TreePoisonDTO;
import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface PoisonService {
    Long add(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs) throws ObjectAlreadyExistsException;
    Long update(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs) throws NoSuchObjectException;
    List<TreePoisonDTO> getTreePoisons(Long id) throws NoSuchObjectException;
    List<TreePoisonDTO> getTreePoisons() throws NoSuchObjectException;
    List<PoisonPestDTO> getPoisonPests(Long id) throws NoSuchObjectException;
    List<PoisonPestDTO> getPoisonPests() throws NoSuchObjectException;
    PoisonDTO getByID(Long id) throws NoSuchObjectException;
    List<PoisonDTO> getAll() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    Poison toPoison(PoisonDTO poisonDTO);
    Poison toPoison(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs);
    PoisonDTO toPoisonDTO(Poison poison) throws NoSuchObjectException;
    List<TreePoisonDTO> toTreePoisonDTO(Poison poison) throws NoSuchObjectException;
    List<TreePoisonDTO> toTreePoisonDTO(List<Poison> poisons) throws NoSuchObjectException;
    List<PoisonPestDTO> toPoisonPestDTO(Poison poison) throws NoSuchObjectException;
    List<PoisonPestDTO> toPoisonPestDTO(List<Poison> poisons) throws NoSuchObjectException;
}