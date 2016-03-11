package com.about.java.service.implement;

import com.about.java.dao.interfaces.PestDAO;
import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
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
    public Long add(PestDTO pestDTO) throws ObjectAlreadyExistsException {
        if (pestDTO == null){
            throw new NullPointerException();
        }

        try{
            // проверка на уникальность
            if (pestDAO.find(pestDTO.getName())){
                return null;
            }
            else {
                Pest pest = toPest(pestDTO);
                return pestDAO.addPest(pest);
            }
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(PestDTO pestDTO) throws NoSuchObjectException {
        if (pestDTO == null){
            throw new NullPointerException();
        }

        try{
            Pest pest = toPest(pestDTO);
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
            return toPestDTO(pestDAO.getPest(id));

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
                PestDTO pestDTO = toPestDTO(pest);
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

    public PestDTO toPestDTO(Pest pest) {
        PestDTO pestDTO = new PestDTO();
        pestDTO.setId(pest.getId());
        pestDTO.setName(pest.getName());

        Set<Poison> poisons = pest.getPoisons();
        if (poisons != null){
            List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
            for(Poison poison : poisons){
                try {
                    PoisonDTO poisonDTO = poisonService.toPoisonDTO(poison);
                    poisonDTOs.add(poisonDTO);
                } catch (NoSuchObjectException e) {
                    e.printStackTrace();
                }
            }
            pestDTO.setPoisonDTOs(poisonDTOs);
        }

        return pestDTO;
    }

    public Pest toPest(PestDTO pestDTO) {
        Pest pest = new Pest();
        pest.setId(pestDTO.getId());
        pest.setName(pestDTO.getName());
        List<PoisonDTO> poisonDTOs = pestDTO.getPoisonDTOs();

        if (pestDTO.getPoisonDTOs() != null){
            Set<Poison> poisons = new HashSet<Poison>();
            for (PoisonDTO poisonDTO : poisonDTOs) {
                Poison poison = poisonService.toPoison(poisonDTO);
                poisons.add(poison);
            }
            pest.setPoisons(poisons);
        }

        return pest;
    }
}
