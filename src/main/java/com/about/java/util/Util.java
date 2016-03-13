package com.about.java.util;


import java.util.Collection;
import java.util.Objects;

public class Util {
    public static <T> int find(Collection<T> collection, T object){
        int i = 0;
        for (T aCollection : collection) {
            if (Objects.equals(aCollection, object)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
}