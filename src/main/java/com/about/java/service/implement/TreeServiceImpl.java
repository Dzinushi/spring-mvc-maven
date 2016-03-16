package com.about.java.service.implement;

import com.about.java.dao.interfaces.CareDAO;
import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.dto.*;
import com.about.java.models.Care;
import com.about.java.models.Poison;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
import com.about.java.util.Util;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeDAO treeTypesDAO;

    @Autowired
    private CareDAO careDAO;

    @Autowired
    private PoisonService poisonService;

    @Transactional
    public Long add(List<TreePoisonDTO> treePoisonDTOs) throws ObjectAlreadyExistsException {
        if (treePoisonDTOs == null){
            throw new NullPointerException();
        }
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs){
            if (treeTypesDAO.find(treePoisonDTO.getTreeDTO().getName())){
                return null;
            }
        }

        try {
            Tree tree = toTree(treePoisonDTOs);
            careDAO.addCare(tree.getCare());
            return treeTypesDAO.addTree(tree);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(List<TreePoisonDTO> treePoisonDTOs) throws NoSuchObjectException {
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs){
            if (treePoisonDTO.getTreeDTO().getId() == 0){
                throw new NullPointerException();
            }
        }

        try{
            Tree tree = toTree(treePoisonDTOs);
            careDAO.updateCare(tree.getCare());
            return treeTypesDAO.updateTree(tree);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreePoisonDTO> get(Long id) throws NoSuchObjectException{
        if (id == 0){
            throw new NullPointerException();
        }
        try{
            Tree tree = treeTypesDAO.getTree(id);
            return toTreePoisonDTO(tree);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreePoisonDTO> get() throws NoSuchObjectException {
        try {
            List<Tree> trees = treeTypesDAO.getTree();
            return toTreePoisonDTO(trees);

        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public TreeDTO getByID(Long id) throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = get(id);
        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();

        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs) {
            PoisonDTO poisonDTO = treePoisonDTO.getPoisonDTO();

            List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
            List<PoisonPestDTO> poisonPestDTOs = poisonService.getPoisonPests(poisonDTO.getId());
            for (PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
                PestDTO pestDTO = poisonPestDTO.getPestDTO();
                pestDTOs.add(pestDTO);
            }
            poisonDTO.setPestDTOs(pestDTOs);

            poisonDTOs.add(poisonDTO);
        }

        TreeDTO treeDTO = treePoisonDTOs.get(0).getTreeDTO();
        treeDTO.setPoisonDTOs(poisonDTOs);

        return treeDTO;
    }

    @Transactional
    public List<TreeDTO> getAll() throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = get();
        List<TreeDTO> treeDTOs = new ArrayList<TreeDTO>();

        Set<Long> idTrees = new HashSet<Long>();
        Map<Long, Integer> indexTrees = new HashMap<Long, Integer>();
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs){
            TreeDTO treeDTO = treePoisonDTO.getTreeDTO();
            int index = Util.find(idTrees, treeDTO.getId());
            idTrees.add(treeDTO.getId());

            PoisonDTO poisonDTO = treePoisonDTO.getPoisonDTO();
            List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
            List<PoisonPestDTO> poisonPestDTOs = poisonService.getPoisonPests(poisonDTO.getId());
            for (PoisonPestDTO poisonPestDTO : poisonPestDTOs) {
                PestDTO pestDTO = poisonPestDTO.getPestDTO();
                pestDTOs.add(pestDTO);
            }
            poisonDTO.setPestDTOs(pestDTOs);

            if (index == -1){
                List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
                poisonDTOs.add(poisonDTO);
                treeDTO.setPoisonDTOs(poisonDTOs);
                treeDTOs.add(treeDTO);
                indexTrees.put(treeDTO.getId(), indexTrees.size());
            }
            else {
                TreeDTO treeDTO1 = treeDTOs.get(indexTrees.get(treeDTO.getId()));
                List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
                poisonDTOs.add(poisonDTO);
                treeDTO1.setPoisonDTOs(poisonDTOs);
                treeDTOs.set(index, treeDTO1);
            }
        }

        return treeDTOs;
    }

    @Transactional
    public void delete(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        try{
            Tree tree = treeTypesDAO.getTree(id);
            careDAO.delete(tree.getCare().getId());
            treeTypesDAO.deleteTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    public TreeDTO toTreeDTO(Tree tree) {
        TreeDTO treeDTO = new TreeDTO();
        treeDTO.setId(tree.getId());
        treeDTO.setName(tree.getName());
        treeDTO.setHeight(tree.getHeight());

        CareDTO careDTO = new CareDTO();
        careDTO.setId(tree.getCare().getId());
        treeDTO.setCareDTO(careDTO);

        return treeDTO;
    }

    public Tree toTree(TreeDTO treeDTO) {
        Tree tree = new Tree();
        tree.setId(treeDTO.getId());
        tree.setName(treeDTO.getName());
        tree.setHeight(treeDTO.getHeight());
//        tree.setDescribe(treeDTO.getDescribe());

        Care care = new Care();
        care.setId(treeDTO.getCareDTO().getId());
        care.setTree(tree);
//        care.setDescribe(treeDTO.getCareDTO().getDescribe());
        tree.setCare(care);

        return tree;
    }

    public Tree toTree(List<TreePoisonDTO> treePoisonDTOs){

        if (treePoisonDTOs == null){
            throw new NullPointerException();
        }

        Set<Poison> poisons = new HashSet<Poison>();
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs) {
            PoisonDTO poisonDTO = treePoisonDTO.getPoisonDTO();
            if (poisonDTO != null){
                Poison poison = poisonService.toPoison(poisonDTO);
                poisons.add(poison);
            }
        }

        TreeDTO treeDTO = treePoisonDTOs.get(0).getTreeDTO();
        Tree tree = toTree(treeDTO);
        tree.setPoisons(poisons);
        return tree;
    }

    public List<TreePoisonDTO> toTreePoisonDTO(Tree tree) throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();

        if (tree.getPoisons().size() == 0){
            TreePoisonDTO treePoisonDTO = new TreePoisonDTO();
            TreeDTO treeDTO = toTreeDTO(tree);
            treePoisonDTO.setTreeDTO(treeDTO);
            treePoisonDTOs.add(treePoisonDTO);
        }
        else {
            for (Poison poison : tree.getPoisons()) {
                TreePoisonDTO treePoisonDTO = new TreePoisonDTO();
                treePoisonDTO.setTreeDTO(toTreeDTO(tree));
                treePoisonDTO.setPoisonDTO(poisonService.toPoisonDTO(poison));

                TreeDTO treeDTO = toTreeDTO(tree);
                treePoisonDTO.setTreeDTO(treeDTO);

                treePoisonDTOs.add(treePoisonDTO);
            }
        }
        return treePoisonDTOs;
    }

    public List<TreePoisonDTO> toTreePoisonDTO(List<Tree> trees) throws NoSuchObjectException {
        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();
        for (Tree tree : trees) {
            List<TreePoisonDTO> partTreePoisonDTOs = toTreePoisonDTO(tree);

            for (TreePoisonDTO partTreePoisonDTO : partTreePoisonDTOs) {
                treePoisonDTOs.add(partTreePoisonDTO);
            }
        }

        return treePoisonDTOs;
    }
}
