package com.about.java.models;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tree")
public class Tree {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "height")
    private String height;

//    @Column(name = "describe", length = 5000)
//    private String describe;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_id")
    private Care care;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "tree_has_poison",
            joinColumns = {@JoinColumn(name = "tree_id")},
            inverseJoinColumns = {@JoinColumn(name = "poison_id")})
    private Set<Poison> poisons;

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

//    public String getDescribe() {
//        return describe;
//    }
//
//    public void setDescribe(String describe) {
//        this.describe = describe;
//    }

    public Set<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(Set<Poison> poisons) {
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

    public void copy(Tree tree){
        setId(tree.getId());
        setName(tree.getName());
        setHeight(tree.getHeight());
        setCare(tree.getCare());
        setPoisons(tree.getPoisons());
    }
}