package com.example.bottomnavigation;

import java.util.Random;

// Dataclass for holding an item
public class Item {
    public String name;
    public Float price;
    public Integer rating;
    public Integer id;
    public Integer tokens;
    static Random r = new Random();

    public Item(String name, Float price, int rating, int tokens){
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.tokens = tokens;
        this.id = r.nextInt();
    }
}


