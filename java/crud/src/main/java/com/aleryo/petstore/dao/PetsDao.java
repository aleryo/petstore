package com.aleryo.petstore.dao;

import com.aleryo.petstore.model.Pet;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class PetsDao {
    public List<Pet> getPets() {
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost/crudstore", "postgres", "mysecretpassword");

        return jdbi.withHandle(handle ->
                handle.createQuery("select * from pet")
                        .mapToBean(Pet.class)
                        .list());
    }
}
