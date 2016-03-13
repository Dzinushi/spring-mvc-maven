package com.about.java.service.interfaces;

import com.about.java.dto.TreeDTO;
import com.about.java.dto.TreePoisonDTO;
import com.about.java.models.Tree;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import java.util.List;

public interface TreeService {
    Long add(List<TreePoisonDTO> treePoisonDTO) throws ObjectAlreadyExistsException;
    Long update(List<TreePoisonDTO> treePoisonDTO) throws NoSuchObjectException;
    List<TreePoisonDTO> get(Long id) throws NoSuchObjectException;
    List<TreePoisonDTO> get() throws NoSuchObjectException;
    TreeDTO getByID(Long id) throws NoSuchObjectException;
    List<TreeDTO> getAll() throws NoSuchObjectException;
    void delete(Long id) throws NoSuchObjectException;
    Tree toTree(List<TreePoisonDTO> treePoisonDTOs);
    TreeDTO toTreeDTO(Tree tree);
    Tree toTree(TreeDTO treeDTO);
    List<TreePoisonDTO> toTreePoisonDTO(Tree tree) throws NoSuchObjectException;
    List<TreePoisonDTO> toTreePoisonDTO(List<Tree> trees) throws NoSuchObjectException;
}