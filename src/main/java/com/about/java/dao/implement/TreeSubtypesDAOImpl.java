package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeSubtypesDAO;
import com.about.java.models.trees.TreeSubtypes;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreeSubtypesDAOImpl implements TreeSubtypesDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Long addTreeSubtype(TreeSubtypes treeSubtypes) {
        sessionFactory.getCurrentSession().save(treeSubtypes);
        return treeSubtypes.getId();
    }

    public Long updateTreeSubtype(TreeSubtypes treeSubtypes) {
        TreeSubtypes lastTreeTypes = (TreeSubtypes) sessionFactory.getCurrentSession().get(TreeSubtypes.class, treeSubtypes.getId());
        lastTreeTypes.setSubtype(treeSubtypes.getSubtype());
        lastTreeTypes.setHeight(treeSubtypes.getHeight());
        lastTreeTypes.setDescribe(treeSubtypes.getDescribe());
        sessionFactory.getCurrentSession().update(lastTreeTypes);
        return treeSubtypes.getId();
    }

    public TreeSubtypes getTreeSubtype(Long id) {
        return (TreeSubtypes) sessionFactory.getCurrentSession().get(TreeSubtypes.class, id);
    }

    public List<TreeSubtypes> getTreeSubtypes() {
        try{
            Criteria criteria = sessionFactory.
                    getCurrentSession().
                    createCriteria(TreeSubtypes.class);
            return criteria.list();
        } catch (HibernateException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deleteTreeSubtype(Long id) {
        TreeSubtypes treeTypes = (TreeSubtypes) sessionFactory.getCurrentSession().get(TreeSubtypes.class, id);

        if (treeTypes != null){
            sessionFactory.getCurrentSession().delete(treeTypes);
        }
    }
}
