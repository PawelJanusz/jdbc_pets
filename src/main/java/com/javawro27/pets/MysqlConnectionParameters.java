package com.javawro27.pets;


import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Setter
public class MysqlConnectionParameters {

    private final static String MYSQL_PROPERTIES_FILENAME = "/jdbc.properties";

    private String username;
    private String password;
    private String databaseName;
    private String databasePort;
    private String databaseHost;

    public MysqlConnectionParameters(){
        init();
    }

    private void init() {
        Properties propertiesHolder = loadProperties();
        username = propertiesHolder.getProperty("database.jdbc.username");
        password = propertiesHolder.getProperty("database.jdbc.password");
        databaseName = propertiesHolder.getProperty("database.jdbc.name");
        databasePort = propertiesHolder.getProperty("database.jdbc.port");
        databaseHost = propertiesHolder.getProperty("database.jdbc.host");
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        InputStream jdbcPropertiesStream = this.getClass().getResourceAsStream(MYSQL_PROPERTIES_FILENAME);
        if (jdbcPropertiesStream == null) {
            System.err.println("Brak pliku konfiguracyjnego w recources. Nazwa brakującego pliku: jdbc.recources");
            System.exit(99); // wyświetla kod błędu programu : 99
        }
        try {
            properties.load(jdbcPropertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
