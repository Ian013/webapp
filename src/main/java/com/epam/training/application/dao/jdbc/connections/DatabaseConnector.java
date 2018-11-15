package com.epam.training.application.dao.jdbc.connections;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final Logger log = Logger.getLogger(DatabaseConnector.class);
    public static Connection getConnection(){

        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/faculty?serverTimezone=UTC&useSSL=false",
                    "admin", "001201313");
            System.out.println("Got our connection");
        } catch (SQLException e) {
            log.error(e.getMessage()+" connection failed");
            e.printStackTrace();
        }
        return con;
    }
}
