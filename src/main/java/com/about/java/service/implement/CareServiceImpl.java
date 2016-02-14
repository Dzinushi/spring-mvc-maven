package com.about.java.service.implement;

import com.about.java.dao.implement.CareDAOImpl;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CareServiceImpl implements CareService{

    @Autowired
    private CareDAOImpl careDAO;

    @Transactional
    public long add(Care care) throws ObjectAlreadyExistsException {
        return careDAO.addCare(care);
    }

    @Transactional
    public long update(Care care) throws NoSuchObjectException {
        return careDAO.updateCare(care);
    }

    @Transactional
    public Care getCare(long id) throws NoSuchObjectException {
        return careDAO.getCare(id);
    }

    @Transactional
    public List<Care> getCare() throws NoSuchObjectException {
        return careDAO.getCare();
    }

    @Transactional
    public void delete(long id) throws NoSuchObjectException {
        careDAO.delete(id);
    }
}