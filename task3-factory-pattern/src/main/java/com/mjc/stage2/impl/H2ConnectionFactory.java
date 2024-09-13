package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Properties prop = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("h2database.properties")){

            prop.load(input);
            String url = prop.getProperty("db_url");
            String name = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(prop.getProperty("jdbc_driver"));

            return DriverManager.getConnection(url, name, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

