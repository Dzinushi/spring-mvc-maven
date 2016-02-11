package com.about.java.service.interfaces;


import com.about.java.models.trees.TreeTypes;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface TreeTypesService {
    Long add(TreeTypes treeTypes) throws ObjectAlreadyExistsException;
    Long updateTree(Long id, String type);
    TreeTypes getTree(Long id) throws NoSuchObjectException;
    List<TreeTypes> getAllTree();
    void deleteInvitation(Long id) throws NoSuchObjectException;
}