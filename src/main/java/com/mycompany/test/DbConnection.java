
package com.mycompany.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sanjeev@moco.com.np
 */
public class DbConnection {

    public static Connection getConnection() {
        String dbURL = Config.getProp("db_URL");
        String username = Config.getProp("db_username");
        String password = Config.getProp("db_password");
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbURL, username, password);
            if (con != null) {
                System.out.println("connected");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(" not connected");
        }
        return con;
    }
}
