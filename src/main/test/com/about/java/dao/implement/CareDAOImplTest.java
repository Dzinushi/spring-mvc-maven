package com.about.java.dao.implement;

import com.about.java.dao.interfaces.CareDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class CareDAOImplTest {

    @Autowired
    private CareDAO careDAO;

    @Test
    public void testAddCare() throws Exception {

    }

    @Test
    public void testUpdateCare() throws Exception {

    }

    @Test
    public void testGetCare() throws Exception {

    }

    @Test
    public void testGetCare1() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}