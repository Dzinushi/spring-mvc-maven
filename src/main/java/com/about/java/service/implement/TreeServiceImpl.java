package com.about.java.service.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.TreeDTO;
import com.about.java.models.Care;
import com.about.java.models.Poison;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.CareService;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private CareService careService;

    @Autowired
    private TreeDAO treeTypesDAO;

    @Autowired
    private PoisonService poisonService;

    public Tree toTree(TreeDTO treeDTO){
        Tree tree = new Tree();
        tree.setName(treeDTO.getName());
        tree.setHeight(treeDTO.getHeight());
        tree.setDescribe(treeDTO.getDescribe());
        Care care = new Care();
        care.setDescribe(treeDTO.getCare());
        tree.setCare(care);

        List<Poison> poisons = new ArrayList<Poison>();
        for (int i = 0; i < treeDTO.getPoisonDTOs().size(); i++) {
            Poison poison = poisonService.toPoison(treeDTO.getPoisonDTOs().get(i));
            poisons.add(poison);
        }
        tree.setPoisons(poisons);
        return tree;
    }

    @Transactional
    public long add(TreeDTO treeDTO) throws ObjectAlreadyExistsException {
        if (treeDTO == null){
            throw new NullPointerException();
        }

        try{
            Tree tree = toTree(treeDTO);
            return treeTypesDAO.addTree(tree);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public long update(TreeDTO treeDTO) throws NoSuchObjectException {
        if (treeDTO.getId() == 0){
            throw new NullPointerException();
        }
        try{
            Tree tree = toTree(treeDTO);
            return treeTypesDAO.updateTree(tree);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public TreeDTO get(long id) throws NoSuchObjectException{
        if (id == 0){
            throw new NullPointerException();
        }
        try{
            Tree tree = treeTypesDAO.getTree(id);
            TreeDTO treeDTO = new TreeDTO();
            treeDTO.setId(tree.getId());
            treeDTO.setName(tree.getName());
            treeDTO.setHeight(tree.getHeight());
            treeDTO.setDescribe(tree.getDescribe());
            treeDTO.setCare(tree.getCare().getDescribe());

            List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
            for (Poison poison : tree.getPoisons()) {
                poisonDTOs.add(poisonService.get(poison.getId()));
            }
            treeDTO.setPoisonDTOs(poisonDTOs);

            return treeDTO;
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreeDTO> get() throws NoSuchObjectException {
        try {
            List<Tree> trees =  treeTypesDAO.getTree();
            List<TreeDTO> treeDTOs = new ArrayList<TreeDTO>();
            for (Tree tree : trees) {
                TreeDTO treeDTO = new TreeDTO();
                treeDTO.setId(tree.getId());
                treeDTO.setName(tree.getName());
                treeDTO.setHeight(tree.getHeight());
                treeDTO.setDescribe(tree.getDescribe());
                treeDTO.setCare(tree.getCare().getDescribe());

                List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
                for (Poison poison : tree.getPoisons()) {
                    poisonDTOs.add(poisonService.get(poison.getId()));
                }
                treeDTO.setPoisonDTOs(poisonDTOs);
                treeDTOs.add(treeDTO);
            }
            return treeDTOs;
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public void delete(long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        try{
            careService.delete(treeTypesDAO.getTree(id).getCare().getId());
            treeTypesDAO.deleteTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
