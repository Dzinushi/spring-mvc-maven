package com.about.java.dao.implement;

import com.about.java.dao.interfaces.CareDAO;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CareDAOImpl implements CareDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Long addCare(Care care) {
        if (care == null){
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(care);
        return care.getId();
    }

    public Long updateCare(Care care) throws NoSuchObjectException {
        if (care == null || care.getId() == 0){
            throw new NullPointerException();
        }
        Care foundCare = (Care) sessionFactory.getCurrentSession().get(Care.class, care.getId());
        if (foundCare != null){
            foundCare.copy(care);
            sessionFactory.getCurrentSession().update(foundCare);
            return foundCare.getId();
        }
        else {
            throw new NoSuchObjectException();
        }
    }

    public Care getCare(Long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return (Care) sessionFactory.getCurrentSession().get(Care.class, id);
    }

    public List<Care> getCare() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Care");
        return query.list();
    }

    public void delete(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Care foundTree = (Care) sessionFactory.getCurrentSession().get(Care.class, id);
        if (foundTree == null){
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(foundTree);
    }

    public boolean find(String describe) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Care where describe=:describe");
        query.setParameter("describe", describe);
        return query.list().size() > 0;
    }
}
