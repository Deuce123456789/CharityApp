package com.example.bottomnavigation;

import java.sql.Savepoint;
import java.util.ArrayList;

public class DataClass {
    public Integer NumTokens;
    public ArrayList<ItemDummy> StoreItems;
    public ArrayList<Charity> CharityItems;

    private static DataClass instance = null;

    private DataClass() {
        // constructor
        DefineCharities();
    }

    public static DataClass get_instance() {
        if (instance == null) {
            instance = new DataClass();
        }
        return instance;
    }

    public void DefineCharities() {
        //salvation army declaration
        Charity SalvationArmy = new Charity("Salvation Army", "Description goes here.");
        CharityItems.add(SalvationArmy);
    }
}
