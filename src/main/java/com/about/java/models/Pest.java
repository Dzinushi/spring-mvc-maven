package com.about.java.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pests")
public class Pest {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "poison_has_pests",
            joinColumns = {@JoinColumn(name = "pests_id")},
            inverseJoinColumns = {@JoinColumn(name = "poison_id")})
    private List<Poison> poisons;

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

    public List<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(List<Poison> poisons) {
        this.poisons = poisons;
    }
}