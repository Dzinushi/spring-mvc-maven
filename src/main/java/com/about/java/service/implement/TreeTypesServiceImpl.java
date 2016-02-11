package com.about.java.service.implement;


import com.about.java.dao.interfaces.TreeTypesDAO;
import com.about.java.models.trees.TreeTypes;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.TreeTypesService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreeTypesServiceImpl implements TreeTypesService {

    @Autowired
    private TreeTypesDAO treeTypesDAO;

    @Transactional
    public Long add(TreeTypes treeTypes) throws ObjectAlreadyExistsException {
        if (treeTypes == null){
            throw new NullPointerException();
        }

        try{
            return treeTypesDAO.addTreeType(treeTypes);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long updateTree(Long id, String type) {
        if (id == null | type == null){
            throw new NullPointerException();
        }
        return treeTypesDAO.updateTreeType(id, type);
    }

    public TreeTypes getTree(Long id) throws NoSuchObjectException{
        if (id == null){
            throw new NullPointerException();
        }

        try{
            return treeTypesDAO.getTreeType(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreeTypes> getAllTree() {
        return treeTypesDAO.getTreeTypes();
    }

    @Transactional
    public void deleteInvitation(Long id) throws NoSuchObjectException {
        if (id == null){
            throw new NullPointerException();
        }

        try{
            treeTypesDAO.deleteTreeType(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
