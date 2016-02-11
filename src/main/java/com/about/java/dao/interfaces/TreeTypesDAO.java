package com.about.java.dao.interfaces;

import com.about.java.models.trees.TreeTypes;

import java.util.List;

public interface TreeTypesDAO {
    Long addTreeType(TreeTypes treeTypes);
    Long updateTreeType(Long id, String type);
    TreeTypes getTreeType(Long id);
    List<TreeTypes> getTreeTypes();
    void deleteTreeType(Long id);
}