package com.about.java.dto;


import com.about.java.models.Pest;
import java.util.List;

public class PestDTO {

    private List<Pest> pests;
    private long count;

    public PestDTO(List<Pest> pests){
        setPests(pests);
        setCount(pests.size());
    }

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
