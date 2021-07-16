/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mypc
 */
public class JDBCUtils {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment";
                return DriverManager.getConnection(url, "sa", "123");
            } catch (Exception e) {
                return null;
            }
        }
        return connection;
    }
}
