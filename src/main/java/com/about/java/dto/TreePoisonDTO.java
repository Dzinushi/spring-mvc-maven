package com.about.java.dto;

import java.util.List;

public class TreePoisonDTO {
    private TreeDTO treeDTO;
    private PoisonDTO poisonDTO;

    public PoisonDTO getPoisonDTO() {
        return poisonDTO;
    }

    public void setPoisonDTO(PoisonDTO poisonDTOs) {
        this.poisonDTO = poisonDTOs;
    }

    public TreeDTO getTreeDTO() {
        return treeDTO;
    }

    public void setTreeDTO(TreeDTO treeDTOs) {
        this.treeDTO = treeDTOs;
    }
}
