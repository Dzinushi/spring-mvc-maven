package com.about.java.dto;

import java.util.List;

public class TreePoisonDTOs {
    private List<TreeDTO> treeDTOs;
    private List<PoisonDTO> poisonDTOs;

    public List<PoisonDTO> getPoisonDTOs() {
        return poisonDTOs;
    }

    public void setPoisonDTOs(List<PoisonDTO> poisonDTOs) {
        this.poisonDTOs = poisonDTOs;
    }

    public List<TreeDTO> getTreeDTOs() {
        return treeDTOs;
    }

    public void setTreeDTOs(List<TreeDTO> treeDTOs) {
        this.treeDTOs = treeDTOs;
    }
}
