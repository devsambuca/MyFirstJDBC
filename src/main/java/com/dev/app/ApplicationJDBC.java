package com.dev.app;

import com.dev.app.dao.DAODeveloper;
import com.dev.app.model.Developer;
import java.sql.*;

public class ApplicationJDBC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DAODeveloper daoDeveloper = new DAODeveloper();
//        daoDeveloper.create(new Developer(13, "Uljya Vorobjeva", 60000));
//        daoDeveloper.delete(9);
        daoDeveloper.read(5);
//        daoDeveloper.update(new Developer(1, "Kolya Verevkin", 20000));

        for (int i = 0; i < daoDeveloper.getAll().size(); i++)
            System.out.println(i);


    }


    /**
     * JDBC Driver and database url
     * User and Password
     */

    public static Connection getConnection() {
        try {

            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DATABASE_URL = "jdbc:mysql://localhost/mytestdb";
            final String USER = "root";
            final String PASSWORD = "root";
            Class.forName(JDBC_DRIVER);

         System.out.println("Checking connection to MySQL");

            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            if (null != connection) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection not established");
            }
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}