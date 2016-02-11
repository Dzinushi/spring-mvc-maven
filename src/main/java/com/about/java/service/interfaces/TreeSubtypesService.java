package com.about.java.service.interfaces;

import com.about.java.models.trees.TreeSubtypes;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface TreeSubtypesService {
    Long add(TreeSubtypes treeSubtypes) throws ObjectAlreadyExistsException;
    Long updateTree(TreeSubtypes treeSubtypes);
    TreeSubtypes getTree(Long id) throws NoSuchObjectException;
    List<TreeSubtypes> getAllTree();
    void deleteInvitation(Long id) throws NoSuchObjectException;
}