package com.about.java.dao.implement;

import com.about.java.dao.interfaces.PestDAO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Query;
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
        Query query = sessionFactory.getCurrentSession().createQuery("from Pest");
        return query.list();
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
