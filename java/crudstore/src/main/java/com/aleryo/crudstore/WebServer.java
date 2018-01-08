package com.aleryo.crudstore;

import static spark.Spark.get;

public class WebServer {

      public static void main(String[] args) {
          get("/first", (req, res) cd-> "First Spark application");
      }
}
