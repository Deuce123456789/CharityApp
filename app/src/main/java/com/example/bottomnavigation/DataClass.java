package com.example.bottomnavigation;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Optional;

// This class is a singleton, so you can access its data from anywhere.
// Could we have made it static? Probably. Did we think of that? No.
public class DataClass {
    // This is a MutableLiveData because the token selector needs to be
    // able to see when it changes.
    public MutableLiveData<Integer> NumTokens = new MutableLiveData<>(0);

    // These are only used in the task screen, but they need to persist
    public Integer tasksCompleted = 0;
    public ArrayList<Boolean> completedTasks = new ArrayList<>();

    // All available tasks, items, and charities
    public ArrayList<Task> tasks = new ArrayList<>();
    public ArrayList<Item> StoreItems = new ArrayList<>();
    public ArrayList<Charity> CharityItems = new ArrayList<>();

    // The current cart
    public ArrayList<Item> cart = new ArrayList<>();

    // Part of a singleton pattern
    private static DataClass instance = null;

    private DataClass() {
        // constructor
        DefineCharities();
        DefineItems();
        DefineTasks();
    }

    // The other part of a singleton pattern
    public static DataClass get_instance() {
        if (instance == null) {
            instance = new DataClass();
        }
        return instance;
    }

    // Set up the list of charities
    public void DefineCharities() {
        //salvation army declaration
        CharityItems.add(new Charity("Salvation Army", "Description goes here."));
        CharityItems.add(new Charity("Salvation Army2", "Description goes here."));
        CharityItems.add(new Charity("Salvation Army3", "Description goes here."));
    }

    // Set up the list of items
    public void DefineItems(){
        StoreItems.add(new Item("My Laptop Item", 999.99f, 4, 69));
        StoreItems.add(new Item("Other Thing", 50f, 2, 420));
        StoreItems.add(new Item("Memes", 420.69f, 5, 69420));
        StoreItems.add(new Item("Some Item Variety", 1234567.89f, 0, 3245));
        StoreItems.add(new Item("Other Thing", 50f, 2,1234567890));
        StoreItems.add(new Item("Memes", 420.69f, 5, 31415265));
    }

    // Set up the list of tasks
    public void DefineTasks(){
        tasks.add(new Task("Do something",10, "Does some stuff that works?"));
        tasks.add(new Task("sdfg",10, "Does some stuff that works?"));
        tasks.add(new Task("sdfgdsgf",10, "Does some stuff that works?"));
        tasks.add(new Task("Dsdfg",10, "Does some stuff that works?"));
        tasks.add(new Task("erg",10, "Does some stuff that works?"));
        tasks.add(new Task("qwerghg",10, "Does some stuff that works?"));
        tasks.add(new Task("qwergd",10, "Does some stuff that works?"));
        //Fill completedTasks with one false for each task (they aren't completed yet)
        for (int i = 0; i < tasks.size(); i++){
            completedTasks.add(false);
        }
    }
}