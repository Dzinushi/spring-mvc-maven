package com.about.java.service.interfaces;

import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface TreeService {
    Long addTreeFamily(Tree treeFamily);
    Long updateTreeFamily(Tree treeFamily);
    Long deleteTreeFamily(Long id);
    Long add(Tree tree) throws ObjectAlreadyExistsException;
    Long update(Tree tree) throws NoSuchObjectException;
    Tree get(Tree tree) throws NoSuchObjectException;
    List<Tree> get() throws NoSuchObjectException;
    void delete(Tree tree) throws NoSuchObjectException;
}