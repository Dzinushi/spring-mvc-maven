package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeDAO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TreeDAOImpl implements TreeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Long addTreeFamily(Tree treeFamily) {
        if (treeFamily == null || treeFamily.getId() == 0){
            throw new NullPointerException();
        }

        sessionFactory.getCurrentSession().save(treeFamily);
        return treeFamily.getId();
    }

    public Long updateTreeFamily(Tree treeFamily) {
        if (treeFamily == null || treeFamily.getId() == 0){
            throw new NullPointerException();
        }

        Tree updateTreeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, treeFamily.getId());
        updateTreeFamily.setParent(treeFamily.getParent());         // скорее всего лишнее
        updateTreeFamily.setChildrens(treeFamily.getChildrens());
        updateTreeFamily.setFamily(treeFamily.getFamily());

        sessionFactory.getCurrentSession().update(updateTreeFamily);
        return treeFamily.getId();
    }

    public void deleteTreeFamily(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Tree treeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, id);

        if (treeFamily == null) {
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(treeFamily);
    }

    public Long addTree(Tree tree) {
        if (tree == null || tree.getId() == 0){
            throw new NullPointerException();
        }

        Tree treeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, tree.getParent().getId());
        if (treeFamily == null){
            throw new NullPointerException();
        }

        tree.setParent(treeFamily);         // указываем семейство
        treeFamily.getChildrens().add(tree);// добавляем в семейство к уже имеющимся видам новый
        sessionFactory.getCurrentSession().update(treeFamily);
        return tree.getId();
    }

    // Костылизм
    public Long updateTree(Tree tree) {
        if (tree == null || tree.getParent().getId() == 0 || tree.getId() == 0){
            throw new NullPointerException();
        }

        Tree treeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, tree.getParent().getId());
        if (treeFamily == null){
            throw new NullPointerException();
        }

        int treeCount = treeFamily.getChildrens().size();
        boolean repeat = true;
        for (int i = 0; i < treeCount || repeat; ++i){
            if (Objects.equals(treeFamily.getChildrens().get(i).getId(), tree.getId())){
                treeFamily.getChildrens().set(i, tree);
                repeat = false;
            }
        }

        sessionFactory.getCurrentSession().update(treeFamily);
        return tree.getId();
    }

    // Костылизм
    public Tree getTree(Tree tree) {
        if (tree.getId() == 0){
            throw new NullPointerException();
        }
        Tree treeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, tree.getParent().getId());

        int treeCount = treeFamily.getChildrens().size();
        for (int i = 0; i < treeCount; ++i){
            if (Objects.equals(treeFamily.getChildrens().get(i).getId(), tree.getId())){
                return treeFamily.getChildrens().get(i);
            }
        }

        return null;
    }

    public List<Tree> getTree() {
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

    // Костылизм
    public void deleteTree(Tree tree) throws NoSuchObjectException {
        if (tree.getId() == 0){
            throw new NullPointerException();
        }

        Tree treeFamily = (Tree) sessionFactory.getCurrentSession().get(Tree.class, tree.getParent().getId());

        int treeCount = treeFamily.getChildrens().size();
        for (int i = 0; i < treeCount; ++i){
            if (Objects.equals(treeFamily.getChildrens().get(i).getId(), tree.getId())){
                treeFamily.getChildrens().remove(i);
                sessionFactory.getCurrentSession().update(treeFamily);
            }
        }
    }
}