package com.about.java.dto;

import com.about.java.models.Poison;

import java.util.List;

public class PoisonDTO {

    private List<Poison> poisons;
    private int count;

    public PoisonDTO(List<Poison> poisons){
        setPoisons(poisons);
        setCount(poisons.size());
    }

    public List<Poison> getPoisons() {
        return poisons;
    }

    public void setPoisons(List<Poison> poisons) {
        this.poisons = poisons;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
