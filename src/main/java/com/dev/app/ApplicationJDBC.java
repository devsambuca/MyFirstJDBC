package com.dev.app;

import com.dev.app.dao.DAODeveloper;
import com.dev.app.model.Developer;

import java.sql.*;
import java.util.logging.Logger;

public class ApplicationJDBC {
    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/mytestdb";
    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {




        System.out.println("------- Проверка подключения к MySQL -------");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            DAODeveloper daoDeveloper = new DAODeveloper();
            daoDeveloper.create(new Developer(10, "Iljya", 30000));

        } catch (SQLException ex) {

        }

        if (null != connection) {
            System.out.println("------- Подключение установлено -------");
        } else {
            System.out.println("------- Подключение НЕ установлено -------");
        }

    }
}