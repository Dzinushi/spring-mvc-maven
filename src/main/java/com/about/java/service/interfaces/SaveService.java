package com.about.java.service.interfaces;


import com.about.java.service.exceptions.ObjectAlreadyExistsException;

public interface SaveService {
    void addStaticTree() throws ObjectAlreadyExistsException;
    void addStaticPoison();
    void addStaticPest() throws ObjectAlreadyExistsException;
    void updateStaticTree();
    void updateStaticPoison();
}