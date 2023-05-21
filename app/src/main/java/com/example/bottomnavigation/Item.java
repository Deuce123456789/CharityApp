package com.example.bottomnavigation;

import java.util.Random;

public class Item {
    public String name;
    public Float price;
    public Integer rating;
    public Integer id;
    public Integer tokens;
    static Random r = new Random();

    public Item(String name, Float price, Integer rating){
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.tokens = r.nextInt()%2000;
        this.id = r.nextInt();
    }
}


