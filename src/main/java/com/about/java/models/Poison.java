package com.about.java.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "poison")
public class Poison {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "name")
    private String name;

//    @Size(max = 20)
//    @Column(name = "type")
//    private String type;

    @ManyToMany(mappedBy = "poisons", fetch = FetchType.LAZY)
    private Set<Tree> trees;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "poison_has_pests",
            joinColumns = {@JoinColumn(name = "poison_id")},
            inverseJoinColumns = {@JoinColumn(name = "pests_id")})
    private Set<Pest> pests;

    public Poison(){}

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

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public Set<Pest> getPests() {
        return pests;
    }

    public void setPests(Set<Pest> pests) {
        this.pests = pests;
    }

    public Set<Tree> getTrees() {
        return trees;
    }

    public void setTrees(Set<Tree> trees) {
        this.trees = trees;
    }

    public void copy(Poison poison){
        setId(poison.getId());
        setName(poison.getName());
        setPests(poison.getPests());
        setTrees(poison.getTrees());
    }
}
