package com.about.java.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "care")
public class Care {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "care")
    private Tree tree;

//    @Column(name = "describe", length = 5000)
//    private String describe;

    public Care(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

//    public String getDescribe() {
//        return describe;
//    }
//
//    public void setDescribe(String describe) {
//        this.describe = describe;
//    }

    public void copy(Care care){
        setId(care.getId());
        setTree(care.getTree());
    }
}
