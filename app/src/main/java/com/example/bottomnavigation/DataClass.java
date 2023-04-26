package com.example.bottomnavigation;

import java.util.ArrayList;

public class DataClass {
    public Integer NumTokens;
    public ArrayList<ItemDummy> StoreItems;

    private static DataClass instance = null;

    private DataClass() {
        // constructor
    }

    public DataClass get_instance() {
        if (instance == null) {
            instance = new DataClass();
        }
        return instance;
    }
}
