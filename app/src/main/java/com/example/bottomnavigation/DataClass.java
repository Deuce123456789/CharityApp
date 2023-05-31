package com.example.bottomnavigation;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Optional;

public class DataClass {
    public MutableLiveData<Integer> NumTokens = new MutableLiveData<>(0);
    public Integer tasksCompleted = 0;
    public ArrayList<Boolean> completedTasks = new ArrayList<>();
    public ArrayList<Task> tasks = new ArrayList<>();
    public ArrayList<Item> StoreItems = new ArrayList<>();
    public ArrayList<Charity> CharityItems = new ArrayList<>();
    public ArrayList<Item> cart = new ArrayList<>();


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

        CharityItems.add(new Charity("Salvation Army", "Description goes here."));
        CharityItems.add(new Charity("Salvation Army2", "Description goes here."));
        CharityItems.add(new Charity("Salvation Army3", "Description goes here."));
    }

    public void DefineItems(){
        //define items in the store
        StoreItems.add(new Item("My Laptop Item", 999.99f, 4, 69));
        StoreItems.add(new Item("Other Thing", 50f, 2, 420));
        StoreItems.add(new Item("Memes", 420.69f, 5, 69420));
        StoreItems.add(new Item("Some Item Variety", 1234567.89f, 0, 3245));
        StoreItems.add(new Item("Other Thing", 50f, 2,1234567890));
        StoreItems.add(new Item("Memes", 420.69f, 5, 31415265));

    }
    public void DefineCompletedTasks(){
        tasks.add(new Task("Do something",10, "Does some stuff that works?"));
        tasks.add(new Task("sdfg",10, "Does some stuff that works?"));
        tasks.add(new Task("sdfgdsgf",10, "Does some stuff that works?"));
        tasks.add(new Task("Dsdfg",10, "Does some stuff that works?"));
        tasks.add(new Task("erg",10, "Does some stuff that works?"));
        tasks.add(new Task("qwerghg",10, "Does some stuff that works?"));
        tasks.add(new Task("qwergd",10, "Does some stuff that works?"));
        //Fill completed tasks with empty booleans
        for (int i = 0; i < tasks.size(); i++){
            completedTasks.add(false);
        }
    }
}