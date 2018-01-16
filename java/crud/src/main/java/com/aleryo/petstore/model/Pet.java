package com.aleryo.petstore.model;

import java.math.BigDecimal;

public class Pet {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    BigDecimal price;

    public Pet(String name, Double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }
}
