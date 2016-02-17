package com.about.java.dto;

import com.about.java.models.Poison;
import com.about.java.models.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeDTO {

    private long id;
    private String name;
    private String height;
    private String describe;
    private String care;
    private List<PoisonDTO> poisonDTOs;

    public TreeDTO(){
        id = 0;
        name = "";
        height = "";
        describe = "";
        care = "";
        poisonDTOs = new ArrayList<PoisonDTO>();
    }

//    public TreeDTO(Tree tree){
//        setId(tree.getId());
//        setName(tree.getName());
//        setHeight(tree.getHeight());
//        setDescribe(tree.getDescribe());
//        setCare(tree.getCare().getDescribe());
//        setPoisonDTOs(tree.getPoisons());
//    }

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

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public List<PoisonDTO> getPoisonDTOs() {
        return poisonDTOs;
    }

    public void setPoisonDTOs(List<PoisonDTO> poisonDTOs) {
        this.poisonDTOs = poisonDTOs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
