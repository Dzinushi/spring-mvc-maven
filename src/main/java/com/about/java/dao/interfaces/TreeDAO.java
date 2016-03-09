package com.about.java.dao.interfaces;

import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface TreeDAO {
    Long addTree(Tree tree);
    Long updateTree(Tree tree) throws NoSuchObjectException;
    Tree getTree(Long id);
    List getTree();
    void deleteTree(Long id) throws NoSuchObjectException;
    boolean find(String name);
}