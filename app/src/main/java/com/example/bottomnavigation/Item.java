package com.example.bottomnavigation;

import java.util.Random;

// Dataclass for holding an item
public class Item {
    public String name;
    public Float price;
    public Integer rating;
    public Integer id;
    public Integer tokens;
    public int image_id;
    static Random r = new Random();

    public Item(String name, Float price, int rating, int tokens, int image_id){
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.tokens = tokens;
        this.id = r.nextInt();
        this.image_id = image_id;
    }
}


