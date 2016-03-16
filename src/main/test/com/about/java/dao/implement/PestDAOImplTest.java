package com.about.java.dao.implement;

import com.about.java.dao.interfaces.PestDAO;
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
public class PestDAOImplTest {

    @Autowired
    private PestDAO pestDAO;

    @Test
    public void testAddPest() throws Exception {

    }

    @Test
    public void testUpdatePest() throws Exception {

    }

    @Test
    public void testGetPest() throws Exception {

    }

    @Test
    public void testGetPest1() throws Exception {

    }

    @Test
    public void testDeletePest() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}