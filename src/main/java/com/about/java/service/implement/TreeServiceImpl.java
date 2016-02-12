package com.about.java.service.implement;


import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.TreeService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeDAO treeTypesDAO;

    public Long addTreeFamily(Tree treeFamily) {
        return null;
    }

    public Long updateTreeFamily(Tree treeFamily) {
        return null;
    }

    public Long deleteTreeFamily(Long id) {
        return null;
    }

    @Transactional
    public Long add(Tree tree) throws ObjectAlreadyExistsException {
        if (tree == null){
            throw new NullPointerException();
        }

        try{
            return treeTypesDAO.addTree(tree);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(Tree tree) throws NoSuchObjectException {
        if (tree.getId() == null){
            throw new NullPointerException();
        }
        try{
            return treeTypesDAO.updateTree(tree);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public Tree get(Tree tree) throws NoSuchObjectException{
        if (id == null){
            throw new NullPointerException();
        }

        try{
            return treeTypesDAO.getTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<Tree> get() throws NoSuchObjectException {
        try {
            return treeTypesDAO.getTree();
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public void delete(Tree tree) throws NoSuchObjectException {
        if (idTree == null){
            throw new NullPointerException();
        }

        try{
            treeTypesDAO.deleteTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
