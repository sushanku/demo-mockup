package com.mycompany.test;

import static spark.Spark.get;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import spark.Request;
import spark.Response;

/**
 *
 * @author Sanjeev Panta <sanjeev@moco.com.np>
 * @Creation Date Jan 23, 2020 12:32:28 PM
 */
public class Main {

    public static void main(String[] args) {
        DbConnection.getConnection();
        Gson gson = new Gson();
        get("/role/:name", "application/json", (req, res)
                -> greet(req, res), gson::toJson);
    }

    private static String greet(Request req, Response res) {
        String name = req.params(":name");
        try (Connection conn = DbConnection.getConnection()) {
            boolean numeric = true;
            numeric = name.matches("-?\\d+(\\.\\d+)?");
            if (numeric) {
                System.out.println(name + " is a number");
            } else {
                System.out.println(name + " is not a number");
                String sql = "INSERT INTO information (name) VALUES(?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.executeUpdate();
                res.type("text/plain");
                return "hello" + " " + req.params(":name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        res.type("text/plain");
        return "It is a number";
        
    }
}
