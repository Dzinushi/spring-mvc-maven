package com.about.java.dao.interfaces;

import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface TreeDAO {
    long addTree(Tree tree);
    long updateTree(Tree tree) throws NoSuchObjectException;
    Tree getTree(long id);
    List<Tree> getTree();
    void deleteTree(long id) throws NoSuchObjectException;
}