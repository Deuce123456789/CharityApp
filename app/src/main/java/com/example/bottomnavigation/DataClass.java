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
        String[] chars = {
                "Salvation Army",
                "Red Cross",
                "Canadian Cancer Society",
                "Food Banks Canada",
                "World Vision Canada",
                "Heart and Stroke Foundation",
                "Indspire"
        };
        for (String c :
                chars) {
            CharityItems.add(new Charity(c));
        }
    }

    // Set up the list of items
    public void DefineItems(){
        StoreItems.add(new Item("Down Jacket", 193.99f, 4, 3284, R.drawable.itm1));
        StoreItems.add(new Item("Rummikub Game", 19.95f, 5, 643, R.drawable.itm2));
        StoreItems.add(new Item("Wallet", 59.99f, 3, 1454, R.drawable.itm3));
        StoreItems.add(new Item("Amazon Echo Dot", 64.99f, 4, 1567, R.drawable.itm4));
        StoreItems.add(new Item("Amazon Echo Dot (kids ver.)", 79.99f, 5,4234, R.drawable.itm5));
        StoreItems.add(new Item("Engraved Necklace", 16.79f, 4, 255, R.drawable.itm6));
    }

    // Set up the list of tasks
    public void DefineTasks(){
        tasks.add(new Task("Watch a 15-second ad",5, "Watch this quick ad for some free tokens."));
        tasks.add(new Task("Watch a 30-second ad",10, "Watch this slightly longer ad for some more free tokens."));
        tasks.add(new Task("Take a Survey",100, "Take this quick 15-question survey and be rewarded."));
        tasks.add(new Task("Subscribe to a Newsletter",20, "Just enter your email."));
        tasks.add(new Task("Visit this Website",5, "They have cool stuff there."));
        //Fill completedTasks with one false for each task (they aren't completed yet)
        for (int i = 0; i < tasks.size(); i++){
            completedTasks.add(false);
        }
    }
}