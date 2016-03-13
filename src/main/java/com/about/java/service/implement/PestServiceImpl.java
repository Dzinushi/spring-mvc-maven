package com.about.java.service.implement;

import com.about.java.dao.interfaces.PestDAO;
import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.models.Pest;
import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PestServiceImpl implements PestService{

    @Autowired
    private PestDAO pestDAO;

    @Autowired
    private PoisonService poisonService;

    @Transactional
    public Long add(List<PoisonPestDTO> poisonPestDTOs) throws ObjectAlreadyExistsException {
        if (poisonPestDTOs == null){
            throw new NullPointerException();
        }

        for(PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
            // проверка на уникальность
            if (pestDAO.find(poisonPestDTO.getPestDTO().getName())) {
                return null;
            }
        }
        try{
            Pest pest = toPest(poisonPestDTOs);
            return pestDAO.addPest(pest);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(List<PoisonPestDTO> poisonPestDTOs) throws NoSuchObjectException {
        if (poisonPestDTOs == null){
            throw new NullPointerException();
        }

        try{
            Pest pest = toPest(poisonPestDTOs);
            return pestDAO.updatePest(pest);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PoisonPestDTO> get(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            return toPoisonPestDTOs(pestDAO.getPest(id));
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PoisonPestDTO> get() throws NoSuchObjectException {
        try {
            return toPoisonPestDTOs(pestDAO.getPest());
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

    public PestDTO toPestDTO(Pest pest) {
        PestDTO pestDTO = new PestDTO();
        pestDTO.setId(pest.getId());
        pestDTO.setName(pest.getName());

        return pestDTO;
    }

    public Pest toPest(PestDTO pestDTO) {
        Pest pest = new Pest();
        pest.setId(pestDTO.getId());
        pest.setName(pestDTO.getName());

        return pest;
    }

    public Pest toPest(List<PoisonPestDTO> poisonPestDTOs) {
        if (poisonPestDTOs == null){
            throw new NullPointerException();
        }

        Set<Poison> poisons = new HashSet<Poison>();
        for (PoisonPestDTO poisonPestDTO : poisonPestDTOs){
            PoisonDTO poisonDTO = poisonPestDTO.getPoisonDTO();
            if (poisonDTO != null) {
                Poison poison = poisonService.toPoison(poisonDTO);
                poisons.add(poison);
            }
        }
        Pest pest = toPest(poisonPestDTOs.get(0).getPestDTO());
        pest.setPoisons(poisons);

        return pest;
    }

    public List<PoisonPestDTO> toPoisonPestDTOs(Pest pest) throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();

        if (pest.getPoisons().size() == 0){
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
            poisonPestDTO.setPestDTO(toPestDTO(pest));
            poisonPestDTOs.add(poisonPestDTO);
        }
        else {
            for (Poison poison : pest.getPoisons()) {
                PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
                poisonPestDTO.setPestDTO(toPestDTO(pest));
                poisonPestDTO.setPoisonDTO(poisonService.toPoisonDTO(poison));
                poisonPestDTOs.add(poisonPestDTO);
            }
        }
        return poisonPestDTOs;
    }

    public List<PoisonPestDTO> toPoisonPestDTOs(List<Pest> pests) throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();

        for (Pest pest : pests){
            List<PoisonPestDTO> partPoisonPestDTOs = toPoisonPestDTOs(pest);

            for (PoisonPestDTO poisonPestDTO : partPoisonPestDTOs){
                poisonPestDTOs.add(poisonPestDTO);
            }
        }

        return poisonPestDTOs;
    }
}