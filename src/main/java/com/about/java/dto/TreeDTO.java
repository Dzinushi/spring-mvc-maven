package com.about.java.dto;

import com.about.java.models.Tree;
import java.util.ArrayList;
import java.util.List;

public class TreeDTO {

    private List<Tree> trees;
    private long count;

    public TreeDTO(Tree tree){
        trees = new ArrayList<Tree>();
        trees.add(tree);
        setCount(trees.size());
    }

    public TreeDTO(List<Tree> trees){
        setTrees(trees);
        setCount(trees.size());
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
