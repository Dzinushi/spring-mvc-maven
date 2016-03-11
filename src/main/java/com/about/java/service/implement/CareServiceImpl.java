package com.about.java.service.implement;

import com.about.java.dao.implement.CareDAOImpl;
import com.about.java.dto.CareDTO;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.CareService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareServiceImpl implements CareService{

    @Autowired
    private CareDAOImpl careDAO;

    @Transactional
    public Long add(CareDTO careDTO) throws ObjectAlreadyExistsException {
        if (careDTO == null){
            throw new NullPointerException();
        }
        try {
//            if (careDAO.find(careDTO.getDescribe())){
//                return null;
//            }
//            else {
                Care care = toCare(careDTO);
                return careDAO.addCare(care);
//            }
        } catch (Exception e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(CareDTO careDTO) throws NoSuchObjectException {
        Care care = toCare(careDTO);
        return careDAO.updateCare(care);
    }

    @Transactional
    public CareDTO getCare(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try{
            return toCareDTO(careDAO.getCare(id));
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<CareDTO> getCare() throws NoSuchObjectException {
        List<Care> cares = careDAO.getCare();
        List<CareDTO> careDTOs = new ArrayList<CareDTO>();
        for (Care care : cares) {
            CareDTO careDTO = toCareDTO(care);
            careDTOs.add(careDTO);
        }
        return careDTOs;
    }

    @Transactional
    public void delete(Long id) throws NoSuchObjectException {
        careDAO.delete(id);
    }

    public Care toCare(CareDTO careDTO) {
        Care care = new Care();
        care.setId(careDTO.getId());
//        care.setDescribe(careDTO.getDescribe());
        return care;
    }

    public CareDTO toCareDTO(Care care) {
        CareDTO careDTO = new CareDTO();
        careDTO.setId(care.getId());
//            careDTO.setDescribe(care.getDescribe());
        return careDTO;
    }
}