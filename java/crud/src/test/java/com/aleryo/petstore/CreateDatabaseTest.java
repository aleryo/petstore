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
    }

    private void createTableCustomers(Jdbi jdbi) {
        List<Customer> customers = Arrays.asList(
                new Customer(1, "Ann"),
                new Customer(2, "Theresa"),
                new Customer(3, "Mike")
        );

        jdbi.useHandle(handle -> {
            try {
                handle.execute("DROP TABLE customer");
            } catch (Exception e) {

            }

            handle.execute("CREATE TABLE customer (id INTEGER PRIMARY KEY, name VARCHAR)");

            for(Customer c : customers) {
                handle.createUpdate("INSERT INTO customer(id, name) VALUES (:id, :name)")
                        .bindBean(c)
                        .execute();
            }
        });
    }

    private void createTablePets(Jdbi jdbi) {
        List<Pet> pets = Arrays.asList(
                new Pet(1, "Yorkshire", 10.0),
                new Pet(2, "Bulldog", 20.0),
                new Pet(3, "Cat", 30.)
        );

        jdbi.useHandle(handle -> {
            try {
                handle.execute("DROP TABLE pet");
            } catch (Exception e) {

            }
            handle.execute("CREATE TABLE pet (id INTEGER PRIMARY KEY, name VARCHAR, price DECIMAL)");

            for(Pet p : pets) {
                handle.createUpdate("INSERT INTO pet(id, name, price) VALUES (:id, :name, :price)")
                        .bindBean(p)
                        .execute();
            }
        });
    }
}
