package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.models.Tree;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreeDAOImpl implements TreeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Long addTree(Tree tree) {
        sessionFactory.getCurrentSession().save(tree);
        return tree.getId();
    }

    public Long updateTree(Long id, String type) {
        Tree lastTree = (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
        lastTree.setType(type);
        sessionFactory.getCurrentSession().update(lastTree);
        return id;
    }

    public Tree getTree(Long id) {
        return (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);
    }

    public List<Tree> getTrees() {
        try{
            Criteria criteria = sessionFactory.
                    getCurrentSession().
                    createCriteria(Tree.class);
            return criteria.list();
        } catch (HibernateException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deleteTree(Long id) {
        Tree tree = (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);

        if (tree != null){
            sessionFactory.getCurrentSession().delete(tree);
        }
    }
}
