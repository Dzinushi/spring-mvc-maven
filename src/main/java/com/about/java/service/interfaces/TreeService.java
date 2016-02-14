package com.about.java.service.interfaces;

import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface TreeService {
    long add(Tree tree) throws ObjectAlreadyExistsException;
    long update(Tree tree) throws NoSuchObjectException;
    Tree get(long id) throws NoSuchObjectException;
    List<Tree> get() throws NoSuchObjectException;
    void delete(long id) throws NoSuchObjectException;
}