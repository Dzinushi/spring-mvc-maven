package com.about.java.dto;

import com.about.java.models.Pest;
import java.util.ArrayList;
import java.util.List;


public class PoisonDTO {

    private long id;
    private String name;
    private String type;
    private List<PestDTO> pestDTOs;

    public PoisonDTO(){
        id = 0;
        name = "";
        type = "";
        pestDTOs = new ArrayList<PestDTO>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
