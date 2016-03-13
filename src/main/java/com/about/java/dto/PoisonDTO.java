package com.about.java.dto;


import java.util.List;

public class PoisonDTO {

    private Long id;
    private String name;
    private String type;
    private List<PestDTO> pestDTOs;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PestDTO> getPestDTOs() {
        return pestDTOs;
    }

    public void setPestDTOs(List<PestDTO> pestDTOs) {
        this.pestDTOs = pestDTOs;
    }
}
