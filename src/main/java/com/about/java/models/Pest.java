package com.about.java.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "pests")
public class Pest {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "pests", fetch = FetchType.LAZY)
    private Set<Poison> poisons;

    public Pest()
    {}

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

    public Set<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(Set<Poison> poisons) {
        this.poisons = poisons;
    }

    public void copy(Pest pest){
        setId(pest.getId());
        setName(pest.getName());
        setPoisons(pest.getPoisons());
    }
}