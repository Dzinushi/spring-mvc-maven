package com.about.java.dao.interfaces;

import com.about.java.models.Tree;
import java.util.List;

public interface TreeDAO {
    Long addTree(Tree tree);
    Long updateTree(Long id, String type);
    Tree getTree(Long id);
    List<Tree> getTrees();
    void deleteTree(Long id);
}