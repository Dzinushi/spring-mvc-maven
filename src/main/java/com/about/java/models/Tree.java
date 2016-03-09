package com.about.java.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tree")
public class Tree {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "height")
    private String height;

    @Column(name = "describe", length = 5000)
    private String describe;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_id")
    private Care care;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
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

    public Care getCare() {
        return care;
    }

    public void setCare(Care care) {
        this.care = care;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}