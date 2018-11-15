package com.epam.training.application.dao.jdbc.connections;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private final static Logger log = Logger.getLogger(ConnectionPool.class);
    private ConnectionPool(){
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }
    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydatabase");
            c = ds.getConnection();
        } catch (NamingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return c;
    }
}
