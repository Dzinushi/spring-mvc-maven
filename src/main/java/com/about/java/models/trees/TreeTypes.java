package com.about.java.models.trees;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TreeTypes")
public class TreeTypes {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany
    @JoinColumn(name = "TreeTypes_id")
    private List<TreeSubtypes> treeSubtypes;

    public TreeTypes()
    {}

    public TreeTypes(Long id, String type){
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TreeSubtypes> getTreeSubtypes() {
        return treeSubtypes;
    }

    public void setTreeSubtypes(List<TreeSubtypes> treeSubtypes) {
        this.treeSubtypes = treeSubtypes;
    }
}
