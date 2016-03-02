package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TreeDAOImpl implements TreeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public long addTree(Tree tree) {
        if (tree == null){
            throw new NullPointerException();
        }

        sessionFactory.getCurrentSession().save(tree);
        return tree.getId();
    }

    public long updateTree(Tree tree) throws NoSuchObjectException{
        if (tree == null || tree.getId() == 0){
            throw new NullPointerException();
        }

        sessionFactory.getCurrentSession().update(tree);
        return tree.getId();
    }

    public Tree getTree(long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return  (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
    }

    public List<Tree> getTree() {
        Query query = sessionFactory.getCurrentSession().createQuery("From Tree");
        return query.list();
    }

    public void deleteTree(long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Tree foundTree = (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
        if (foundTree == null || foundTree.getId() == 0){
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(foundTree);
    }
}