package com.aleryo.petstore;

import com.aleryo.petstore.dao.PetsDao;
import com.aleryo.petstore.model.Pet;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.List;

import static spark.Spark.get;

public class WebServer {

    public static void main(String[] args) {
        get("/pets", (req, res) -> {
            PetsDao petsDao = new PetsDao();
            List<Pet> pets = petsDao.getPets();
            return new HandlebarsTemplateEngine().render(new ModelAndView(pets, "pets.hbs"));
        });
    }
}
