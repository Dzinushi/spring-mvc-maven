package com.about.java.dao.interfaces;

import com.about.java.models.trees.TreeSubtypes;
import java.util.List;

public interface TreeSubtypesDAO {
    Long addTreeSubtype(TreeSubtypes treeSubtypes);
    Long updateTreeSubtype(TreeSubtypes treeSubtypes);
    TreeSubtypes getTreeSubtype(Long id);
    List<TreeSubtypes> getTreeSubtypes();
    void deleteTreeSubtype(Long id);
}
