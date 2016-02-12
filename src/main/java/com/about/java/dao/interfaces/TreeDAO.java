package com.about.java.dao.interfaces;

import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;

import java.util.List;

public interface TreeDAO {
    Long addTreeFamily(Tree treeFamily);
    Long updateTreeFamily(Tree treeFamily);
    void deleteTreeFamily(Long id) throws NoSuchObjectException;
    Long addTree(Tree tree);
    Long updateTree(Tree tree);
    Tree getTree(Tree tree);
    List<Tree> getTree();
    void deleteTree(Tree tree) throws NoSuchObjectException;
}