package com.about.java.service.implement;

import com.about.java.dao.interfaces.PoisonDAO;
import com.about.java.models.Poison;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PoisonService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoisonServiceImpl implements PoisonService{

    @Autowired
    private PoisonDAO poisonDAO;

    @Transactional
    public Long add(Poison poison) throws ObjectAlreadyExistsException {
        if (poison == null){
            throw new NullPointerException();
        }
        try {
            return poisonDAO.addPoison(poison);
        } catch (HibernateException e){
            throw new ObjectAlreadyExistsException();
        }
    }

    @Transactional
    public Long update(Poison poison) throws NoSuchObjectException {
        if (poison == null){
            throw new NullPointerException();
        }
        try {
            return poisonDAO.updatePoison(poison);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public Poison get(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            return poisonDAO.getPoison(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public List<Poison> get() throws NoSuchObjectException {
        try {
            return poisonDAO.getPoison();
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }

    @Transactional
    public void delete(Long id) throws NoSuchObjectException {
        if (id == 0){
            throw new NullPointerException();
        }
        try {
            poisonDAO.deletePoison(id);
        } catch (HibernateException e){
            throw new NoSuchObjectException();
        }
    }
}
