package com.about.java.service.implement;

import com.about.java.dao.interfaces.PoisonDAO;
import com.about.java.dto.*;
import com.about.java.models.Care;
import com.about.java.models.Pest;
import com.about.java.models.Poison;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
import com.about.java.util.Util;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class PoisonServiceImpl implements PoisonService{

    @Autowired
    private TreeService treeService;

    @Autowired
    private PoisonDAO poisonDAO;

    @Autowired
    private PestService pestService;

    @Transactional
    public Long add(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs) throws ObjectAlreadyExistsException {
        if (treePoisonDTOs == null){
            throw new NullPointerException();
        }
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs){
            if (poisonDAO.find(treePoisonDTO.getPoisonDTO().getName())){
                return null;
            }
        }

        try {
            Poison poison = toPoison(treePoisonDTOs, poisonPestDTOs);
            return poisonDAO.addPoison(poison);
        } catch (HibernateException e) {
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs) throws NoSuchObjectException {
        if (treePoisonDTOs == null){
            throw new NullPointerException();
        }
        try {
            Poison poison = toPoison(treePoisonDTOs, poisonPestDTOs);
            return poisonDAO.updatePoison(poison);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreePoisonDTO> getTreePoisons(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            Poison poison = poisonDAO.getPoison(id);
            return toTreePoisonDTO(poison);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreePoisonDTO> getTreePoisons() throws NoSuchObjectException {
        try {
            List<Poison> poisons =  poisonDAO.getPoison();
            return toTreePoisonDTO(poisons);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PoisonPestDTO> getPoisonPests(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            Poison poison = poisonDAO.getPoison(id);
            return toPoisonPestDTO(poison);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<PoisonPestDTO> getPoisonPests() throws NoSuchObjectException {
        try {
            List<Poison> poisons =  poisonDAO.getPoison();
            return toPoisonPestDTO(poisons);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public PoisonDTO getByID(Long id) throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = getPoisonPests(id);
        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        PoisonDTO poisonDTO = poisonPestDTOs.get(0).getPoisonDTO();

        for (PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
            PestDTO pestDTO = poisonPestDTO.getPestDTO();
            pestDTOs.add(pestDTO);

            poisonDTO.setPestDTOs(pestDTOs);
        }

        return poisonDTO;
    }

    @Transactional
    public PoisonDTO getByName(String name) {
        PoisonDTO poisonDTO = null;
        if (name != null) {
            Poison poison = poisonDAO.getPoison(name);
            try {
                poisonDTO = toPoisonDTO(poison);
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
            }
        }

        return poisonDTO;
    }

    @Transactional
    public List<PoisonDTO> getAll() throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = getPoisonPests();
        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();

        Set<Long> idPoisons = new HashSet<Long>();
        Map<Long, Integer> indexPoisons = new HashMap<Long, Integer>();
        for (PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
            PoisonDTO poisonDTO = poisonPestDTO.getPoisonDTO();
            int index = Util.find(idPoisons, poisonDTO.getId());
            idPoisons.add(poisonDTO.getId());

            if (index == -1){
                List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
                pestDTOs.add(poisonPestDTO.getPestDTO());
                poisonDTO.setPestDTOs(pestDTOs);
                poisonDTOs.add(poisonDTO);
                indexPoisons.put(poisonDTO.getId(), indexPoisons.size());
            }
            else {
                List<PestDTO> pestDTOs = poisonDTOs.get(indexPoisons.get(poisonDTO.getId())).getPestDTOs();
                pestDTOs.add(poisonPestDTO.getPestDTO());
                poisonDTO.setPestDTOs(pestDTOs);
                poisonDTOs.set(index, poisonDTO);
            }
        }

        return poisonDTOs;
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

    public Poison toPoison(List<TreePoisonDTO> treePoisonDTOs, List<PoisonPestDTO> poisonPestDTOs) {

        if (treePoisonDTOs == null || poisonPestDTOs == null){
            throw new NullPointerException();
        }

        Set<Tree> trees = new HashSet<Tree>();
        Set<Pest> pests = new HashSet<Pest>();

        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs) {
            TreeDTO treeDTO = treePoisonDTO.getTreeDTO();
            if (treeDTO != null) {
                Tree tree = treeService.toTree(treeDTO);
                trees.add(tree);
            }
        }

        for (PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
            PestDTO pestDTO = poisonPestDTO.getPestDTO();
            if (pestDTO != null) {
                Pest pest = pestService.toPest(pestDTO);
                pests.add(pest);
            }
        }

        PoisonDTO poisonDTO;
        if (treePoisonDTOs.size() == 0){
            poisonDTO = poisonPestDTOs.get(0).getPoisonDTO();
        }
        else {
            poisonDTO = treePoisonDTOs.get(0).getPoisonDTO();
        }

        Poison poison = toPoison(poisonDTO);
        poison.setTrees(trees);
        poison.setPests(pests);

        return poison;
    }

    public Poison toPoison(PoisonDTO poisonDTO){
        Poison poison = new Poison();
        poison.setId(poisonDTO.getId());
        poison.setName(poisonDTO.getName());

        if (poisonDTO.getPestDTOs() != null){
            Set<Pest> pests = new HashSet<Pest>();
            for (PestDTO pestDTO : poisonDTO.getPestDTOs()){
                Pest pest = pestService.toPest(pestDTO);
                pests.add(pest);
            }
            poison.setPests(pests);
        }

        return poison;
    }

    public PoisonDTO toPoisonDTO(Poison poison) throws NoSuchObjectException {
        PoisonDTO poisonDTO = new PoisonDTO();
        poisonDTO.setId(poison.getId());
        poisonDTO.setName(poison.getName());
//                poisonDTO.setType(poison.getType());

        if (poison.getPests() != null){
            List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
            for (Pest pest : poison.getPests()) {
                PestDTO pestDTO = pestService.toPestDTO(pest);
                pestDTOs.add(pestDTO);
            }
            poisonDTO.setPestDTOs(pestDTOs);
        }

        return poisonDTO;
    }

    public List<TreePoisonDTO> toTreePoisonDTO(Poison poison) throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();

        if (poison.getTrees().size() == 0){
            TreePoisonDTO treePoisonDTO = new TreePoisonDTO();

            PoisonDTO poisonDTO = toPoisonDTO(poison);
            treePoisonDTO.setPoisonDTO(poisonDTO);

            treePoisonDTOs.add(treePoisonDTO);
        }
        else {
            for(Tree tree : poison.getTrees()){
                TreePoisonDTO treePoisonDTO = new TreePoisonDTO();

                TreeDTO treeDTO = treeService.toTreeDTO(tree);
                treePoisonDTO.setTreeDTO(treeDTO);

                PoisonDTO poisonDTO = toPoisonDTO(poison);
                treePoisonDTO.setPoisonDTO(poisonDTO);

                treePoisonDTOs.add(treePoisonDTO);
            }
        }

        return treePoisonDTOs;
    }

    public List<TreePoisonDTO> toTreePoisonDTO(List<Poison> poisons) throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();

        for(Poison poison : poisons){
            List<TreePoisonDTO> partTreePoisonDTOs = toTreePoisonDTO(poison);

            for (TreePoisonDTO partTreePoisonDTO : partTreePoisonDTOs) {
                treePoisonDTOs.add(partTreePoisonDTO);
            }
        }
        return treePoisonDTOs;
    }

    public List<PoisonPestDTO> toPoisonPestDTO(Poison poison) throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();

        if (poison.getPests().size() == 0){
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();

            PoisonDTO poisonDTO = toPoisonDTO(poison);
            poisonPestDTO.setPoisonDTO(poisonDTO);

            poisonPestDTOs.add(poisonPestDTO);
        }
        else {
            for(Pest pest : poison.getPests()){
                PoisonPestDTO poisonPestDTO = new PoisonPestDTO();

                PoisonDTO poisonDTO = toPoisonDTO(poison);
                poisonPestDTO.setPoisonDTO(poisonDTO);

                PestDTO pestDTO = pestService.toPestDTO(pest);
                poisonPestDTO.setPestDTO(pestDTO);

                poisonPestDTOs.add(poisonPestDTO);
            }
        }

        return poisonPestDTOs;
    }

    public List<PoisonPestDTO> toPoisonPestDTO(List<Poison> poisons) throws NoSuchObjectException {
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();

        for(Poison poison : poisons){
            List<PoisonPestDTO> partPoisonPestDTOs = toPoisonPestDTO(poison);

            for (PoisonPestDTO partPoisonPestDTO : partPoisonPestDTOs) {
                poisonPestDTOs.add(partPoisonPestDTO);
            }
        }
        return poisonPestDTOs;
    }
}
