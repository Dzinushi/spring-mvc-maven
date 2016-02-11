package com.about.java.models.trees;

import sun.reflect.generics.tree.Tree;

import javax.persistence.*;

@Entity
@Table(name = "TreeSubtypes")
public class TreeSubtypes {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "subtype")
    private String subtype;

    @Column(name = "height")
    private double height;

    @Column(name = "describe")
    private String describe;

    @ManyToOne
    @JoinColumn(name = "TreeTypes_id")
    private TreeTypes type;

    public TreeSubtypes()
    {}

    public TreeSubtypes(Long id, String subtype, Double height, String describe){
        this.id = id;
        this.subtype = subtype;
        this.height = height;
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public TreeTypes getTreeTypes() {
        return type;
    }

    public void setTreeTypes(TreeTypes type) {
        this.type = type;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}