/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mypc
 */
public class JDBCUtils {

    private static Connection connection;
    private static String SERVERNAME = "";
    private static String DATABASENAME = "";
    private static String PORTNUMBER = "";
    private static String INSTANCE = "";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private static String USERNAME = "";
    private static String PASSWORD = "";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                loadENV();
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment";
                String url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + "\\" + INSTANCE + ";databaseName=" + DATABASENAME;
                if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
                    url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + ";databaseName=" + DATABASENAME;
                }
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                return DriverManager.getConnection(url, userID, password);
                return DriverManager.getConnection(url, USERNAME, PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    public static void loadENV() {
        Dotenv dotenv = Dotenv.configure().load();
//         for (DotenvEntry e : dotenv.entries()) {
//            System.out.println(e);
//        }
        SERVERNAME = dotenv.get("SERVERNAME");
        DATABASENAME = dotenv.get("DATABASENAME");
        PORTNUMBER = dotenv.get("PORTNUMBER");
        INSTANCE = dotenv.get("INSTANCE");
        USERNAME = dotenv.get("USER");
        PASSWORD = dotenv.get("PASSWORD");

    }
}
