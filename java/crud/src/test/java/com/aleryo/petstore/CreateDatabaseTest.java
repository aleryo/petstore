package com.aleryo.petstore;

import com.aleryo.petstore.model.Customer;
import com.aleryo.petstore.model.Pet;
import org.jdbi.v3.core.Jdbi;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CreateDatabaseTest {

    @Test
    @Ignore("Run only to create database")
    public void createTables() {
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost/crudstore", "postgres", "mysecretpassword");

        createTableCustomers(jdbi);
        createTablePets(jdbi);
        createTableOrder(jdbi);
    }

    private void createTableCustomers(Jdbi jdbi) {
        List<Customer> customers = Arrays.asList(
                new Customer("Ann"),
                new Customer("Theresa"),
                new Customer("Mike")
        );

        jdbi.useHandle(handle -> {
            try {
                handle.execute("DROP TABLE customer");
            } catch (Exception e) {

            }

            handle.execute("CREATE TABLE customer (id SERIAL PRIMARY KEY, name VARCHAR)");

            for(Customer c : customers) {
                handle.createUpdate("INSERT INTO customer(name) VALUES (:name)")
                        .bindBean(c)
                        .execute();
            }
        });
    }

    private void createTablePets(Jdbi jdbi) {
        List<Pet> pets = Arrays.asList(
                new Pet("Yorkshire", 10.0),
                new Pet("Bulldog", 20.0),
                new Pet("Cat", 30.)
        );

        jdbi.useHandle(handle -> {
            try {
                handle.execute("DROP TABLE pet");
            } catch (Exception e) {

            }
            handle.execute("CREATE TABLE pet (id SERIAL PRIMARY KEY, name VARCHAR, price DECIMAL)");

            for(Pet p : pets) {
                handle.createUpdate("INSERT INTO pet(name, price) VALUES (:name, :price)")
                        .bindBean(p)
                        .execute();
            }
        });
    }

    private void createTableOrder(Jdbi jdbi) {
        jdbi.useHandle(handle -> {
            try {
                handle.execute("DROP TABLE order");
                handle.execute("DROP TABLE order_pet");
            } catch (Exception e) {

            }
            handle.execute("CREATE TABLE basket (id SERIAL PRIMARY KEY, customer_id INTEGER)");
            handle.execute("CREATE TABLE basket_pet (id SERIAL PRIMARY KEY, basket_id INTEGER, pet_id INTEGER)");
        });
    }
}
