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
public class TreeServiceImpl implements TreeService{

    @Autowired
    private TreeDAO treeDAO;

    public Long addTree(Tree tree) throws ObjectAlreadyExistsException {
        if (tree == null){
            throw new NullPointerException();
        }

        try{
            return treeDAO.addTree(tree);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    public Tree getTree(Long id) throws NoSuchObjectException{
        if (id == null){
            throw new NullPointerException();
        }

        try{
            return treeDAO.getTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<Tree> getAllTree() {
        return treeDAO.getTrees();
    }

    @Transactional
    public void deleteInvitation(Long id) throws NoSuchObjectException {
        if (id == null){
            throw new NullPointerException();
        }

        try{
            treeDAO.deleteTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
