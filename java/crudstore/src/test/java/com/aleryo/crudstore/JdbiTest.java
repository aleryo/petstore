package com.aleryo.crudstore;

import com.aleryo.crudstore.model.Customer;
import org.jdbi.v3.core.Jdbi;
import org.junit.Test;

import java.util.List;

public class JdbiTest {

    @Test
    public void firstJdbi() {
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost/crudstore", "postgres", "mysecretpassword");


        List<Customer> users = jdbi.withHandle(handle -> {
            handle.execute("DROP TABLE customer");
            handle.execute("CREATE TABLE customer (id INTEGER PRIMARY KEY, name VARCHAR)");

            // Inline positional parameters
            handle.execute("INSERT INTO customer(id, name) VALUES (?, ?)", 0, "Alice");

            // Positional parameters
            handle.createUpdate("INSERT INTO customer(id, name) VALUES (?, ?)")
                    .bind(0, 1) // 0-based parameter indexes
                    .bind(1, "Bob")
                    .execute();

            // Named parameters
            handle.createUpdate("INSERT INTO customer(id, name) VALUES (:id, :name)")
                    .bind("id", 2)
                    .bind("name", "Clarice")
                    .execute();

            // Named parameters from bean properties
            handle.createUpdate("INSERT INTO customer(id, name) VALUES (:id, :name)")
                    .bindBean(new Customer(3, "David"))
                    .execute();

            // Easy mapping to any type
            return handle.createQuery("SELECT * FROM customer ORDER BY name")
                    .mapToBean(Customer.class)
                    .list();
        });
    }
}
