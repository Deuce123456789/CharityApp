package com.example.bottomnavigation;

import java.util.ArrayList;
import java.util.Optional;

public class DataClass {
    public Integer NumTokens = 0;
    public Integer tasksCompleted = 0;
    public ArrayList<Boolean> completedTasks = new ArrayList<>();
    public ArrayList<Item> StoreItems = new ArrayList<>();
    public ArrayList<Charity> CharityItems = new ArrayList<>();
    public ArrayList<Item> cart = new ArrayList<Item>();

    private static DataClass instance = null;

    private DataClass() {
        // constructor
        DefineCharities();
        DefineItems();
        DefineCompletedTasks();
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

    public void DefineItems(){
        //define items in the store
        Item Laptop = new Item("My Laptop Item", 999.99f, 4);
        StoreItems.add(Laptop);

    }
    public void DefineCompletedTasks(){
        //Fill completed tasks with empty booleans
        for (int i = 0; i < 4; i++){
            completedTasks.add(false);
        }
    }
}