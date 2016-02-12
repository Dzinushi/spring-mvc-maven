package com.about.java.dao.implement;

import com.about.java.dao.interfaces.PestDAO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PestDAOImpl implements PestDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Long addPest(Pest pest) {
        if (pest == null || pest.getId() == 0){
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(pest);
        return pest.getId();
    }

    public Long updatePest(Pest pest) {
        if (pest == null || pest.getId() == 0){
            throw new NullPointerException();
        }
        Pest updatePest = (Pest) sessionFactory.getCurrentSession().get(Pest.class, pest.getId());
        updatePest.setName(pest.getName());
        updatePest.setPoisons(pest.getPoisons());
        sessionFactory.getCurrentSession().update(updatePest);
        return updatePest.getId();
    }

    public Pest getPest(Long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return (Pest) sessionFactory.getCurrentSession().get(Pest.class, id);
    }

    public List<Pest> getPest() {
        try{
            Criteria criteria = sessionFactory.
                    getCurrentSession().
                    createCriteria(Pest .class);
            return criteria.list();
        } catch (HibernateException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deletePest(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }

        Pest pest = (Pest) sessionFactory.getCurrentSession().get(Pest.class, id);
        if (pest == null){
            throw new NoSuchObjectException();
        }

        sessionFactory.getCurrentSession().delete(pest);
    }
}
