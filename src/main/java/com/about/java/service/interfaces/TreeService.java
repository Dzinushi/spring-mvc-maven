package com.about.java.service.interfaces;

import com.about.java.dto.TreeDTO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface TreeService {
    long add(TreeDTO tree) throws ObjectAlreadyExistsException;
    long update(TreeDTO tree) throws NoSuchObjectException;
    TreeDTO get(long id) throws NoSuchObjectException;
    List<TreeDTO> get() throws NoSuchObjectException;
    void delete(long id) throws NoSuchObjectException;
    public Tree toTree(TreeDTO treeDTO);
}