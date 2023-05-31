package com.example.bottomnavigation;

import java.util.Random;

// Dataclass for holding a task
public class Task {
    public String name;
    public String description;
    public Integer tokens;
    public Integer id;
    static Random r = new Random();

    public Task(String name, Integer tokens, String description){
        this.name = name;
        this.tokens = tokens;
        this.description = description;
        this.id = r.nextInt();
    }

}


