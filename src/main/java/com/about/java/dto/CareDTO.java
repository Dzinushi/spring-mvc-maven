package com.about.java.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

public class CareDTO {
    private Long id;
    private String describe;
//    private TreeDTO treeDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

//    public TreeDTO getTreeDTO() {
//        return treeDTO;
//    }
//
//    public void setTreeDTO(TreeDTO treeDTO) {
//        this.treeDTO = treeDTO;
//    }
}
