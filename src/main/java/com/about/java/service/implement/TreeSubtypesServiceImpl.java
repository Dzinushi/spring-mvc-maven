package com.about.java.service.implement;

import com.about.java.dao.interfaces.TreeSubtypesDAO;
import com.about.java.models.trees.TreeSubtypes;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.TreeSubtypesService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreeSubtypesServiceImpl implements TreeSubtypesService{

    @Autowired
    private TreeSubtypesDAO treeSubtypesDAO;

    @Transactional
    public Long add(TreeSubtypes treeSubtypes) throws ObjectAlreadyExistsException {
        if (treeSubtypes == null){
            throw new NullPointerException();
        }

        try{
            return treeSubtypesDAO.addTreeSubtype(treeSubtypes);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long updateTree(TreeSubtypes treeSubtypes) {
        if (treeSubtypes.getId() == null){
            throw new NullPointerException();
        }
        return treeSubtypesDAO.updateTreeSubtype(treeSubtypes);
    }

    public TreeSubtypes getTree(Long id) throws NoSuchObjectException {
        if (id == null){
            throw new NullPointerException();
        }

        try{
            return treeSubtypesDAO.getTreeSubtype(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<TreeSubtypes> getAllTree() {
        return treeSubtypesDAO.getTreeSubtypes();
    }

    @Transactional
    public void deleteInvitation(Long id) throws NoSuchObjectException {
        if (id == null){
            throw new NullPointerException();
        }

        try{
            treeSubtypesDAO.deleteTreeSubtype(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}