package com.about.java.dto;


import java.util.List;

public class PestDTO {

    private Long id;
    private String name;
    private List<PoisonDTO> poisonDTOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PoisonDTO> getPoisonDTOs() {
        return poisonDTOs;
    }

    public void setPoisonDTOs(List<PoisonDTO> poisonDTOs) {
        this.poisonDTOs = poisonDTOs;
    }
}