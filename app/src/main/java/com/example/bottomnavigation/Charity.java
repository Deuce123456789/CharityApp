package com.example.bottomnavigation;
import java.util.Random;

// Dataclass for holding a charity
public class Charity {
    public String name;
    public int id;
    static Random r = new Random();

    public Charity(String name) {
        this.name = name;
        this.id = r.nextInt();

    }
}
