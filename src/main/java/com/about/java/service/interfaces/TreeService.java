package com.about.java.service.interfaces;

import com.about.java.dto.TreeDTO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface TreeService {
    Long add(TreeDTO tree) throws ObjectAlreadyExistsException;
    Long update(TreeDTO tree) throws NoSuchObjectException;
    TreeDTO get(Long id) throws NoSuchObjectException;
    List<TreeDTO> get() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    TreeDTO toTreeDTO(Tree tree) throws NoSuchObjectException;
    Tree toTree(TreeDTO treeDTO);
}