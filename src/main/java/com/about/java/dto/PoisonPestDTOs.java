package com.about.java.dto;


import java.util.List;

public class PoisonPestDTOs {
    private List<PoisonDTO> poisonDTOs;
    private List<PestDTO> pestDTOs;

    public List<PoisonDTO> getPoisonDTOs() {
        return poisonDTOs;
    }

    public void setPoisonDTOs(List<PoisonDTO> poisonDTOs) {
        this.poisonDTOs = poisonDTOs;
    }

    public List<PestDTO> getPestDTOs() {
        return pestDTOs;
    }

    public void setPestDTOs(List<PestDTO> pestDTOs) {
        this.pestDTOs = pestDTOs;
    }
}
