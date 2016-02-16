package com.about.java.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "poison")
public class Poison {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tree_has_poison",
            joinColumns = {@JoinColumn(name = "poison_id")},
            inverseJoinColumns = {@JoinColumn(name = "tree_id")})
    private List<Tree> trees;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "poison_has_pests",
            joinColumns = {@JoinColumn(name = "poison_id")},
            inverseJoinColumns = {@JoinColumn(name = "pests_id")})
    private List<Pest> pests;

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

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }
}
