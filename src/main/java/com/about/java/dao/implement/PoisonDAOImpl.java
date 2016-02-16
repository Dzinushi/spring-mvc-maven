package com.about.java.dao.implement;


import com.about.java.dao.interfaces.PoisonDAO;
import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoisonDAOImpl implements PoisonDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Long addPoison(Poison poison) {
        if (poison == null || poison.getId() == 0){
            throw new NullPointerException();
        }
        sessionFactory.getCurrentSession().save(poison);
        return poison.getId();
    }

    public Long updatePoison(Poison poison) {
        if (poison == null || poison.getId() == 0){
            throw new NullPointerException();
        }
        Poison updatePoison = (Poison) sessionFactory.getCurrentSession().get(Poison.class, poison.getId());
        updatePoison.setName(poison.getName());
        updatePoison.setTrees(poison.getTrees());
        updatePoison.setPests(poison.getPests());
        updatePoison.setType(poison.getType());
        sessionFactory.getCurrentSession().update(updatePoison);
        return updatePoison.getId();
    }

    public Poison getPoison(Long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return (Poison) sessionFactory.getCurrentSession().get(Poison.class, id);
    }

    public List<Poison> getPoison() {
        try{
            Criteria criteria = sessionFactory.
                    getCurrentSession().
                    createCriteria(Poison .class);
            return criteria.list();
        } catch (HibernateException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deletePoison(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        Poison poison = (Poison) sessionFactory.getCurrentSession().get(Poison.class, id);
        if (poison == null){
            throw new NoSuchObjectException();
        }
        sessionFactory.getCurrentSession().delete(poison);
    }
}
