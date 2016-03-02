package com.about.java.dao.implement;

import com.about.java.dao.interfaces.CareDAO;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CareDAOImpl implements CareDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public long addCare(Care care) {
        if (care == null){
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(care);
        return care.getId();
    }

    public long updateCare(Care care) throws NoSuchObjectException {
        if (care == null || care.getId() == 0){
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().update(care);
        return care.getId();
    }

    public Care getCare(long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return (Care) sessionFactory.getCurrentSession().get(Care.class, id);
    }

    public List<Care> getCare() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Care");
        return query.list();
    }

    public void delete(long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Care foundTree = (Care) sessionFactory.getCurrentSession().get(Care.class, id);
        if (foundTree == null){
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(foundTree);
    }
}
