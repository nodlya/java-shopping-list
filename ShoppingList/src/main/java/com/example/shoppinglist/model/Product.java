package com.example.shoppinglist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean bought;

    public Product() {
    }

    public Product(Long id, String name, boolean bought) {
        this.id = id;
        this.name = name;
        this.bought = bought;
    }

    public Product(String name){
        this.name = name;
        bought = false;
    }

    public Product(Long id, String name){
        this.id = id;
        this.name = name;
        bought = false;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
