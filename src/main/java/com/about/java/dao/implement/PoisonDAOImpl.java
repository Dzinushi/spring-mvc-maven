package com.about.java.dao.implement;

import com.about.java.dao.interfaces.PoisonDAO;
import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class PoisonDAOImpl implements PoisonDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public PoisonDAOImpl() {
    }

    public Long addPoison(Poison poison) {
        if (poison == null){
            throw new NullPointerException();
        }
        try {
            sessionFactory.getCurrentSession().save(poison);
        } catch (Exception e){
            e.printStackTrace();
        }
        return poison.getId();
    }

    public Long updatePoison(Poison poison) throws NoSuchObjectException {
        if (poison == null || poison.getId() == 0){
            throw new NullPointerException();
        }
        Poison foundPoison = (Poison) sessionFactory.getCurrentSession().get(Poison.class, poison.getId());
        if (foundPoison != null){
            foundPoison.copy(poison);
            sessionFactory.getCurrentSession().update(foundPoison);
            return foundPoison.getId();
        }
        else {
            throw new NoSuchObjectException();
        }
    }

    public Poison getPoison(Long id) {
        if (id == 0){
            throw new NullPointerException();
        }
        return (Poison) sessionFactory.getCurrentSession().get(Poison.class, id);
    }

    public Poison getPoison(String poisonName) {
        if (poisonName.length() == 0){
            throw new NullPointerException();
        }
        Query query = sessionFactory.getCurrentSession().createQuery("from Poison where name=:poisonName");
        query.setParameter("poisonName", poisonName);
        return (Poison) query.list().get(0);
    }

    public List<Poison> getPoison() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Poison");
        return query.list();
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

    public boolean find(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Poison where name=:poisonName");
        query.setParameter("poisonName", name);
        return query.list().size() > 0;
    }
}
