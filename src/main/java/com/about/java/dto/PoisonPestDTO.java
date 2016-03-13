package com.about.java.dto;


public class PoisonPestDTO {
    private PoisonDTO poisonDTO;
    private PestDTO pestDTO;

    public PoisonDTO getPoisonDTO() {
        return poisonDTO;
    }

    public void setPoisonDTO(PoisonDTO poisonDTO) {
        this.poisonDTO = poisonDTO;
    }

    public PestDTO getPestDTO() {
        return pestDTO;
    }

    public void setPestDTO(PestDTO pestDTO) {
        this.pestDTO = pestDTO;
    }
}
