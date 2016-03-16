package com.about.java.dao.implement;

import com.about.java.dao.interfaces.PoisonDAO;
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
public class PoisonDAOImplTest {

    @Autowired
    private PoisonDAO poisonDAO;

    @Test
    public void testAddPoison() throws Exception {

    }

    @Test
    public void testUpdatePoison() throws Exception {

    }

    @Test
    public void testGetPoison() throws Exception {

    }

    @Test
    public void testGetPoison1() throws Exception {

    }

    @Test
    public void testDeletePoison() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}