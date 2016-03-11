package com.about.java.service.interfaces;

import com.about.java.dto.CareDTO;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface CareService {
    Long add(CareDTO care) throws ObjectAlreadyExistsException;
    Long update(CareDTO care) throws NoSuchObjectException;
    CareDTO getCare(Long id) throws NoSuchObjectException;
    List<CareDTO> getCare() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    Care toCare(CareDTO careDTO);
    CareDTO toCareDTO(Care care);
}
