package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private final static String DB_URL;
    private final static String DB_USER;
    private final static String DB_PASS;

    static {
        try {
            Properties properties = new Properties();
            properties.load(H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties"));
            Class.forName(properties.getProperty("jdbc_driver"));
            DB_URL = properties.getProperty("db_url");
            DB_USER = properties.getProperty("user");
            DB_PASS = properties.getProperty("password");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

