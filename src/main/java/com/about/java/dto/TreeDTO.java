package com.about.java.dto;


import java.util.List;

public class TreeDTO {

    private Long id;
    private String name;
    private String height;
    private String describe;
    private CareDTO careDTO;
    private List<PoisonDTO> poisonDTOs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CareDTO getCareDTO() {
        return careDTO;
    }

    public void setCareDTO(CareDTO careDTO) {
        this.careDTO = careDTO;
    }

    public List<PoisonDTO> getPoisonDTOs() {
        return poisonDTOs;
    }

    public void setPoisonDTOs(List<PoisonDTO> poisonDTOs) {
        this.poisonDTOs = poisonDTOs;
    }
}
