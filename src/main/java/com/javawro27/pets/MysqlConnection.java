package com.javawro27.pets;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {

    private MysqlConnectionParameters mysqlConnectionParameters;
    private MysqlDataSource mysqlDataSource;

    public MysqlConnection() {
        mysqlConnectionParameters = new MysqlConnectionParameters();
        initialize();
    }


    private void initialize() {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser(mysqlConnectionParameters.getUsername());
        mysqlDataSource.setPassword(mysqlConnectionParameters.getPassword());
        mysqlDataSource.setDatabaseName(mysqlConnectionParameters.getDatabaseName());
        mysqlDataSource.setServerName(mysqlConnectionParameters.getDatabaseHost());
        mysqlDataSource.setPort(Integer.parseInt(mysqlConnectionParameters.getDatabasePort()));


        try {
            mysqlDataSource.setServerTimezone("Europe/Warsaw"); // strefa czasowa
            mysqlDataSource.setUseSSL(false); // czy szyfrowanie : nie
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return mysqlDataSource.getConnection();
    }
}
