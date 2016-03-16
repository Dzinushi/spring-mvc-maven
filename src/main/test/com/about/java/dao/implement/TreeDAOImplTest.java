package com.about.java.dao.implement;

import com.about.java.dao.interfaces.TreeDAO;
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
public class TreeDAOImplTest {

    @Autowired
    private TreeDAO treeDAO;

    @Test
    public void testAddTree() throws Exception {

    }

    @Test
    public void testUpdateTree() throws Exception {

    }

    @Test
    public void testGetTree() throws Exception {

    }

    @Test
    public void testGetTree1() throws Exception {

    }

    @Test
    public void testDeleteTree() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}