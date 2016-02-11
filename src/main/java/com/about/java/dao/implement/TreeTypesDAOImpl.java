package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeTypesDAO;
import com.about.java.models.trees.TreeTypes;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreeTypesDAOImpl implements TreeTypesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Long addTreeType(TreeTypes treeTypes) {
        sessionFactory.getCurrentSession().save(treeTypes);
        return treeTypes.getId();
    }

    public Long updateTreeType(Long id, String type) {
        TreeTypes lastTreeTypes = (TreeTypes) sessionFactory.getCurrentSession().get(TreeTypes.class, id);
        lastTreeTypes.setType(type);
        sessionFactory.getCurrentSession().update(lastTreeTypes);
        return id;
    }

    public TreeTypes getTreeType(Long id) {
        return (TreeTypes) sessionFactory.getCurrentSession().get(TreeTypes.class, id);
    }

    public List<TreeTypes> getTreeTypes() {
        try{
            Criteria criteria = sessionFactory.
                    getCurrentSession().
                    createCriteria(TreeTypes.class);
            return criteria.list();
        } catch (HibernateException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deleteTreeType(Long id) {
        TreeTypes treeTypes = (TreeTypes) sessionFactory.getCurrentSession().get(TreeTypes.class, id);

        if (treeTypes != null){
            sessionFactory.getCurrentSession().delete(treeTypes);
        }
    }
}
