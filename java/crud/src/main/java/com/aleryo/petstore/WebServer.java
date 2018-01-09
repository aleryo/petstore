package com.aleryo.petstore;

import static spark.Spark.get;

public class WebServer {

      public static void main(String[] args) {
          get("/first", (req, res) -> "First Spark application");
      }
}
