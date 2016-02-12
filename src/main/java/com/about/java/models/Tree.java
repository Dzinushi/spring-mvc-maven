package com.about.java.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tree")
public class Tree {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "family")
    private String family;

    @Column(name = "subtype")
    private String subtype;

    @Column(name = "height")
    private String height;

    @Column(name = "describe")
    private String describe;

    @ManyToOne
    private Tree parent;

    @OneToMany(mappedBy = "parent")
    private List<Tree> childrens;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tree_has_poison",
            joinColumns = {@JoinColumn(name = "tree_id")},
            inverseJoinColumns = {@JoinColumn(name = "poison_id")})
    private List<Poison> poisons;

    public Tree()
    {}

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

    public List<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(List<Poison> poisons) {
        this.poisons = poisons;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public List<Tree> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Tree> childrens) {
        this.childrens = childrens;
    }
}