package com.about.java.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pests")
public class Pest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "poison_has_pests",
            joinColumns = {@JoinColumn(name = "pests_id")},
            inverseJoinColumns = {@JoinColumn(name = "poison_id")})
    private List<Poison> poisons;

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

    public List<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(List<Poison> poisons) {
        this.poisons = poisons;
    }
}
