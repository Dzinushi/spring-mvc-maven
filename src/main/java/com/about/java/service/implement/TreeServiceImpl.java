package com.about.java.service.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.dto.TreeDTO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PoisonService;
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

    @Autowired
    private PoisonService poisonService;

    @Transactional
    public long add(Tree tree) throws ObjectAlreadyExistsException {
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
    public long update(Tree tree) throws NoSuchObjectException {
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
    public TreeDTO get(long id) throws NoSuchObjectException{
        if (id == 0){
            throw new NullPointerException();
        }

        try{
            return new TreeDTO(treeTypesDAO.getTree(id));
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public TreeDTO get() throws NoSuchObjectException {
        try {
            return new TreeDTO(treeTypesDAO.getTree());
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
            treeTypesDAO.deleteTree(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
