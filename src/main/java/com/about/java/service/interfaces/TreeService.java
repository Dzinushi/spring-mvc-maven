package com.about.java.service.interfaces;


import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;

import java.util.List;

public interface TreeService {
    Long add(Tree tree) throws ObjectAlreadyExistsException;
    Long updateTree(Long id, String type);
    Tree getTree(Long id) throws NoSuchObjectException;
    List<Tree> getAllTree();
    void deleteInvitation(Long id) throws NoSuchObjectException;
}
