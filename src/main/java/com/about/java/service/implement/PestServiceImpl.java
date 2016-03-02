package com.about.java.service.implement;

import com.about.java.dao.interfaces.PestDAO;
import com.about.java.dto.PestDTO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PestServiceImpl implements PestService{

    @Autowired
    private PestDAO pestDAO;

    @Transactional
    public Long add(Pest pest) throws ObjectAlreadyExistsException {
        if (pest == null){
            throw new NullPointerException();
        }

        try{
            // проверка на уникальность
            if (pestDAO.find(pest.getName())){
                return null;
            }
            else {
                return pestDAO.addPest(pest);
            }
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(Pest pest) throws NoSuchObjectException {
        if (pest == null){
            throw new NullPointerException();
        }

        try{
            return pestDAO.updatePest(pest);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public PestDTO get(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            Pest pest = pestDAO.getPest(id);
            PestDTO pestDTO = new PestDTO();
            pestDTO.setId(pest.getId());
            pestDTO.setName(pest.getName());
            return pestDTO;

        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PestDTO> get() throws NoSuchObjectException {
        try {
            List<Pest> pests = pestDAO.getPest();
            List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
            for (Pest pest : pests){
                PestDTO pestDTO = new PestDTO();
                pestDTO.setId(pest.getId());
                pestDTO.setName(pest.getName());
                pestDTOs.add(pestDTO);
            }
            return pestDTOs;
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public void delete(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            pestDAO.deletePest(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
