package com.aleryo.petstore.model;

public class Customer {
    Integer id;
    String name;

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
