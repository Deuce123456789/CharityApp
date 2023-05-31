package com.example.bottomnavigation;
import java.util.Random;

// Dataclass for holding a charity
public class Charity {
    public String name;
    public String desc;
    public int id;
    static Random r = new Random();

    public Charity(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.id = r.nextInt();

    }
}
