package com.about.java.service.implement;

import com.about.java.dao.interfaces.PoisonDAO;
import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.TreeDTO;
import com.about.java.models.Pest;
import com.about.java.models.Poison;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoisonServiceImpl implements PoisonService{

    @Autowired
    private TreeService treeService;

    @Autowired
    private PoisonDAO poisonDAO;

    @Autowired
    private PestService pestService;

    @Transactional
    public Long add(PoisonDTO poisonDTO) throws ObjectAlreadyExistsException {
        if (poisonDTO == null){
            throw new NullPointerException();
        }
        try {
            if(poisonDAO.find(poisonDTO.getName())){
                return null;
            }
            else {
                Poison poison = toPoison(poisonDTO);
                return poisonDAO.addPoison(poison);
            }
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(PoisonDTO poisonDTO) throws NoSuchObjectException {
        if (poisonDTO == null){
            throw new NullPointerException();
        }
        try {
            Poison poison = toPoison(poisonDTO);
            return poisonDAO.updatePoison(poison);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public PoisonDTO get(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            Poison poison = poisonDAO.getPoison(id);
            PoisonDTO poisonDTO = new PoisonDTO();
            poisonDTO.setId(poison.getId());
            poisonDTO.setName(poison.getName());
            poisonDTO.setType(poison.getType());

            List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
            for (Pest pest : poison.getPests()){
                pestDTOs.add(pestService.get(pest.getId()));
            }
            poisonDTO.setPestDTOs(pestDTOs);
            return poisonDTO;

        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PoisonDTO> get() throws NoSuchObjectException {
        try {
            List<Poison> poisons =  poisonDAO.getPoison();
            List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
            for (Poison poison : poisons) {
                PoisonDTO poisonDTO = new PoisonDTO();
                poisonDTO.setId(poison.getId());
                poisonDTO.setName(poison.getName());
                poisonDTO.setType(poison.getType());

                List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
                for (Pest pest : poison.getPests()){
                    pestDTOs.add(pestService.get(pest.getId()));
                }
                poisonDTO.setPestDTOs(pestDTOs);
                poisonDTOs.add(poisonDTO);
            }
            return poisonDTOs;
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
            poisonDAO.deletePoison(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
    
    public Poison toPoison(PoisonDTO poisonDTO){
        Poison poison = new Poison();
        poison.setId(poisonDTO.getId());
        poison.setName(poisonDTO.getName());

        if (poisonDTO.getPestDTOs() != null){
            List<Pest> pests = new ArrayList<Pest>();
            for (int i = 0; i < poisonDTO.getPestDTOs().size(); i++) {
                PestDTO pestDTO = poisonDTO.getPestDTOs().get(i);
                Pest pest = new Pest();
                pest.setId(pestDTO.getId());
                pest.setName(pestDTO.getName());
                pests.add(pest);
            }
            poison.setPests(pests);
        }

        if (poisonDTO.getTreeDTOs() != null){
            List<Tree> trees = new ArrayList<Tree>();
            for (int i = 0; i < poisonDTO.getTreeDTOs().size(); i++) {
                TreeDTO treeDTO = poisonDTO.getTreeDTOs().get(i);
                Tree tree = treeService.toTree(treeDTO);
                trees.add(tree);
            }
            poison.setTrees(trees);
        }

        return poison;
    }
}
