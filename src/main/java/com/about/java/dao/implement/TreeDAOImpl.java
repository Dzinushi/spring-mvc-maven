package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class TreeDAOImpl implements TreeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public TreeDAOImpl(BasicDataSource basicDataSource) {
    }

    public TreeDAOImpl() {
    }

    public Long addTree(Tree tree) {
        if (tree == null){
            throw new NullPointerException();
        }
        try{
            sessionFactory.getCurrentSession().save(tree);
        } catch (Exception e){
            e.printStackTrace();
        }
        return tree.getId();
    }

    public Long updateTree(Tree tree) throws NoSuchObjectException{
        if (tree == null || tree.getId() == 0){
            throw new NullPointerException();
        }

        Tree foundTree = (Tree) sessionFactory.getCurrentSession().get(Tree.class, tree.getId());
        if (foundTree != null){
            foundTree.copy(tree);
            sessionFactory.getCurrentSession().update(foundTree);
            return foundTree.getId();
        }
        else {
            throw new NoSuchObjectException();
        }
    }

    public Tree getTree(Long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return  (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
    }

    public List<Tree> getTree() {
        Query query = sessionFactory.getCurrentSession().createQuery("From Tree");
        return query.list();
    }

    public void deleteTree(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Tree foundTree = (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
        if (foundTree == null || foundTree.getId() == 0){
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(foundTree);
    }

    public boolean find(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Tree where name=:treeName");
        query.setParameter("treeName", name);
        return query.list().size() > 0;
    }
}